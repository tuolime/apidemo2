package com.ss.apidemo.excel;

import android.os.Environment;

import com.ss.apidemo.excel.bean.UserExcelBean;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Test {
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
            u.setMobile("手机号" + i);
            u.setSex("男");
            u.setAddress("地点" + i);
            u.setMemo("备注" + i);
            u.setOther("其他信息" + i);
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
        List<UserExcelBean> users = new ArrayList<>();
        for (int i = 1; i <= 150; i++) {
            UserExcelBean u = new UserExcelBean();
            u.setName("大到飞起来" + i);
            u.setMobile("手机号" + i);
            u.setSex("男");
            u.setAddress("地点" + i);
            u.setMemo("备注" + i);
            u.setOther("其他信息" + i);
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
