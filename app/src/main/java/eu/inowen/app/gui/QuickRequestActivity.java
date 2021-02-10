package eu.inowen.app.gui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.Serializable;

import eu.inowen.app.R;
import eu.inowen.app.reddit.ListingCategory;
import eu.inowen.app.reddit.RequestSpecification;

public class QuickRequestActivity extends AppCompatActivity {

    private static final int MIN_UPVOTES_PROGRESS_MULTIPLIER = 20;

    private Button submitButton;
    private SeekBar minUpvotesSeekBar;
    private TextView minUpvotesTextView;
    private RadioButton hotRadioBtn, newRadioBtn, topRadioBtn, risingRadioBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_request);

        // Make upvote counter textView show how many upvotes are required per the seek bar
        minUpvotesSeekBar = findViewById(R.id.min_upvotes_seekbar);
        minUpvotesTextView = findViewById(R.id.upvotes_count_textview);

        minUpvotesSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                minUpvotesTextView.setText(String.valueOf(progress*progress));
            }
            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        // Get Radio Buttons
        topRadioBtn = findViewById(R.id.top_radio_btn);
        hotRadioBtn = findViewById(R.id.hot_radio_btn);
        newRadioBtn = findViewById(R.id.new_radio_btn);
        risingRadioBtn = findViewById(R.id.rising_radio_btn);

        // Add listener to submit button
        submitButton = findViewById(R.id.quick_request_submit_btn);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub = ((EditText)findViewById(R.id.inputSubredditName)).getText().toString();
                ListingCategory cat = topRadioBtn.isChecked() ? ListingCategory.TOP :
                        hotRadioBtn.isChecked() ? ListingCategory.HOT :
                        newRadioBtn.isChecked() ? ListingCategory.NEW :
                        risingRadioBtn.isChecked() ? ListingCategory.NEW :
                        ListingCategory.RISING;
                int minUps = minUpvotesSeekBar.getProgress() * minUpvotesSeekBar.getProgress();
                RequestSpecification request = new RequestSpecification(sub, cat, minUps);

                Intent intent = new Intent(getApplicationContext(), ImageScrollActivity.class);
                intent.putExtra(RequestSpecification.class.getSimpleName(), request);
                startActivity(intent);
                finish();
            }
        });


    }

    // TODO:
    // - Make upvote counter (currently at 0) show where the bar is at
    //  (make a method that gets how many upvotes the bar is at, based on max and min values)

    // - Make submit button check which of the radio buttons is checked, and then create
    //   a RequestSpecification, launch ImageScrollActivity, and finish current one.



}