package ir.eyrsa.app.ayinname.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ir.eyrsa.app.ayinname.Model.FlashCards_Model;
import ir.eyrsa.app.ayinname.R;

public class ViewPagerFlashCards_Adapter extends RecyclerView.Adapter<ViewPagerFlashCards_Adapter.MyViewHolder> {

   Context context;
   ArrayList<FlashCards_Model> arrayFlashCardsModel;

    public ViewPagerFlashCards_Adapter(Context context, ArrayList<FlashCards_Model> arrayFlashCardsModel) {
        this.context = context;
        this.arrayFlashCardsModel = arrayFlashCardsModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_viewpager_flashcard,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (arrayFlashCardsModel.get(position).isFront())
        {
            front(holder,position);
        }
        else
        {
            back(holder,position);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ObjectAnimator oa1 = ObjectAnimator.ofFloat(holder.cardView, "scaleX", 1f, 0f);
                final ObjectAnimator oa2 = ObjectAnimator.ofFloat(holder.cardView, "scaleX", 0f, 1f);
                oa1.setInterpolator(new DecelerateInterpolator());
                oa2.setInterpolator(new AccelerateDecelerateInterpolator());

                oa1.addListener(new AnimatorListenerAdapter()
                {
                    @Override
                    public void onAnimationEnd(Animator animation)
                    {
                        super.onAnimationEnd(animation);
                        if (arrayFlashCardsModel.get(position).isFront()) {
                            front(holder,position);
                        }
                        else
                        {
                            back(holder,position);
                        }
                        arrayFlashCardsModel.get(position).setFront(
                                !arrayFlashCardsModel.get(position).isFront());
                        oa2.start();

                    }
                });
                oa1.start();

            }
        });


    }

    private void back(MyViewHolder holder, int position) {
        holder.textView_card.setVisibility(View.GONE);
        holder.imageView.setVisibility(View.VISIBLE);
        Glide.with(context).load(arrayFlashCardsModel.get(position).getLinkImage()).into(holder.imageView);
    }

    private void front(MyViewHolder holder, int position) {
        holder.textView_card.setVisibility(View.VISIBLE);
        holder.imageView.setVisibility(View.GONE);
        holder.textView_card.setText(arrayFlashCardsModel.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayFlashCardsModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView_card;
        ImageView imageView;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView_card=itemView.findViewById(R.id.textView_card);
            imageView=itemView.findViewById(R.id.imageView);
            cardView=itemView.findViewById(R.id.cardView);

        }
    }
}
