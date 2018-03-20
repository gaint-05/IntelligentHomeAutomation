package com.rajhack4.homeautomation;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Key;
import java.util.zip.GZIPInputStream;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * Created by dbot_5 on 19-03-2018.
 */
public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private FrameLayout mFrameLayout;
    private FirebaseAuth mAuth;
    public static final String DECRYPT_KEY = "fd7e915003168929c1a9b0ec32a60788";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar)findViewById(R.id.mainToolbar));
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.home_action_bar);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24px);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mFrameLayout=findViewById(R.id.content_frame);

      /*  mAuth = FirebaseAuth.getInstance();
        final FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser == null) {
            startActivity(new Intent(this, SignUpActivity.class));
            finish();
        }else if(!currentUser.isEmailVerified()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Email Not verified")
                    .setMessage("You haven't verified email till now please verify it or send a new link!")
                    .setPositiveButton("send!", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            currentUser.sendEmailVerification();
                        }
                    })
                    .setNegativeButton("verified", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(currentUser.isEmailVerified()){
                                finish();
                            }else {
                                Toast.makeText(MainActivity.this,"Not Verified",Toast.LENGTH_LONG).show();
                            }
                        }
                    }).show();}
*/
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        switch(menuItem.getItemId()){

                            case R.id.nav_home:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment(), "Remote").addToBackStack("home").commit();
                                break;
                            case R.id.nav_remote:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new RemoteFragment(), "Remote").addToBackStack("home").commit();
                                break;
                            case R.id.nav_tv_guide:
                                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new TvGuideFragment(), "tvGuide").addToBackStack("home").commit();
                                break;

                        }

                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        // close drawer when item is tapped
                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });

        navigationView.setCheckedItem(R.id.nav_home);
        getSupportFragmentManager().beginTransaction().add(R.id.content_frame, new HomeFragment(), "HELLO").commit();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static String b(String str, String str2) throws Exception {
        String str3 = null;
        if (!(str == null || str2 == null)) {
            try {
                byte[] bytes = str.getBytes("UTF-8");
                byte[] bytes2 = str2.getBytes("UTF-8");
                if (!(bytes == null || bytes2 == null)) {
                    Key secretKeySpec = new SecretKeySpec(bytes2, "AES");
                    Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
                    instance.init(2, secretKeySpec);
                    str3 = a(instance.doFinal(Base64.decodeBase64(bytes)));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return str3;
    }
    private static String a(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPInputStream gZIPInputStream = new GZIPInputStream(new ByteArrayInputStream(bArr));
        byte[] bArr2 = new byte[256];
        while (true) {
            int read = gZIPInputStream.read(bArr2);
            if (read >= 0) {
                byteArrayOutputStream.write(bArr2, 0, read);
            } else {
                gZIPInputStream.close();
                return new String(byteArrayOutputStream.toByteArray());
            }
        }
    }
}
