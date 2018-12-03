package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    public void hide(View view) {

        @SuppressLint("WrongViewCast") TextView txtView = (TextView)findViewById(R.id.imageView9);

        //Toggle
        if (txtView.getVisibility() == View.VISIBLE)
            txtView.setVisibility(View.INVISIBLE);
        else
            txtView.setVisibility(View.VISIBLE);

        //If you want it only one time
        //txtView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onClick(View v) {

    }
}
