package com.ss.apidemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.ss.apidemo.R;
import com.ss.apidemo.db.bean.UserValue;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserMessageAdapter extends RecyclerView.Adapter<UserMessageAdapter.ViewHolder> {
    private List<UserValue> dataList;
    public ClickItem clickItem;
    public Context context;
    public UserMessageAdapter(List<UserValue> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }
    public void setDatas(List<UserValue> datas) {
        this.dataList = datas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_value_array_item, parent, false);
        return new ViewHolder(view);
    }

    public void setClickItem(ClickItem clickItem){
        this.clickItem = clickItem;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final UserValue userValue = dataList.get(position);
        String title = context.getResources().getString(R.string.event)+" "+(position+1);
        holder.tv_user_title.setText(title);
        holder.tv_user_data.setText(userValue.getDate());
        String content = "";
        //工作模式 工作模式 stack 1 2 3 4 shr 5 hr  6  auto  7  30 8 100 9 400 10
        if (userValue.getMode().equals("1") ||userValue.getMode().equals("2") ||
                userValue.getMode().equals("3") ||userValue.getMode().equals("4") ||
                userValue.getMode().equals("5")||userValue.getMode().equals("6")){
            content = context.getResources().getString(R.string.mode)+": "+SetModeOne(userValue.getMode())+"   "+
                    context.getResources().getString(R.string.skin)+": "+SetSkinType(userValue.getSkinType())+"   "+
                    context.getResources().getString(R.string.body)+": "+SetModeOneBodyType(userValue.getGender(),userValue.getBodyType())+"   "+
                    context.getResources().getString(R.string.energy)+": "+userValue.getEnergy()+"J"+"   "+
                    context.getResources().getString(R.string.work_count)+": "+userValue.getWorkCount()+"   "+
                    context.getResources().getString(R.string.fluence)+": "+userValue.getFluence()+"   "+
                    context.getResources().getString(R.string.frequency)+": "+ userValue.getFrequency()+"Hz";
        }
        if (userValue.getMode().equals("7") ||userValue.getMode().equals("8") ||
                userValue.getMode().equals("9") ||userValue.getMode().equals("10")){
            content = context.getResources().getString(R.string.mode)+": "+SetModeTwo(userValue.getMode())+"   "+
                    context.getResources().getString(R.string.skin)+": "+SetSkinType(userValue.getSkinType())+"   "+
                    context.getResources().getString(R.string.body)+": "+SetModeTwoBodyType(userValue.getBodyType())+"   "+
                    context.getResources().getString(R.string.energy)+": "+userValue.getEnergy()+"J"+"   "+
                    context.getResources().getString(R.string.work_count)+": "+userValue.getWorkCount()+"   "+
                    context.getResources().getString(R.string.fluence)+": "+userValue.getFluence()+"   "+
                    context.getResources().getString(R.string.frequency)+": "+ userValue.getFrequency()+"Hz";
        }

        holder.tv_user_content.setText(content);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.onClickItem(userValue.get_id(),userValue.getTel());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_user_title;
        private TextView tv_user_data;
        private TextView tv_user_content;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_user_title = itemView.findViewById(R.id.tv_user_title);
            tv_user_data = itemView.findViewById(R.id.tv_user_data);
            tv_user_content = itemView.findViewById(R.id.tv_user_content);
        }
    }
    public interface ClickItem{
        void onClickItem(int id,String tel);
    }

    public String SetModeOne(String mode){
        //工作模式  1 2 3 4 5 shr 6 hr
        String modeString = "";
        switch (mode){
            case "1":
            case "2":
            case "3":
            case "4":
                modeString = "SHR STACK";
                break;
            case "5":
                modeString = "SHR";
                break;
            case "6":
                modeString = "HR";
                break;
        }
        return modeString;
    }

    public String SetModeTwo(String mode){
        //工作模式  1 2 3 4 5 shr 6 hr
        String modeString = "";
        switch (mode){
            case "7":
                modeString = "AUTO";
                break;
            case "8":
                modeString = "30";
                break;
            case "9":
                modeString = "100";
                break;
            case "10":
                modeString = "400";
                break;
        }
        return modeString;
    }

    public String SetSkinType(String skinType){
        String skinTypeString = "";
        switch (skinType){
            case "1":
                skinTypeString = "I";
                break;
            case "2":
                skinTypeString = "II";
                break;
            case "3":
                skinTypeString = "III";
                break;
            case "4":
                skinTypeString = "IV";
                break;
            case "5":
                skinTypeString = "V";
                break;
            case "6":
                skinTypeString = "VI";
                break;
        }
        return skinTypeString;
    }
    /*
    * 工作模式一
    * */
    public String SetModeOneBodyType(String gender,String bodyType){
        // 男部位id
        //1：男性额头；2：男性面颊；3：男性嘴唇；4：男性脖子；
        // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手；
        // 13：后颈；14：后背；15：臀；16：肩；

        // 部位id
        // 1：女性额头；2：女性面颊；3：女性嘴唇；4：女性脖子；
        // 5：胸；6：腹；7：比基尼；8：大腿；9：膝盖；10：小腿；11：腋下；12：手臂；13：手；
        // 14：后颈；15：后背；16：臀；17：肩；
        String bodyTypeString = "";
        if (gender.equals("1")){//男
            switch (bodyType){
                case "1":
                    bodyTypeString = "forehead";
                    break;
                case "2":
                    bodyTypeString = "cheek";
                    break;
                case "3":
                    bodyTypeString = "lip";
                    break;
                case "4":
                    bodyTypeString = "neck";
                    break;
                case "5":
                    bodyTypeString = "chest";
                    break;
                case "6":
                    bodyTypeString = "abdomen";
                    break;
                case "7":
                    bodyTypeString = "Bikini";
                    break;
                case "8":
                    bodyTypeString = "thigh";
                    break;
                case "9":
                    bodyTypeString = "knee";
                    break;
                case "10":
                    bodyTypeString = "leg";
                    break;
                case "11":
                    bodyTypeString = "armpit";
                    break;
                case "12":
                    bodyTypeString = "hand";
                    break;
                case "13":
                    bodyTypeString = "nape";
                    break;
                case "14":
                    bodyTypeString = "back";
                    break;
                case "15":
                    bodyTypeString = "Buttocks";
                    break;
                case "16":
                    bodyTypeString = "shoulder";
                    break;

            }
        }else {//女
            switch (bodyType){
                case "1":
                    bodyTypeString = "forehead";
                    break;
                case "2":
                    bodyTypeString = "cheek";
                    break;
                case "3":
                    bodyTypeString = "lip";
                    break;
                case "4":
                    bodyTypeString = "neck";
                    break;
                case "5":
                    bodyTypeString = "chest";
                    break;
                case "6":
                    bodyTypeString = "abdomen";
                    break;
                case "7":
                    bodyTypeString = "Bikini";
                    break;
                case "8":
                    bodyTypeString = "thigh";
                    break;
                case "9":
                    bodyTypeString = "knee";
                    break;
                case "10":
                    bodyTypeString = "leg";
                    break;
                case "11":
                    bodyTypeString = "armpit";
                    break;
                case "12":
                    bodyTypeString = "arm";
                case "13":
                    bodyTypeString = "hand";
                    break;
                case "14":
                    bodyTypeString = "nape";
                    break;
                case "15":
                    bodyTypeString = "back";
                    break;
                case "16":
                    bodyTypeString = "Buttocks";
                    break;
                case "17":
                    bodyTypeString = "shoulder";
                    break;

            }
        }

        return bodyTypeString;
    }
    /*
    * 工作模式二
    * */
    public String SetModeTwoBodyType(String bodyType){
        //1：面部；2：四肢；3：腋下；4：腹部；
        // 5：背部；6：隐私；
        String bodyTypeString = "";
        switch (bodyType){
            case "1":
                bodyTypeString = "face";
                break;
            case "2":
                bodyTypeString = "the four limbs";
                break;
            case "3":
                bodyTypeString = "armpit";
                break;
            case "4":
                bodyTypeString = "abdomen";
                break;
            case "5":
                bodyTypeString = "back";
                break;
            case "6":
                bodyTypeString = "privacy";
                break;
        }

        return bodyTypeString;
    }
}