package eu.inowen.app.gui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import eu.inowen.app.R;
import eu.inowen.app.reddit.RequestSpecification;

/**
 * Receives a RequestSpecification through a Bundle added to the intent.
 */
public class ImageScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_scroll);

        Bundle bundle = getIntent().getExtras();
        // Force parameters, otherwise the activity won't open.
        if (bundle == null) {
            finish();
        }
        RequestSpecification requestSpecification = (RequestSpecification) bundle.get(RequestSpecification.class.getSimpleName());

        ViewPager pager = findViewById(R.id.imageScrollViewPager);
        ImageViewPagerAdapter adapter = new ImageViewPagerAdapter(requestSpecification, pager, getApplicationContext());
        pager.setAdapter(adapter);

    }
}