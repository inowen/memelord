package eu.inowen.app.reddit;

/**
 * Iterator to page through a certain subreddit (Reddit).
 */
public class SubredditPageIterator {

    private static final String URL_PREFIX = "https://www.reddit.com/r/";

    private String sub;
    private String category;
    private String limit;
    private String nextPage = null;

    public SubredditPageIterator(String sub, int postsPerPage, Category cat) {
        this.sub = sub;
        this.limit = String.valueOf(postsPerPage);
        this.category = cat.toString();
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
