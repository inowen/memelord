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
}
