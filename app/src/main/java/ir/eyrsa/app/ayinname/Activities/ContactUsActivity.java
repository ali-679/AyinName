package ir.eyrsa.app.ayinname.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ir.eyrsa.app.ayinname.R;

public class ContactUsActivity extends AppCompatActivity {

    Button button_telegram,button_gmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);

        button_telegram=findViewById(R.id.button_telegram);
        button_gmail=findViewById(R.id.button_gmail);
        button_telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTelegram=new Intent(Intent.ACTION_VIEW);
                intentTelegram.setData(Uri.parse("https://t.me/ali_679"));
                startActivity(intentTelegram);
            }
        });
        button_gmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentGmail=new Intent(Intent.ACTION_SEND);
                intentGmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"fzali.679@gmail.com"});
                intentGmail.setType("message/rfc822");

                // startActivity with intent with chooser as Email client using createChooser function
                startActivity(Intent.createChooser(intentGmail, "جیمیل را انتخاب کنید"));
            }
        });

    }
}