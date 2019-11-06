package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import android.Manifest;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.pm.PackageManager;
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
    private KeyguardManager keyguardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);


        fingertext = findViewById(R.id.fingertext);
        palcefinger = findViewById(R.id.palcefinger);
        iconfinger = findViewById(R.id.iconfinger);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fingerprintManager = (FingerprintManager) getSystemService(FINGERPRINT_SERVICE);
            keyguardManager = (KeyguardManager) getSystemService(KEYGUARD_SERVICE);

            if (!fingerprintManager.isHardwareDetected()) {

                palcefinger.setText("FingerPrint Scanner Is Not Detacted In Device");

            } else {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.USE_FINGERPRINT) != PackageManager.PERMISSION_GRANTED) {
                    palcefinger.setText("Permission not granted to use fingerprint scanner");

                } else if (keyguardManager.isKeyguardSecure()) {
                    palcefinger.setText("Add Lock To Your Phone In Settings");

                } else {
                    if (fingerprintManager.hasEnrolledFingerprints()) {

                        palcefinger.setText("YOuShould Add At Least 1 FingerPrint To Use This Feature");

                    } else {

                        palcefinger.setText("Place your finger on scanner to access the app");

                        FingerprintHandler fingerprintHandler = new FingerprintHandler(this);
                        fingerprintHandler.startAuth(fingerprintManager, null);
                    }

                }
            }

        }
    }
}


