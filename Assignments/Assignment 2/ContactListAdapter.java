package com.example.fawadbro.myapplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
public class ContactListAdapter extends ArrayAdapter<Contacts> implements Filterable{
    private ArrayList<Contacts> contacts;
    private ArrayList<Contacts> filteredcontacts;
    private Filter filter;

    public ContactListAdapter(Context context, ArrayList<Contacts> c){
        super(context,0,c);
        this.contacts = c;
    }

    @SuppressLint("ResourceType")
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Contacts contact = getItem(position);
        if(convertView == null)
        {
            LinearLayout layout = new LinearLayout(getContext());
            layout.setLayoutParams(new AbsListView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout.setOrientation(LinearLayout.HORIZONTAL);


            LinearLayout layout2 = new LinearLayout(getContext());
            layout2.setLayoutParams(new AbsListView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout2.setOrientation(LinearLayout.VERTICAL);

            TextView fname = new TextView(getContext());
            fname.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,1f));
            fname.setId(1);
            layout2.addView(fname);

            TextView title = new TextView(getContext());
            title.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            title.setId(2);
            layout2.addView(title);

            layout.addView(layout2);

            LinearLayout layout3 = new LinearLayout(getContext());
            layout3.setLayoutParams(new AbsListView.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            layout3.setOrientation(LinearLayout.VERTICAL);

            TextView text2 = new TextView(getContext());
            text2.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT));
            text2.setId(3);
            layout3.addView(text2);

            layout.addView(layout3);

            convertView = layout;
        }
        TextView text = (TextView) convertView.findViewById(1);
        text.setText(contact.name);
        TextView text2 = (TextView) convertView.findViewById(2);
        text2.setText(contact.title);
        TextView text3 = (TextView) convertView.findViewById(3);
        text3.setText(contact.lname);
        return convertView;
    }
    public Contacts getItem(int position){
        return filteredcontacts.get(position);
    }

    public int getCount() {
        return filteredcontacts.size();
    }
    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new ContactsFilter();
        }
        return filter;
    }
    private class ContactsFilter extends android.widget.Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                ArrayList<Contacts> filteredList = new ArrayList<Contacts>();
                for(int i=0; i < contacts.size(); i++){
                    if(contacts.get(i).lname.contains(constraint)){
                        filteredList.add(contacts.get(i));
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
            }
            else{
                results.count = contacts.size();
                results.values = contacts;
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filteredcontacts= (ArrayList<Contacts>) results.values;
            notifyDataSetChanged();
        }

    }
}
