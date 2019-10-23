package y2019.aoc.layanz.layanzaoc2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {



    private TextView tvWelcom;
    TextToSpeech tts;
    String text;
    int i = 0;
    String[] arrSignUp = {"Welcome To Blink for more info click upper side of screen","Let's Sign Up Here", "Your Name is", "Your ID Number is","Sign Up" };

    //1. properties defenition
    EditText editTextEmail, editTextPassword;
    Button buttonSignUp, buttonLogIn;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_up);
        tvWelcom = (TextView) findViewById(R.id.tvWelcom);
        tvWelcom.setOnClickListener(this);

        //2. initial;ize properties
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);





        tts=new TextToSpeech(SignUpActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language i-s not supported");
                    }
                    else{
                        ConvertTextToSpeech();
                    }
                }
                else
                    Log.e("error", "Initilization Failed!");
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
       // updateUI(currentUser);
    }


    public void signUp(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent i = new Intent(SignUpActivity.this, StartActivity.class);
                            i.putExtra("Email", editTextEmail.getText().toString());
                            i.putExtra("Password", editTextPassword.getText().toString());
                            startActivity(i);
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("firebase", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                           // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("firebase", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }



    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        text = arrSignUp[i];
        i++;
        if(i == arrSignUp.length - 1)
            i=0;
        if(text==null||"".equals(text))
        {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    @Override
    public void onClick(View v) {
        if(v == buttonSignUp) {
            if(editTextPassword.getText().toString().equals(" ")|| editTextEmail.getText().toString().equals(""))
            {
                Toast.makeText( this, "Empty Email or Password", Toast.LENGTH_LONG).show();
            }
            else{
              signUp(editTextEmail.getText().toString(),editTextPassword.getText().toString());

            }

        }else if ( v == tvWelcom){
            ConvertTextToSpeech();
        }
      //  else{

          //  Intent i = new Intent(this, HomePageActivity.class);
          //  startActivity(i);
        //}
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    String[] result = data.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvWelcom.setText(0);
                }
                break;
        }
    }













    public void getSTT(View view) throws InterruptedException {
        getSpeechInput();
        Thread.sleep(5000);
        getSpeechInput();
    }
    public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivityForResult(intent, 10);
        }else{
            Toast.makeText(this, "Doesn't support Speech to text", Toast.LENGTH_LONG).show();
        }
    }
    public void getSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Dont Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }
}

