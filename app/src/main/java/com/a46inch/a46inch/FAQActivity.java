package com.a46inch.a46inch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.a46inch.a46inch.Classes.FAQ;
import com.a46inch.a46inch.CustomAdapters.CustomListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {
    private ListView lvFaq;
    private CustomListAdapter adapter;
    private List<FAQ> FaQList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        lvFaq = (ListView)findViewById(R.id.FAQList);
        FaQList = new ArrayList<>();
        FaQList.add(new FAQ("Who is The Developer of this App ?","Aditya Narayan is the Developer of this App"));
        adapter = new CustomListAdapter(getApplicationContext(), FaQList);
        lvFaq.setAdapter(adapter);
    }
}
