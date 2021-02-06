package eu.inowen.app.reddit;

import android.graphics.Bitmap;

/**
 * Takes a SubredditIterator and a desired size for the cache queue.
 * When the end is reached, it just returns the noMoreImages Bitmap.
 */
public class BitmapBufferQueue {

    private SubredditIterator subredditIterator;
    private int maxSize;
    private int halfSize;
    private Thread downloaderThread;
    private static Bitmap noMoreImages;

    static {

    }

    /**
     * A BitmapBufferQueue downloads images from the posts in a subreddit whose urls can
     * be decoded into images, and stores them in a buffer.
     * @param it Iterator over a subreddit.
     * @param desiredSize How long the cache queue can become.
     */
    public BitmapBufferQueue(SubredditIterator it, int desiredSize) {
        subredditIterator = it;
        maxSize = Math.max(desiredSize, 2);
        halfSize = maxSize/2;
        // Create the thread that fills the cache
    }



    public class RefillCache implements Runnable {
        @Override
        public void run() {

        }
    }
}
