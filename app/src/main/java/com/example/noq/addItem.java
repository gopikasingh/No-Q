package com.example.noq;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import id.zelory.compressor.Compressor;

public class addItem extends AppCompatActivity {

    private Toolbar newPostToolbar;

    public ImageView newPostImage;
    public EditText newitemname;
    public EditText newitemprice;
    public EditText newPostDesc;
    public EditText newitemquanity;
    private Button newPostBtn;
    private TextView newbarcodeBtn;
    public String jugaduri;

    public Uri postImageUri = null;

    private ProgressBar newPostProgress;

    private StorageReference storageReference;
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;

    public String current_user_id;

    public Bitmap compressedImageFile;
    String push_id;
    String user_id;
    String barcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        //current_user_id = firebaseAuth.getCurrentUser().getUid();

       /* newPostToolbar = findViewById(R.id.new_post_toolbar);
        setSupportActionBar(newPostToolbar);
        getSupportActionBar().setTitle("Add New Post");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        newPostImage = findViewById(R.id.new_post_image);
        newPostDesc = findViewById(R.id.new_post_desc);
        newitemname = findViewById(R.id.new_item_name);
        newitemprice = findViewById(R.id.new_item_price);
        newitemquanity = findViewById(R.id.new_item_quantity);
        newbarcodeBtn=findViewById(R.id.barcode_btn);

        newPostBtn = findViewById(R.id.post_btn);
        newPostProgress = findViewById(R.id.new_post_progress);
        push_id = mDatabase.child("users").push().getKey();
        newPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .setMinCropResultSize(512, 512)
                        .setAspectRatio(1, 1)
                        .start(addItem.this);

            }
        });

        newPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name = newitemname.getText().toString();
                final float price = Float.valueOf(newitemprice.getText().toString());
                //final String price = newitemprice.getText().toString();
                final String desc = newPostDesc.getText().toString();
                final int quantity = Integer.parseInt(newitemquanity.getText().toString());

                if(!TextUtils.isEmpty(desc) && !TextUtils.isEmpty(name) && price!=0 && postImageUri != null){

                    newPostProgress.setVisibility(View.VISIBLE);

                    final String randomName = UUID.randomUUID().toString();

                    // PHOTO UPLOAD
                    File newImageFile = new File(postImageUri.getPath());
                    try {

                        compressedImageFile = new Compressor(addItem.this)
                                .setMaxHeight(130)
                                .setMaxWidth(130)
                                .setQuality(50)
                                .compressToBitmap(newImageFile);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte[] imageData = baos.toByteArray();

                    // PHOTO UPLOAD

                    UploadTask filePath = storageReference.child(randomName + ".jpg").putBytes(imageData);
                    filePath.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onComplete(@NonNull final Task<UploadTask.TaskSnapshot> task) {

                            final String downloadUri = task.getResult().getUploadSessionUri().toString();
                            //final String downloadUri = task.getMetadata().getReference().getDownloadUrl().toString();
                            //final String downloadUri;
                            //public void ajeeb();

                            task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    if (taskSnapshot.getMetadata() != null) {
                                        if (taskSnapshot.getMetadata().getReference() != null) {
                                            Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
                                            result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String imageUrl = uri.toString();
                                                    createNewPost(imageUrl);
                                                    //this.downloaduri=
                                                }
                                            });
                                        }
                                    }
                                }});

                            if(task.isSuccessful()){

                                File newThumbFile = new File(postImageUri.getPath());
                                try {

                                    compressedImageFile = new Compressor(addItem.this)
                                            .setMaxHeight(100)
                                            .setMaxWidth(100)
                                            .setQuality(1)
                                            .compressToBitmap(newThumbFile);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                                byte[] thumbData = baos.toByteArray();
                                //user_id = firebaseAuth.getCurrentUser().getUid();



                                UploadTask uploadTask = storageReference.child("post_images/thumbs")
                                        .child(randomName + ".jpg").putBytes(thumbData);

                                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                        String downloadthumbUri = taskSnapshot.getUploadSessionUri().toString();

                                        Map<String, Object> postMap = new HashMap<>();
                                        //postMap.put("itemImage",downloadUri );
                                        postMap.put("itemImage",jugaduri );
                                        postMap.put("itemImagethumb", downloadthumbUri);
                                        postMap.put("desc", desc);
                                        postMap.put("name", name);
                                        postMap.put("price", price);
                                        postMap.put("user_id", current_user_id);
                                        postMap.put("quantity", quantity);
                                        postMap.put("barcode", barcode);
                                        //postMap.put("timestamp", FieldValue.serverTimestamp());

                                        mDatabase.child("shops").child(firebaseAuth.getCurrentUser().getUid()).child("items").child(barcode).setValue(postMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Toast.makeText(addItem.this, "Uploading to the database is done", Toast.LENGTH_SHORT).show();
                                                Intent mainIntent = new Intent(addItem.this, itemlist.class);
                                                startActivity(mainIntent);
                                                finish();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Toast.makeText(addItem.this, "Problem in registuring the information", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        /*firebaseFirestore.collection("Posts").add(postMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentReference> task) {

                                                if(task.isSuccessful()){

                                                    Toast.makeText(addItem.this, "Post was added", Toast.LENGTH_LONG).show();
                                                    Intent mainIntent = new Intent(addItem.this, MainActivity.class);
                                                    startActivity(mainIntent);
                                                    finish();

                                                } else {


                                                }

                                                newPostProgress.setVisibility(View.INVISIBLE);

                                            }
                                        });*/

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                        //Error handling

                                        Toast.makeText(addItem.this, "Problem in Uploading  Photos.", Toast.LENGTH_SHORT).show();

                                    }
                                });


                            } else {

                                newPostProgress.setVisibility(View.INVISIBLE);

                            }

                        }
                    });


                }

            }
        });


    }

    public void ScanButton1(View view){
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                postImageUri = result.getUri();
                newPostImage.setImageURI(postImageUri);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            if (intentResult.getContents() == null){
                //textView.setText("Cancelled");
            }else {
                //textView.setText(intentResult.getContents());
                //Scannerresult exampleDialog = new Scannerresult();
                //exampleDialog.show(getSupportFragmentManager(), "example dialog");
                //showDialog(intentResult.getContents());
                barcode=intentResult.getContents();
                newbarcodeBtn.setText(barcode);
            }
        }

    }
    public void createNewPost(String uri)
    {
        this.jugaduri=uri;

    }

    /*@Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            // do your stuff
        } else {
            signInAnonymously();
        }
    }
    private void signInAnonymously() {
        firebaseAuth.signInAnonymously().addOnSuccessListener(this, new  OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                // do your stuff
            }
        })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Log.e(TAG, "signInAnonymously:FAILURE", exception);
                    }
                });
    }*/
}