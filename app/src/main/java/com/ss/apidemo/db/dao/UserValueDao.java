package com.ss.apidemo.db.dao;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.stmt.Where;
import com.ss.apidemo.MyApplication;
import com.ss.apidemo.db.SQLiteHelper;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.utils.DateUtil;
import com.ss.apidemo.utils.LogUtils;

/**
 * 用户的存储记录数据
 */
public class UserValueDao {
    private static final String TAG = "UserDao";

    private static UserValueDao instance = null;
    public Dao<UserValue, Integer> userValueDao;
    private SQLiteHelper mHelper;


    private UserValueDao() {
        try {
            mHelper = OpenHelperManager.getHelper(MyApplication.instance(), SQLiteHelper.class);
            userValueDao = DaoManager.createDao(mHelper.getConnectionSource(), UserValue.class);
            mHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final UserValueDao getInstance() {
        if (instance == null) {
            synchronized (UserValueDao.class) {
                if (instance == null) {
                    instance = new UserValueDao();
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

    public void createUserValue(UserValue userValue) {
        if (userValue == null) return;
        UserValue userValue1 = new UserValue();
        userValue1.setTel(userValue.getTel());
        userValue1.setMode(userValue.getMode());
        userValue1.setGender(userValue.getGender());
        userValue1.setSkinType(userValue.getSkinType());
        userValue1.setBodyType(userValue.getBodyType());
        userValue1.setEnergy(userValue.getEnergy());
        userValue1.setFrequency(userValue.getFrequency());
        userValue1.setWorkCount(userValue.getWorkCount());
        userValue1.setFluence(userValue.getFluence());
        String nowTime = DateUtil.getNowTime();
        userValue1.setDate(nowTime);
        createU(userValue1);

    }


    public void createU(UserValue user) {
        try {
            LogUtils.e("插入成功");
            userValueDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAllUserValue() {
        List<UserValue> query = new ArrayList<>();
        try {
            query = userValueDao.queryForAll();
            if (query != null) {
                userValueDao.deleteBuilder().delete();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteAllUserValue(String tel) {
        List<UserValue> query = new ArrayList<>();
        try {
            query = userValueDao.queryForAll();
            if (query != null) {
                DeleteBuilder<UserValue, Integer> builder = userValueDao.deleteBuilder();
                builder.where().eq("tel", tel);
                userValueDao.delete(builder.prepare());
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 删除标签
    public void deleteUserValue(int id,String tel) {
        try {
            DeleteBuilder<UserValue, Integer> builder = userValueDao.deleteBuilder();
            builder.where().eq("_id", id).and().eq("tel", tel);
            userValueDao.delete(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<UserValue> getAllUserValue() {
        List<UserValue> query = new ArrayList<>();
        try {
            query = userValueDao.queryForAll();
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    public List<UserValue> getUserValue(String tel) {
        List<UserValue> query = new ArrayList<>();
        try {
            PreparedQuery<UserValue> preparedQuery = userValueDao.queryBuilder().where()
                    .eq("tel", tel)
                    .prepare();
            query = userValueDao.query(preparedQuery);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}