package com.databasehelper;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.models.DataAkun;
import com.models.NilaiMingguan;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper{

	private Context context;
	private static String DATABASE_NAME = "antariksa_assistant.db";
	private static int DATABASE_VERSION = 1;
	private RuntimeExceptionDao<DataAkun, Long> exceptionDaoKontak ;
	private RuntimeExceptionDao<NilaiMingguan, Long> exceptionDaoNilaiMingguan ;
	
	public DataBaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}
	
	@Override
	public void onCreate(SQLiteDatabase arg0, ConnectionSource arg1) {
		try {
			TableUtils.createTable(arg1, DataAkun.class);
			TableUtils.createTable(arg1, NilaiMingguan.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, ConnectionSource arg1, int arg2,
			int arg3) {
		try {
			TableUtils.dropTable(arg1, DataAkun.class, false);
			TableUtils.dropTable(arg1, NilaiMingguan.class, false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public RuntimeExceptionDao<DataAkun, Long> getExceptionDaoAkun(){
		if(exceptionDaoKontak == null){
			try {
				exceptionDaoKontak = RuntimeExceptionDao.createDao(getConnectionSource(), DataAkun.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return exceptionDaoKontak;
	}
	
	public RuntimeExceptionDao<NilaiMingguan, Long> getExceptionDaoNilaiMingguan(){
		if(exceptionDaoKontak == null){
			try {
				exceptionDaoNilaiMingguan = RuntimeExceptionDao.createDao(getConnectionSource(), NilaiMingguan.class);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return exceptionDaoNilaiMingguan;
	}

}
