package eu.inowen.memebot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    private Button suggestionsButton;

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

    }
}