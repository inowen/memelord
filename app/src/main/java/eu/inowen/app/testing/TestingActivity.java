package eu.inowen.app.testing;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.io.IOException;
import java.util.ArrayList;

import eu.inowen.app.R;
import eu.inowen.app.gui.ViewPagerAdapter;
import eu.inowen.app.reddit.BitmapBufferQueue;
import eu.inowen.app.reddit.ListingCategory;
import eu.inowen.app.reddit.SubredditIterator;
import eu.inowen.app.utils.ImageDownloader;

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
        ViewPagerAdapter adapter = new ViewPagerAdapter("memes", ListingCategory.HOT, pager, getApplicationContext());
        pager.setAdapter(adapter);

    }
}
