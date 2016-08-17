package com.recyclerview.demo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StaggeredActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private StaggeredAdapter adapter;
    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        findViewById(R.id.right_btn).setVisibility(View.GONE);
        recyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        boolean type = getIntent().getBooleanExtra("type", true);
        adapter = new StaggeredAdapter(this, datas, type);
        recyclerView.setAdapter(adapter);
        if (type) {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));
        } else {
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));
        }

        //添加分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        adapter.setOnItemClickListener(new StaggeredAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(StaggeredActivity.this, "click " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(StaggeredActivity.this, "LongClick " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    //    public void onClick(View view){
//        showDialog();
//    }
    private void initData() {
        datas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            datas.add("item " + i);
        }
    }

    int pos = 0;

    private void showDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(StaggeredActivity.this);
        builder.setTitle("");
        builder.setCancelable(false);
        final String[] list = {"listview", "gridview", "horizontalgridview"};
        // 设置一个单项选择下拉框
        /**
         * 第一个参数指定我们要显示的一组下拉单选框的数据集合 第二个参数代表索引，指定默认哪一个单选框被勾选上，0表示默认会被勾选上
         * 第三个参数给每一个单选项绑定一个监听器
         */

        builder.setSingleChoiceItems(list, pos, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pos = which;
                switch (which) {
                    case 0:
                        recyclerView.setLayoutManager(new LinearLayoutManager(StaggeredActivity.this));
                        break;
                    case 1:
                        recyclerView.setLayoutManager(new GridLayoutManager(StaggeredActivity.this, 3));
                        break;
                    case 2:
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL));
                        break;
                    default:
                        break;
                }
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
