package com.ss.apidemo.excel.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * format the content.
 * usual,you can add it on method which return WritableCellFormat,
 * most times ,it doesn't fit the big picture
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.METHOD})
public @interface ExcelContentCellFormat {
    String titleName();
}