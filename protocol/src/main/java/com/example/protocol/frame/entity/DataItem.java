package com.example.protocol.frame.entity;


import java.util.List;

/**
 * 采集项目
 * 
 * @Class Name ItemBean
 * @author zhangfeng
 * @Create In 2018年6月12日
 */
public class DataItem {

	private static final long serialVersionUID = 1L;

	/**
	 * 采集项id
	 */
	private String id;

	/**
	 * 采集项名称
	 */
	private String name;

	/**
	 * 采集项长度/倍数
	 */
	private int length;

	/**
	 * 采集项值
	 */
	private String value;

	/**
	 * 采集项值单位
	 */
	private String unit;

	/**
	 * 采集项值数据格式
	 */
	private String format;


	/**
	 * 采集项值高位数据
	 */
	private  String hiValue;

	/**
	 * 采集项值低位数据
	 */
	private  String lowValue;

	/**
	 * 采集项值高位数据项
	 */
	private  List<DataItem> hiDataItems;

	/**
	 * 采集项值低位数据项
	 */
	private  List<DataItem> lowDataItems;

	/**
	 * 采集项目
	 */
	private List<DataItem> items;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getHiValue() {
		return hiValue;
	}

	public void setHiValue(String hiValue) {
		this.hiValue = hiValue;
	}

	public String getLowValue() {
		return lowValue;
	}

	public void setLowValue(String lowValue) {
		this.lowValue = lowValue;
	}

	public List<DataItem> getHiDataItems() {
		return hiDataItems;
	}

	public void setHiDataItems(List<DataItem> hiDataItems) {
		this.hiDataItems = hiDataItems;
	}

	public List<DataItem> getLowDataItems() {
		return lowDataItems;
	}

	public void setLowDataItems(List<DataItem> lowDataItems) {
		this.lowDataItems = lowDataItems;
	}

	public List<DataItem> getItems() {
		return items;
	}

	public void setItems(List<DataItem> items) {
		this.items = items;
	}
}
