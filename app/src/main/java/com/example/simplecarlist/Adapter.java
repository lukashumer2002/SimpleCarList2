package com.example.simplecarlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<Logic>{
    private Context context;

    private List<Logic> list = new ArrayList<>();

    public Adapter(Context context, int textViewResourceId, List<Logic> objects) {
        super(context, textViewResourceId, objects);
        this.context=context;
        this.list = objects;
    }


    public View getView(int position, View convertView, ViewGroup parent){

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.textvies, null);
        }
        Logic currentLogic = list.get(position);

        if (currentLogic != null) {

            TextView t1 = (TextView) v.findViewById(R.id.textView);
            t1.setText(currentLogic.getFirstName());

            TextView t2 = (TextView) v.findViewById(R.id.textView2);
            t2.setText(currentLogic.getLastName());

            TextView t3 = (TextView) v.findViewById(R.id.textView3);
            t3.setText(currentLogic.getMake());

            TextView t4 = (TextView) v.findViewById(R.id.textView4);
            t4.setText(currentLogic.getModel());

        }

        return v;

    }

}