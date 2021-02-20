package eu.inowen.app.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.File;
import java.util.ArrayList;

import eu.inowen.app.R;
import eu.inowen.app.reddit.RequestSpecification;

public class FavoritesActivity extends AppCompatActivity {

    // A list of favorites is stored in a textfile in the internal storage of the application:
    // - One line per entry

    public static final String FAVORITES_TXT = "favorites.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

    }








    private File getFavoritesFile() {
        return new File(getFilesDir(), FAVORITES_TXT);
    }
}





/*
 Big scroll view that covers half three quarters of the screen, with one column of premades

 Below that, a button to add a new favorite and some option to delete favs


 */