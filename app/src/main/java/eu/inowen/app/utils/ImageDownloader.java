package eu.inowen.app.utils;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Helper to download something from the web via its url.
 */
public class ImageDownloader {

    private String urlStr;
    private File destination;

    /**
     * Initialize the destination where the image is downloaded to, add extension, etc
     * @param url The URL where the image is located on the internet
     * @param pathToFolder Folder where the image is supposed to end up
     */
    public ImageDownloader(String url, File destinationFolder) {
        urlStr = url;
        String fileName = extractIdentifier(url) + getExtension(url);
        destination = new File(destinationFolder, fileName);
    }

    /**
     * Download the image
     * @return Path to the name of the image in the filesystem
     */
    public String download() {
        return destination.getAbsolutePath();
    }

    /**
     * Cuts off the extension of the image/gif/other format and returns it.
     * @param url The url with an extension at the end
     * @return Extension
     */
    private String getExtension(String url) {
        String[] split = url.split("\\.");
        int length = split.length;
        if (length > 1) {
            return "." + split[length-1];
        }
        return "";
    }

    /**
     * Takes a unique identifier from a url, to temporarily
     * store an image with that name without collisions.
     * @param url
     * @return
     */
    private String extractIdentifier(String url) {
        String[] split = url.split("/");
        if (split.length > 1) {
            return split[split.length-1].split("\\.")[0];
        }
        return "";
    }

}
