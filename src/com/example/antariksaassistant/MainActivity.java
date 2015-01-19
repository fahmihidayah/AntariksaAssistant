package com.example.antariksaassistant;


import butterknife.ButterKnife;
import butterknife.OnClick;
import android.support.v4.app.FragmentActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends FragmentActivity {

	@OnClick(R.id.buttonAkunDataBaru)
	public void onClickButtonAkunDataBaru(View view){
		startActivity(new Intent(this, AkunDataBaruActivity.class));
	}
	@OnClick(R.id.ButtonTambahData)
	public void onClickButtonTambahData(View view){
		startActivity(new Intent(this, ListMateriActivity.class));
	}
	
	
	@SuppressLint("NewApi")
	private void initialComponent(){
//		View customActionBar = getLayoutInflater().inflate(
//				R.layout.main_action_bar, null);
//		getActionBar().setDisplayShowHomeEnabled(false);
//		getActionBar().setDisplayShowTitleEnabled(false);
//		getActionBar().setDisplayShowCustomEnabled(true);
//		getActionBar().setCustomView(customActionBar);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initialComponent();
		ButterKnife.inject(this);
	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
