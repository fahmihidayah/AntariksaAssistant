package com.example.antariksaassistant;

import java.util.ArrayList;

import com.databasehelper.DataBaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.models.DataAkun;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class DataAkunActivity extends ActionBarActivity {

	private DataBaseHelper dataBaseHelper = null;

	@InjectView(R.id.editTextNamaPeserta)
	EditText editTextNamaPeserta;
	@InjectView(R.id.editTextNamaPengajar)
	EditText editTextNamaPengajar;
	@InjectView(R.id.editTextNamaAsisten)
	EditText editTextNamaAsisten;
	@InjectView(R.id.editTextNamaKelas)
	EditText editTextNamaKelas;
	@InjectView(R.id.editTextEmail)
	EditText editTextEmail;
	
	DataAkun dataAkun;
	private boolean isNew = true;

	@OnClick(R.id.buttonSimpan)
	public void onClickSimpan(View view) {
		if (isNew) {
			dataAkun = new DataAkun(editTextNamaPeserta.getText()
					.toString(), editTextNamaPengajar.getText().toString(),
					editTextNamaAsisten.getText().toString(), editTextNamaKelas
							.getText().toString());
			dataAkun.setEmail(editTextEmail.getText().toString());
			dataBaseHelper.getExceptionDaoAkun().create(dataAkun);
		} else {
			dataAkun.setNamaAsisten(editTextNamaAsisten.getText().toString());
			dataAkun.setNamaKelas(editTextNamaKelas.getText().toString());
			dataAkun.setNamaPengajar(editTextNamaPengajar.getText().toString());
			dataAkun.setNamaPeserta(editTextNamaPeserta.getText().toString());
			dataAkun.setEmail(editTextEmail.getText().toString());
			dataBaseHelper.getExceptionDaoAkun().update(dataAkun);
		}
		finish();
	}

	@SuppressLint("NewApi")
	private void initialComponent() {
		isNew = getIntent().getBooleanExtra("isNew", true);
		Long id = getIntent().getLongExtra("id",-1);
//		Toast.makeText(this, "new " + isNew + " id " + id , Toast.LENGTH_LONG).show();
		dataBaseHelper = getDataBaseHelper();
//		View customActionBar = getLayoutInflater().inflate(
//				R.layout.data_akun_action_bar, null);
//		getActionBar().setDisplayShowHomeEnabled(false);
//		getActionBar().setDisplayShowTitleEnabled(false);
//		getActionBar().setDisplayShowCustomEnabled(true);
//		getActionBar().setCustomView(customActionBar);
		if (!isNew) {
			id = getIntent().getLongExtra("id",0);
			dataAkun = getDataBaseHelper().getExceptionDaoAkun().queryForId(id);
			editTextNamaAsisten.setText(dataAkun.getNamaAsisten());
			editTextNamaKelas.setText(dataAkun.getNamaKelas());
			editTextNamaPengajar.setText(dataAkun.getNamaPengajar());
			editTextNamaPeserta.setText(dataAkun.getNamaPeserta());
			editTextEmail.setText(dataAkun.getEmail());
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.data_akun_activity);
		ButterKnife.inject(this);
		initialComponent();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (dataBaseHelper != null) {
			OpenHelperManager.releaseHelper();
			dataBaseHelper = null;
		}
	}

	private DataBaseHelper getDataBaseHelper() {
		if (dataBaseHelper == null) {
			dataBaseHelper = OpenHelperManager.getHelper(this,
					DataBaseHelper.class);
		}
		return dataBaseHelper;
	}
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// // Inflate the menu; this adds items to the action bar if it is present.
	// getMenuInflater().inflate(R.menu.main, menu);
	// return true;
	// }

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }
}
