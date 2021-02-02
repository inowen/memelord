package eu.inowen.app.testing;

import androidx.appcompat.app.AppCompatActivity;

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

        TextView testDisplay = findViewById(R.id.testDisplay);

        File file = new File(getApplicationContext().getCacheDir(), "hey.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        testDisplay.setText(file.getAbsolutePath());

    }
}


class Downloader implements Runnable {

    @Override
    public void run() {

    }

    
}
