package me.xylum.jetfuel.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import me.xylum.jetfuel.R;

public class LoginActivity extends ActionBarActivity {

    public static final String TAG = "LoginActivity";

    private Context mContext = this;
    private LoginButton mLoginButton = null;
    private CallbackManager mCallbackManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Init. Facebook, needed for all Facebook functionality to work.
        FacebookSdk.sdkInitialize(mContext);

        setContentView(R.layout.activity_login);

        // Set up the callback manager, used by facebook upon login.
        mCallbackManager = CallbackManager.Factory.create();

        // Set up any XML elements in the code.
        mLoginButton = (LoginButton) findViewById(R.id.login_facebook_button);

        // Register the Facebook button.
        mLoginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>(){
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Log.i(TAG, "Facebook login success, key: " + loginResult.getAccessToken().toString());
            }

            @Override
            public void onCancel() {
                // App code
                Log.i(TAG, "Facebook login cancelled");
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.e(TAG, "Facebook login error");
                exception.printStackTrace();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
