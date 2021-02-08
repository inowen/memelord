package eu.inowen.app.reddit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import eu.inowen.app.R;
import eu.inowen.app.gui.MainMenu;
import eu.inowen.app.utils.App;
import eu.inowen.app.utils.ImageDownloader;

/**
 * Takes a SubredditIterator and a desired size for the cache queue.
 * When the end is reached, it just returns the noMoreImages Bitmap.
 */
public class BitmapBufferQueue {

    private Queue<Bitmap> buffer = new LinkedList<>();
    private int maxSize;
    private int halfSize;
    private Bitmap noMoreImages;

    private RequestSpecification requestSpecification;
    private SubredditIterator subredditIterator = null; // Access only from non-ui threads
    private Thread downloaderThread;

    /**
     * A BitmapBufferQueue downloads images from the posts in a subreddit whose urls can
     * be decoded into images, and stores them in a buffer.
     * @param requestSpecs RequestSpecification for where images should be downloaded and
     *                     which criteria they should meet.
     * @param cacheSize How long the cache queue can become.
     */
    public BitmapBufferQueue(RequestSpecification requestSpecs, int cacheSize) {
        this.requestSpecification = requestSpecs;
        maxSize = Math.max(cacheSize, 2);
        halfSize = maxSize/2;
        noMoreImages = BitmapFactory.decodeResource(App.getInstance().getResources(), R.drawable.end_of_buffer);
        // Create the thread that fills the cache
        downloaderThread = new Thread(new RefillCache());
        downloaderThread.start();
    }

    /**
     * The next bitmap in the buffer (or null if empty).
     * Also launches the thread to refill the cache if less than half the size is used.
     * @return Bitmap
     */
    public synchronized Bitmap next() {
        if (size()<halfSize) {
            if (!downloaderThread.isAlive()) {
                downloaderThread = new Thread(new RefillCache());
                downloaderThread.start();
            }
        }
        return buffer.poll();
    }

    /**
     * Number of elements currently in the buffer.
     * @return int
     */
    public synchronized int size() {
        return buffer.size();
    }

    // Add an element to the bitmap buffer
    private synchronized void addToBuffer(Bitmap bitmap) {
        buffer.add(bitmap);
    }

    // The downloading thread runs this to refill the queue.
    private class RefillCache implements Runnable {
        @Override
        public void run() {
            initializeIterator();
            while(buffer.size()<maxSize) {
                Bitmap addMe = null;
                if (subredditIterator.hasNext()) {
                    try {
                        JSONObject post = subredditIterator.nextPost();
                        if (requestSpecification.matches(post)) {
                            addMe = new ImageDownloader(post.getString("url"), new File("")).downloadBitmap();
                        }
                    } catch (Exception ignored) { }
                }
                else { addMe = noMoreImages; }

                if (addMe != null)
                    if (addMe.getAllocationByteCount() <= (5<<20))
                        addToBuffer(addMe);
            }
        }

        // If the iterator isn't already created, do that
        private void initializeIterator() {
            if (subredditIterator == null) {
                subredditIterator = new SubredditIterator(requestSpecification, 50);
            }
        }
    }
}
