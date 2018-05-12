package com.forkbuddy.forkbuddy;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Lobby extends AppCompatActivity {

    RelativeLayout relativeLayout;
    AnimationDrawable animationDrawable ;
    Dialog loginDialog, signUpDialog;

    EditText emailLogin, passwordLogin, emailSignup, passwordSignup, repeatPasswordSignup, usernameSignup;
    Button btnLogin, btnForgotPw, btnSignUp;

    private FirebaseAuth mAuth;

    public static final String PREFS_NAME = "MyPrefsFile";

    public void signUp(){
        final String username = usernameSignup.getText().toString();
        String email = emailSignup.getText().toString();
        String password = passwordSignup.getText().toString();
        String passwordRepeat = repeatPasswordSignup.getText().toString();

        if(!password.equals(passwordRepeat)){
            Toast.makeText(this, "Passwords are not same!", Toast.LENGTH_SHORT).show();
        }else{
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(getApplicationContext(), "Account created.", Toast.LENGTH_SHORT).show();

                        FirebaseUser user = mAuth.getCurrentUser();
                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(username).build();
                        user.updateProfile(userProfileChangeRequest);

                        signUpDialog.dismiss();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    public void logIn(){
        String email = emailLogin.getText().toString();
        String password = passwordLogin.getText().toString();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    SharedPreferences settings = getSharedPreferences(Lobby.PREFS_NAME, 0); // 0 - for private mode
                    SharedPreferences.Editor editor = settings.edit();

                    editor.putBoolean("hasLoggedIn", true);

                    // Commit the edits!
                    editor.commit();


                    Intent mainActivityIntent = new Intent(getApplicationContext(), Main.class);
                    startActivity(mainActivityIntent);

                    finish();
                }
                else{
                    Toast.makeText(Lobby.this, "Email & Password combination is not correct!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        ///// START OF BACKGROUND ANIMATION /////
        relativeLayout = findViewById(R.id.LobbyLayout);
        animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(0);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();
        ///// END OF BACKGROUND ANIMATION /////

        /// INITIALIZATION OF LOGIN DIALOG////

        loginDialog = new Dialog(Lobby.this);
        loginDialog.setContentView(R.layout.dialog_sign_in);
        loginDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // DO NOT TOUCH, DESIGN ISSUES
        btnLogin = loginDialog.findViewById(R.id.btnSignIn);
        btnForgotPw = loginDialog.findViewById(R.id.btnForgotPw);
        emailLogin = loginDialog.findViewById(R.id.emailSignIn);
        passwordLogin = loginDialog.findViewById(R.id.passwordSignIn);
        /// INITIALIZATION OF LOGIN DIALOG ENDS HERE////

        final Button openLogin = findViewById(R.id.btnLogin); // button that opens the login dialog
        openLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openLogin.setSelected(false);
                openLogin.setPressed(false);
                loginDialog.show(); // Show the dialog

            }
        });

        mAuth = FirebaseAuth.getInstance();

        SharedPreferences settings = getSharedPreferences(Lobby.PREFS_NAME, 0);
        //Get "hasLoggedIn" value. If the value doesn't exist yet false is returned
        boolean hasLoggedIn = settings.getBoolean("hasLoggedIn", false);

        if(hasLoggedIn)
        {
            Intent mainActivityIntent = new Intent(getApplicationContext(), Main.class);
            startActivity(mainActivityIntent);

            finish();
        }


        btnLogin.setOnClickListener(new View.OnClickListener() { //Login to the app
            @Override
            public void onClick(View v) {
                logIn();
            }
        });

        /// INITIALIZATION OF SIGNUP DIALOG////
        signUpDialog = new Dialog(Lobby.this);
        signUpDialog.setContentView(R.layout.dialog_sign_up);
        signUpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT)); // DO NOT TOUCH, DESIGN ISSUES
        usernameSignup = signUpDialog.findViewById(R.id.usernameSignUp);
        emailSignup = signUpDialog.findViewById(R.id.emailSignUp);
        passwordSignup = signUpDialog.findViewById(R.id.passwordSignUp);
        repeatPasswordSignup = signUpDialog.findViewById(R.id.repeatPwSignUp);
        btnSignUp = signUpDialog.findViewById(R.id.btnSignUp);
        /// INITIALIZATION OF SIGNUP DIALOG ENDS HERE////

        final Button openSignUp = findViewById(R.id.btnOpenSignUp);
        openSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignUp.setSelected(false);
                openSignUp.setPressed(false);
                signUpDialog.show(); // Show the dialog

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Important for efficiency
        if (animationDrawable != null && !animationDrawable.isRunning())
            animationDrawable.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        //Important for efficiency
        if (animationDrawable != null && animationDrawable.isRunning())
            animationDrawable.stop();

    }
}
