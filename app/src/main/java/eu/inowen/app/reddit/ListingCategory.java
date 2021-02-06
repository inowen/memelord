package eu.inowen.app.reddit;

/**
 * Which listing: hot, new, rising, top
 * (values are HOT, NEW, RISING, TOP)
 */
public enum ListingCategory {
    HOT("hot"),
    NEW("new"),
    RISING("rising"),
    TOP("top");

    private String stringVal; // hot, new, rising, top
    ListingCategory(String stringVal) { this.stringVal = stringVal; }

    @Override
    public String toString() { return stringVal; }
}