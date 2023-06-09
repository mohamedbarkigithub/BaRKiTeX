package com.mohamed.barki.latex.mathview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.mohamed.barki.latex.R;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class KatexView extends WebView {
  String TAG = "BarkiKatexView";
  private static final float default_text_size = 18;
  private String display_text;
  private int text_color;
  private int text_size;
  private boolean clickable = false;


  public KatexView(Context context) {
    super(context);
    configurationSettingWebView(false);
    setDefaultTextColor(context);
    setDefaultTextSize();
  }



	public KatexView(Context context, AttributeSet attrs) {
    super(context, attrs);
    configurationSettingWebView(false);
    TypedArray mTypeArray = context.getTheme().obtainStyledAttributes(
        attrs,
			R.styleable.KatexView,
        0, 0);
    try {
        setBackgroundColor(mTypeArray.getInteger(R.styleable.KatexView_setViewBackgroundColor, ContextCompat.getColor(context,android.R.color.transparent)));
		setTextColor(mTypeArray.getColor(R.styleable.KatexView_setTextColor,ContextCompat.getColor(context,android.R.color.black)));
		pixelSizeConversion(mTypeArray.getDimension(R.styleable.KatexView_setTextSize,default_text_size));
		setDisplayText(mTypeArray.getString(R.styleable.KatexView_setText));
		setClickable(mTypeArray.getBoolean(R.styleable.KatexView_setClickable,false));


    }
    catch (Exception e)
    {
      Log.d(TAG,"Exception:"+ e);
    }


  }
  private void pixelSizeConversion(float dimension) {
    if (dimension==default_text_size)
    {
      setTextSize((int)default_text_size);
    }
    else
    {
      int pixel_dimen_equivalent_size = (int) ((double) dimension / 1.6);
      setTextSize(pixel_dimen_equivalent_size);
    }
  }


  @SuppressLint({"SetJavaScriptEnabled", "NewApi"})
  private void configurationSettingWebView(boolean enable_zoom_in_controls)
  {
    this.setLayerType(View.LAYER_TYPE_HARDWARE,null);
    WebSettings settings = this.getSettings();
    settings.setJavaScriptEnabled(true);
    settings.setAllowFileAccess(true);
    settings.setDisplayZoomControls(enable_zoom_in_controls);
    settings.setBuiltInZoomControls(enable_zoom_in_controls);
    settings.setSupportZoom(enable_zoom_in_controls);
    this.setVerticalScrollBarEnabled(enable_zoom_in_controls);
    this.setHorizontalScrollBarEnabled(enable_zoom_in_controls);
    Log.d(TAG,"Zoom in controls:"+enable_zoom_in_controls);
  }


  public void setDisplayText(String formula_text) {
    this.display_text = formula_text;
    loadData();
  }


  private String getOfflineKatexConfig()
  {
    String offline_config = "<!DOCTYPE html>\n" +
        "<html>\n" +
        "    <head>\n" +
        "        <meta charset=\"UTF-8\">\n" +
        "        <title>Auto-render test</title>\n" +
        "        <link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/katex/katex.min.css\">\n" +
        "        <link rel=\"stylesheet\" type=\"text/css\" href=\"file:///android_asset/themes/style.css\" >\n" +
        "        <script type=\"text/javascript\" src=\"file:///android_asset/katex/katex.min.js\" ></script>\n" +
        "        <script type=\"text/javascript\" src=\"file:///android_asset/katex/contrib/auto-render.min.js\" ></script>\n" +
        "        <script type=\"text/javascript\" src=\"file:///android_asset/katex/contrib/auto-render.js\" ></script>\n" +
        "        <script type=\"text/javascript\" src=\"file:///android_asset/jquery.min.js\" ></script>\n" +
        "        <script type=\"text/javascript\" src=\"file:///android_asset/latex_parser.js\" ></script>\n"+
        "        <meta name=\"viewport\" content=\"width=device-width\"/>\n"+
        "<link rel=\"stylesheet\" href=\"file:///android_asset/webviewstyle.css\"/>\n" +
        "<style type='text/css'>"+
        "body {"+
        "margin: 0px;"+
        "padding: 0px;"+
        "font-size:" +this.text_size+"px;"+
        "color:"+getHexColor(this.text_color)+";"+
        " }"+
        " </style>"+
        "    </head>\n" +
        "    <body>\n" +
        "        {formula}\n" +
        "    </body>\n" +
        "</html>";

    return offline_config.replace("{formula}",this.display_text);


  }

  public void setTextSize(int size)
  {
    this.text_size = size;
    loadData();

  }
  public void setTextColor(int color)
  {

    this.text_color = color;
    loadData();
  }
  private String getHexColor(int intColor)
  {
    //Android and javascript color format differ javascript support Hex color, so the android color which user sets is converted to hexcolor to replicate the same in javascript.
    String hexColor = String.format("#%06X", (0xFFFFFF & intColor));
    Log.d(TAG,"Hex Color:"+hexColor);
    return hexColor;
  }


  private void setDefaultTextColor(Context context) {
    //sets default text color to black
    this.text_color = ContextCompat.getColor(context,android.R.color.black);

  }

  private void setDefaultTextSize() {
    //sets view default text size to 18
    this.text_size =(int) default_text_size;
  }

  private void loadData()
  {
    if (this.display_text!=null)
    {
      this.loadDataWithBaseURL("null",getOfflineKatexConfig(),"text/html","UTF-8","about:blank");
    }

  }

  public void setClickable(boolean is_clickable)
  {
    this.setEnabled(true);
    this.clickable = is_clickable;
    boolean enable_zoom_in_controls = !is_clickable;
    configurationSettingWebView(enable_zoom_in_controls);
    this.invalidate();
  }

  @SuppressLint({"NewApi", "ClickableViewAccessibility"})
  @Override public boolean onTouchEvent(MotionEvent event) {
    if (this.clickable && event.getAction() == MotionEvent.ACTION_DOWN) {
      this.callOnClick();
      return false;
    }
    else {
      return super.onTouchEvent(event);
    }
  }

}
