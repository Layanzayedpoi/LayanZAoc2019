package y2019.aoc.layanz.layanzaoc2019;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Locale;

public class PickUpActivity extends AppCompatActivity implements View.OnClickListener {

  //  SharedPreferfnces pref;
    private TextView tvResult;
    private static final int SPEEK_TEXT = 10;
    private RadioButton readB;
    private RadioButton writeB;
    private RadioButton remindB;
    private RadioButton callB;
    private RadioGroup radioGroup;
    private Button NextB;
    int option=0;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    TextToSpeech tts;
    TextView tvPickUp;
    String text;
    int i = 0;
    String[] arrPickUp = {"Pick Up Yor Options To Know What The Opitions Are click upper side of screen", "Do You Want The App to", "Read Your Messages", "Write Your Messages", " Remind You", "Call For Emergency "};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);


        tvPickUp = (TextView) findViewById(R.id.tvPickUp);
        tvPickUp.setOnClickListener(this);

        readB = (RadioButton) findViewById(R.id.buttonreadmessages);
        writeB = (RadioButton) findViewById(R.id.buttonwritemytext);
        remindB = (RadioButton) findViewById(R.id.buttonremindme);
        callB = (RadioButton) findViewById(R.id.buttoncallforem);

        NextB = (Button) findViewById(R.id.nextbutton);
     //   NextB.setOnClickListener(this);//msh 3arfe eza s7

        radioGroup = findViewById(R.id.radioGPick);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.buttonreadmessages:
                        break;
                    case R.id.buttonwritemytext:
                        break;
                    case R.id.buttonremindme:
                        break;
                    case R.id.buttoncallforem:
                        String arr = "If you want to call emergency Please say police ambulance or Fireservices";
                       emergeyncyOption(arr);
                        break;
                }

            }
        });

    }

    public void emergeyncyOption(final String text){
        tts = new TextToSpeech(PickUpActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language i-s not supported");
                    } else {
                        ConvertTextToSpeech(text);
                    }
                } else
                    Log.e("error", "Initilization Failed!");
            }
        });
        callForEmergency(0);
    }
    public void onClick(View v) {

        tts = new TextToSpeech(PickUpActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if (status == TextToSpeech.SUCCESS) {
                    int result = tts.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("error", "This Language i-s not supported");
                    } else {
                        ConvertTextToSpeech(arrPickUp[0]);
                    }
                } else
                    Log.e("error", "Initilization Failed!");
            }
        });
    }

   // firebase
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        // updateUI(currentUser);
    }


    //text to speach
    private void ConvertTextToSpeech(String text) {
        // TODO Auto-generated method stub
        //text = arr[i];
       // i++;

        if (text == null || "".equals(text)) {
            text = "Content not available";
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        } else
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    public void callForEmergency(int option){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CALL_PHONE},1);
        } else {
            switch (option) {
                case 0:
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:101")));

                case 1:
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:100")));

                case 2:
                    startActivity(new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:102")));

            }
        }

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {

            case 123:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    callForEmergency(option);
                } else {
                    Log.d("TAG", "Call Permission Not Granted");
                }
                break;

            default:
                break;
        }
    }


    public void getSpeechInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            //startActivityForResult(intent, 10);
            startActivityForResult(intent, SPEEK_TEXT);
        } else {
            Toast.makeText(this, "Doesn't support Speech to text", Toast.LENGTH_LONG).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SPEEK_TEXT:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    tvResult.setText(result.get(0));
                    if(result.get(0).equals("police")){
                        option = 0;
                    }else
                        if(result.get(0).equals("ambulance"))
                            option = 1;
                        else
                            option = 2;
                }
                break;
        }
    }
}






