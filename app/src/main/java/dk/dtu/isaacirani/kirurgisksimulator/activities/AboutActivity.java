package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton aboutus_phone;
    ImageButton aboutus_pin;
    ImageButton aboutus_mail;
    TextView phonenumber;
    TextView address;
    TextView emailaddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutus_phone = (ImageButton) findViewById(R.id.aboutus_phone);
        aboutus_pin = (ImageButton) findViewById(R.id.aboutus_pin);
        aboutus_mail = (ImageButton) findViewById(R.id.aboutus_mail);
        phonenumber = (TextView) findViewById(R.id.phonenumber);
        address = (TextView) findViewById(R.id.address);
        emailaddress = (TextView) findViewById(R.id.emailaddress);

        aboutus_mail.setOnClickListener(this);
        aboutus_pin.setOnClickListener(this);
        aboutus_phone.setOnClickListener(this);
        emailaddress.setOnClickListener(this);
        address.setOnClickListener(this);
        phonenumber.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
         /*
        if(view == aboutus_mail || view == emailaddress){
            Intent mailintent = new Intent(Intent.ACTION_SENDTO);
            mailintent.setData(Uri.parse("cames.rigshospitalet@regionh.dk"));

        } else if (view == aboutus_pin || view == address){
            String uri = "MHX8+5G København";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            startActivity(intent);

        }

    }*/
    }
}