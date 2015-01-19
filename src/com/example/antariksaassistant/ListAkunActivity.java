package com.example.antariksaassistant;

import java.util.ArrayList;
import java.util.List;

import com.databasehelper.DataBaseHelper;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.models.DataAkun;
import com.models.Materi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class ListAkunActivity extends FragmentActivity{
	private DataBaseHelper dataBaseHelper = null;
	
	@InjectView(R.id.listViewAkun)
	ListView listViewAkun ;
	
	@OnItemClick(R.id.listViewAkun)
	public void onItemClickListViewMateri(int position){
		Intent intent = new Intent(this, PenilaianActivity.class);
		intent.putExtra("materi", materi);
		DataAkun dataAkun = listDataAkun.get(position);
		intent.putExtra("id", dataAkun.getId());
		startActivity(intent);
	}
	
	List<DataAkun> listDataAkun = new ArrayList<>();
	
	Materi materi;
	
	CustomAdapter<DataAkun> customAdapter;
	
	@SuppressLint("NewApi")
	private void initialComponent(){
		
		materi = (Materi) getIntent().getSerializableExtra("materi");
		
//		View customActionBar = getLayoutInflater().inflate(
//				R.layout.list_akun_action_bar, null);
//		getActionBar().setDisplayShowHomeEnabled(false);
//		getActionBar().setDisplayShowTitleEnabled(false);
//		getActionBar().setDisplayShowCustomEnabled(true);
//		getActionBar().setCustomView(customActionBar);

		listDataAkun = getDataBaseHelper().getExceptionDaoAkun().queryForAll();
		
		
		customAdapter = new CustomAdapter<DataAkun>(this, R.layout.item_data_akun, listDataAkun) {
			
			@Override
			public void setViewItems(View view, DataAkun data) {
				ViewSetterUtilities.setTextToView(view, R.id.textViewNama, "Nama : " + data.getNamaPeserta()+ "\nPengajar : "+ data.getNamaPengajar() + "\nAsisten : " + data.getNamaAsisten());
			}
		};
		listViewAkun.setAdapter(customAdapter);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_akun_activity);
		ButterKnife.inject(this);
		initialComponent();
	}
	

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(dataBaseHelper != null){
			OpenHelperManager.releaseHelper();
			dataBaseHelper = null;
		}
	}
	
	private DataBaseHelper getDataBaseHelper(){
		if(dataBaseHelper == null){
			dataBaseHelper = OpenHelperManager.getHelper(this, DataBaseHelper.class);
		}
		return dataBaseHelper;
	}
}
