package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);



        TextView start;


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

        }
        return true;
    }

}



