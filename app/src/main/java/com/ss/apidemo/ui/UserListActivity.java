package com.ss.apidemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.ss.apidemo.R;
import com.ss.apidemo.adapter.UserListAdapter;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.dao.UserDao;
import com.ss.apidemo.utils.ToastUtil;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
 * 用户列表
 * */
public class UserListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private UserListAdapter adapter;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Intent intent = getIntent();
        if (intent != null) {
            tel = intent.getStringExtra("tel");
        }
        initView();
    }

    private void initView() {
        ImageView iv_back = findViewById(R.id.iv_back);
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //退出时 下发报文选择好的温度和水流。。。。
                //退出该页面
                finish();
            }
        });
        recyclerView = findViewById(R.id.recyclerview);
        //设置LayoutManager，以LinearLayoutManager为例子进行线性布局
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        //设置分割线
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //创建适配器
        adapter = new UserListAdapter(getArrayList(), this);
        //设置适配器
        recyclerView.setAdapter(adapter);
        adapter.setClickItem(new UserListAdapter.ClickItem() {
            @Override
            public void onClickItem(String tel) {
                Intent intent = new Intent(UserListActivity.this, UserMessageActivity.class);
                intent.putExtra("tel", tel);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.setDatas(getArrayList());
        }
    }

    private List<User> getArrayList() {
        List<User> allUser;
        if (tel != null && !tel.equals("")) {
            allUser = UserDao.getInstance().getLikeUser(tel);
        } else {
            allUser = UserDao.getInstance().getAllUser();
        }
        Collections.sort(allUser, Comparator.comparing(User::getName));
        return allUser;
    }
}