package com.ss.apidemo.db.dao;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.apidemo.MyApplication;
import com.ss.apidemo.db.SQLiteHelper;
import com.ss.apidemo.db.bean.FlightPlan;
import com.ss.apidemo.utils.LogUtils;

/**
 * 飞机计划的Dao
 */
public class FlightPlanDao {
    private static final String TAG = "FlightPlanDao";

    private static FlightPlanDao instance = null;
    public Dao<FlightPlan, Integer> flightPlanDao;
    private SQLiteHelper mHelper;


    private FlightPlanDao() {
        try {
            mHelper = OpenHelperManager.getHelper(MyApplication.instance(), SQLiteHelper.class);
            flightPlanDao = DaoManager.createDao(mHelper.getConnectionSource(), FlightPlan.class);
            mHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final FlightPlanDao getInstance() {
        if (instance == null) {
            synchronized (FlightPlanDao.class) {
                if (instance == null) {
                    instance = new FlightPlanDao();
                }
            }
        }
        return instance;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        OpenHelperManager.releaseHelper();
    }

    public void createFlightPlanList(List<FlightPlan> flightPlanListRsps) {
        if (flightPlanListRsps == null || flightPlanListRsps.size() == 0) return;

        for (FlightPlan flight : flightPlanListRsps) {
            FlightPlan flightPlan = new FlightPlan();
            flightPlan.setId(flight.getId());
            flightPlan.setLineNumber(flight.getLineNumber());
            flightPlan.setUserId(flight.getUserId());
            flightPlan.setUserName(flight.getUserName());
            flightPlan.setFlightId(flight.getFlightId());
            flightPlan.setMeteorologicalId(flight.getMeteorologicalId());
            flightPlan.setExecutionDate(flight.getExecutionDate());
            flightPlan.setFlightNumber(flight.getFlightNumber());
            flightPlan.setAircraftType(flight.getAircraftType());
            flightPlan.setPlaneNo(flight.getPlaneNo());
            flightPlan.setLineStand(flight.getLineStand());
            flightPlan.setPlanDepTime(flight.getPlanDepTime());
            flightPlan.setPlanArrTime(flight.getPlanArrTime());
            flightPlan.setAirportOfDeparture(flight.getAirportOfDeparture());
            flightPlan.setAirportOfDestination(flight.getAirportOfDestination());
            flightPlan.setFlightNature(flight.getFlightNature());
            flightPlan.setFlightState(flight.getFlightState());
            flightPlan.setReleaseFlag(flight.getReleaseFlag());
            flightPlan.setPlanTxt(flight.getPlanTxt());
            flightPlan.setMeteorologicalTxt(flight.getMeteorologicalTxt());
            flightPlan.setNotamTxt(flight.getNotamTxt());
            flightPlan.setRoutePath(flight.getRoutePath());
            createFlightPlan(flightPlan);
        }
    }


    public void createFlightPlan(FlightPlan flight) {
        try {
           LogUtils.e("插入成功");
            flightPlanDao.create(flight);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteFlightPlan() {
        List<FlightPlan> query = new ArrayList<>();
        try {
            query = flightPlanDao.queryForAll();
            if (query != null){
                flightPlanDao.deleteBuilder().delete();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<FlightPlan> getAllFlightPlan() {
        List<FlightPlan> query = new ArrayList<>();
        try {
//            PreparedQuery<FlightPlan> preparedQuery = flightPlanDao.queryBuilder().prepare();

            query = flightPlanDao.queryForAll();
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    public List<FlightPlan> getFlightPlan(String id) {
        List<FlightPlan> query = new ArrayList<>();
        try {
            PreparedQuery<FlightPlan> preparedQuery = flightPlanDao.queryBuilder().where()
                    .eq("id", id)
                    .prepare();
            query = flightPlanDao.query(preparedQuery);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}