package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.Locale;

public class PickUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextToSpeech tts;
    TextView tvPickUp;
    String text;
    int i =0;
    String[] arrPickUp = {"Pick Up Yor Options", "Do You Want The App to", "Read Your Messages","Write Your Messages"," Remind You","Call For Emergency "};


    RadioButton buttonreadMyMessages, buttonwritemytext, buttonremindme, buttoncallforemergency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);

        tvPickUp= (TextView)findViewById(R.id.tvPickUp);
        tvPickUp.setOnClickListener(this);



    }

    public void onClick(View v) {

        buttonreadMyMessages = findViewById(R.id.buttonreadmessages);
        buttonreadMyMessages.setOnClickListener(this);
        buttonremindme = findViewById(R.id.buttonremindme);
        buttonremindme.setOnClickListener(this);
        buttonwritemytext = findViewById(R.id.buttonwritemytext);
        buttonwritemytext.setOnClickListener(this);
        buttoncallforemergency = findViewById(R.id.buttoncallforemergencybutton);
        buttoncallforemergency.setOnClickListener(this);


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
                        ConvertTextToSpeech();
                    }
                } else
                    Log.e("error", "Initilization Failed!");
            }
        });
    }

        private void ConvertTextToSpeech() {
            // TODO Auto-generated method stub
            text = arrPickUp[i];
            i++;
            if(i == arrPickUp.length - 1)
                i=0;
            if(text==null||"".equals(text))
            {
                text = "Content not available";
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }else
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        }



    public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);

    }
    }






