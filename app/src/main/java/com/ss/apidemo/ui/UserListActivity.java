package com.ss.apidemo.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.OutputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.R;
import com.ss.apidemo.adapter.UserListAdapter;
import com.ss.apidemo.base.BaseActivity;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.dao.UserDao;
import com.ss.apidemo.excel.Test;
import com.ss.apidemo.utils.BackgroundChangeUtils;
import com.ss.apidemo.utils.SharedPrefsUtil;
import com.ss.apidemo.utils.ToastUtil;

import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/*
 * 用户列表
 * */
public class UserListActivity extends BaseActivity {
    private static final String TAG = "UserListActivity";
    private RecyclerView recyclerView;
    private UserListAdapter adapter;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        LinearLayout ll_main = findViewById(R.id.ll_main);
        BackgroundChangeUtils.backgroundChange(this,ll_main);
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
        //用户数据导出
        ImageView iv_load = findViewById(R.id.iv_load);
        boolean userDataDownLoad = SharedPrefsUtil.getBooleanValue(AppConfig.USERDATADOWMLOAD, false);
        if (userDataDownLoad){
            iv_load.setVisibility(View.VISIBLE);
        }else {
            iv_load.setVisibility(View.INVISIBLE);
        }
        iv_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //导出数据
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    startActivityForResult(intent, 0);
                }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (data != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                try {
                    Uri uri = data.getData();
                    // 使用DocumentFile进行文件操作
                    DocumentFile documentFile = DocumentFile.fromTreeUri(UserListActivity.this, uri);
//                    String outputFilename = new Date().getTime() + ".xlsx";
//                    DocumentFile newFile = documentFile.createFile("application/xlsx", outputFilename);
                    DocumentFile newFile = documentFile.createFile("application/xlsx", "usersExport.xls");
                    // 获得输出流
                    OutputStream excelOutputStream = UserListActivity.this.getContentResolver().openOutputStream(newFile.getUri());
                    // 进行输出操作（写文件）
                    Test.exportUser2(excelOutputStream);
                } catch (Exception e) {
                    // 进行异常处理
                    // showShortToast(e.getMessage());
                    Log.d(TAG, "Exception: " + e.toString());
                }

            }
        }

    }
}