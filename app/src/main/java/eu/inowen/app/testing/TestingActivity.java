package eu.inowen.app.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import eu.inowen.app.R;
import eu.inowen.app.reddit.SubredditIterator;
import eu.inowen.app.reddit.SubredditPageIterator.*;
import eu.inowen.app.utils.ImageDownloader;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        // Text on the testing activity to show whatever debug message there is to show
        final TextView testDisplay = findViewById(R.id.testDisplay);

        // The ImageView to test showing images
        final ImageView testImageView = findViewById(R.id.testImageView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SubredditIterator subredditIterator = new SubredditIterator("memes");

                while (subredditIterator.hasNext()) {
                    ImageDownloader imageDownloader = new ImageDownloader(subredditIterator.nextUrl(), null);

                    // Download image bitmap
                    Bitmap bitmap = null;
                    try {
                        bitmap = imageDownloader.downloadBitmap();
                    } catch(IOException e) { e.printStackTrace(); }

                    // Show bitmap on testImageView on UI thread
                    final Bitmap finalBitmap = bitmap;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (finalBitmap != null)
                                testImageView.setImageBitmap(finalBitmap);
                        }
                    });

                    try { Thread.sleep(2500); } catch (InterruptedException e) { e.printStackTrace(); }
                }

            }
        }).start();

    }
}

