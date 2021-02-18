package eu.inowen.app.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import eu.inowen.app.R;
import eu.inowen.app.reddit.ListingCategory;
import eu.inowen.app.reddit.RequestSpecification;
import eu.inowen.app.testing.TestingActivity;

public class MainMenu extends AppCompatActivity {

    private Button sendMemesButton;
    private Button suggestionsButton;
    private Button settingsButton;
    private Button goTestPageButton;
    private Button quickRequestButton;
    private Button favoritesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        // Enable suggestions button to show suggestions screen
        suggestionsButton = findViewById(R.id.suggestions_btn);
        suggestionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SuggestionsActivity.class);
                startActivity(intent);
            }
        });


        // Make settings button show settings screen
        settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        // Bind the button that leads to the testing screen
        goTestPageButton = findViewById(R.id.testButton);
        goTestPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestingActivity.class);
                startActivity(intent);
            }
        });

        // Add listener to Send Memes button
        sendMemesButton = findViewById(R.id.send_memes_btn);
        sendMemesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ImageScrollActivity.class);
                RequestSpecification requestSpecification = new RequestSpecification("memes", ListingCategory.HOT, 1000);
                Bundle b = new Bundle();
                b.putSerializable(RequestSpecification.class.getSimpleName(), requestSpecification);
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        // Add listener for Quick Request button to send user to quick request activity
        quickRequestButton = findViewById(R.id.quick_request_btn);
        quickRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuickRequestActivity.class);
                startActivity(intent);
            }
        });

        // Click listener for the button that leads to FavoritesActivity
        favoritesButton = findViewById(R.id.favorites_btn);
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FavoritesActivity.class));
            }
        });

    }
}