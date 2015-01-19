package com.example.antariksaassistant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.databasehelper.DataBaseHelper;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.models.DataAkun;
import com.models.NilaiMingguan;

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
import android.graphics.Rect;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfDocument.Page;
import android.graphics.pdf.PdfDocument.PageInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ReportActivity extends Activity {
	private DataBaseHelper dataBaseHelper = null;
	
	DataAkun dataAkun;
	NilaiMingguan nilaiMingguan;
	
	@InjectView(R.id.textViewLaporan)
	TextView textViewLaporan;
	
	@SuppressLint("NewApi")
	private void initialComponent() {
//		View customActionBar = getLayoutInflater().inflate(
//				R.layout.report_action_bar, null);
//		getActionBar().setDisplayShowHomeEnabled(false);
//		getActionBar().setDisplayShowTitleEnabled(false);
//		getActionBar().setDisplayShowCustomEnabled(true);
//		getActionBar().setCustomView(customActionBar);
//		Button buttonCreateReport = (Button) customActionBar.findViewById(R.id.buttonReport);
//		buttonCreateReport.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				createReport();
//			}
//		});
		Long idAkun = getIntent().getLongExtra("id_akun", new Long(0));
		Long idNilaiMingguan = getIntent().getLongExtra("id_nilai_mingguan", new Long(0));
		dataAkun = getDataBaseHelper().getExceptionDaoAkun().queryForId(idAkun);
		for (NilaiMingguan e : dataAkun.getListNilaiMingguan()){
			if(e.getId().equals(idNilaiMingguan)){
				nilaiMingguan = e;
				break;
			}
		}
		if(nilaiMingguan == null){
			Toast.makeText(this, "Maaf nilai harus disimpan terlebih dahulu", Toast.LENGTH_LONG).show();
			finish();
			return;
		}
		
		
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_activity);
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
	
	public void createReport1(){
		
	}
	
	@SuppressLint("NewApi")
	public void createReport(){
		PdfDocument document = new PdfDocument();
		PageInfo pageInfo = new PageInfo.Builder(100, 100, 1).create();
		Page page = document.startPage(pageInfo);
		View content = findViewById(android.R.id.content);
		content.draw(page.getCanvas());
		document.finishPage(page);
		
		
		File rootPath = new File(Environment.getExternalStorageDirectory(), "Antariksa Assistant");
    	if(!rootPath.exists()){
    		rootPath.mkdirs();
    	}
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
    	
    	File file = new File(rootPath, dataAkun.getNamaPeserta() + "_" + nilaiMingguan.getPekan() + "_" + nilaiMingguan.getNamaMateri() + "_" + simpleDateFormat.format(new Date()));
    	ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			document.writeTo(oos);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		document.close();
		Toast.makeText(this, "laporan berhasil dibuat ", Toast.LENGTH_LONG).show();
		finish();
	}
	
}
