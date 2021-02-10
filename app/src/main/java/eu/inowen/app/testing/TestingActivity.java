package eu.inowen.app.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.io.Serializable;

import eu.inowen.app.R;
import eu.inowen.app.gui.ImageScrollActivity;
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

        // TESTING ADDING STUFF TO HORIZONTAL SCROLL VIEW
        HorizontalScrollView hsv = findViewById(R.id.test_horizontal_scroll_view);


    }

    class MyOnClickListener implements View.OnClickListener {
        private RequestSpecification requestSpecification;
        public MyOnClickListener(RequestSpecification requestSpecification) {
            this.requestSpecification = requestSpecification;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ImageScrollActivity.class);
            intent.putExtra(RequestSpecification.class.getSimpleName(), requestSpecification);
            startActivity(intent);
            finish();
        }
    }
}
