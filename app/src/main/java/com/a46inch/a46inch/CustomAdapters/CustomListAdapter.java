package com.a46inch.a46inch.CustomAdapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.a46inch.a46inch.Classes.FAQ;
import com.a46inch.a46inch.R;

import java.util.List;

/**
 * Created by ar265 on 6/21/2017.
 */

public class CustomListAdapter extends BaseAdapter{
    private Context context;
    private List<FAQ> faqList;

    public CustomListAdapter(Context contxt, List<FAQ> faqlist) {
        this.context = contxt;
        this.faqList = faqlist;
    }

    @Override
    public int getCount() {
        return faqList.size();
    }

    @Override
    public Object getItem(int position) {
        return faqList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.faqlistview, null);
        TextView QuesTxt = (TextView)v.findViewById(R.id.questxt);
        TextView AnsTxt = (TextView)v.findViewById(R.id.anstxt);
        QuesTxt.setText(faqList.get(position).getQuestion());
        AnsTxt.setText(faqList.get(position).getAnswer());
        return v;
    }
}
