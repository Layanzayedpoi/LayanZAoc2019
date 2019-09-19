package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    //1. properties defenition
    EditText editTextEmail, editTextPassword;
    Button buttonSignUp, buttonLogIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //2. initial;ize properties
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextEmail = findViewById(R.id.editTextPassword);

        buttonLogIn = findViewById(R.id.buttonLogIn);
        buttonLogIn.setOnClickListener(this);
        buttonSignUp = findViewById(R.id.buttonSignUp);

    }

    @Override
    public void onClick(View v) {
        if(v == buttonSignUp) {
            Intent i = new Intent(this, HomePageActivity.class);
            startActivity(i);

        }
        else{

            }


    }
}
