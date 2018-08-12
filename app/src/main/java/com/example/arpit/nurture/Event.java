package com.example.arpit.nurture;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Event extends Fragment {


    public Event() {
        // Required empty public constructor
    }

    ListView listView;
    TextView name, DayDatetime, place, aboutEvent;
    ImageView eventImage;
    String NameOfEvent[] = {"Waste Management", "Reduce Noise Pollution", "River Safety", "Green Eve", "t"};
    String TimeOfEvent[] = {"ThursDay 07-Aug-2018 9:00 AM", "FriDay 17-Aug-2018 9:45 AM", "SunDay 20-Aug-2018 10:00 AM", "t", ""};
    String Place[] = {"Rajendra Place", "Mayur Vihar", "Lakshmi Nagar", "Subhash Nagar", "t"};
    String AboutEvent[] = {"Waste Management", "Reduce Noise Pollution", "River Safety", "Event to promote plantation", "t"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_event, container, false);


        listView = v.findViewById(R.id.List);

        return v;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        MyAdapter adapter = new MyAdapter(getActivity());

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Object value = listView.getAdapter().getItem(position);
                if (position == 0) {

                } else if (position == 1) {

                } else if (position == 2) {

                } else if (position == 3) {

                }

            }

        });
    }

    class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(@NonNull Context context) {
            super(context, R.layout.event_layout, NameOfEvent);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();

            Integer pos = position;

            View V = (View) inflater.inflate(R.layout.event_layout, parent, false);
            name = V.findViewById(R.id.Eventname);
            DayDatetime = (TextView) V.findViewById(R.id.DayDatetime);
            place = (TextView) V.findViewById(R.id.eventPlace);
            aboutEvent = (TextView) V.findViewById(R.id.AboutEvent);
            ImageView eventImage = (ImageView) V.findViewById(R.id.EventImage);
            name.setText(NameOfEvent[position]);
            Log.i("positon", pos.toString());
            DayDatetime.setText(TimeOfEvent[position]);
            place.setText(Place[position]);
            aboutEvent.setText(AboutEvent[position]);

            ArrayList<Integer> itemsimg = new ArrayList<>();
            itemsimg.add(R.drawable.waste);
            itemsimg.add(R.drawable.noise);
            itemsimg.add(R.drawable.river);
            itemsimg.add(R.drawable.green);
            itemsimg.add(R.drawable.green);

            eventImage.setImageResource(itemsimg.get(position));

            return V;

        }
    }
}