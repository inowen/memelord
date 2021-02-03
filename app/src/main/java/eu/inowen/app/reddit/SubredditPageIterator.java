package eu.inowen.app.reddit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Iterator to page through a certain subreddit (Reddit).
 */
public class SubredditPageIterator {

    private static final String URL_PREFIX = "https://www.reddit.com/r/";

    private String sub;
    private String category;
    private String limit;
    private String nextAfter = null;
    private boolean afterInitiated = false;

    /**
     * SubredditPageIterator pages through a subreddit, page by page.
     * @param sub The name of the sub
     * @param postsPerPage Limit to how many posts should be on each page
     * @param cat One of the following: HOT, RISING, NEW, TOP
     */
    public SubredditPageIterator(String sub, int postsPerPage, Category cat) {
        this.sub = sub;
        this.limit = String.valueOf(postsPerPage);
        this.category = cat.toString();
    }

    /**
     * Default value for Category: HOT
     * @param sub
     * @param postsPerPage
     */
    public SubredditPageIterator(String sub, int postsPerPage) {
        this(sub, postsPerPage, Category.HOT);
    }

    /**
     * Default value for category: HOT, default value for postsPerPage: 50
     * @param sub
     */
    public SubredditPageIterator(String sub) {
        this(sub, 50);
    }

    public ArrayList<JSONObject> nextPage() {
        ArrayList<JSONObject> page = new ArrayList<>();
        // If it's at the last page, return an empty page.
        if (!this.hasNext()) {
            return page;
        }

        // Get a URL for the next page
        URL url = null;
        try {
            url = new URL(getFormattedURLString());
        }
        catch (MalformedURLException e) {  e.printStackTrace(); }

        // Create a connection and set properties
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "MemeLoader for Reddit application here");
        }
        catch (IOException e) { e.printStackTrace(); }

        // Connect and try until it works
        int response = -1;
        do {
            try {
                connection.connect();
                response = connection.getResponseCode();
            }
            catch (IOException e) { e.printStackTrace(); }
        } while (response != HttpURLConnection.HTTP_OK);

        // Read from the connection and leave everything in a string
        String connectionContent = "";
        try {
            Scanner scanner = new Scanner(connection.getInputStream());
            while(scanner.hasNext()) { connectionContent += scanner.nextLine(); }
        }
        catch (IOException e) { e.printStackTrace(); }

        // Convert string to JSONObject
        JSONObject pageJson = null;
        try {
            pageJson = new JSONObject(connectionContent);
        }
        catch (JSONException e) { e.printStackTrace(); }

        // Set nextPage again for the next advancement
        try {
            if (pageJson.getJSONObject("data").isNull("after"))
                nextAfter = null;
            else
                nextAfter = pageJson.getJSONObject("data").getString("after");
        }
        catch (JSONException e) { e.printStackTrace(); }
        afterInitiated = true;

        // Add all the elements in the array to the page list
        try {
            JSONArray posts = pageJson.getJSONObject("data").getJSONArray("children");
            for (int i=0; i<posts.length(); ++i) {
                page.add(posts.getJSONObject(i).getJSONObject("data"));
            }
        }
        catch (JSONException e) { e.printStackTrace(); }


        return page;
    }

    /**
     * Whether the end of the subreddit was reached.
     * @return boolean
     */
    public boolean hasNext() {
        return (!afterInitiated || nextAfter!=null);
    }

    private String getFormattedURLString() {
        String url = URL_PREFIX + sub + "/" + category + "/.json?"
                + "limit=" + limit;

        if (afterInitiated) {
            url += "&after=" + nextAfter;
        }

        return url;
    }


    /**
     * Which listing: hot, new, rising, top
     * (values are HOT, NEW, RISING, TOP)
     */
    public static enum Category {
        HOT("hot"),
        NEW("new"),
        RISING("rising"),
        TOP("top");

        private String stringVal; // hot, new, rising, top
        Category(String stringVal) { this.stringVal = stringVal; }

        @Override
        public String toString() { return stringVal; }
    }
}
