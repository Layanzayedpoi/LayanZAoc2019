package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LogInActivity extends AppCompatActivity {

    private TextView fingertext;
    private TextView palcefinger;
    private ImageView iconfinger;

    private FingerprintManager fingerprintManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        fingertext = findViewById(R.id.fingertext);
        palcefinger = findViewById(R.id.palcefinger);
        iconfinger = findViewById(R.id.iconfinger);

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M){
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);

            //if (fingerprintManager.isHardwareDetected())
        }
    }
}
