package com.example.noq;

import android.app.Application;
import com.parse.Parse;
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        /*Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("FVYc5eyCRHWl8IuLoy7nnvSjUhASv9K5It3p5udP")
                // if defined
                .clientKey("QBpUgMgfeOlAFXNv6Vk0jQTN6YT1o9GoQLK9ci9q")
                .server("https://parseapi.back4app.com/")
                .build()
        );*/

    }
}
