package org.miage.placesearcher;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.squareup.otto.Subscribe;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mListView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final List<Place> listItems = new ArrayList<Place>();
        for(int i=1;i<50;i++)
        {
            listItems.add(new Place("Street"+i, "NANTES", "44000"));
        }
        ArrayAdapter adapter = new PlaceAdapter(this, listItems);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                /** Lancement d'un son au clic d'un élément */
                MediaPlayer player;
                try {
                    AssetFileDescriptor afd = getAssets().openFd("sonnette.mp3");
                    player = new MediaPlayer();
                    player.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    player.prepare();
                    player.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                /** Lancement de l'activité - Affichage du détail de l'élément
                 * On envoie la rue de l'élément cliqué à l'activité qu'on lance
                 */
                Intent seePlaceDetailsIntent = new Intent(MainActivity.this, PlaceDetailsActivity.class);
                /** Trouver comment récupérer la rue de l'item sur lequel on a cliqué */
                seePlaceDetailsIntent.putExtra("detailInfoStreet", listItems.get(position).getStreet());
                startActivity(seePlaceDetailsIntent);
            }
        });
    }

    @Subscribe
    public void searchResult(final SearchResultEvent event)
    {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                /** Mise à jour de la liste des place */
                ArrayAdapter adapter = new PlaceAdapter(MainActivity.this, event.getPlaces());
                mListView.setAdapter(adapter);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBusManager.BUS.register(this);
        PlaceSearchService.INSTANCE.searchPlacesFromAddress("Place du commerce");


    }

    @Override
    protected void onPause(){
        EventBusManager.BUS.unregister(this);
        super.onPause();
    }



}
