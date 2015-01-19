package com.example.antariksaassistant;

import java.util.ArrayList;

import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.models.Materi;

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
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnItemClick;

public class ListMateriActivity extends FragmentActivity{
	@InjectView(R.id.listViewMateri)
	ListView listViewMateri ;
	
	@OnItemClick(R.id.listViewMateri)
	public void onItemClickListViewMateri(int position){
		Intent intent = new Intent(this, ListAkunActivity.class);
		Materi materi = listMateri.get(position);
		intent.putExtra("materi", materi);
		startActivity(intent);
	}
	
	ArrayList<Materi> listMateri = new ArrayList<>();
	
	CustomAdapter<Materi> customAdapter;
	
	
	@SuppressLint("NewApi")
	private void initialComponent(){
//		View customActionBar = getLayoutInflater().inflate(
//				R.layout.list_materi_action_bar, null);
//		getActionBar().setDisplayShowHomeEnabled(false);
//		getActionBar().setDisplayShowTitleEnabled(false);
//		getActionBar().setDisplayShowCustomEnabled(true);
//		getActionBar().setCustomView(customActionBar);
		listMateri.add(new Materi(1, "Perkenalan Tajwid, Hukum dan Faedah"));
		listMateri.add(new Materi(2, "Pengenalan Huruf Hijaiyah"));
		listMateri.add(new Materi(3, "Tasydid, sukum dan alif lam"));
		listMateri.add(new Materi(4, "Wakaf, jenis-jenis wakaf dan cara berwakaf"));
		listMateri.add(new Materi(5, "Mad sesi 1, 2 harakat"));
		listMateri.add(new Materi(6, "Mad sesi 2, 4 harakat dan 6 harakat"));
		listMateri.add(new Materi(7, "Ghunah sesi 1, mim nun tasydid"));
		listMateri.add(new Materi(8, "Ghunah sesi 2 dan Ghunah yang berhubungan dengan : Nun mati dan tanwin, nun mati bertamu dengan ba"));
		listMateri.add(new Materi(9, "Kesempurnaan Vokal (Kevo)"));
		listMateri.add(new Materi(10, "Qolqolah"));
		listMateri.add(new Materi(11, "Ayat-ayat gharibah"));
		listMateri.add(new Materi(12, "Ujian akhir"));

		customAdapter = new CustomAdapter<Materi>(this, R.layout.item_data_akun, listMateri) {
			
			@Override
			public void setViewItems(View view, Materi data) {
				ViewSetterUtilities.setTextToView(view, R.id.textViewNama, "Pekan ke "+ data.getMinggu() + "\n"+  data.getMateri());
			}
		};
		listViewMateri.setAdapter(customAdapter);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_materi_activity);
		ButterKnife.inject(this);
		initialComponent();

	}
	
	
}
