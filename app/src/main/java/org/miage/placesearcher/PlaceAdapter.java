package org.miage.placesearcher;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Laurie on 08/01/2018.
 */

public class PlaceAdapter extends ArrayAdapter<Place> {

    @BindView(R.id.textViewCity)
    TextView textViewCity;

    @BindView(R.id.textViewStreet)
    TextView textViewStreet;

    @BindView(R.id.textViewZipCode)
    TextView textViewZipCode;


    public PlaceAdapter(Context context, List<Place> listePlaces)
    {
        super(context, -1, listePlaces);
    }

    public View getView(int i, View convertView, ViewGroup parent)
    {
        View actualView = convertView;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            actualView = inflater.inflate(R.layout.place_searcher_layout, parent, false);
        }
        ButterKnife.bind(this, actualView);
        textViewCity.setText(getItem(i).getCity());
        textViewZipCode.setText(getItem(i).getZipCode());
        textViewStreet.setText(getItem(i).getStreet());

        return actualView;
    }
}
