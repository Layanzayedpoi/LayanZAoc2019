package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart;
    TextToSpeech tts;
    String text;
    int i=0;
    String name;
    String[] arrStart = new String[2];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        name = getSharedPreferences("mypref", MODE_PRIVATE ).getString("name","");

        arrStart[0]= "Hello"+name;
        arrStart[1] = " Start Applcation";
        TextView start;

        tts=new TextToSpeech(StartActivity.this, new TextToSpeech.OnInitListener() {

            @Override
            public void onInit(int status) {
                // TODO Auto-generated method stub
                if(status == TextToSpeech.SUCCESS){
                    int result=tts.setLanguage(Locale.US);
                    if(result==TextToSpeech.LANG_MISSING_DATA ||
                            result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("error", "This Language is not supported");
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


    private void ConvertTextToSpeech() {
        // TODO Auto-generated method stub
        text = arrStart[i];
        i++;
        if(i == arrStart.length-1)
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
    }

    public boolean onCreateOptionsMenu(Menu menu) {
      getMenuInflater().inflate(R.menu.bmenu, menu);
      return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Intent goToNextActivity = new Intent(getApplicationContext(), SignUpActivity.class);

        switch (item.getItemId()) {

            case R.id.settings:
                goToNextActivity = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(goToNextActivity);
                break;

            case R.id.signup:
                goToNextActivity = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(goToNextActivity);
                break;

            case R.id.pickupmenu:
                goToNextActivity = new Intent(getApplicationContext(), PickUpActivity.class);
                startActivity(goToNextActivity);
                break;

            case R.id.moreinfo:
                goToNextActivity = new Intent(getApplicationContext(), MoreInfoActivity.class);
                startActivity(goToNextActivity);
                break;

        }
        return true;
    }

}



