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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText_email,editText_password;
    TextView textView_forgetPass,textView_register;
    Button button_login;

    IRetrofit iRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

    }

    void init()
    {
        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL + Config.LOGIN_URL).create(IRetrofit.class);

        editText_email=findViewById(R.id.editText_email);
        editText_password=findViewById(R.id.editText_password);
        textView_forgetPass=findViewById(R.id.textView_forgetPass);
        textView_register=findViewById(R.id.textView_register);
        button_login=findViewById(R.id.button_login);

        click();

    }

    private void click() {
        button_login.setOnClickListener(this);
        textView_forgetPass.setOnClickListener(this);
        textView_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==textView_register)
        {
            startActivity(new Intent(Application.getContext(),RegisterActivity.class));
        }
        else if(view==textView_forgetPass)
        {
            startActivity(new Intent(Application.getContext(),ForgetPasswordActivity.class));
        }
        else if (view==button_login)
        {
            String email=editText_email.getText().toString().trim();
            String pass=editText_password.getText().toString().trim();
            login(email,pass);
        }
    }

    private void login(String email, String pass) {
        iRetrofit.Login(email,pass).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String message=jsonObject(response.body().string());
                    if (message.equals("Exsists"))
                    {
                        SharedPreferencesManager.getSharedPreferences().edit().putString(SharedPreferencesManager.EMAIL,email).apply();
                        startActivity(new Intent(Application.getContext(),MainActivity.class));
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