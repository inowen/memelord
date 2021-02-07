package eu.inowen.app.gui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import eu.inowen.app.R;
import eu.inowen.app.reddit.BitmapBufferQueue;
import eu.inowen.app.reddit.ListingCategory;
import eu.inowen.app.utils.App;

public class ViewPagerAdapter extends PagerAdapter {

    private String subreddit;
    private ListingCategory listingCategory;
    private BitmapBufferQueue bitmapBufferQueue;

    Context context;
    ViewPager associatedPager;
    private ForgetfulBitmapArray dataSet = new ForgetfulBitmapArray(20);

    /**
     * ViewPagerAdapter is used to slide through the images on a subreddit.
     * @param sub Name of the subreddit
     * @param category HOT, NEW, TOP, RISING
     * @param associatedPager Reference to the pager that this adapter works with
     * @param context Application context (getApplicationContext()).
     */
    public ViewPagerAdapter(String sub, ListingCategory category, ViewPager associatedPager, Context context) {
        this.context = context;
        this.associatedPager = associatedPager;
        this.subreddit = sub;
        this.listingCategory = category;
        bitmapBufferQueue = new BitmapBufferQueue(subreddit, listingCategory, 20);
    }

    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(dataSet.get(position));
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


    /**
     * Stores bitmaps for the pager (really only stores the last couple positions, but simulates
     * a potentially much larger array where these positions are just the end of the array).
     */
    private class ForgetfulBitmapArray {
        private ArrayList<Bitmap> array;
        private int maxArraySize;
        private int offset;
        private Bitmap noMoreScrollBack = BitmapFactory.decodeResource(App.getInstance().getResources(), R.drawable.end_of_buffer); // Temporary, change the image!

        ForgetfulBitmapArray(int maxArraySize) {
            array = new ArrayList<>();
            this.maxArraySize = maxArraySize;
            offset = 0;
        }

        /**
         * Total size of the array (stored positions plus offset).
         * @return int
         */
        public int size() { return offset + array.size(); }

        /**
         * How many positions the forgetful array really holds at the end.
         * @return int
         */
        public int numCached() { return array.size(); }

        public Bitmap get(int pos) {
            if (pos<offset) {
                return noMoreScrollBack;
            }
            return array.get(pos - offset);
        }

    }

    // Updates the forgetful array
    ViewPager.OnPageChangeListener pagerDataSetUpdater = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrollStateChanged(int state) {
            int currentPosition = associatedPager.getCurrentItem();
            // If the array doesn't have enough cached yet:
            if (dataSet.numCached() < dataSet.maxArraySize) {
                while(bitmapBufferQueue.size()>0 && dataSet.numCached()<dataSet.maxArraySize) {
                    Bitmap next = bitmapBufferQueue.next();
                    if (next!=null)
                        dataSet.array.add(next);
                }
            }
            // Else, If a replacement is needed:
            else {
                Bitmap next = null;
                while(bitmapBufferQueue.size()>0 && next==null) {
                    next = bitmapBufferQueue.next();
                }
                if (next != null) {
                    dataSet.array.remove(0);
                    dataSet.array.add(next);
                }
            }
        }

        @Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
        @Override public void onPageSelected(int position) { }
    };
}
