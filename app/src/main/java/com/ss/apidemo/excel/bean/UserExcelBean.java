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
    private String Name;

    @ExcelContent(titleName = "Gender")
    private String Gender;

    @ExcelContent(titleName = "Age")
    private String Age;

    @ExcelContent(titleName = "Tel")
    private String Tel;

    @ExcelContent(titleName = "mode")
    private String mode;//工作模式
    @ExcelContent(titleName = "skinType")
    private String skinType;//皮肤类型
    @ExcelContent(titleName = "bodyType")
    private String bodyType;//身体部位
    @ExcelContent(titleName = "energy")
    private String energy;//能量
    @ExcelContent(titleName = "frequency")
    private String frequency;//频率 就是 Hz
    @ExcelContent(titleName = "workCount")
    private String workCount;//工作次数
    @ExcelContent(titleName = "fluence")
    private String fluence;//单脉冲
    @ExcelContent(titleName = "date")
    private String date;//时间

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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getSkinType() {
        return skinType;
    }

    public void setSkinType(String skinType) {
        this.skinType = skinType;
    }

    public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getWorkCount() {
        return workCount;
    }

    public void setWorkCount(String workCount) {
        this.workCount = workCount;
    }

    public String getFluence() {
        return fluence;
    }

    public void setFluence(String fluence) {
        this.fluence = fluence;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
