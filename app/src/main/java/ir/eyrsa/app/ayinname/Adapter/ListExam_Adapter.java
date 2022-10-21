package ir.eyrsa.app.ayinname.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

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

        holder.textView_nameExam.setText(arrayExamsModel.get(position).getNameExam());

    }

    @Override
    public int getItemCount() {
        return arrayExamsModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_nameExam;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_nameExam=itemView.findViewById(R.id.textView_nameExam);
        }
    }
}
