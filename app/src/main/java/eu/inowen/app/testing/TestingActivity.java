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

        Test test = new Test(downloader);
        Thread t = new Thread(test);
        while (test.getResponse() == "") { }
        testDisplay.setText("Download response: " + test.getResponse());

    }
}

class Test implements Runnable {

    private ImageDownloader imageDownloader;
    private String response = "";

    public Test(ImageDownloader im) {
        imageDownloader = im;
    }

    @Override
    public void run() {
        response = imageDownloader.download();
    }

    public String getResponse() { return response; }
}