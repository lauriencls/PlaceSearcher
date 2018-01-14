package org.miage.placesearcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceDetailsActivity extends AppCompatActivity {

    @BindView(R.id.detail_street)
    TextView mDetailStreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.place_details_layout);
        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String detailStreetInfo = getIntent().getStringExtra("detailInfoStreet");
        mDetailStreet.setText(detailStreetInfo);


    }

}
