package eu.inowen.app.gui;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class ViewPagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return false;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


    /**
     * Stores bitmaps for the pager (really only stores the last couple positions, but simulates
     * a potentially much larger array where these positions are just the end of the array).
     */
    private class ForgetfulArray {
        private ArrayList<Bitmap> array;
        private int arraySize;
        private int offset;

        ForgetfulArray(int arraySize) {
            array = new ArrayList<>();
            this.arraySize = arraySize;
            offset = 0;
        }

        public int size() { return offset + array.size(); }

    }

    private ViewPager.OnPageChangeListener update____ = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
