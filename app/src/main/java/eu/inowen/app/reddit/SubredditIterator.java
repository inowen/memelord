package eu.inowen.app.reddit;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Iterate over the posts in a subreddit.
 */
public class SubredditIterator {

    private SubredditPageIterator pageIterator;
    private ArrayList<JSONObject> currentPage;
    private int index; // The index in the current page.

    /**
     * SubredditIterator iterates over the posts in a subreddit.
     * @param sub Name of the sub
     * @param pageSize How many posts per page
     * @param cat HOT, RISING, NEW or TOP
     */
    public SubredditIterator(String sub, int pageSize, ListingCategory cat) {
        pageIterator = new SubredditPageIterator(sub, pageSize, cat);
        currentPage = pageIterator.nextPage();
        index = 0;
    }

    /**
     * Iterator over the posts in a subreddit (listing: HOT, 50 posts per page).
     * @param sub Name of the subreddit
     */
    public SubredditIterator(String sub) {
        this(sub, 50, ListingCategory.HOT);
    }

    /**
     * Whether there is another post to iterate over (only false if the iterator has
     * reached the end of the sub).
     * @return boolean
     */
    public boolean hasNext() {
        return !(!pageIterator.hasNext() && index>=currentPage.size()-1);
    }

    /**
     * Get the next post object (the data object, not the kind object)
     * @return
     */
    public JSONObject nextPost() {
        index++;
        if (index >= currentPage.size()) {
            currentPage = pageIterator.nextPage();
            index = 0;
        }
        return currentPage.get(index);
    }

    public String nextUrl() {
        JSONObject post = nextPost();
        return post.optString("url");
    }

    public String nextTitle() {
        JSONObject post = nextPost();
        return post.optString("title");
    }
}
