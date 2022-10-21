package ir.eyrsa.app.ayinname.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;

import ir.eyrsa.app.ayinname.Adapter.ViewPagerFlashCards_Adapter;
import ir.eyrsa.app.ayinname.Config.Application;
import ir.eyrsa.app.ayinname.Model.FlashCards_Model;
import ir.eyrsa.app.ayinname.R;

public class FlashCardsActivity extends AppCompatActivity {

    ViewPager2 viewPager2;
    ViewPagerFlashCards_Adapter viewPagerFlashCards_adapter;
    ArrayList<FlashCards_Model> arrayFlashCardsModel=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_cards);

        init();

    }

    private void init()
    {
        viewPager2=findViewById(R.id.viewpager2);
    }

    private void setupViewPager()
    {
        viewPagerFlashCards_adapter=new ViewPagerFlashCards_Adapter(Application.getContext(),arrayFlashCardsModel);
        viewPager2.setAdapter(viewPagerFlashCards_adapter);

    }

}