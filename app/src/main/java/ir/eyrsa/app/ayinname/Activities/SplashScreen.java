package ir.eyrsa.app.ayinname.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import ir.eyrsa.app.ayinname.Config.Application;
import ir.eyrsa.app.ayinname.Config.SharedPreferencesManager;
import ir.eyrsa.app.ayinname.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String email = SharedPreferencesManager.getSharedPreferences().getString(SharedPreferencesManager.EMAIL,"");
                //This method will be executed once the timer is over
                if (!email.equals(""))
                startActivity(new Intent(Application.getContext(), MainActivity.class));
                else
                    startActivity(new Intent(Application.getContext(), LoginActivity.class));
                finish();
            }
        }, 3000);

    }
}