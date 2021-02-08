package eu.inowen.app.reddit;

import java.util.ArrayList;
import java.util.Arrays;

public class RequestSpecification {

    private String subName;
    private ListingCategory listingCategory;
    private int minUpvotes;
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

    private String optAddExtensionPoint(String extension) {
        if (extension!="" && extension.charAt(0)!='.')
            extension = "." + extension;
        return extension;
    }

}
