package eu.inowen.app.testing;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import eu.inowen.app.R;
import eu.inowen.app.gui.ImageViewPagerAdapter;
import eu.inowen.app.reddit.ListingCategory;
import eu.inowen.app.reddit.RequestSpecification;

public class TestingActivity extends AppCompatActivity {

    int lastPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        // Text on the testing activity to show whatever debug message there is to show
        final TextView testDisplay = findViewById(R.id.testDisplay);

        // The ImageView to test showing images
        final ImageView testImageView = findViewById(R.id.testImageView);

        ViewPager pager = findViewById(R.id.testViewPager);
        RequestSpecification memesHot = new RequestSpecification("programmerhumor", ListingCategory.HOT, 1000);
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(memesHot, pager, getApplicationContext());
        pager.setAdapter(adapter);

    }
}
