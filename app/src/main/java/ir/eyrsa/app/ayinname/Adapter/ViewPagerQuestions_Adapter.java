package ir.eyrsa.app.ayinname.Adapter;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ir.eyrsa.app.ayinname.Model.Questions_Model;
import ir.eyrsa.app.ayinname.R;

public class ViewPagerQuestions_Adapter extends RecyclerView.Adapter<ViewPagerQuestions_Adapter.MyViewHolder> implements View.OnClickListener {

    Context context;
    ArrayList<Questions_Model> arrayQuestionModel;
    Button[] buttons;
    int pos;

    public ViewPagerQuestions_Adapter(Context context, ArrayList<Questions_Model> arrayQuestionModel) {
        this.context = context;
        this.arrayQuestionModel = arrayQuestionModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_viewpager_questions, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        pos=position;

        if (arrayQuestionModel.get(position).getGozine() != 0) {
            setDisableButtons();
            setColorData();
        } else {
            setBackgroundReset();
        }

        holder.textView_question.setText(arrayQuestionModel.get(position).getNameQuestion());
        buttons[0].setText(arrayQuestionModel.get(position).getOp1());
        buttons[1].setText(arrayQuestionModel.get(position).getOp2());
        buttons[2].setText(arrayQuestionModel.get(position).getOp3());
        buttons[3].setText(arrayQuestionModel.get(position).getOp4());
        if (!arrayQuestionModel.get(position).getLinkImage().equals("no")) {
            Glide.with(context).load(arrayQuestionModel.get(position).getLinkImage()).into(holder.imageView);
        } else
            holder.imageView.setBackground(context.getResources().getDrawable(R.drawable.ic_tik));

        for (int i = 0; i < 4; i++)
        {
            buttons[i].setOnClickListener(this);
        }

    }

    private void setColorData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int gozine = arrayQuestionModel.get(pos).getGozine();
            int correct = arrayQuestionModel.get(pos).getOpCorrect();
            if (gozine == correct) {
                buttons[gozine - 1].setBackgroundColor(context.getColor(R.color.green));
            }
            else if(gozine!=0)
            {
                buttons[gozine - 1].setBackgroundColor(context.getColor(R.color.red));
                buttons[correct - 1].setBackgroundColor(context.getColor(R.color.green));
            }
        }
    }

    private void setDisableButtons() {
        for (int i = 0; i < 4; i++) {
            buttons[i].setEnabled(false);
        }
    }

    private void setBackgroundReset() {
        for (int i = 0; i < 4; i++) {
            buttons[i].setBackground(context.getResources().getDrawable(R.drawable.bg_button_gozine));
        }
    }

    @Override
    public int getItemCount() {
        return arrayQuestionModel.size();
    }

    @Override
    public void onClick(View view) {
        if(arrayQuestionModel.get(pos).getGozine()==0) {
            arrayQuestionModel.get(pos).setGozine((Integer) view.getTag());
            setColorData();
        }
        else
            setDisableButtons();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView_question;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView_question = itemView.findViewById(R.id.textView_question);
            buttons = new Button[]{itemView.findViewById(R.id.button1),
                    itemView.findViewById(R.id.button2),
                    itemView.findViewById(R.id.button3),
                    itemView.findViewById(R.id.button4)};
            for (int i = 0; i < 4; i++) {
                buttons[i].setTag(i + 1);
            }


        }
    }
}
