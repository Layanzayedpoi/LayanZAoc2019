package y2019.aoc.layanz.layanzaoc2019;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

public class ReadMyMessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_my_mess);
    }

    public static final String INBOX = "content://sms/inbox";
    public static final String SENT = "content://sms/sent";
    public static final String DRAFT = "content://sms/draft";
    Cursor cursor = getContentResolver().query(Uri.parse("content://sms/inbox"), null, null, null, null);

if (cursor.moveToFirst()) { // must check the result to prevent exception
        do {
            String msgData = "";
            for(int idx=0;idx<cursor.getColumnCount();idx++)
            {
                msgData += " " + cursor.getColumnName(idx) + ":" + cursor.getString(idx);
            }
            // use msgData
        } while (cursor.moveToNext());
    } else {
        // empty box, no SMS
    }
    Please add READ_SMS p




        }