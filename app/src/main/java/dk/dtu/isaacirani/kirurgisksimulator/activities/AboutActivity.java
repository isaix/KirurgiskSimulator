package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{

    MediaPlayer buttonPush;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void hide(View view) {

        @SuppressLint("WrongViewCast") TextView txtView = (TextView) findViewById(R.id.textView3);

        buttonPush = MediaPlayer.create(this, R.raw.monsterkill_f);
        buttonPush.start();




        if (txtView.getVisibility() == View.VISIBLE)
            txtView.setVisibility(View.INVISIBLE);

        else
            txtView.setVisibility(View.VISIBLE);


    }
    public void hide2(View view) {

        @SuppressLint("WrongViewCast") TextView txtView2 = (TextView) findViewById(R.id.textView4);

        if (txtView2.getVisibility() == View.VISIBLE)
            txtView2.setVisibility(View.INVISIBLE);
        else
            txtView2.setVisibility(View.VISIBLE);

    }
    public void hide3(View view) {

        @SuppressLint("WrongViewCast") TextView txtView3 = (TextView) findViewById(R.id.textView5);


        if (txtView3.getVisibility() == View.VISIBLE)
            txtView3.setVisibility(View.INVISIBLE);
        else
            txtView3.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {

    }
}
