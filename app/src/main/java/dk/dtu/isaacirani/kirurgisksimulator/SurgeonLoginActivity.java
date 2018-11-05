package dk.dtu.isaacirani.kirurgisksimulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dk.dtu.isaacirani.kirurgisksimulator.activities.SurgeonActivity;

public class  SurgeonLoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button surgeonEnterLogin;
    Intent surgeon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surgeon_login);

        surgeonEnterLogin = findViewById(R.id.enterSurgeonLogin);
        surgeonEnterLogin.setOnClickListener(this);

        surgeon = new Intent(this, SurgeonActivity.class);
    }

    @Override
    public void onClick(View v) {
        startActivity(surgeon);
    }
}
