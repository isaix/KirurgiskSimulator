package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

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