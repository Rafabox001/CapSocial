package com.example.rafaeldomingo.testapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rafaeldomingo.testapp.net.ApiManager;
import com.example.rafaeldomingo.testapp.net.HomeActivity;
import com.example.rafaeldomingo.testapp.net.request.LoginRequest;
import com.example.rafaeldomingo.testapp.net.response.LoginResponse;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {

    private static final String TAG = "LoginActivity";

    /**
     * Facebook login callback manager
     */
    private CallbackManager mCallbackManager;


    // UI references.
    @BindView(R.id.input_layout_user)TextInputLayout layoutUser;
    @BindView(R.id.input_layout_password)TextInputLayout layoutPass;
    @BindView(R.id.user)EditText user;
    @BindView(R.id.pass)EditText pass;
    @BindView(R.id.forgot_pass)TextView recovery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        //set up facebook login
        setUpFacebookLogin();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }


    /**
     * Method that sets up facebook login sdk methods
     */
    private void setUpFacebookLogin() {
        mCallbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().logOut();

        LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                goToMain();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "Login Exception ", error);
            }
        });
    }

    /**
     * Go to the main activity
     */
    private void goToMain() {
        Log.d(TAG, "goToMain: success");
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


    ///////////////////////////////////////////////////////////////////////////
    // Ui Callbacks
    ///////////////////////////////////////////////////////////////////////////

    @OnClick(R.id.btn_login_fb)
    protected void onFbLoginClick(){
        String[] fbPermissions = new String[]{"email", "public_profile", "user_birthday"};
        LoginManager.getInstance().logInWithReadPermissions(this,Arrays.asList(fbPermissions));
    }


    @OnClick(R.id.btn_sigin)
    protected void executeLoginCall() {

        LoginRequest loginRequest = new LoginRequest(user.getText().toString(),pass.getText().toString());
        Call<LoginResponse> responseCall = ApiManager.getApiInstance().login(loginRequest);

        responseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Log.d(TAG, "onResponse: Successfull");
                    Log.d(TAG, "onResponse: Response body "+response.body().toString());
                    goToMain();
                }else{
                    Log.e(TAG, "onResponse: Error on response = "+response.message() );
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: error connecting to service", t);
                Toast.makeText(LoginActivity.this, "Login data not valid", Toast.LENGTH_SHORT).show();
            }
        });
    }


}

