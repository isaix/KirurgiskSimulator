package dk.dtu.isaacirani.kirurgisksimulator.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import dk.dtu.isaacirani.kirurgisksimulator.R;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton aboutus_phone;
    ImageButton aboutus_pin;
    ImageButton aboutus_mail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_about);
        setSupportActionBar(findViewById(R.id.toolbar));

        aboutus_phone = (ImageButton) findViewById(R.id.aboutus_phone);
        aboutus_pin = (ImageButton) findViewById(R.id.aboutus_pin);
        aboutus_mail = (ImageButton) findViewById(R.id.aboutus_mail);

        aboutus_mail.setOnClickListener(this);
        aboutus_pin.setOnClickListener(this);
        aboutus_phone.setOnClickListener(this);

    }



    @Override
    public void onClick(View view) {

        if (view == aboutus_mail) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("You're about to leave the page, do you want to proceed?");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("plain/text");
                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"cames.rigshospital@regionh.dk"});
                    intent.putExtra(Intent.EXTRA_SUBJECT, "");
                    intent.putExtra(Intent.EXTRA_TEXT, "");
                    startActivity(Intent.createChooser(intent, ""));
                }
            });

            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.create();
            alertDialog.show();

        } else if (view == aboutus_pin) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("You're about to leave the page, do you want to proceed?");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String uri = "https://www.google.com/maps/place/Copenhagen+Academy+for+Medical+Education+and+Simulation/@55.6979566,12.5640934,17z/data=!3m1!4b1!4m5!3m4!1s0x465252ff26e39a0b:0x95e11a295631ad5d!8m2!3d55.6979536!4d12.5662821";
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                    startActivity(intent);
                }
            });
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.create();
            alertDialog.show();

        } else if (view == aboutus_phone) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setMessage("You're about to leave the page, do you want to proceed?");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:+45 35 45 54 04"));
                    startActivity(intent);

                }
            });
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.create();
            alertDialog.show();
        }

    }

}