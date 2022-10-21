package ir.eyrsa.app.ayinname.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import ir.eyrsa.app.ayinname.Adapter.ViewPagerQuestions_Adapter;
import ir.eyrsa.app.ayinname.Config.Application;
import ir.eyrsa.app.ayinname.Config.Config;
import ir.eyrsa.app.ayinname.Model.Questions_Model;
import ir.eyrsa.app.ayinname.R;
import ir.eyrsa.app.ayinname.Retrofit.IRetrofit;
import ir.eyrsa.app.ayinname.Retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivity extends AppCompatActivity {

    IRetrofit iRetrofit;
    int idExam;

    ArrayList<Questions_Model> arrayQuestion = new ArrayList<>();

    ViewPager2 viewPager2;

    ViewPagerQuestions_Adapter viewPagerQuestions_adapter;

    private int duration = 1500;
    private boolean timerRunning = false;
    TextView textView_hour, textView_minute, textView_second;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        init();

    }

    private void init() {
        viewPager2 = findViewById(R.id.viewpager2);
        textView_hour = findViewById(R.id.textView_hour);
        textView_minute = findViewById(R.id.textView_minute);
        textView_second = findViewById(R.id.textView_second);

        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL + Config.EXAM_URL).create(IRetrofit.class);
        idExam = 1;
        getData();
        setTimer();
    }

    private void setTimer() {
        if (!timerRunning) {
            timerRunning = true;
            new CountDownTimer(duration * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            String time = String.format(Locale.getDefault(), "%02d:%02d:%02d",
                                    TimeUnit.MILLISECONDS.toHours(l),
                                    TimeUnit.MILLISECONDS.toMinutes(l) -
                                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(l)),
                                    TimeUnit.MILLISECONDS.toSeconds(l) -
                                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(l)));

                            final String[] hourMinSec = time.split(":");
                            textView_hour.setText(hourMinSec[0]);
                            textView_minute.setText(hourMinSec[1]);
                            textView_second.setText(hourMinSec[2]);
                        }
                    });
                }

                @Override
                public void onFinish() {
                    duration = 1500;
                    timerRunning = false;
                }
            }.start();
        }
    }


    private void getData() {
        try {
            iRetrofit.getQuestions(idExam).enqueue(new Callback<List<Questions_Model>>() {
                @Override
                public void onResponse(Call<List<Questions_Model>> call, Response<List<Questions_Model>> response) {
                    arrayQuestion.addAll(response.body());
                    setupViewPger();
                }

                @Override
                public void onFailure(Call<List<Questions_Model>> call, Throwable t) {
                    Config.failedToast(getString(R.string.toast_errorGetData));
                }
            });
        } catch (Exception e) {
            Log.e("TAG", "getData: " + e.getMessage());
        }

    }

    private void setupViewPger() {
        viewPagerQuestions_adapter = new ViewPagerQuestions_Adapter(Application.getContext(), arrayQuestion);
        viewPager2.setAdapter(viewPagerQuestions_adapter);
    }

}