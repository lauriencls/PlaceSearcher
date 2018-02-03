package org.miage.placesearcher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlaceDetailsActivity extends AppCompatActivity {

    private static final int SELECT_PHOTO = 1;
    private Uri uriImageSelected;

    @BindView(R.id.detail_street)
    TextView mDetailStreet;

    @BindView(R.id.button_google_search)
    Button mButtonGoogleSearch;

    @BindView(R.id.button_share)
    Button mButtonShare;

    @BindView(R.id.button_choose_image)
    Button mButtonChooseImage;

    @BindView(R.id.button_share_image)
    Button mButtonShareImage;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.imageView)
    ImageView mImageView;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.place_details_layout);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        String detailStreetInfo = getIntent().getStringExtra("detailInfoStreet");
        mDetailStreet.setText(detailStreetInfo);

        /** Action quand on clique sur l'intitulé de la rue */
        mDetailStreet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /** Première manière **/
                PlaceDetailsActivity.this.finish();

                /** Seconde manière
                 * Intent mainActivityIntent = new Intent(PlaceDetailsActivity.this, MainActivity.class);
                 * startActivity(mainActivityIntent);
                 */
            }
        });


        /** Action quand on clique sur "Chercher sur Google" */
        mButtonGoogleSearch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /** Lancement recherche de la rue dans Google */
                Uri url = Uri.parse("http://www.google.fr/search?q=" + mDetailStreet.getText());
                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, url);
                startActivity(launchBrowser);
            }
        });


        /** Action quand on clique sur "Partager" */
        mButtonShare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /** Lancement des options de partage */
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_TEXT, "Adresse : " + mDetailStreet.getText());
                shareIntent.setType("text/plain");
                startActivity(shareIntent);
            }

        });

        /** Action quand on clique sur "Choisir image" */
        mButtonChooseImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /** Lancement du choix d'une image */
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, SELECT_PHOTO);
            }
        });

        /** Action quand on clique sur "Partager image" */
        mButtonShareImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /** Lancement des options de partage */
                Intent shareIntent = new Intent();
                shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.putExtra(Intent.EXTRA_STREAM, uriImageSelected);
                shareIntent.setType("image/jpeg");
                startActivity(shareIntent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent)
    {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        switch(requestCode)
        {
            case SELECT_PHOTO:
                if(resultCode == RESULT_OK)
                {
                    uriImageSelected = imageReturnedIntent.getData();
                    mImageView.setImageURI(uriImageSelected);
                }
        }
    }
}