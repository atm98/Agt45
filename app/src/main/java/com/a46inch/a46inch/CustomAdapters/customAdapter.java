package com.a46inch.a46inch.CustomAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a46inch.a46inch.Classes.Products;
import com.a46inch.a46inch.R;

import java.util.ArrayList;

/**
 * Created by ar265 on 7/20/2017.
 */

public class customAdapter extends BaseAdapter {
    Context c;
    ArrayList<Products> products;
    public customAdapter(Context c, ArrayList<Products> products) {
        this.c = c;
        this.products = products;
    }
    @Override
    public int getCount() {
        return products.size();
    }
    @Override
    public Object getItem(int pos) {
        return products.get(pos);
    }
    @Override
    public long getItemId(int pos) {
        return pos;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null) {

            convertView = LayoutInflater.from(c).inflate(R.layout.model, viewGroup, false);
            Log.d("if","contentviewnull");

        }
        TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTxt);
        TextView priceTxt = (TextView) convertView.findViewById(R.id.priceTxt);
        final Products s= (Products) this.getItem(position);
        nameTxt.setText(s.getPname());
        priceTxt.setText(s.getPprice());
        return convertView;
    }

}
