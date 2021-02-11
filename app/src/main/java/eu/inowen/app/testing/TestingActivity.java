package eu.inowen.app.testing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
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

        Button button = new Button(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.topMargin = 10;
        params.gravity = Gravity.TOP | Gravity.CENTER_VERTICAL;
        button.setText("Dynamic button");


        GridLayout viewGroup = new GridLayout(this);
        viewGroup.setAlignmentMode(GridLayout.ALIGN_MARGINS);
        viewGroup.setColumnCount(2);
        viewGroup.setBackgroundColor(Color.GREEN);
        ScrollView scrollView = new ScrollView(this);
        viewGroup.addView(button);
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        Button btn1 = new Button(this);
        btn1.setText("twentycharacterlimit");
        viewGroup.addView(btn1);
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        Button btn2 = new Button(this);
        btn2.setText("twentycharacterlimit");
        viewGroup.addView(btn2);
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));
        viewGroup.addView(new Button(this));

        scrollView.addView(viewGroup);
        addContentView(scrollView, params);

        button.setOnClickListener(new MyOnClickListener(new RequestSpecification("memes", ListingCategory.HOT, 50)));

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
