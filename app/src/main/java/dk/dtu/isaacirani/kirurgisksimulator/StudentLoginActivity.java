package dk.dtu.isaacirani.kirurgisksimulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dk.dtu.isaacirani.kirurgisksimulator.activities.SimulatorActivity;

public class StudentLoginActivity extends AppCompatActivity implements View.OnClickListener {
    Intent simulator;
    Button enterStudentLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);

        enterStudentLogin = findViewById(R.id.enterStudentLogin);
        enterStudentLogin.setOnClickListener(this);

        simulator = new Intent(this, SimulatorActivity.class);

    }

    @Override
    public void onClick(View v) {
        startActivity(simulator);
    }
}
