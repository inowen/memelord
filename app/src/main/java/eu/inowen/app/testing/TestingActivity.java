package eu.inowen.app.testing;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.io.IOException;
import java.util.ArrayList;

import eu.inowen.app.R;
import eu.inowen.app.reddit.SubredditIterator;
import eu.inowen.app.utils.ImageDownloader;

public class TestingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testing);

        // Text on the testing activity to show whatever debug message there is to show
        final TextView testDisplay = findViewById(R.id.testDisplay);

        // The ImageView to test showing images
        final ImageView testImageView = findViewById(R.id.testImageView);

        // The ViewPager to swipe through images
        final ViewPager2 viewPager = findViewById(R.id.testViewPager);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SubredditIterator subredditIterator = new SubredditIterator("memes");

                while (subredditIterator.hasNext()) {
                    ImageDownloader imageDownloader = new ImageDownloader(subredditIterator.nextUrl(), null);

                    // Download image bitmap
                    Bitmap bitmap = null;
                    try {
                        bitmap = imageDownloader.downloadBitmap();
                    } catch(IOException e) { e.printStackTrace(); }

                    // Show bitmap on testImageView on UI thread
                    final Bitmap finalBitmap = bitmap;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (finalBitmap != null)
                                testImageView.setImageBitmap(finalBitmap);
                        }
                    });

                    try { Thread.sleep(2500); } catch (InterruptedException e) { e.printStackTrace(); }
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
        super.destroyItem(container, position, object);
    }
}

