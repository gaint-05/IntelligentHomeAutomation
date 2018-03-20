package com.rajhack4.homeautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignedInActivity extends AppCompatActivity {
    private Button mSignOut;
    private Button mDeleteAccount;
    private Button mHome;
    private TextView mUserName;
    private TextView mEmail;
    private ImageView mUserProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
        mSignOut = (Button) findViewById(R.id.sign_out);
        mDeleteAccount = (Button)findViewById(R.id.delete_account);
        mHome = (Button)findViewById(R.id.home);
        mUserName = (TextView)findViewById(R.id.username);
        mEmail = (TextView)findViewById(R.id.email);
        mUserProfileImage = (ImageView)findViewById(R.id.userimage);
        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut(v);
            }
        });
        mDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAccount(v);
            }
        });
        mHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (SignedInActivity.this,HomeAutomationLauncherActivity.class);
                startActivity(i);
            }
        });
        FirebaseUser currentUser =
                FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser == null) {
            startActivity(new Intent(this, FirebaseAuthActivity.class));
            finish();
            return;
        } TextView mEmail = (TextView) findViewById(R.id.email);
        TextView mUserName = (TextView) findViewById(R.id.username);
        mEmail.setText(currentUser.getEmail());
        mUserName.setText(currentUser.getDisplayName());
    }

    public void signOut(View view) {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(
                                    SignedInActivity.this,
                                    FirebaseAuthActivity.class));
                            finish();
                        } else {
// Report error to user
                        }
                    }
                });
    }
    public void deleteAccount(View view) {
        AuthUI.getInstance()
                .delete(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(SignedInActivity.this,
                                    FirebaseAuthActivity.class));
                            finish();
                        } else {
// Notify user of error
                        }
                    }
                });
    }
}