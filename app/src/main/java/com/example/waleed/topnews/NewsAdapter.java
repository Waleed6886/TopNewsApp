package com.example.waleed.topnews;

/**
 * Created by Waleed on 10/05/17.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by Waleed on 05/05/17.
 */

public class NewsAdapter extends ArrayAdapter<News> {


    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent , false);
        }
        // Get the News object located at this position in the list
        News currentNews = getItem(position);
        // Find the TextView in the list_item.xml layout with the ID title_name
        TextView titleTextView = (TextView) listItemView.findViewById(R.id.title);
        // Get the news name from the current News object and
        // set this text on the name TextView
        titleTextView.setText(currentNews.getTitle());

        // Find the TextView in the list_item.xml layout with the ID section_name
        TextView sectionNameTextView = (TextView) listItemView.findViewById(R.id.section_name);
        // Get the section name from the current News object and
        // set this text on the name TextView
        sectionNameTextView.setText(currentNews.getSectionName());

        // Find the TextView in the list_item.xml layout with the ID section_name
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.date);

        dateTextView.setText(currentNews.getPublicationDate().replace('T',' ').replace('Z',' ').replace(" ",System.getProperty("line.separator")));

        return listItemView;
    }


}
