package com.AbhishekKangeProductions.mhtcetclassroom;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class registerActivity extends AppCompatActivity {



    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;
    FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(registerActivity.this,MainActivity.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signInButton = findViewById(R.id.signInBtn);
        mAuth = FirebaseAuth.getInstance();

        processRequest();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                processLogin();

            }
        });



    }

    private void processLogin(){

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,101);


    }

    private void processRequest(){

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==101){

            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = (GoogleSignInAccount) ((Task) task).getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, "Error Logging...", Toast.LENGTH_SHORT).show();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }




        }



    }
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            FirebaseUser user = mAuth.getCurrentUser();
                            String UserName =  mAuth.getCurrentUser().getDisplayName();


                            //Adding User Data to Backend
                            Intent intent = new Intent(registerActivity.this,studentDetailsActivity.class);
                            startActivity(intent);


                        } else {
                            Toast.makeText(registerActivity.this, "Error Logging 2...", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}