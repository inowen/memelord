package eu.inowen.app.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import eu.inowen.app.R;
import eu.inowen.app.utils.ImageDownloader;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        TextView testDisplay = findViewById(R.id.testDisplay);

        String url = "https://i.redd.it/vssspzxvdue61.jpg";
        ImageDownloader downloader = new ImageDownloader(url, "data/data/eu.inowen.memebot/cache");

    }
}
