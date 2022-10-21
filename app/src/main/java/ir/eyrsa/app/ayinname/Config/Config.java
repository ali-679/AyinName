package ir.eyrsa.app.ayinname.Config;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import ir.eyrsa.app.ayinname.R;

public class Config {

    public static final String TAG = "Eyrsa";
    public static final String BASE_URL = "http://alifeyzabadi.ir/AyinName/";
    public static final String EXAM_URL = "Exam/";

    public static void successToast(String message)
    {
        Toast toast=new Toast(Application.getContext());

        View view= LayoutInflater.from(Application.getContext()).inflate(R.layout.success_toast,null);

        ImageView imageView=view.findViewById(R.id.imageView);
        TextView textView=view.findViewById(R.id.textView);

        imageView.setImageResource(R.drawable.ic_tik);
        textView.setText(message);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM,0,50);
        toast.show();
    }

    public static void failedToast(String message)
    {
        Toast toast=new Toast(Application.getContext());

        View view= LayoutInflater.from(Application.getContext()).inflate(R.layout.failed_toast,null);

        ImageView imageView=view.findViewById(R.id.imageView);
        TextView textView=view.findViewById(R.id.textView);

        imageView.setImageResource(R.drawable.ic_close);
        textView.setText(message);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.BOTTOM,0,50);
        toast.show();
    }

    public static Boolean isConnected()
    {
        ConnectivityManager connectivityManager= (ConnectivityManager) Application.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        return connectivityManager != null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static String jsonObject(String key, String json) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json);
            return jsonObject.getString(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return "false";
        }
    }


}
