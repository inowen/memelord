package eu.inowen.app.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import eu.inowen.app.R;
import eu.inowen.app.utils.ImageDownloader;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        // Text on the testing activity to show whatever debug message there is to show
        final TextView testDisplay = findViewById(R.id.testDisplay);
        final String displayThis = "From my own findViewById!";

        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "https://i.redd.it/iyajyqjmg0f61.jpg";
                ImageDownloader downloader = new ImageDownloader(url, getApplicationContext().getCacheDir());
                try {
                    downloader.download();
                } catch (IOException e) {
                    testDisplay.setText("Exception from download.");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        testDisplay.setText("Done, image was downloaded.");
                    }
                });
            }
        }).start();

    }
}

