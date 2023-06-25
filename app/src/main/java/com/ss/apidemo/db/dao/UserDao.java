package com.ss.apidemo.db.dao;


import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.apidemo.MyApplication;
import com.ss.apidemo.db.SQLiteHelper;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.utils.LogUtils;

/**
 * 用户的Dao
 */
public class UserDao {
    private static final String TAG = "UserDao";

    private static UserDao instance = null;
    public Dao<User, Integer> userDao;
    private SQLiteHelper mHelper;


    private UserDao() {
        try {
            mHelper = OpenHelperManager.getHelper(MyApplication.instance(), SQLiteHelper.class);
            userDao = DaoManager.createDao(mHelper.getConnectionSource(), User.class);
            mHelper.getWritableDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static final UserDao getInstance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
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

    public void createUser(User user) {
        if (user == null) return;
        User user1 = new User();
        user1.setId(user.getId());
        user1.setName(user.getName());
        user1.setGender(user.getGender());
        user1.setAge(user.getAge());
        user1.setTel(user.getTel());
        createU(user1);

    }


    public void createU(User user) {
        try {
            LogUtils.e("插入成功");
            userDao.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteUser() {
        List<User> query = new ArrayList<>();
        try {
            query = userDao.queryForAll();
            if (query != null) {
                userDao.deleteBuilder().delete();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // 删除标签
    public void deleteUser(String tel) {
        try {
            DeleteBuilder<User, Integer> builder = userDao.deleteBuilder();
            builder.where().eq("tel", tel);
            userDao.delete(builder.prepare());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUser() {
        List<User> query = new ArrayList<>();
        try {
            query = userDao.queryForAll();
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    public List<User> getUser(String tel) {
        List<User> query = new ArrayList<>();
        try {
            PreparedQuery<User> preparedQuery = userDao.queryBuilder().where()
                    .eq("tel", tel)
                    .prepare();
            query = userDao.query(preparedQuery);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }

    public List<User> getLikeUser(String tel) {
        List<User> query = new ArrayList<>();
        try {
            PreparedQuery<User> preparedQuery = userDao.queryBuilder().where()
                    .like("tel", "%"+tel+"%")
                    .prepare();
            query = userDao.query(preparedQuery);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return query;
    }
}