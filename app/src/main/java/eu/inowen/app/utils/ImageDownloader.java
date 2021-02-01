package eu.inowen.app.utils;


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
     * @return
     */
    public String download() {
        throw new UnsupportedOperationException("Not implemented");
    }

    /**
     * Cuts off the extension of the image/gif/other format and returns it.
     * @param url The url with an extension at the end
     * @return Extension
     */
    public String getExtension(String url) {
        String[] split = url.split(".");
        int length = split.length;
        if (length > 1) {
            return split[length-1];
        }
        return "";
    }

    public String extractIdentifier(String url) {
        String[] split = url.split("/");
        if (split.length > 1) {
            return url.split(".")[0];
        }
        return "";
    }

}
