package eu.inowen.app.gui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;

import eu.inowen.app.R;
import eu.inowen.app.reddit.ListingCategory;
import eu.inowen.app.reddit.RequestSpecification;

public class SuggestionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        String[] suggestions = getResources().getStringArray(R.array.suggestions);

        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(2);
        gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

        for (String suggestion : suggestions) {
            Button currentBtn = new Button(this);
            RequestSpecification request = new RequestSpecification(suggestion, ListingCategory.HOT, 500);
            currentBtn.setText(suggestion);
            currentBtn.setOnClickListener(new ClickListener(request));
            gridLayout.addView(currentBtn);
        }

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(gridLayout);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.WRAP_CONTENT,
                FrameLayout.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.TOP | Gravity.CENTER_VERTICAL;
        params.topMargin = 150;

        addContentView(scrollView, params);
    }


    private class ClickListener implements View.OnClickListener {
        private RequestSpecification request;
        private ClickListener(RequestSpecification req) {
            request = req;
        }
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), ImageScrollActivity.class);
            intent.putExtra(RequestSpecification.class.getSimpleName(), request);
            startActivity(intent);
            finish();
        }
    }
}
