package com.ss.apidemo.excel;

import android.os.Environment;

import com.ss.apidemo.R;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.db.dao.UserDao;
import com.ss.apidemo.db.dao.UserValueDao;
import com.ss.apidemo.dialog.HintDialog;
import com.ss.apidemo.excel.bean.UserExcelBean;
import com.ss.apidemo.ui.SplashActivity;
import com.ss.apidemo.ui.UserListActivity;

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
            u.setA_Name("大到飞起来" + i);
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

    public static void exportUser2(OutputStream excelOutputStream, UserListActivity context) throws Exception {
        long t1 = System.currentTimeMillis();
        List<User> allUser = UserDao.getInstance().getAllUser();
        List<UserValue> allUserValue = UserValueDao.getInstance().getAllUserValue();
        List<UserExcelBean> users = new ArrayList<>();
        if (allUserValue != null && allUserValue.size()>0){
            for (int i = 0; i < allUserValue.size(); i++) {
                UserExcelBean u = new UserExcelBean();
                u.setB_Gender(allUserValue.get(i).getGender());
                u.setD_Tel(allUserValue.get(i).getTel());
                u.setH_energy(allUserValue.get(i).getEnergy());
                u.setI_frequency(allUserValue.get(i).getFrequency());
                u.setJ_workCount(allUserValue.get(i).getWorkCount());
                u.setK_fluence(allUserValue.get(i).getFluence());
                u.setL_date(allUserValue.get(i).getDate());
                u.setF_skinType(SetSkinType(allUserValue.get(i).getSkinType()));
                if (allUserValue.get(i).getMode().equals("1") ||allUserValue.get(i).getMode().equals("2") ||
                        allUserValue.get(i).getMode().equals("3") ||allUserValue.get(i).getMode().equals("4") ||
                        allUserValue.get(i).getMode().equals("5")||allUserValue.get(i).getMode().equals("6")){
                    u.setE_mode(SetModeOne(allUserValue.get(i).getMode()));
                    u.setG_bodyType(SetModeOneBodyType(allUserValue.get(i).getGender(),allUserValue.get(i).getBodyType()));
                }
                if (allUserValue.get(i).getMode().equals("7") ||allUserValue.get(i).getMode().equals("8") ||
                        allUserValue.get(i).getMode().equals("9") ||allUserValue.get(i).getMode().equals("10")){
                    u.setE_mode(SetModeTwo(allUserValue.get(i).getMode()));
                    u.setG_bodyType(SetModeTwoBodyType(allUserValue.get(i).getBodyType()));
                }



                if (allUser != null && allUser.size()>0){
                    for (int j = 0; j < allUser.size(); j++) {
                        if (allUserValue.get(i).getTel().equals(allUser.get(j).getTel())){
                            u.setA_Name(allUser.get(j).getName());
                            u.setC_Age(allUser.get(j).getAge());
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
                ShowDialog(context,R.string.excel_success);
            } else {
                System.err.print("导出失败");
                ShowDialog(context,R.string.excel_fail);
            }
        }

    }


    private static void ShowDialog(UserListActivity context, int text){
        HintDialog dialog = new HintDialog(context);
        dialog.loadDialog(context, new HintDialog.OnClickIsConfirm() {
            @Override
            public void OnClickIsConfirmListener() {//确定
            }

        }, context.getResources().getString(text));
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

    public static  String SetModeOne(String mode){
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

    public static String SetModeTwo(String mode){
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

    public static  String SetSkinType(String skinType){
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
    public static String SetModeOneBodyType(String gender,String bodyType){
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
    public static String SetModeTwoBodyType(String bodyType){
        //1：面部；2：四肢；3：腋下；4：腹部；
        // 5：背部；6：比基尼；
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
                bodyTypeString = "Bikini";
                break;
        }

        return bodyTypeString;
    }
}
