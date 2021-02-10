package eu.inowen.app.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import eu.inowen.app.R;

public class QuickRequestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_request);
    }

    // TODO:
    // - Make upvote counter (currently at 0) show where the bar is at
    //  (make a method that gets how many upvotes the bar is at, based on max and min values)

    // - Make submit button check which of the radio buttons is checked, and then create
    //   a RequestSpecification, launch ImageScrollActivity, and finish current one.
}