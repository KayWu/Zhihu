package com.kay.zhihu.UI;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.kay.zhihu.R;
import com.kay.zhihu.Support.LoadNewsTask;
import com.kay.zhihu.Support.NewsAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout refreshLayout;
    private ListView lv;
    private TextView tvDate;
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
        tvDate = (TextView) findViewById(R.id.tv_date);
        tvDate.setText(getTime());
        adapter = new NewsAdapter(this, R.layout.listview_item);
        lv.setAdapter(adapter);
        new LoadNewsTask(adapter).execute();

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
}
