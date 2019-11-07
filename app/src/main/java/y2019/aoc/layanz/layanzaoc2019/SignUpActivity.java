package y2019.aoc.layanz.layanzaoc2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    SharedPreferences pref;
    private static final int SPEEK_TEXT = 10;
    private TextView tvWelcom, tvResult, writename2;
    TextToSpeech tts;
    String text, name;
    int i = 0;
    String[] arrSignUp = {"Welcome To Blink for more info click upper side of screen","Let's Sign Up Here", "Your Email Is", "Your ID Number is","Your Name is","Sign Up" };


    //1. properties defenition
    EditText editTextEmail, editTextPassword;
    Button buttonSignUp, buttonlogin;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        tvWelcom = (TextView) findViewById(R.id.tvWelcom);
        tvWelcom.setOnClickListener(this);

        tvResult = (TextView) findViewById(R.id.tvResult);
        tvWelcom.setOnClickListener(this);

        writename2 = findViewById(R.id.writename2);
        writename2.setOnClickListener(this);

        //2. initial;ize properties
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSignUp = findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);
        buttonlogin = findViewById(R.id.buttonlogin);
        buttonlogin.setOnClickListener(this);

        pref=getSharedPreferences("mypref", MODE_PRIVATE );


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
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SPEEK_TEXT:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvResult.setText(result.get(0));
                     name = result.get(0);
                  SharedPreferences.Editor editor= pref.edit();
                  editor.putString("name",name);
                  editor.commit();
                }
                break;
        }
    }

    public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if(intent.resolveActivity(getPackageManager()) != null){
            //startActivityForResult(intent, 10);
            startActivityForResult(intent, SPEEK_TEXT);
        }else{
            Toast.makeText(this, "Doesn't support Speech to text", Toast.LENGTH_LONG).show();
        }
    }
/*


    public static int getSpeekText() {
        return SPEEK_TEXT;
    }

    public TextView getTvWelcom() {
        return tvWelcom;
    }

    public TextView getTvResult() {
        return tvResult;
    }

    public TextView getWritename2() {
        return writename2;
    }

    public TextToSpeech getTts() {
        return tts;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public int getI() {
        return i;
    }

    public String[] getArrSignUp() {
        return arrSignUp;
    }

    public EditText getEditTextEmail() {
        return editTextEmail;
    }

    public EditText getEditTextPassword() {
        return editTextPassword;
    }

    public Button getButtonSignUp() {
        return buttonSignUp;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }


    public void setTvWelcom(TextView tvWelcom) {
        this.tvWelcom = tvWelcom;
    }

    public void setTvResult(TextView tvResult) {
        this.tvResult = tvResult;
    }

    public void setWritename2(TextView writename2) {
        this.writename2 = writename2;
    }

    public void setTts(TextToSpeech tts) {
        this.tts = tts;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setArrSignUp(String[] arrSignUp) {
        this.arrSignUp = arrSignUp;
    }

    public void setEditTextEmail(EditText editTextEmail) {
        this.editTextEmail = editTextEmail;
    }

    public void setEditTextPassword(EditText editTextPassword) {
        this.editTextPassword = editTextPassword;
    }

    public void setButtonSignUp(Button buttonSignUp) {
        this.buttonSignUp = buttonSignUp;
    }

    public void setmAuth(FirebaseAuth mAuth) {
        this.mAuth = mAuth;
    }
*/
}

