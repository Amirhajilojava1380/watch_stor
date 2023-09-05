package com.example.myapplication.Golobol;

import android.widget.Filter;

import com.example.myapplication.Adapter.Search_Adapter;
import com.example.myapplication.Model.OffAmazing;

import java.util.ArrayList;
import java.util.List;

public class FilterSearch extends Filter {
    Search_Adapter search_adapter;
    List< OffAmazing > data;

    public FilterSearch ( Search_Adapter search_adapter , List< OffAmazing > data ) {
        this.search_adapter = search_adapter;
        this.data = data;
    }

    @Override
    protected FilterResults performFiltering ( CharSequence constraint ) {
        FilterResults results = new FilterResults ();
        if ( constraint!=null&&constraint.length ()>0 ){
            constraint =constraint.toString ().toUpperCase ();
            List<OffAmazing> data_filter =new ArrayList<> (  );

            for ( int i=0;i< data.size ( );i++ ){
                if ( data.get ( i ).getName ().toUpperCase().contains ( constraint ) ){

                    data_filter.add ( data.get ( i ) );

                }
            }

            results.count =data_filter.size();
            results.values =data_filter;
        }else {

            results.count =data.size();
            results.values =data;

        }



        return results;
    }

    @Override
    protected void publishResults ( CharSequence constraint , FilterResults results ) {
        search_adapter.data =(List<OffAmazing>)results.values;
        search_adapter.notifyDataSetChanged();
    }
}
