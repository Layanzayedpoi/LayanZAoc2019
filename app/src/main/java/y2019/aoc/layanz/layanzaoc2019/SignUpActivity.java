package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            if(editTextPassword.getText().toString().equals(" ")|| editTextEmail.getText().toString().equals(""))
            {
              Toast.makeText( this, "Empty Email or Password", Toast.LENGTH_LONG).show();
            }
            else{

                    Intent i = new Intent(this, MainActivity.class);
                    i.putExtra("Email", editTextEmail.getText().toString());
                    i.putExtra("Password", editTextPassword.getText().toString());
                    startActivity(i);
            }


        }
        else{
            Intent i = new Intent(this, HomePageActivity.class);
            startActivity(i);

            }


    }
}
