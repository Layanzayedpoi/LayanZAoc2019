package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class PickUpActivity extends AppCompatActivity implements View.OnClickListener {

    private RadioButton readB;
    private RadioButton writeB;
    private RadioButton remindB;
    private RadioButton callB;
    private Button NextB;

    TextToSpeech tts;
    TextView tvPickUp;
    String text;
    int i =0;
    String[] arrPickUp = {"Pick Up Yor Options To Know What The Opitions Are click upper side of screen", "Do You Want The App to", "Read Your Messages","Write Your Messages"," Remind You","Call For Emergency "};


    RadioButton buttonreadMyMessages, buttonwritemytext, buttonremindme, buttoncallforemergency;
    Button nextbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up);



        tvPickUp= (TextView)findViewById(R.id.tvPickUp);
        tvPickUp.setOnClickListener(this);
        readB = (RadioButton)findViewById(R.id.buttonreadmessages);
        writeB = (RadioButton)findViewById(R.id.buttonwritemytext);
        remindB =  (RadioButton)findViewById(R.id.buttonremindme);
        callB = (RadioButton)findViewById(R.id.buttoncallforem);
        NextB = (Button)findViewById(R.id.nextbutton);
        NextB.setOnClickListener(this);//msh 3arfe eza s7



    }

    public void onClick(View v) {

        buttonreadMyMessages = findViewById(R.id.buttonreadmessages);
        buttonreadMyMessages.setOnClickListener(this);
        buttonremindme = findViewById(R.id.buttonremindme);
        buttonremindme.setOnClickListener(this);
        buttonwritemytext = findViewById(R.id.buttonwritemytext);
        buttonwritemytext.setOnClickListener(this);
        buttoncallforemergency = findViewById(R.id.buttoncallforem);
        buttoncallforemergency.setOnClickListener(this);
        nextbutton = findViewById(R.id.nextbutton);
        nextbutton.setOnClickListener(this);

        int selectedId1 = readB.getId();
        readB = (RadioButton)findViewById(selectedId1);
        int selectedId2 = writeB.getId();
        writeB = (RadioButton)findViewById(selectedId2);
        int selectedId3 = remindB.getId();
        remindB = (RadioButton)findViewById(selectedId3);
        int selectedId4 = callB.getId();
        callB = (RadioButton)findViewById(selectedId4);

       





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

        public void onClickR(View v){
        if(v==nextbutton){
            Intent i = new Intent(this, StartActivity.class);
            startActivity(i);
        }

        }





            public void getSpeechInput(View view){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);

    }
    }






