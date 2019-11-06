package y2019.aoc.layanz.layanzaoc2019;

import android.accessibilityservice.FingerprintGestureController;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.os.CancellationSignal;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;

    public FingerprintHandler(Context context){

        this.context = context;
    }

    public  void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();

        fingerprintManager.authenticate(cryptoObject , cancellationSignal, 0, this, null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        this.update("There was am auth error" + errString, false);

    }


    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        this.update("error"+ helpString, false);
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        this.update("you can now access the app", true);
    }


    @Override
    public void onAuthenticationFailed() {
        this.update("auth faild", false);
    }

    public  void update(String s, boolean b){

        TextView paralabel = (TextView) ((Activity)context).findViewById(R.id.palcefinger);
        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.iconfinger);

        paralabel.setText(s);

        if (b == false){

            paralabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else{

            paralabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));
            imageView.setImageResource(R.mipmap.actiondone);
        }
    }
}
