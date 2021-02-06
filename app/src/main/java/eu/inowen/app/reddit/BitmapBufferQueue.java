package eu.inowen.app.reddit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;

import java.io.File;
import java.io.IOException;
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

    private SubredditIterator subredditIterator;
    private Queue<Bitmap> buffer;
    private int maxSize;
    private int halfSize;
    private Thread downloaderThread;
    private Bitmap noMoreImages;

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
        noMoreImages = BitmapFactory.decodeResource(App.getInstance().getResources(), R.drawable.end_of_buffer);
        // Create the thread that fills the cache
        downloaderThread = new Thread(new RefillCache());
        downloaderThread.start();
    }

    /**
     * The next bitmap in the buffer (or null if empty).
     * @return Bitmap
     */
    public synchronized Bitmap next() { // Make this launch the refilling thread?
        if (size()<halfSize) {
            if (!downloaderThread.isAlive()) {
                downloaderThread = new Thread(new RefillCache());
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
            while(size() < maxSize) {
                // Download image as bitmap
                ImageDownloader downloader = new ImageDownloader(subredditIterator.nextUrl(), new File(""));
                Bitmap bm = null;
                try { bm = downloader.downloadBitmap(); } catch (IOException e) { e.printStackTrace(); }
                // Add to buffer
                if (bm != null) {
                    addToBuffer(bm);
                }
            }
            System.out.println("Debug: Refill thread finished");
        }
    }
}
