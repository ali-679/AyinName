package ir.eyrsa.app.ayinname.Retrofit;

import java.util.List;

import ir.eyrsa.app.ayinname.Model.Exams_Model;
import ir.eyrsa.app.ayinname.Model.Questions_Model;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IRetrofit {

    // Exam API
    @POST("GetQuestions.php")
    Call<List<Questions_Model>> getQuestions(@Query("E_idexam") int E_idexam);

    @POST("GetExams.php")
    Call<List<Exams_Model>> getExams(@Query("U_idUser") String U_idUser);

}
