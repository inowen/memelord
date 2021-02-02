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
        final String displayThis = "Tomato here from the other thread!";

        new Thread(new Runnable() {
            @Override
            public void run() {
                testDisplay.setText(displayThis);
            }
        }).start();

    }
}

