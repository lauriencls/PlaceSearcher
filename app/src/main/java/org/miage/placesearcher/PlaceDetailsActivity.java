package org.miage.placesearcher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceDetailsActivity extends AppCompatActivity {

    @BindView(R.id.detail_street)
    TextView mDetailStreet;

    @BindView(R.id.button_google_search)
    Button mButtonGoogleSearch;

    @BindView(R.id.button_share)
    Button mButtonShare;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.place_details_layout);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        String detailStreetInfo = getIntent().getStringExtra("detailInfoStreet");
        mDetailStreet.setText(detailStreetInfo);

        /** Action quand on clique sur l'intitulé de la rue */
        mDetailStreet.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                /** Première manière **/
                PlaceDetailsActivity.this.finish();

                /** Seconde manière
                 * Intent mainActivityIntent = new Intent(PlaceDetailsActivity.this, MainActivity.class);
                 * startActivity(mainActivityIntent);
                 */
            }
        });


        /** Action quand on clique sur "Chercher sur Google" */
        mButtonGoogleSearch.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Log.e("LOG", "Avant le Google");

                /** Lancement recherche de la rue dans Google */
                Uri url = Uri.parse("http://www.google.fr/search?q="+mDetailStreet.getText());
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, url);
                startActivity(launchBrowser);
            }
        });

    }

}
