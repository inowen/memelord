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

        /*

        // The ViewPager to swipe through images
        final ViewPager viewPager = findViewById(R.id.testViewPager);

        ArrayList<Bitmap> images = new ArrayList<>();
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.main_screen_bg));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background));
        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_foreground));

        ViewPagerAdapter adapter = new ViewPagerAdapter(getApplicationContext(), images);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            // This one is called whenever there is a minuscule scrolling motion (multiple times per scroll!)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                lastPosition = position;
            }

            // This one is called when the user starts or stops scrolling. Works even if it doesn't go to a new page
            @Override
            public void onPageScrollStateChanged(int state) {
                System.out.println("OnPageScrollStateChanged. Position is " + lastPosition);
                viewPager.getCurrentItem();
            }

            // This one is only called when the pager shows a new page (doesn't count scroll attempts)
            @Override
            public void onPageSelected(int position) { }
        });

        images.add(BitmapFactory.decodeResource(getResources(), R.drawable.main_screen_bg));
        adapter.notifyDataSetChanged();

        */

        final BitmapBufferQueue bufferQueue = new BitmapBufferQueue("memes", ListingCategory.HOT, 20);

        new Thread(new Runnable() {
            @Override
            public void run() {

                Bitmap bitmap = null;
                while(true) {
                    bitmap = bufferQueue.next();
                    if (bitmap != null) {
                        final Bitmap finalBitmap = bitmap;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                testImageView.setImageBitmap(finalBitmap);
                            }
                        });
                        try { Thread.sleep(2000); } catch(Exception ignored) {}
                    }
                    else {
                        System.out.println("The buffer returned null...");
                        try { Thread.sleep(1500); } catch (Exception ignored) {}
                    }
                }
            }
        }).start();

    }
}

class ViewPagerAdapter extends PagerAdapter {

    private Context context;
    private ArrayList<Bitmap> images;

    ViewPagerAdapter(Context context, ArrayList<Bitmap> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView view = new ImageView(context);
        view.setImageBitmap(images.get(position));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}

