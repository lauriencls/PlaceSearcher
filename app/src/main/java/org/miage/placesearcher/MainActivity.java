package org.miage.placesearcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ratingBar) RatingBar ratingBar;
    @BindView(R.id.textView) TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ratingBar.setRating(ratingBar.getRating() - 1);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.ratingBar.setRating(this.ratingBar.getRating() + 1);
    }



}
