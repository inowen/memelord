package eu.inowen.app.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import eu.inowen.app.R;

public class MainMenu extends AppCompatActivity {

    private Button suggestionsButton;
    private Button settingsButton;

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

        ImageView logo = findViewById(R.id.memeapp);
        logo.setImageDrawable(getDrawable(R.drawable.main_screen_bg));

    }
}