package dk.dtu.isaacirani.kirurgisksimulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button student;
    Button surgeon;
    ImageButton burgerMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        student = findViewById(R.id.student);
        surgeon = findViewById(R.id.surgeon);
        burgerMenu = findViewById(R.id.burgerMenu);

        student.setOnClickListener(this);
        surgeon.setOnClickListener(this);
        burgerMenu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==student.getId()){
            return;
        }
        if(v.getId()==surgeon.getId()){
            return;
        }
        if(v.getId()==burgerMenu.getId()){
            Intent i;
            i = new Intent(this,BurgerMenu.class);
            startActivity(i);
        }
    }
}
