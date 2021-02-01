package eu.inowen.app.utils;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * Helper to download something from the web via its url.
 */
public class ImageDownloader {

    private String urlStr;
    private String destination;

    /**
     * Initialize the destination where the image is downloaded to, add extension, etc
     * @param url The URL where the image is located on the internet
     * @param pathToFolder Folder where the image is supposed to end up
     */
    public ImageDownloader(String url, String pathToFolder) {
        urlStr = url;
        destination = pathToFolder + "/" + extractIdentifier(url) + getExtension(url);
    }

    /**
     * Download the image
     * @return Path to the name of the image in the filesystem
     */
    public String download() {
        try {
            URL url = new URL(urlStr);
            InputStream in = new BufferedInputStream(url.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1!=(n=in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();

            FileOutputStream fos = new FileOutputStream(destination);
            fos.write(response);
            fos.close();
        }
        catch (Exception e) {
            System.err.println("Exception: " + e.toString());
            e.printStackTrace();
            System.exit(-1);
        }

        return destination;
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
