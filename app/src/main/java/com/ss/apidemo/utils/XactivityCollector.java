package com.ss.apidemo.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class XactivityCollector {
	
	public static List<Activity> activitis = new ArrayList<Activity>();
	public static void addActivity(Activity activity){
		activitis.add(activity);
	}
	public static void removeActivity(Activity activity){
		activitis.remove(activity);
	}
	public static void finishAll(){
		for(Activity activity :activitis){
			if(!activity.isFinishing()){
				activity.finish();
			}
		}
	}

}
