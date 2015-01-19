package com.example.antariksaassistant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.databasehelper.DataBaseHelper;
import com.framework.adapter.CustomAdapter;
import com.framework.common_utilities.ViewSetterUtilities;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.models.Constantas;
import com.models.DataAkun;
import com.models.Materi;
import com.models.NilaiMingguan;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class PenilaianActivity extends OrmLiteBaseActivity<DataBaseHelper> implements Constantas{
	
	
	Materi materi;
	DataAkun dataAkun;
	NilaiMingguan nilaiMingguan;
	int index = 0;
	@SuppressLint("NewApi")
	private void initialComponent() {
		materi = (Materi) getIntent().getSerializableExtra("materi");
//		dataAkun ;
		Long id = (Long) getIntent().getLongExtra("id", new Long(-1));
		dataAkun = getHelper().getExceptionDaoAkun().queryForId(id);
		textViewMateri.setText("Materi : "+ materi.getMateri());
		
		for (NilaiMingguan e : dataAkun.getListNilaiMingguan()) {
			if(e.getNamaMateri().equalsIgnoreCase(materi.getMateri())){
				nilaiMingguan = e;
				break;
			}
			index++;
		}
		if(nilaiMingguan == null){
			nilaiMingguan = new NilaiMingguan();
		}
		nilaiMingguan.setNamaMateri(materi.getMateri());
		nilaiMingguan.setPekan((long)materi.getMinggu());
		
//		View customActionBar = getLayoutInflater().inflate(
//				R.layout.penilaian_action_bar, null);
//		getActionBar().setDisplayShowHomeEnabled(false);
//		getActionBar().setDisplayShowTitleEnabled(false);
//		getActionBar().setDisplayShowCustomEnabled(true);
//		getActionBar().setCustomView(customActionBar);
		
		radioGroupKehadiran.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				RadioButton radioButton = (RadioButton) PenilaianActivity.this.findViewById(checkedId);
				if(radioButton == radioTanpaKeterangan){
					textViewKeteranganTidakHadir.setVisibility(View.VISIBLE);
					editTextKeteranganTidakHadir.setVisibility(View.VISIBLE);
					nilaiMingguan.setHadir(false);
				}else {
					textViewKeteranganTidakHadir.setVisibility(View.GONE);
					editTextKeteranganTidakHadir.setVisibility(View.GONE);
					if(radioButton == radioHadir){
						nilaiMingguan.setHadir(true);
					}
					else {
						nilaiMingguan.setHadir(false);
					}
				}
			}
		});
		checkBoxmenyetorkan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					spinnerKeterangan.setVisibility(View.VISIBLE);
					textViewKeteranganGKB.setVisibility(View.VISIBLE);
					editTextKeteranganGKB.setVisibility(View.VISIBLE);
				}
				else {
					spinnerKeterangan.setVisibility(View.GONE);
					textViewKeteranganGKB.setVisibility(View.GONE);
					editTextKeteranganGKB.setVisibility(View.GONE);
				}
			}
		});
		
		textViewKeteranganTidakHadir.setVisibility(View.GONE);
		editTextKeteranganTidakHadir.setVisibility(View.GONE);
		setNilaiMingguanToView(nilaiMingguan);
	}

	@InjectView(R.id.textViewMateri)
	TextView textViewMateri;
	
	@InjectView(R.id.textViewNilai)
	TextView textViewNilai;
	
	@InjectView(R.id.radioGroupKehadiran)
	RadioGroup radioGroupKehadiran;
	
	@InjectView(R.id.radioHadir)
	RadioButton radioHadir;
	
	@InjectView(R.id.radioIzin)
	RadioButton radioIzin;
	
	@InjectView(R.id.radioSakit)
	RadioButton radioSakit;
	
	@InjectView(R.id.radioTanpaKeterangan)
	RadioButton radioTanpaKeterangan;
	
	@InjectView(R.id.textViewKeteranganTidakHadir)
	TextView textViewKeteranganTidakHadir;
	
	@InjectView(R.id.editTextKeteranganTidakHadir)
	EditText editTextKeteranganTidakHadir;
	
	@InjectView(R.id.checkBoxmenyetorkan)
	CheckBox checkBoxmenyetorkan;
	
	@InjectView(R.id.spinnerKeterangan)
	Spinner spinnerKeterangan;
	
	@InjectView(R.id.editTextIsian)
	EditText editTextIsian;
	
	@InjectView(R.id.editTextBenarSalah)
	EditText editTextBenarSalah;
	
	@InjectView(R.id.editTextPilihanGanda)
	EditText editTextPilihanGanda;
	
	@InjectView(R.id.editTextKeteranganBenarSalah)
	EditText editTextKeteranganBenarSalah;
	
	@InjectView(R.id.editTextKeteranganIsian)
	EditText editTextKeteranganIsian;
	
	@InjectView(R.id.editTextKeteranganPilihanGanda)
	EditText editTextKeteranganPilihanGanda;
	
	@InjectView(R.id.editTextKeteranganGKB)
	EditText editTextKeteranganGKB;
	
	@InjectView(R.id.textViewKeteranganGKB)
	TextView textViewKeteranganGKB;
	
	@OnClick(R.id.buttonSave)
	public void onClickButtonSave(View view){
		nilaiMingguan.setDataAkun(dataAkun);
		setKehadiran(nilaiMingguan);
		setNilaiSetoran(nilaiMingguan);
		setNilaiPot(nilaiMingguan);
		nilaiMingguan.hitungTotalNilai();
		nilaiMingguan.setKeteranganBenarSalah(editTextKeteranganBenarSalah.getText().toString());
		nilaiMingguan.setKeteranganIsian(editTextKeteranganIsian.getText().toString());
		nilaiMingguan.setKeteranganPilihanGanda(editTextKeteranganPilihanGanda.getText().toString());
		if(nilaiMingguan.getId() == null){
//			nilaiMingguan.setDataAkun(dataAkun);
			dataAkun.getListNilaiMingguan().add(nilaiMingguan);
			
//			getHelper().getExceptionDaoNilaiMingguan().create(nilaiMingguan);
		}
		else{
//			getHelper().getExceptionDaoNilaiMingguan().update(nilaiMingguan);
			dataAkun.getListNilaiMingguan().remove(nilaiMingguan);
			dataAkun.getListNilaiMingguan().add(nilaiMingguan);
		}
//		finish();
		Toast.makeText(this, "Data telah disimpan", Toast.LENGTH_LONG).show();
	}
	
	@OnClick(R.id.buttonLihatNilaiTotal)
	public void onClickLihatNilaiTotal(View view){
		nilaiMingguan.setDataAkun(dataAkun);
		setKehadiran(nilaiMingguan);
		setNilaiSetoran(nilaiMingguan);
		setNilaiPot(nilaiMingguan);
		nilaiMingguan.hitungTotalNilai();
//		Toast.makeText(this, "" + nilaiMingguan.getNilaiGkb() + "," + nilaiMingguan.getNilaiBenarSalah()+
//				"," + nilaiMingguan.getNilaiIsian() +"," +nilaiMingguan.getNilaiPilihanGanda(), Toast.LENGTH_LONG).show();
//		Toast.makeText(this, "nilai " + nilaiMingguan.getTotalNilai(), Toast.LENGTH_LONG).show();
		textViewNilai.setText("Nilai\n"+nilaiMingguan.getNilaiTotalHuruf());
		
	}
	@OnClick(R.id.buttonReport)
	public void onClickButtonReport(View view){
		Document doc = new Document();
		try{
			String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" +FOLDER_APP;
			File dir = new File(path);
			if(!dir.exists()){
				dir.mkdirs();
			}
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			File file = new File(dir, dataAkun.getNamaPeserta() + "_" + nilaiMingguan.getNamaMateri() + ".pdf");
			if(file.exists()){
				file.delete();
			}
			FileOutputStream fOut = new FileOutputStream(file);

			PdfWriter.getInstance(doc, fOut);

			//open the document
			doc.open();
			
			Paragraph p1 = new Paragraph("Laporan Mingguan Antariksa\nPekan " + nilaiMingguan.getPekan()+ " - " + nilaiMingguan.getNamaMateri());
			/* Create Set Font and its Size */
			Font paraFont= new Font(Font.TIMES_ROMAN);
			paraFont.setSize(20);
			p1.setAlignment(Paragraph.ALIGN_CENTER);
			p1.setFont(paraFont);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			 Bitmap bitmap = BitmapFactory.decodeResource(getBaseContext().getResources(), R.drawable.ic_report);
             bitmap.compress(Bitmap.CompressFormat.JPEG, 20 , stream);
             Image myImg = Image.getInstance(stream.toByteArray());
             myImg.setAlignment(Image.MIDDLE);
             doc.add(myImg);
			//add paragraph to document    
			doc.add(p1);
			
			Paragraph p2 = new Paragraph(
					 "Data Peserta \n\n" +
					String.format("%s%s","Peserta                     : " , dataAkun.getNamaPeserta()) +"\n" +
					String.format("%s%s","Pengajar    	              : " , dataAkun.getNamaPengajar()) +"\n" +
					String.format("%s%s","Asisten                     : " , dataAkun.getNamaAsisten()) +"\n" +
					String.format("%s%s","Email                        : " , dataAkun.getEmail()) +"\n" +
					String.format("%s%s","Kelas                        : " , dataAkun.getNamaKelas()) +"\n" +
					
					"\n" +
					"Nilai \n\n" +
					String.format("%s%s","Nama Materi       	    : " , nilaiMingguan.getNamaMateri()) + "\n" +
					String.format("%s%s","Pekan                      : " , nilaiMingguan.getPekan()) +"\n" +
					String.format("%s%s","Kehadiran                : " , nilaiMingguan.getHadir()? "Hadir": "Tidak Hadir") +"\n" +
					String.format("%s%s","Keterangan              : " ,  nilaiMingguan.getKeteranganKehadiran()) +"\n" +
					String.format("%s%s","Nilai GKB                 : " , nilaiMingguan.getNilaiGkb()) +"\n" +
					String.format("%s%s","Nilai Isian                 : " ,  nilaiMingguan.getNilaiIsian()) +"\n" +
					String.format("%s%s","Nilai Pilihan Ganda : " ,nilaiMingguan.getNilaiPilihanGanda()) +"\n" +
					String.format("%s%s","Nilai Benar Salah    : " , nilaiMingguan.getNilaiBenarSalah()) +"\n" +
					String.format("%s%s","Total Nilai                 : " , nilaiMingguan.getNilaiTotalHuruf()) + "\n\n" +
					"Keterangan GKB :\n" + nilaiMingguan.getKeteranganGkb() + "\n"+
					"Keterangan Isian :\n" + nilaiMingguan.getKeteranganIsian() + "\n"+
					"Keterangan Pilihan Ganda :\n" + nilaiMingguan.getKeteranganPilihanGanda() + "\n"+
					"Keterangan Benar Salah :\n" + nilaiMingguan.getKeteranganBenarSalah() + "\n"
					);
			/* Create Set Font and its Size */
			Font paraFont2= new Font(Font.TIMES_ROMAN);
			paraFont.setSize(16);
			p2.setAlignment(Paragraph.ALIGN_JUSTIFIED);
			p2.setFont(paraFont2);
			doc.add(p2);
//			Toast.makeText(getApplicationContext(), "Laporan berhasil dibuat", Toast.LENGTH_LONG).show();
			sendEmail(dataAkun.getEmail(), file);
			
		}catch (DocumentException de) {
			Toast.makeText(getApplicationContext(), "Doc ex", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			Toast.makeText(getApplicationContext(), "IO ex", Toast.LENGTH_LONG).show();
		}
		finally{
			doc.close();
		}
		
	}
	
	public void sendEmail(String email, File fileLocation){
		Uri uriFile= Uri.fromFile(fileLocation);    
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		// set the type to 'email'
		emailIntent .setType("vnd.android.cursor.dir/email");
		String to[] = {email};
		emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
		// the attachment
		emailIntent .putExtra(Intent.EXTRA_STREAM, uriFile);
		// the mail subject
		emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Laporan Nilai Antariksa - Pekan " +nilaiMingguan.getPekan() +" - " + nilaiMingguan.getNamaMateri());
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Assalamualaikum\n Dear "+dataAkun.getNamaPeserta()+"\n Berikut ini adalah laporan nilai pekan " +nilaiMingguan.getPekan() +" dengan materi " + nilaiMingguan.getNamaMateri() + "\n\nTerimakasih Wasalamualakum wr wb" );
		startActivity(Intent.createChooser(emailIntent , "Send email..."));
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.penilaian_activity);
		ButterKnife.inject(this);
		initialComponent();
	}
	
	
	
	private void setKehadiran(NilaiMingguan nilaiMingguan){
		RadioButton radioButtonSelected = (RadioButton) findViewById(radioGroupKehadiran.getCheckedRadioButtonId());
		if(radioButtonSelected == radioHadir){
			nilaiMingguan.setHadir(true);
		}
		else {
			nilaiMingguan.setHadir(false);
			if(radioButtonSelected == radioIzin){
				nilaiMingguan.setKeteranganKehadiran("Izin");
			}
			if(radioButtonSelected == radioSakit){
				nilaiMingguan.setKeteranganKehadiran("sakit");
			}
			else{
				nilaiMingguan.setKeteranganKehadiran(editTextKeteranganTidakHadir.getText().toString());	
			}
		}
	}
	
	private void setNilaiSetoran(NilaiMingguan nilaiMingguan){
		if(checkBoxmenyetorkan.isChecked()){
			nilaiMingguan.setGkb(true);
			if(spinnerKeterangan.getSelectedItemPosition() == 0){
				nilaiMingguan.setNilaiGkb(100);
			}
			else if(spinnerKeterangan.getSelectedItemPosition() == 1){
				nilaiMingguan.setNilaiGkb(84);
			}
			else if(spinnerKeterangan.getSelectedItemPosition() == 2){
				nilaiMingguan.setNilaiGkb(74);
			}
			else if(spinnerKeterangan.getSelectedItemPosition() == 3){
				nilaiMingguan.setNilaiGkb(49);
			}
			else {
				nilaiMingguan.setNilaiGkb(0);
			}
			nilaiMingguan.setKeteranganGkb(editTextKeteranganGKB.getText().toString());
		}
		else {
			nilaiMingguan.setGkb(false);
		}
		
	}
	
	private void setNilaiPot(NilaiMingguan nilaiMingguan){
		nilaiMingguan.setNilaiIsian(Integer.parseInt(editTextIsian.getText().toString()));
		nilaiMingguan.setNilaiPilihanGanda(Integer.parseInt(editTextPilihanGanda.getText().toString()));
		nilaiMingguan.setNilaiBenarSalah(Integer.parseInt(editTextBenarSalah.getText().toString()));
	}
	
	private void setNilaiMingguanToView(NilaiMingguan nilaiMingguan){
		if(nilaiMingguan.getId() != null){
			if(!nilaiMingguan.getHadir()){
				if(nilaiMingguan.getKeteranganKehadiran().equalsIgnoreCase("sakit")){
					radioSakit.setChecked(true);
				}
				else if(nilaiMingguan.getKeteranganKehadiran().equalsIgnoreCase("Izin")) {
					radioIzin.setChecked(true);
				}
				else {
					 radioTanpaKeterangan.setChecked(true);
					 editTextKeteranganTidakHadir.setText(nilaiMingguan.getKeteranganKehadiran());
				}
			}
			else{
				radioHadir.setChecked(true);
			}
			
			if(nilaiMingguan.getGkb()){
				checkBoxmenyetorkan.setChecked(true);
				int index = 0;
				if(nilaiMingguan.getNilaiGkb() == 100){
					index = 0;
				}else if(nilaiMingguan.getNilaiGkb() == 84){
					index = 1;
				}else if(nilaiMingguan.getNilaiGkb() == 74){
					index = 2;
				}else if(nilaiMingguan.getNilaiGkb() == 49){
					index = 3;
				}else {
					index = 0;
				}
				spinnerKeterangan.setSelection(index);
			}else {
				checkBoxmenyetorkan.setChecked(false);
			}
			
			editTextIsian.setText(nilaiMingguan.getNilaiIsian() + "");
			editTextPilihanGanda.setText(nilaiMingguan.getNilaiPilihanGanda() + "");
			editTextBenarSalah.setText(nilaiMingguan.getNilaiBenarSalah()+ "");
			editTextKeteranganBenarSalah.setText(nilaiMingguan.getKeteranganBenarSalah());
			editTextKeteranganIsian.setText(nilaiMingguan.getKeteranganIsian());
			editTextKeteranganPilihanGanda.setText(nilaiMingguan.getKeteranganPilihanGanda());
			editTextKeteranganGKB.setText(nilaiMingguan.getKeteranganGkb());
			textViewNilai.setText("Nilai\n"+nilaiMingguan.getNilaiTotalHuruf());
		}
	}
}
