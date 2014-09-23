package com.example.admin.webviewcomplete;

/**
 * Created by admin on 8/16/2014.
 */
        import android.app.Activity;
        import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;
        import android.content.IntentFilter;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.EditText;

        import android.widget.Toast;

        import android.widget.Button;


public class LoginActivity extends Activity {

    // Email, password edittext
    EditText txtUsername, txtPassword;

    // login button
    Button btnLogin;

    // Session Manager Class
    SessionManager session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.package.ACTION_LOGOUT");
        registerReceiver(new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("onReceive", "Logout in progress");
                //At this point you should start the login activity and finish this one
                finish();
            }
        }, intentFilter);


        // Session Manager
        session = new SessionManager(getApplicationContext());

        // Email, Password input text
        txtUsername = (EditText) findViewById(R.id.user);
        txtPassword = (EditText) findViewById(R.id.pass);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        // Login button
        btnLogin = (Button) findViewById(R.id.submit);


        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                // Check if username, password is filled
                if(username.trim().length() > 0 && password.trim().length() > 0){
                    // For testing puspose username, password is checked with sample data
                    // username = test
                    // password = test
                    if(username.equals("demouser1") && password.equals("demopass1")){

                        // Creating user login session
                        // For testing i am stroing name, email as follow
                        // Use user real data
                        session.createLoginSession(username, password);

                        // Staring MainActivity
                        //Intent i = new Intent(getApplicationContext(), MyActivity.class);
                        //startActivity(i);
                       // finish();

                        Intent i = new Intent(LoginActivity.this, MyActivity.class);
                        // set the new task and clear flags
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(i);
                        finish();

                    }else{
                        // username / password doesn't match
                        Toast.makeText(getBaseContext(),"Username/Password incorrect",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    // user didn't entered username or password
                    // Show alert asking him to enter the details
                    Toast.makeText(getBaseContext(),"Please enter Username/Password",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}
