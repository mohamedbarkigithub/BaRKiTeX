package com.mohamed.barki.latex;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class LoginActivity extends Activity
{
	final Context contextSave = LoginActivity.this;
	@Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        Function.saveFromText(contextSave, "screen", "false");
		if(Function.getValue(contextSave, "screen").equals("true")){getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);}
		setContentView(R.layout.login);

		initialisation();
	}
    String str1 = Function.st, str2 = Function.stt, str3 = Function.sttt, str4 = Function.stttt, str5 = Function.sttttt;
	@Override
    public void onBackPressed() {super.onBackPressed();}
	@Override
	protected void onSaveInstanceState(@NonNull Bundle outState) {
		super.onSaveInstanceState(outState);
	}
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		initialisation();
	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			Function.trimCache(LoginActivity.this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void initialisation() {
		ImageView imgView = findViewById(R.id.webView);

		Glide.with(this).asGif().load(R.raw.gear_duo).into(imgView);

		if(getPackageName().compareTo(str1+str2+str3+str4) != 0){
			Function.showToastMessage(this, getString(R.string.worng_package));
		}else if(!Function.getApplicationName(this).equals(str5)){
			Function.showToastMessage(this, getString(R.string.worng_name));
		}else {
			new android.os.Handler().postDelayed(() -> {
				startActivity(new Intent(LoginActivity.this, GroupActivity.class));
				finish();
			}, 2800);
		}
	}
	@Override
	protected void onResume() {
		super.onResume();
	}
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}
	@Override
	protected void onStop() {super.onStop();}
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}
	@Override
	public void onConfigurationChanged(@NonNull Configuration newConfig) {super.onConfigurationChanged(newConfig);}
}

