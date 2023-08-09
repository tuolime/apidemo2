package com.ss.apidemo.excel;

import android.os.Environment;

import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.db.dao.UserDao;
import com.ss.apidemo.db.dao.UserValueDao;
import com.ss.apidemo.excel.bean.UserExcelBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
/*
* 导出excel
* */
public class ExcelExport {
    public static void main(String[] args) throws Exception {
        exportUser();
//        importUser();
    }

    public static void exportUser() throws Exception {
        long t1 = System.currentTimeMillis();
        List<UserExcelBean> users = new ArrayList<>();
        for (int i = 1; i <= 150; i++) {
            UserExcelBean u = new UserExcelBean();
            u.setName("大到飞起来" + i);
//            u.setMobile("手机号" + i);
//            u.setSex("男");
//            u.setAddress("地点" + i);
//            u.setMemo("备注" + i);
//            u.setOther("其他信息" + i);
            users.add(u);
        }
        ExcelManager excelManager = new ExcelManager();
        File file = new File(getFileDir(), "usersExport.xls");
        OutputStream excelStream = new FileOutputStream( file);

        boolean success = excelManager.toExcel(excelStream, users);
        long t2 = System.currentTimeMillis();

        double time = (t2 - t1) / 1000.0D;
        if (success) {
            System.out.print("导出成功：\n用时:" + time + "秒");
        } else {
            System.err.print("导出失败");
        }
    }

    public static void exportUser2(OutputStream excelOutputStream) throws Exception {
        long t1 = System.currentTimeMillis();
        List<User> allUser = UserDao.getInstance().getAllUser();
        List<UserValue> allUserValue = UserValueDao.getInstance().getAllUserValue();
        List<UserExcelBean> users = new ArrayList<>();
        if (allUserValue != null && allUserValue.size()>0){
            for (int i = 0; i < allUserValue.size(); i++) {
                UserExcelBean u = new UserExcelBean();
                u.setGender(allUserValue.get(i).getGender());
                u.setTel(allUserValue.get(i).getTel());
                u.setMode(allUserValue.get(i).getMode());
                u.setSkinType(allUserValue.get(i).getSkinType());
                u.setBodyType(allUserValue.get(i).getBodyType());
                u.setEnergy(allUserValue.get(i).getEnergy());
                u.setFrequency(allUserValue.get(i).getFrequency());
                u.setWorkCount(allUserValue.get(i).getWorkCount());
                u.setFluence(allUserValue.get(i).getFluence());
                u.setDate(allUserValue.get(i).getDate());
                if (allUser != null && allUser.size()>0){
                    for (int j = 0; j < allUser.size(); j++) {
                        if (allUserValue.get(i).getTel().equals(allUser.get(j).getTel())){
                            u.setName(allUser.get(j).getName());
                            u.setAge(allUser.get(j).getAge());
                        }
                    }
                }


                users.add(u);
            }
            ExcelManager excelManager = new ExcelManager();
//        File file = new File(getFileDir2(url), "usersExport.xls");
//        File file = new File(getFileDir2(url), url);
            OutputStream excelStream = excelOutputStream;

            boolean success = excelManager.toExcel(excelStream, users);
            long t2 = System.currentTimeMillis();

            double time = (t2 - t1) / 1000.0D;
            if (success) {
                System.out.print("导出成功：\n用时:" + time + "秒");
            } else {
                System.err.print("导出失败");
            }
        }

    }

    static void importUser() throws Exception {
        long t1 = System.currentTimeMillis();
        InputStream excelStream = new FileInputStream("users.xls");
        ExcelManager excelManager = new ExcelManager();
        List<UserExcelBean> users = excelManager.fromExcel(excelStream, UserExcelBean.class);
        long t2 = System.currentTimeMillis();
        double time = (t2 - t1) / 1000.0D;
        System.out.print("读到User个数:" + users.size() + "\n用时:" + time + "秒");
    }

    public static String getFileDir() {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/excelTest/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
    public static String getFileDir2(String url) {
        File file = new File(url + "/excelTest/");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath();
    }
}
