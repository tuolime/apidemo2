package com.ss.apidemo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.AndroidDatabaseConnection;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.support.DatabaseConnection;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.apidemo.db.bean.FlightPlan;
import com.ss.apidemo.db.bean.User;
import com.ss.apidemo.db.bean.UserValue;
import com.ss.apidemo.utils.LogUtils;


public class SQLiteHelper extends OrmLiteSqliteOpenHelper {
    public static final String DATABASE_NAME = "analysis.db";
    private static final String TAG = "SQLiteHelper";
    private static final int DATABASE_VERSION = 1;

    // public static final String DATABASE_PATH = Config.SDCARD_PATH +
    // File.separator + "shiku" + File.separator + "shiku.db";
    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 重建数据库，
     */
    public static void rebuildDatabase(Context ctx) {
        ctx.deleteDatabase(SQLiteHelper.DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connSource) {
        createTables(connSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connSource, int oldVersion, int newVersion) {
        dropAllUserTables(db);
        createTables(connSource);
    }

    private void dropAllUserTables(SQLiteDatabase db) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);
        //noinspection TryFinallyCanBeTryWithResources not available with API < 19
        try {
            List<String> tables = new ArrayList<>(cursor.getCount());

            while (cursor.moveToNext()) {
                tables.add(cursor.getString(0));
            }

            for (String table : tables) {
                if (table.startsWith("sqlite_")) {
                    continue;
                }
                if (table.equals("tb_areas")) {
                    continue;
                }
                db.execSQL("DROP TABLE IF EXISTS " + table);
                LogUtils.e(TAG, "Dropped table " + table);
            }
        } finally {
            cursor.close();
        }
    }

    /**
     * 复制的， {@link OrmLiteSqliteOpenHelper#onUpgrade(SQLiteDatabase, int, int)}
     */
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ConnectionSource cs = this.getConnectionSource();
        DatabaseConnection conn = cs.getSpecialConnection();
        boolean clearSpecial = false;
        if (conn == null) {
            conn = new AndroidDatabaseConnection(db, true, this.cancelQueriesEnabled);

            try {
                cs.saveSpecialConnection((DatabaseConnection) conn);
                clearSpecial = true;
            } catch (SQLException var11) {
                throw new IllegalStateException("Could not save special connection", var11);
            }
        }

        try {
            this.onDowngrade(db, cs, oldVersion, newVersion);
        } finally {
            if (clearSpecial) {
                cs.clearSpecialConnection((DatabaseConnection) conn);
            }

        }
    }

    private void onDowngrade(SQLiteDatabase db, ConnectionSource connSource, int oldVersion, int newVersion) {
        dropAllUserTables(db);
        createTables(connSource);
    }

    private void createTables(ConnectionSource connSource) {
        try {
            TableUtils.createTableIfNotExists(connSource, FlightPlan.class);
            TableUtils.createTableIfNotExists(connSource, User.class);
            TableUtils.createTableIfNotExists(connSource, UserValue.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void version2Drop(ConnectionSource connSource) {
        try {
            DatabaseConnection rw = connSource.getReadWriteConnection();
            rw.closeQuietly();
            TableUtils.dropTable(connSource, FlightPlan.class, false);
            TableUtils.dropTable(connSource, User.class, false);
            TableUtils.dropTable(connSource, UserValue.class, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
