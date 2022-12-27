package ir.eyrsa.app.ayinname.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ir.eyrsa.app.ayinname.Config.Application;
import ir.eyrsa.app.ayinname.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img1,img2,img3,img4;
    Animation animationMove,animationMoveTwo,animationMoveFour,animationOpacity;

    FloatingActionButton floating_support;
    CardView cardView_exam,cardView_roadSign,cardView_train,cardView_buy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.img1);
        img2 = findViewById(R.id.img2);
        img3 = findViewById(R.id.img3);
        img4 = findViewById(R.id.img4);

        animationMove = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bgs);
        animationMoveTwo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bgstwo);
        animationOpacity = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bgss);
        animationMoveFour = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bgsfour);
        img1.startAnimation(animationMove);
        img2.startAnimation(animationMoveTwo);
        img3.startAnimation(animationOpacity);
        img4.startAnimation(animationMoveFour);

        click();

        floating_support=findViewById(R.id.floating_support);


    }

    private void click() {
        floating_support.setOnClickListener(this);
        cardView_exam.setOnClickListener(this);
        cardView_roadSign.setOnClickListener(this);
        cardView_train.setOnClickListener(this);
        cardView_buy.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view==floating_support)
        {
            startActivity(new Intent(Application.getContext(),ContactUsActivity.class));
        }
        else if (view==cardView_exam)
        {
            startActivity(new Intent(Application.getContext(),ListExamsActivity.class));
        }
        else if (view==cardView_roadSign)
        {
            startActivity(new Intent(Application.getContext(),FlashCardsActivity.class));
        }
        else if (view==cardView_train)
        {

        }
        else if (view==cardView_buy)
        {

        }
    }
}