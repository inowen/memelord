package eu.inowen.app.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import eu.inowen.app.R;
import eu.inowen.app.reddit.SubredditPageIterator;
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
                SubredditPageIterator pageIterator = new SubredditPageIterator("memes", 5, Category.RISING);
                int numPage = 0;
                while(pageIterator.hasNext()) {
                    ArrayList<JSONObject> currentPage = pageIterator.nextPage();

                    for (JSONObject post : currentPage) {
                        String title = "";
                        try {
                            title = post.getString("title");
                            final String finalTitle = title;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    testDisplay.setText(finalTitle);
                                }
                            });
                            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
                        }
                        catch(final JSONException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    testDisplay.setText(e.toString());
                                }
                            });
                        }
                    }
                }

                testDisplay.setText("Done, no more on memes");
            }
        }).start();

    }
}

