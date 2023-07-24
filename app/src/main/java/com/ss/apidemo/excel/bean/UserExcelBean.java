package com.ss.apidemo.excel.bean;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

/**
 * 用户表，作为用户的导出Excel的中间格式化实体，所有字段都为 String
 */
@ExcelSheet(sheetName = "User")
public class UserExcelBean {

    @ExcelContent(titleName = "Name")
    private String Name;

    @ExcelContent(titleName = "Gender")
    private String Gender;

    @ExcelContent(titleName = "Age")
    private String Age;

    @ExcelContent(titleName = "Tel")
    private String Tel;

//    @ExcelContent(titleName = "其他")
//    private String Other;
//
//    @ExcelContent(titleName = "备注")
//    private String Memo;

    @ExcelTitleCellFormat(titleName = "Name")
    private static WritableCellFormat getTitleFormat() {
        WritableCellFormat format = new WritableCellFormat();
        try {
            // 单元格格式
            // 背景颜色
            // format.setBackground(Colour.PINK);
            // 边框线
            format.setBorder(Border.BOTTOM, BorderLineStyle.THIN, Colour.RED);
            // 设置文字居中对齐方式;
            format.setAlignment(Alignment.CENTRE);
            // 设置垂直居中;
            format.setVerticalAlignment(VerticalAlignment.CENTRE);
            // 设置自动换行
            format.setWrap(false);

            // 字体格式
            WritableFont font = new WritableFont(WritableFont.ARIAL);
            // 字体颜色
            font.setColour(Colour.BLUE2);
            // 字体加粗
            font.setBoldStyle(WritableFont.BOLD);
            // 字体加下划线
            font.setUnderlineStyle(UnderlineStyle.SINGLE);
            // 字体大小
            font.setPointSize(20);
            format.setFont(font);
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    private static int f1flag = 0;
    private static int f2flag = 0;
    private static int f3flag = 0;
    private static int f4flag = 0;
    private static int f5flag = 0;
    private static int f6flag = 0;

    @ExcelContentCellFormat(titleName = "Name")
    private WritableCellFormat f1() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f1flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }

            if (Name.contains("4")) {
                format.setBackground(Colour.RED);
            }

            f1flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "Gender")
    private WritableCellFormat f2() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f2flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f2flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "Age")
    private WritableCellFormat f3() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f3flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f3flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "Tel")
    private WritableCellFormat f4() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f4flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f4flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

//    @ExcelContentCellFormat(titleName = "其他")
//    private WritableCellFormat f5() {
//        WritableCellFormat format = null;
//        try {
//            format = new WritableCellFormat();
//            if ((f5flag & 1) != 0) {
//                format.setBackground(Colour.GRAY_25);
//            }
//            f5flag++;
//        } catch (WriteException e) {
//            e.printStackTrace();
//        }
//        return format;
//    }
//
//    @ExcelContentCellFormat(titleName = "备注")
//    private WritableCellFormat f6() {
//        WritableCellFormat format = null;
//        try {
//            format = new WritableCellFormat();
//            if ((f6flag & 1) != 0) {
//                format.setBackground(Colour.GRAY_25);
//            }
//            f6flag++;
//        } catch (WriteException e) {
//            e.printStackTrace();
//        }
//        return format;
//    }

    public UserExcelBean() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }

//    public String getOther() {
//        return Other;
//    }
//
//    public void setOther(String other) {
//        Other = other;
//    }
//
//    public String getMemo() {
//        return Memo;
//    }
//
//    public void setMemo(String memo) {
//        Memo = memo;
//    }
}
