package com.mohamed.barki.latex;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.mohamed.barki.latex.latex.LatexEditor;

import java.io.File;
import java.lang.Character.UnicodeBlock;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class Function extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		
	}
	public static boolean isNetworkConnected(Context getAppContext) {
		ConnectivityManager cm = (ConnectivityManager) getAppContext.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
		return cm.getActiveNetwork() != null && cm.getNetworkCapabilities(cm.getActiveNetwork()) != null && activeNetworkInfo != null && activeNetworkInfo.isConnected();
	}
	public static boolean isAdmin(Activity activity) {
		return Function.isPackageInstalled(activity, activity.getString(R.string.pkgadmin));
	}

	public static boolean isPackageInstalled(Context getAppContext, String packageName) {
		PackageManager packageManager = getAppContext.getPackageManager();
		try {
			packageManager.getPackageInfo(packageName, 0);
			return true;
		} catch (PackageManager.NameNotFoundException e) {
			return false;
		}
	}
	public static boolean validate(Context cntx, LatexEditor edt, String ss) {
        
		if (ss.isEmpty()) {
            edt.setError(Html.fromHtml("<font color='red'>"+cntx.getString(R.string.error)+"</font>"));
			showToastMessage(cntx, cntx.getString(R.string.text_empty));
			return true;
        }else{
			if (ss.length()>500) {
				edt.setError(Html.fromHtml("<font color='red'>"+cntx.getString(R.string.error)+"</font>"));
				showToastMessage(cntx, cntx.getString(R.string.validate_empty));
				return true;
			}
		}

        return false;
    }
    public static boolean validateL2(Context cntx, EditText edt, String numGroup) {
        String ss = edt.getText().toString();
        if (ss.isEmpty()) {
            edt.setError(Html.fromHtml("<font color='red'>"+cntx.getString(R.string.error)+"</font>"));
            showToastMessage(cntx, cntx.getString(R.string.text_empty));
            return false;
        }else{
            if (ss.length()>15) {
                edt.setError(Html.fromHtml("<font color='red'>"+cntx.getString(R.string.error)+"</font>"));
                showToastMessage(cntx, cntx.getString(R.string.validate_empty));
                return false;
            }else{
                int nbrItem = Integer.parseInt(Function.getValue(cntx, "nbrItem@"+numGroup));
                int i = 0;
                while(i<=nbrItem){
                    if(ss.equals(Function.getValue(cntx, "itemT@"+numGroup+ i))){
                        edt.setError(Html.fromHtml("<font color='red'>"+cntx.getString(R.string.error)+"</font>"));
                        showToastMessage(cntx, cntx.getString(R.string.other_name));
                        return false;
                    }else{i++;}
                }

            }
        }

        return true;
    }
    public static boolean validate2(Context cntx, EditText edt) {
        String ss = edt.getText().toString();
        if (ss.isEmpty()) {
            edt.setError(Html.fromHtml("<font color='red'>"+"Error"+"</font>"));
            showToastMessage(cntx, cntx.getString(R.string.text_empty));
            return false;
        }else{
            if (ss.length()>15) {
                edt.setError(Html.fromHtml("<font color='red'>"+"Error"+"</font>"));
                showToastMessage(cntx, cntx.getString(R.string.validate_empty));
                return false;
            }else{
                int nbrItemGroup = Integer.parseInt(Function.getValue(cntx, "nbrItemGroup"));
                int i = 0;
                while(i<=nbrItemGroup){
                    if(ss.equals(Function.getValue(cntx, "itemGroupT@"+ i))){
                        edt.setError(Html.fromHtml("<font color='red'>"+"Error"+"</font>"));
                        showToastMessage(cntx, cntx.getString(R.string.other_name));
                        return false;
                    }else{i++;}
                }
            }
        }

        return true;
    }
    public static void showToastMessage(Context getAppContext, String text) {
		Toast.makeText(getAppContext, text, Toast.LENGTH_SHORT).show();
	}
	public static String getValue(Context getAppContext, String key) {
		SharedPreferences prefs = getAppContext.getSharedPreferences(getApplicationName(getAppContext), MODE_PRIVATE);
		return prefs.getString(key, "");
	}
	public static void saveFromText(Context getAppContext, String key, String text) {
		SharedPreferences prefs = getAppContext.getSharedPreferences(getApplicationName(getAppContext), MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, text).apply();
	}
	public static void removeSaveText(Context getAppContext, String key) {
        SharedPreferences prefs = getAppContext.getSharedPreferences(getApplicationName(getAppContext), MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key).apply();
	}
	public static void doCopy(Context cntx, String text)  {
		ClipboardManager clipboardManager;
		clipboardManager = (ClipboardManager) cntx.getSystemService(Context.CLIPBOARD_SERVICE);
		//String txtCopy = this.edt.getText().toString();
        ClipData clipData = ClipData.newPlainText("text", text);
        // Copy ClipData to Clipboard.
        clipboardManager.setPrimaryClip(clipData);
        showToastMessage(cntx, cntx.getString(R.string.copy_text_to_clip));
    }
	@SuppressLint("AppCompatCustomView")
	public static class HeliVnTextView extends TextView {
		private static Typeface mTypeface;
		public HeliVnTextView(final Context context) {
			this(context, null);
		}
		public HeliVnTextView(final Context context, final AttributeSet attrs) {
			this(context, attrs, 0);
		}
		public HeliVnTextView(final Context context, final AttributeSet attrs, final int defStyle) {
			super(context, attrs, defStyle);

			if (mTypeface == null) {
				mTypeface = Typeface.createFromAsset(context.getAssets(), "molhim.ttf");
			}
			setTypeface(mTypeface);
		}
	}
	public static void hideKeyboard(Context cntx, LatexEditor edt) {
		// hide keyboard
		InputMethodManager inputManager = (InputMethodManager) cntx.getSystemService(Context.INPUT_METHOD_SERVICE);
		inputManager.hideSoftInputFromWindow(edt.getWindowToken(), 0);
	}
    public static void hideKeyboard2(Context cntx, EditText edt) {
        // hide keyboard
        InputMethodManager inputManager = (InputMethodManager) cntx.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(edt.getWindowToken(), 0);
    }
	public static String utf8ToUnicode(String source) {
		if(source.isEmpty()){return "";}
        int len = source.length();

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char unicodeChar = source.charAt(i);

            UnicodeBlock ub = UnicodeBlock.of(unicodeChar);

            if (ub == UnicodeBlock.BASIC_LATIN) {
                sb.append(source);//from  w  w w.j  ava  2s .  c o  m
            } else if (ub == UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

                int j = (int) unicodeChar - 65248;
                sb.append((char) j);

            } else {

				String hexS = Integer.toHexString(unicodeChar);
                String unicode = "\\u" + hexS.toUpperCase();
                sb.append(unicode);
            }
        }
		if(sb.toString().isEmpty()){return "";}
		String unicod = sb.toString();
		if(!unicod.contains("\\u")){return "";}
		while(unicod.contains("\\u")){
			unicod = unicod.replace("\\u", "\\char\"");
		}
		return unicod;
    }
	public static String st = "com.moh", stt = "amed.ba", sttt = "rki.lat", stttt = "ex", sttttt = "BaRKiTeX";
	public static String PackageName, VersionName, LanguageName;
	public static int VersionCode;
	public static PermissionInfo[] Permissions;
	public static void info(final Context contextSave) {
		String v3 = contextSave.getString(R.string.page_about);
		String v4 = "<a href=\"fb://profile/3203834\">" + "Barki Mohamed"+"</a>"+"</p><p>"+"<a href=\"fb://profile/1049535328\">" +"محمد باركي"+"</a>";
		String v30 = contextSave.getString(R.string.email_about);
		String v40 = "<font face=\"arial\" color=\"blue\">mohamedbarkimaths@gmail.com</font>";
		String v33 = "لتحديث التطبيق :";
		String v44 = "<a href=\"https://play.google.com/store/apps/details?id="+contextSave.getPackageName()+"\">"+"من متجر "+"Google Play</a>";
		String v7 = "(Copyright 2023 M. Barki)";
		String value1 = "<html><p>"+v3+"</p><p>"+v4+"</p><p>"+v30+"</p><p>"+v40+"</p><p>"+v33+"</p><p>"+v44+"</p><p>"+v7+"</p></html>";
		
		final Dialog dialog = new Dialog(contextSave, R.style.DialogStyle);
		dialog.setContentView(R.layout.dialog_information);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		dialog.findViewById(R.id.dialog_close).setOnClickListener(v -> dialog.dismiss());
		((TextView) dialog.findViewById(R.id.dialog_info)).setText(Html.fromHtml(value1));
		((TextView) dialog.findViewById(R.id.dialog_info)).setMovementMethod(LinkMovementMethod.getInstance());
		PackageManager manager = contextSave.getPackageManager();
		try {
			PackageInfo info = manager.getPackageInfo(contextSave.getPackageName(), PackageManager.GET_ACTIVITIES);
			PackageName = info.packageName;
			VersionCode = info.versionCode;
			VersionName = info.versionName;
			Permissions = info.permissions;
			LanguageName = Locale.getDefault().getDisplayLanguage();
		} catch (PackageManager.NameNotFoundException e) {e.printStackTrace();}
		String title;
		if(Function.getValue(contextSave, "screen").equals("true")){
			title = getApplicationName(contextSave)+" (Free) ["+ VersionCode +"] {v"+VersionName+"}";
		}else{
			title = getApplicationName(contextSave)+" (Pro) ["+ VersionCode +"] {v"+VersionName+"}";
		}
		((TextView) dialog.findViewById(R.id.dialog_infoo)).setText(Html.fromHtml(title));
		String v1 =
			"</p><p>"+
			contextSave.getString(R.string.title_about)
			+"</p><p>"+
			contextSave.getString(R.string.text_about)
			+"</p>";
		LinearLayout lnyAll = dialog.findViewById(R.id.dialog_lny);
		TextView tv = new TextView(contextSave);
		tv.setGravity(Gravity.LEFT);
		tv.setTextIsSelectable(true);
		tv.setPadding(dpToPx(10),dpToPx(10),dpToPx(10),dpToPx(10));
		tv.setText(Html.fromHtml("<html>"+v1+"</html>"));
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		tv.setTextColor(Color.WHITE);
		if(LanguageName.contains("rab") || LanguageName.contains("عربي")){
            tv.setGravity(Gravity.RIGHT);
            ((LinearLayout) dialog.findViewById(R.id.dialog_lny)).setGravity(Gravity.RIGHT);
            ((TextView) dialog.findViewById(R.id.dialog_info)).setGravity(Gravity.RIGHT);
			((TextView) dialog.findViewById(R.id.dialog_infoo)).setGravity(Gravity.RIGHT);
		}
		lnyAll.addView(tv);
		dialog.show();
	}
	public static void share(final Context getAppContext, final Activity myActivity) {
		final Dialog dialog = new Dialog(getAppContext, R.style.DialogStyle);
		dialog.setContentView(R.layout.dialog_share);
		dialog.setCanceledOnTouchOutside(false);
		dialog.setCancelable(false);
		dialog.findViewById(R.id.dialog_close).setOnClickListener(v -> dialog.dismiss());
		int widthPX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getAppContext.getResources().getDisplayMetrics());
		int heightPX = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300, getAppContext.getResources().getDisplayMetrics());
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthPX, heightPX);
		dialog.findViewById(R.id.dialog_lny).setLayoutParams(layoutParams);
		qr_code(getAppContext, dialog.findViewById(R.id.dialog_image));
		dialog.findViewById(R.id.dialog_button).setOnClickListener(v -> {
			Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
			sharingIntent.setType("text/plain");
			sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, getAppContext.getString(R.string.sharesubject));
			sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getAppContext.getString(R.string.app_url));
			myActivity.startActivity(Intent.createChooser(sharingIntent, getAppContext.getString(R.string.share)));
		});
		dialog.show();
	}

	/**
	 * @noinspection CallToPrintStackTrace
	 */
	public static void qr_code(final Context getAppContext, final ImageView img) {
		Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<>();
		hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H); // H = 30% damage
		int size = 1024;
		QRCodeWriter writer = new QRCodeWriter();
		try {
			BitMatrix bitMatrix = writer.encode(getAppContext.getString(R.string.app_url), BarcodeFormat.QR_CODE, size, size, hintMap);
			int width = bitMatrix.getWidth();
			int height = bitMatrix.getHeight();

			Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
				}
			}
			img.setImageBitmap(bmp);
		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
	public static int dpToPx(int dp) {return (int) (dp * Resources.getSystem().getDisplayMetrics().density);}
	public static String getApplicationName(Context context) {
		ApplicationInfo applicationInfo = context.getApplicationInfo();
		int stringId = applicationInfo.labelRes;
		return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
	}
	public static void trimCache(Context getAppContext) {
		try {
			File dir = getAppContext.getCacheDir();
			if (dir != null && dir.isDirectory()) {
				deleteDir(dir);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static boolean deleteDir(File dir) {
		if (dir != null && dir.isDirectory()) {
			String[] children = dir.list();
			assert children != null;
			for (String child : children) {
				boolean success = deleteDir(new File(dir, child));
				if (!success) {
					return false;
				}
			}
		}
		assert dir != null;
		return dir.delete();
	}
	public static String setTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
		String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

		return currentDate.replaceAll("-", "") + currentTime.replaceAll(":", "");
	}
}
