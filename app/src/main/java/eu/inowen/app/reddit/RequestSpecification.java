package eu.inowen.app.reddit;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class RequestSpecification implements Serializable {

    private String subName;
    private ListingCategory listingCategory;
    private int minUpvotes;
    private boolean downloadPinned = false;
    private ArrayList<String> validExtensions = new ArrayList<>(Arrays.asList(".png", ".jpg"));

    /**
     * A RequestSpecification contains the parameters that are used to choose which images
     * to download and from where.
     * @param subName Name of the subreddit from where the images should be downloaded.
     * @param listingCategory HOT, NEW, RISING, TOP
     * @param minUpvotes Min number of upvotes to qualify for download
     */
    public RequestSpecification(String subName, ListingCategory listingCategory, int minUpvotes) {
        this.subName = subName;
        this.listingCategory = listingCategory;
        this.minUpvotes = minUpvotes;
    }

    /**
     * A RequestSpecification contains the parameters that are used to choose which images to download
     * and from where. Default for minUpvotes: 0 (no filter).
     * @param subName Name of the subreddit from where the images should be downloaded
     * @param listingCategory HOT, NEW, RISING, TOP
     */
    public RequestSpecification(String subName, ListingCategory listingCategory) {
        this(subName, listingCategory, 0);
    }

    /**
     * Check whether a post qualifies for download based on this specific request.
     * Receives a JSONObject, will check url format, upvotes, etc.
     * @param post JSONObject
     * @return boolean
     */
    public boolean matches(JSONObject post) throws JSONException {
        boolean urlOk = hasValidExtension(post.getString("url"));
        boolean upvotesOk = post.getInt("ups") >= minUpvotes;
        boolean pinnedStatusOk = !post.getBoolean("pinned") || downloadPinned;
        return urlOk && upvotesOk && pinnedStatusOk;
    }

    /**
     * Add an extension to the list of extensions that are acceptable to download an image
     * (by default the list only contains .jpg and .png).
     * @param extension
     */
    public void addValidExtension(String extension) {
        extension = optAddExtensionPoint(extension);
        validExtensions.add(extension);
    }

    public String getSubName() { return subName; }
    public ListingCategory getListingCategory() { return listingCategory; }
    public int getMinUpvotes() { return minUpvotes; }

    /**
     * Whether the given image url has an extension that matches one of the valid extensions
     * for this request.
     * @param url
     * @return boolean
     */
    public boolean hasValidExtension(String url) {
        String[] split = url.split("\\.");
        return isValidExtension(split[split.length-1]);
    }


    /**
     * Whether the given extension (with or without point) marks an image that might be downloaded.
     * @param extension
     * @return
     */
    public boolean isValidExtension(String extension) {
        return validExtensions.contains(optAddExtensionPoint(extension));
    }

    /**
     * By default a pinned post does not qualify for download. Can be changed here.
     * @param allowDownloadingPinned
     */
    public void setAllowDownloadingPinned(boolean allowDownloadingPinned) {
        this.downloadPinned = allowDownloadingPinned;
    }

    private String optAddExtensionPoint(String extension) {
        if (extension!="" && extension.charAt(0)!='.')
            extension = "." + extension;
        return extension;
    }

}
