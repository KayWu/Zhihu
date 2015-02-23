package com.kay.zhihu.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.kay.zhihu.R;
import com.kay.zhihu.adapter.NewsAdapter;
import com.kay.zhihu.task.LoadNewsTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private SwipeRefreshLayout refreshLayout;
    private ListView lv;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh_layout);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        lv = (ListView) findViewById(R.id.lv);
        setTitle(getTime());
        adapter = new NewsAdapter(this, R.layout.listview_item);
        lv.setAdapter(adapter);
        new LoadNewsTask(adapter).execute();
        lv.setOnItemClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_favourite) {
            Intent i = new Intent(this, FavouriteActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh() {
        new LoadNewsTask(adapter, new LoadNewsTask.onFinishListener() {
            @Override
            public void afterTaskFinish() {
                refreshLayout.setRefreshing(false);
//                Toast.makeText(MainActivity.this, "Refresh success", Toast.LENGTH_SHORT).show();
            }
        }).execute();
    }


    public String getTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(getString(R.string.date_format));
        return format.format(c.getTime());

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        NewsDetailActivity.startActivity(this, adapter.getItem(position));
    }
}
