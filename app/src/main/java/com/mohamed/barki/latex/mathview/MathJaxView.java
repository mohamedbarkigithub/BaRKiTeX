package com.mohamed.barki.latex.mathview;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;

import com.mohamed.barki.latex.R;

@SuppressWarnings({"deprecation", "RedundantSuppression"})
public class MathJaxView extends WebView {

    String text, config, preDefinedConfig;

    @SuppressLint("SetJavaScriptEnabled")
    public MathJaxView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // if text is set in XML, call setText() with that text
        TypedArray a = getContext().obtainStyledAttributes(
                attrs,
			R.styleable.MathJaxView);
        String text = a.getString(R.styleable.MathJaxView_android_text);

        // default config for MathJax
        setConfig(
                "MathJax.Hub.Config({" +
                "    extensions: ['fast-preview.js']," +
                "    messageStyle: 'none'," +
                "    \"fast-preview\": {" +
                "      disabled: false" +
                "    }," +
                "    CommonHTML: {" +
                "      linebreaks: { automatic: true, width: \"container\" }" +
                "    }," +
                "    tex2jax: {" +
                "      inlineMath: [ ['$','$'] ]," +
                "      displayMath: [ ['$$','$$'] ]," +
                "      processEscapes: true" +
                "    }," +
                "    TeX: {" +
                "      extensions: [\"file:///android_asset/MathJax/extensions/TeX/mhchem.js\"]," +
                "      mhchem: {legacy: false}"+
                "    }" +
                "});"
        );
        preDefinedConfig = "TeX-MML-AM_CHTML";

        if (!TextUtils.isEmpty(text))
            setText(text);
        a.recycle();

        getSettings().setJavaScriptEnabled(true);
        getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        setBackgroundColor(Color.TRANSPARENT);

        // render MathJax once page finishes loading
        setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                view.loadUrl("javascript:MathJax.Hub.Queue(['Typeset',MathJax.Hub]);");
            }
        });
    }

    // disable touch event on MathJaxView
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void setConfig(String config) {
        this.config = minifyConfig(config);
    }

    private String minifyConfig(String config) {
        return config.replace("\n", "").replace(" ", "");
    }

    public void setText(String text) {
        this.text = text;
        loadDataWithBaseURL("about:blank",
                "<html><head>" +
                        "<style>" +
                        "body {" +
                        "    text-align: center;" +
                        "}" +
                        "</style>" +
                        "<script type=\"text/x-mathjax-config\">" +
                        config +
                        "</script>" +
                        "<script type=\"text/javascript\" async src=\"file:///android_asset/MathJax/MathJax.js?config=" + preDefinedConfig + "\"></script>" +
                        "</head>" +
                        "<body>" +
                        text +
                        "</body>" +
                        "</html>", "text/html", "utf-8", "");
    }

    /**
     * Returns the MathJax code that was passed into using setText
     *
     * @return raw MathJax code
     */
    @Nullable
    public String getText() {
        return text;
    }
}
