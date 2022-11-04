package ir.eyrsa.app.ayinname.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.chaos.view.PinView;

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

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText_email,editText_password;
    PinView pinView;
    Button button_continue,button_checkCode,button_goBack;

    LinearLayout layout_email,layout_pinView,layout_password;

    int codeRand;


    IRetrofit iRetrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        init();

    }

    void init()
    {
        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL + Config.LOGIN_URL).create(IRetrofit.class);

        editText_email=findViewById(R.id.editText_email);
        editText_password=findViewById(R.id.editText_password);
        pinView=findViewById(R.id.pinView);
        button_continue=findViewById(R.id.button_continue);
        button_checkCode=findViewById(R.id.button_checkCode);
        button_goBack=findViewById(R.id.button_goBack);
        layout_email=findViewById(R.id.layout_email);
        layout_pinView=findViewById(R.id.layout_pinView);
        layout_password=findViewById(R.id.layout_password);

        click();

    }

    private void click() {
        button_continue.setOnClickListener(this);
        button_checkCode.setOnClickListener(this);
        button_goBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==button_continue)
        {
            String email=editText_email.getText().toString().trim();
            if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                Config.failedToast(getString(R.string.toast_errorEmail));
            }
            else
            {
                checkEmail(email);
            }
        }
        else if (view==button_checkCode)
        {
            String checkCode = pinView.getText().toString().trim();
            if (checkCode.equals(codeRand + "")) {// code ok
                layout_pinView.setVisibility(View.GONE);
                button_checkCode.setVisibility(View.GONE);

                layout_password.setVisibility(View.VISIBLE);
                button_goBack.setVisibility(View.VISIBLE);
            } else {
                pinView.setLineColor(getResources().getColor(R.color.colorRedError));
            }
        }
        else if (view==button_goBack)
        {
            String password = editText_password.getText().toString().trim();
            if (password.length() < 8) {
                Config.failedToast(getString(R.string.toast_errorLengthPass));
            } else {
                changePassword(password);
            }
        }
    }

    private void changePassword(String password) {
        String email =editText_email.getText().toString().trim();
        iRetrofit.ChangePassword(email,password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String message=jsonObject(response.body().string());
                    if (message.equals("OK"))
                    {
                        SharedPreferencesManager.getSharedPreferences().edit().putString(SharedPreferencesManager.EMAIL,email).apply();
                        startActivity(new Intent(Application.getContext(),MainActivity.class));
                    }
                    else
                    {
                        Config.failedToast(getString(R.string.toast_errorEnter));
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

    private void checkEmail(String email) {
        double rand = Math.random();
        codeRand = (int) (rand * 100000);
        iRetrofit.ForgetPassword(email,codeRand+"").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String message=jsonObject(response.body().string());
                    if (message.equals("email_ok"))
                    {
                        layout_email.setVisibility(View.GONE);
                        button_continue.setVisibility(View.GONE);

                        layout_pinView.setVisibility(View.VISIBLE);
                        button_checkCode.setVisibility(View.VISIBLE);
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