package ir.eyrsa.app.ayinname.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ir.eyrsa.app.ayinname.Config.Application;
import ir.eyrsa.app.ayinname.Config.Config;
import ir.eyrsa.app.ayinname.Config.SharedPreferencesManager;
import ir.eyrsa.app.ayinname.R;
import ir.eyrsa.app.ayinname.Retrofit.IRetrofit;
import ir.eyrsa.app.ayinname.Retrofit.RetrofitClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText_email, editText_password, editText_repassword;
    Button button_save;
    TextView textView_login;

    IRetrofit iRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();

    }

    void init() {

        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL + Config.LOGIN_URL).create(IRetrofit.class);

        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        editText_repassword = findViewById(R.id.editText_repassword);
        button_save = findViewById(R.id.button_save);
        textView_login = findViewById(R.id.textView_login);

        click();

    }

    private void click() {
        button_save.setOnClickListener(this);
        textView_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == button_save) {
            String email = editText_email.getText().toString().trim();
            String password = editText_password.getText().toString().trim();
            String repassword = editText_repassword.getText().toString().trim();
            if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                Config.failedToast(getString(R.string.toast_errorEmail));
            }
            else if (password.length()<8)
            {
                Config.failedToast(getString(R.string.toast_errorLengthPass));
            }
            else if (!password.equals(repassword))
            {
                Config.failedToast(getString(R.string.toast_errorRePass));
            }
            else
            {
                checkUserExists(email,password);
            }
        } else if (view == textView_login) {
            startActivity(new Intent(Application.getContext(),LoginActivity.class));
            finish();
        }
    }

    private void checkUserExists(String email,String password) {
        iRetrofit.CheckUserExists(email).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String message = jsonObject(response.body().string());
                    if (message.equals("Exsists"))
                    {
                        Config.failedToast(getString(R.string.toast_errorEmailExists));
                    }
                    else if (message.equals("user_not_exists"))
                    {
                        insert(email,password);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Config.failedToast(getString(R.string.toast_errorGetData));
            }
        });
    }

    private void insert(String email, String password) {

        iRetrofit.RegisterUser(email,password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String message=jsonObject(response.body().string());
                    if (message.equals("OK"))
                    {
                        SharedPreferencesManager.getSharedPreferences().edit().putString(SharedPreferencesManager.EMAIL,email).apply();
                        startActivity(new Intent(Application.getContext(),MainActivity.class));
                        finish();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Config.failedToast(getString(R.string.toast_errorGetData));
            }
        });
        
    }

    private String jsonObject(String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            return jsonObject.getString("message");
        } catch (JSONException e) {
            e.printStackTrace();
            return "false";
        }
    }

}