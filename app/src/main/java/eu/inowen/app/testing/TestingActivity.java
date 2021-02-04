package eu.inowen.app.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import eu.inowen.app.R;
import eu.inowen.app.reddit.SubredditIterator;
import eu.inowen.app.reddit.SubredditPageIterator.*;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);



        // Text on the testing activity to show whatever debug message there is to show
        final TextView testDisplay = findViewById(R.id.testDisplay);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SubredditIterator subredditIterator = new SubredditIterator("memes", 5, Category.HOT);
                while(subredditIterator.hasNext()) {
                    final String title = subredditIterator.nextTitle();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            testDisplay.setText(title);
                        }
                    });
                    try { Thread.sleep(1000); } catch(InterruptedException e) { e.printStackTrace(); }
                }
            }
        }).start();
    }
}

