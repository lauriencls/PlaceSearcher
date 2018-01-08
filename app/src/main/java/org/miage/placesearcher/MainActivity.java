package org.miage.placesearcher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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


        List<Place> listItems = new ArrayList<Place>();
        for(int i=1;i<50;i++)
        {
            listItems.add(new Place("Street"+i, "NANTES", "44000"));
        }
        ArrayAdapter adapter = new PlaceAdapter(this, listItems);
        mListView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }



}
