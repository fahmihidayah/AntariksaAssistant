package com.example.antariksaassistant;

import java.util.ArrayList;
import java.util.List;

import com.databasehelper.DataBaseHelper;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.models.DataAkun;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import butterknife.OnItemLongClick;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class AkunDataBaruActivity extends FragmentActivity {

	private DataBaseHelper dataBaseHelper = null;
	
	ArrayList<DataAkun> listDataAkun = new ArrayList<>();

	CustomAdapter<DataAkun> customAdapter;

	int selectedId = -1;
	
	@InjectView(R.id.listViewAkun)
	ListView listViewAkun;
	
	@InjectView(R.id.textViewJumlahPeserta)
	TextView textViewJumlahPeserta;

	@OnClick(R.id.buttonTambah)
	public void onClickTambah(View view) {
		startActivity(new Intent(this, DataAkunActivity.class));
	}
	
	@OnItemLongClick(R.id.listViewAkun)
	public boolean onItemLongClickListViewAkun(int position){
		selectedId = position;
		OptionDialog optionDialog = new OptionDialog();
		optionDialog.show(getSupportFragmentManager(), "option_dialog");
		return true;
	}

	@SuppressLint("NewApi")
	private void initialComponent() {

//		View customActionBar = getLayoutInflater().inflate(
//				R.layout.akun_data_baru_action_bar, null);
//		
//		getActionBar().setDisplayShowHomeEnabled(false);
//		getActionBar().setDisplayShowTitleEnabled(false);
//		getActionBar().setDisplayShowCustomEnabled(true);
//		getActionBar().setCustomView(customActionBar);
		customAdapter = new CustomAdapter<DataAkun>(this,
				R.layout.item_data_akun, listDataAkun) {

			@Override
			public void setViewItems(View view, DataAkun data) {
				ViewSetterUtilities.setTextToView(view, R.id.textViewNama,
						data.getNamaPeserta());
			}
		};
		listViewAkun.setAdapter(customAdapter);
	}

	@Override
	protected void onResume() {
		List<DataAkun> listAkun = getDataBaseHelper().getExceptionDaoAkun()
				.queryForAll();
		listDataAkun.clear();
		textViewJumlahPeserta.setText(""+listAkun.size());
		listDataAkun.addAll(listAkun);
		customAdapter.notifyDataSetChanged();
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.akun_data_baru_activity);
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
	
	public class OptionDialog extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the Builder class for convenient dialog construction
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setItems(R.array.option_edit_delete, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
//	            	   Toast.makeText(getActivity(), "which " + which, Toast.LENGTH_LONG).show();
	            	   switch (which) {
					case 0:
						Intent intent = new Intent(getActivity(), DataAkunActivity.class);
						intent.putExtra("isNew", false);
						intent.putExtra("id", listDataAkun.get(selectedId).getId());
						AkunDataBaruActivity.this.startActivity(intent);
						break;
					case 1 :
						DeletAkunDialog deletAkunDialog = new DeletAkunDialog();
						deletAkunDialog.show(getFragmentManager(), "delete_akun_dialog");
						break;
					default:
						break;
					}
	               }
			});
			// Create the AlertDialog object and return it
			return builder.create();
		}
	}

	public class DeletAkunDialog extends DialogFragment {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the Builder class for convenient dialog construction
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("Apakah anda ingin menghapus data ini?")
					.setPositiveButton("Iya",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									if(selectedId != -1){
										DataAkun dataAkun = listDataAkun.get(selectedId);
										listDataAkun.remove(selectedId);
										textViewJumlahPeserta.setText(""+listDataAkun.size());
										getDataBaseHelper().getExceptionDaoAkun().delete(dataAkun);
										customAdapter.notifyDataSetChanged();
										selectedId = -1;
									}
									
									
								}
							})
					.setNegativeButton("Tidak",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {

								}
							});
			// Create the AlertDialog object and return it
			return builder.create();
		}
	}
}
