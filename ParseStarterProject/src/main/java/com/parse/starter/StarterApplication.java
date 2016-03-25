package com.parse.starter;

import android.app.Application;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import com.parse.ParsePush;
import com.parse.PushService;
import com.parse.ParseObject;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseInstallation;


import android.widget.Toast;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    // Parse.enableLocalDatastore(this);
//    Parse.initialize(this, "fooApp", "gggclientKey");
//    Parse.serverURL = 'http://irelia.in:1337/1/';
    // Add your initialization code here

      Parse.initialize(this);
//    Parse.initialize(new Parse.Configuration.Builder(this)
//                    .applicationId("zAQaHNRFUYs2K3rSKzHX0UYBQ0hnLRwaNi4wSEFJ")
//                    .clientKey("m9hdVWPEMFihR73XnId3J1tqBGOi1aLnOC7Vgl1M")
////                    .server("http://YOUR_PARSE_SERVER:1337/parse")
//                    .build()
//    );


      ParseUser.logInInBackground("karamfil", "karamfil", new LogInCallback()
      {
          public void done(ParseUser user, ParseException e) {
              if (user != null)
              {
                    ParsePush.subscribeInBackground("Giants");

                  new android.os.Handler().postDelayed(
                    new Runnable() {
                      public void run() {
                          ParseInstallation installation = ParseInstallation.getCurrentInstallation();
                          installation.put("user",ParseUser.getCurrentUser());
                          installation.saveInBackground();
                      }
                  }, 1000);

//                  ParseObject testObject = new ParseObject("TestObject");
//                  testObject.put("logged", "karamfil");
//                  testObject.saveInBackground();

                  Toast toast = Toast.makeText(getApplicationContext(), "LOGGED IN", Toast.LENGTH_LONG);
                  toast.show();
              } else {
                  // Signup failed. Look at the ParseException to see what happened.
//                  Log.w("PARSE LOGIN", "ERROR LOGGING IN");
              }

          }
      });


      ParseUser.enableAutomaticUser();
      ParseACL defaultACL = new ParseACL();
    // Optionally enable public read access.
    // defaultACL.setPublicReadAccess(true);
      ParseACL.setDefaultACL(defaultACL, true);

//      PushService.setDefaultPushCallback(this, MainActivity.class);
  }
}
