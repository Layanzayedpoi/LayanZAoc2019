package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvEmail, tvPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String password = getIntent().getStringExtra("Password");

        String email = getIntent().getStringExtra("Email");
/*
        tvEmail = findViewById(R.id.tvEmail);
        tvPassword =findViewById(R.id.tvPassword);

        tvEmail.setText(email);
        tvPassword.setText(password);*/


    }
    
}
