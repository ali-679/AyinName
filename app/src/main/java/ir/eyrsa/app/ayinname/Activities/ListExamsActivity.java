package ir.eyrsa.app.ayinname.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ir.eyrsa.app.ayinname.Adapter.ListExam_Adapter;
import ir.eyrsa.app.ayinname.Config.Application;
import ir.eyrsa.app.ayinname.Config.Config;
import ir.eyrsa.app.ayinname.Model.Exams_Model;
import ir.eyrsa.app.ayinname.R;
import ir.eyrsa.app.ayinname.Retrofit.IRetrofit;
import ir.eyrsa.app.ayinname.Retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListExamsActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    IRetrofit iRetrofit;

    String userName;

    ArrayList<Exams_Model> arrayExamsModel=new ArrayList<>();

    ListExam_Adapter listExam_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exams);

        init();

    }

    private void init()
    {
        recyclerView=findViewById(R.id.recyclerView);

        iRetrofit = RetrofitClient.getRetrofit(Config.BASE_URL + Config.EXAM_URL).create(IRetrofit.class);

        userName="ali";

        getData();

    }

    private void getData() {
        iRetrofit.getExams(userName).enqueue(new Callback<List<Exams_Model>>() {
            @Override
            public void onResponse(Call<List<Exams_Model>> call, Response<List<Exams_Model>> response) {
                arrayExamsModel.addAll(response.body());
                setupRecyclerView();
            }

            @Override
            public void onFailure(Call<List<Exams_Model>> call, Throwable t) {
                Config.failedToast(getString(R.string.toast_errorGetData));
                getData();
            }
        });
    }

    private void setupRecyclerView() {
        listExam_adapter=new ListExam_Adapter(Application.getContext(),arrayExamsModel);
        recyclerView.setAdapter(listExam_adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(Application.getContext(),4,RecyclerView.VERTICAL,false));
        recyclerView.setHasFixedSize(true);
    }

}