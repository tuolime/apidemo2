package com.ss.apidemo.excel.bean;

import com.j256.ormlite.field.DatabaseField;

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
    private String a_Name;

    @ExcelContent(titleName = "Gender")
    private String b_Gender;

    @ExcelContent(titleName = "Age")
    private String c_Age;

    @ExcelContent(titleName = "Tel")
    private String d_Tel;

    @ExcelContent(titleName = "mode")
    private String e_mode;//工作模式
    @ExcelContent(titleName = "skinType")
    private String f_skinType;//皮肤类型
    @ExcelContent(titleName = "bodyType")
    private String g_bodyType;//身体部位
    @ExcelContent(titleName = "energy")
    private String h_energy;//能量
    @ExcelContent(titleName = "frequency")
    private String i_frequency;//频率 就是 Hz
    @ExcelContent(titleName = "workCount")
    private String j_workCount;//工作次数
    @ExcelContent(titleName = "fluence")
    private String k_fluence;//单脉冲
    @ExcelContent(titleName = "date")
    private String l_date;//时间

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
    private static int f7flag = 0;
    private static int f8flag = 0;
    private static int f9flag = 0;
    private static int f10flag = 0;
    private static int f11flag = 0;
    private static int f12flag = 0;

    @ExcelContentCellFormat(titleName = "Name")
    private WritableCellFormat f1() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f1flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }

            if (a_Name.contains("4")) {
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

    @ExcelContentCellFormat(titleName = "mode")
    private WritableCellFormat f5() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f5flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f5flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "skinType")
    private WritableCellFormat f6() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f6flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f6flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "bodyType")
    private WritableCellFormat f7() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f7flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f7flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "energy")
    private WritableCellFormat f8() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f8flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f8flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "frequency")
    private WritableCellFormat f9() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f9flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f9flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "workCount")
    private WritableCellFormat f10() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f10flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f10flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "fluence")
    private WritableCellFormat f11() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f11flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f11flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    @ExcelContentCellFormat(titleName = "date")
    private WritableCellFormat f12() {
        WritableCellFormat format = null;
        try {
            format = new WritableCellFormat();
            if ((f12flag & 1) != 0) {
                format.setBackground(Colour.GRAY_25);
            }
            f12flag++;
        } catch (WriteException e) {
            e.printStackTrace();
        }
        return format;
    }

    public UserExcelBean() {

    }

    public String getA_Name() {
        return a_Name;
    }

    public void setA_Name(String a_Name) {
        this.a_Name = a_Name;
    }

    public String getB_Gender() {
        return b_Gender;
    }

    public void setB_Gender(String b_Gender) {
        this.b_Gender = b_Gender;
    }

    public String getC_Age() {
        return c_Age;
    }

    public void setC_Age(String c_Age) {
        this.c_Age = c_Age;
    }

    public String getD_Tel() {
        return d_Tel;
    }

    public void setD_Tel(String d_Tel) {
        this.d_Tel = d_Tel;
    }

    public String getE_mode() {
        return e_mode;
    }

    public void setE_mode(String e_mode) {
        this.e_mode = e_mode;
    }

    public String getF_skinType() {
        return f_skinType;
    }

    public void setF_skinType(String f_skinType) {
        this.f_skinType = f_skinType;
    }

    public String getG_bodyType() {
        return g_bodyType;
    }

    public void setG_bodyType(String g_bodyType) {
        this.g_bodyType = g_bodyType;
    }

    public String getH_energy() {
        return h_energy;
    }

    public void setH_energy(String h_energy) {
        this.h_energy = h_energy;
    }

    public String getI_frequency() {
        return i_frequency;
    }

    public void setI_frequency(String i_frequency) {
        this.i_frequency = i_frequency;
    }

    public String getJ_workCount() {
        return j_workCount;
    }

    public void setJ_workCount(String j_workCount) {
        this.j_workCount = j_workCount;
    }

    public String getK_fluence() {
        return k_fluence;
    }

    public void setK_fluence(String k_fluence) {
        this.k_fluence = k_fluence;
    }

    public String getL_date() {
        return l_date;
    }

    public void setL_date(String l_date) {
        this.l_date = l_date;
    }
}
