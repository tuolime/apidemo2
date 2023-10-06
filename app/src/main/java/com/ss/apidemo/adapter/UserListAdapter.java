package com.ss.apidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.ss.apidemo.AppConfig;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.R;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.utils.PlayVoiceUtils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {
    private List<User> dataList;
    public ClickItem clickItem;
    public Context context;
    public  UserListAdapter(List<User> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    public void setDatas(List<User> datas) {
        this.dataList = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        return new ViewHolder(view);
    }

    public void setClickItem(ClickItem clickItem){
        this.clickItem = clickItem;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = dataList.get(position);
        if (user.getGender().equals("1")){
            holder.iv_gender.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_man));
        }else {
            holder.iv_gender.setImageDrawable(context.getResources().getDrawable(R.mipmap.ic_woman));
        }
        holder.tv_user_id.setText(user.getId());
        holder.tv_user_name.setText(user.getName());
        holder.tv_user_tel.setText(user.getTel());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayVoiceUtils.startPlayVoice(MyApplication.instance(), AppConfig.KEY);

                clickItem.onClickItem(user.getTel());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_gender;
        private TextView tv_user_id;
        private TextView tv_user_name;
        private TextView tv_user_tel;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_gender = itemView.findViewById(R.id.iv_gender);
            tv_user_id = itemView.findViewById(R.id.tv_user_id);
            tv_user_name = itemView.findViewById(R.id.tv_user_name);
            tv_user_tel = itemView.findViewById(R.id.tv_user_tel);
        }
    }
    public interface ClickItem{
        void onClickItem(String tel);
    }
}