package com.example.covid;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button mSignOutButton;
    private Button mCoronaBtn;
    private SignInButton mSignInButton;

    private String[] listOfAdmins = {"Josh Bone"}; // i know this isn't scalable but it's a temporary fix. And assuming there isn't too many admins this is fast

    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth fAuth;
    private int SIGN_IN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();
        mSignInButton = findViewById(R.id.SignInBtn);
        mSignOutButton = findViewById(R.id.SignOutBtn);
        mCoronaBtn = findViewById(R.id.coronaBtn);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        mSignOutButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                fAuth.signOut();
            }
        });
        mCoronaBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                toInfoDash();
            }
        });
        mSignInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                signIn();
            }
        });
    }

    //this method gets called when the user clicks "COVID-19 info..." button
    private void toInfoDash(){
        Intent intent = new Intent(this, InfoDashActivity.class);
        startActivity(intent);
    }

    //this method gets called when the google sign-in button is clicked
    private void signIn(){
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN){
            //"the task from this function call is always completed" - https://developers.google.com/identity/sign-in/android
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            //separate method will handle the result
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try{
            GoogleSignInAccount acc = completedTask.getResult(ApiException.class);
            Authenticate(acc);
        }
        catch(ApiException ex){
            Log.w(null, "signInResult:failed code=" + ex.getStatusCode());
            Toast.makeText(MainActivity.this, "Sign-in Failed. code=" + ex.getStatusCode(), Toast.LENGTH_LONG).show();
            //updateUI(null, null);
        }
    }

    private void Authenticate(final GoogleSignInAccount acc){
        assert acc != null;

        AuthCredential authCredential = GoogleAuthProvider.getCredential(acc.getIdToken(), null);
        fAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            //listens for task completion (whether successful or not, should complete)
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    FirebaseUser user = fAuth.getCurrentUser();
//                    if(user == null){
//                        Toast.makeText(MainActivity.this, "Account is null", Toast.LENGTH_SHORT).show();
//                    }
                    updateUI(user, acc);
                } else {
                    //user is null on startup
                    updateUI(null, acc);
                }
            }
        });
    }

    private void updateUI(FirebaseUser fUser, GoogleSignInAccount account){
        //note: still need to add logic that checks user's status
        //and sends them to admin activity if they are admin
        String username = account.getDisplayName();

        //check if user is admin
        boolean isAdmin = false;
        for(String element : listOfAdmins){
            if( element.equals(username) ){
                isAdmin = true;
                break;
            }
        }

        if(isAdmin){
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
        }
        else {
            //user is student, send them to fill out the survey
            Intent intent = new Intent(this, SymptomSurveyActivity.class);
            GoogleSignInAccount acc = GoogleSignIn.getLastSignedInAccount(getApplicationContext()); //probably can delete this line...
            intent.putExtra("DISPLAY_NAME", username);
            startActivity(intent);
        }
    }

}