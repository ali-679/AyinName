package ir.eyrsa.app.ayinname.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.eyrsa.app.ayinname.Activities.QuestionsActivity;
import ir.eyrsa.app.ayinname.Config.Application;
import ir.eyrsa.app.ayinname.Config.Config;
import ir.eyrsa.app.ayinname.Model.Exams_Model;
import ir.eyrsa.app.ayinname.R;

public class ListExam_Adapter extends RecyclerView.Adapter<ListExam_Adapter.MyViewHolder> {

    Context context;
    ArrayList<Exams_Model> arrayExamsModel;

    public ListExam_Adapter(Context context, ArrayList<Exams_Model> arrayExamsModel) {
        this.context = context;
        this.arrayExamsModel = arrayExamsModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_recycler_list_exam,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (checkFree(position))
        {
            holder.imageView_lock.setVisibility(View.VISIBLE);
        }
        else
            holder.imageView_lock.setVisibility(View.GONE);
        holder.textView_number.setText(position+"");

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkFree(position))
                    Config.failedToast(context.getString(R.string.toast_errorBuy));
                else
                {
                    Intent intent=new Intent(Application.getContext(), QuestionsActivity.class);
                    intent.putExtra("idExam",arrayExamsModel.get(position).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });


    }
    private boolean checkFree(int position)
    {
        if (arrayExamsModel.get(position).getFree()==(byte)0)
            return true;
        return false;
    }

    @Override
    public int getItemCount() {
        return arrayExamsModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_number;
        ImageView imageView_lock;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_number=itemView.findViewById(R.id.textView_number);
            imageView_lock=itemView.findViewById(R.id.imageView_lock);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }
}
