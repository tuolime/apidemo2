package com.ss.apidemo.db.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

//飞行计划
@DatabaseTable
public class FlightPlan implements Serializable {
    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField
    private String id;//后台数据库id返回
    @DatabaseField
    private String lineNumber;//航班编号
    @DatabaseField(canBeNull = false)
    private String userId;//用户id
    @DatabaseField
    private String userName;//用户名
    @DatabaseField
    private String flightId;//航班id
    @DatabaseField
    private String meteorologicalId;//气象id
    @DatabaseField
    private String executionDate;//航班日期
    @DatabaseField
    private String flightNumber;//航班号
    @DatabaseField
    private String aircraftType;//机尾号机型
    @DatabaseField
    private String planeNo;//机号
    @DatabaseField
    private String lineStand;//航信停机位
    @DatabaseField
    private String planDepTime;//计划起飞时间
    @DatabaseField
    private String planArrTime;//计划落地时间
    @DatabaseField
    private String airportOfDeparture;//计划起飞机场
    @DatabaseField
    private String airportOfDestination;//计划落地机场
    @DatabaseField
    private String flightNature;//航班性质
    @DatabaseField
    private String flightState;//航班状态
    @DatabaseField
    private String releaseFlag;//是否放行
    @DatabaseField
    private String planTxt;//CFP原文
    @DatabaseField
    private String meteorologicalTxt;//气象原文
    @DatabaseField
    private String notamTxt;//NOTAM原文
    @DatabaseField
    private String routePath;//航信分析文档路径
//    @DatabaseField
    private boolean ischeck;//记录是否选择当前

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getMeteorologicalId() {
        return meteorologicalId;
    }

    public void setMeteorologicalId(String meteorologicalId) {
        this.meteorologicalId = meteorologicalId;
    }

    public String getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(String executionDate) {
        this.executionDate = executionDate;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public String getPlaneNo() {
        return planeNo;
    }

    public void setPlaneNo(String planeNo) {
        this.planeNo = planeNo;
    }

    public String getLineStand() {
        return lineStand;
    }

    public void setLineStand(String lineStand) {
        this.lineStand = lineStand;
    }

    public String getPlanDepTime() {
        return planDepTime;
    }

    public void setPlanDepTime(String planDepTime) {
        this.planDepTime = planDepTime;
    }

    public String getPlanArrTime() {
        return planArrTime;
    }

    public void setPlanArrTime(String planArrTime) {
        this.planArrTime = planArrTime;
    }

    public String getAirportOfDeparture() {
        return airportOfDeparture;
    }

    public void setAirportOfDeparture(String airportOfDeparture) {
        this.airportOfDeparture = airportOfDeparture;
    }

    public String getAirportOfDestination() {
        return airportOfDestination;
    }

    public void setAirportOfDestination(String airportOfDestination) {
        this.airportOfDestination = airportOfDestination;
    }

    public String getFlightNature() {
        return flightNature;
    }

    public void setFlightNature(String flightNature) {
        this.flightNature = flightNature;
    }

    public String getFlightState() {
        return flightState;
    }

    public void setFlightState(String flightState) {
        this.flightState = flightState;
    }

    public String getReleaseFlag() {
        return releaseFlag;
    }

    public void setReleaseFlag(String releaseFlag) {
        this.releaseFlag = releaseFlag;
    }

    public String getPlanTxt() {
        return planTxt;
    }

    public void setPlanTxt(String planTxt) {
        this.planTxt = planTxt;
    }

    public String getMeteorologicalTxt() {
        return meteorologicalTxt;
    }

    public void setMeteorologicalTxt(String meteorologicalTxt) {
        this.meteorologicalTxt = meteorologicalTxt;
    }

    public String getNotamTxt() {
        return notamTxt;
    }

    public void setNotamTxt(String notamTxt) {
        this.notamTxt = notamTxt;
    }

    public String getRoutePath() {
        return routePath;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public boolean ischeck() {
        return ischeck;
    }

    public void setischeck(boolean ischeck) {
        this.ischeck = ischeck;
    }
}
