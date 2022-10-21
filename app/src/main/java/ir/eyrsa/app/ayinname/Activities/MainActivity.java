package ir.eyrsa.app.ayinname.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import ir.eyrsa.app.ayinname.R;

public class MainActivity extends AppCompatActivity {

    ImageView img1,img2,img3,img4;
    Animation animationMove,animationMoveTwo,animationMoveFour,animationOpacity;

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

    }
}