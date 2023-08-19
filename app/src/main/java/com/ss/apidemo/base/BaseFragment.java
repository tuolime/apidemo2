package com.ss.apidemo.base;


import com.ss.apidemo.AppConfig;
import com.ss.apidemo.utils.SharedPrefsUtil;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    /*
    * 获取是否设置过最大值
    * */
    public int getEnergyUpper(){
        int current_energyUpper = SharedPrefsUtil.getIntValue(AppConfig.ENERGYUPPER, 0);
        if (current_energyUpper == 0){
            return 0;
        }else {
            return current_energyUpper;
        }

    }
}

