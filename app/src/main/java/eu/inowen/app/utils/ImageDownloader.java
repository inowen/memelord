package eu.inowen.app.utils;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
     * @param destinationFolder Folder where the image is supposed to end up
     */
    public ImageDownloader(String url, File destinationFolder) {
        urlStr = url;
        String fileName = extractIdentifier(url) + getExtension(url);
        destination = new File(destinationFolder, fileName);
    }

    /**
     * By default the name for the file to which the image is downloaded is taken
     * from the url. This changes that name.
     * @param newName
     */
    public void setName(String newName) {
        destination = new File(destination.getParentFile(), newName);
    }

    /**
     * Download the image
     * @throws IOException
     * @return Path to the name of the image in the filesystem
     */
    public String download() throws IOException {
        URL url = new URL(urlStr);
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        // Write everything in the input stream to the output stream
        int n; byte[] buf = new byte[1024];
        while(-1!=(n=in.read(buf)))
            out.write(buf, 0, n);
        in.close();
        out.close();
        byte[] imageByteArray = out.toByteArray();

        // Write to the destination file
        destination.createNewFile();
        FileOutputStream fos = new FileOutputStream(destination);
        fos.write(imageByteArray);
        fos.close();

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
