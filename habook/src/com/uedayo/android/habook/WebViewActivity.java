
package com.uedayo.android.habook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {

    static final String TAG = WebViewActivity.class.getSimpleName();

    static final String EXTRA_ACTION = "com.uedayo.android.habook.action_extra_action";
    static final String EXTRA_ACTION_LEND = "com.uedayo.android.habook.action_extra_action_lend";
    static final String EXTRA_ACTION_RETURN = "com.uedayo.android.habook.action_extra_action_return";
    static final String EXTRA_ACTION_SEARCH = "com.uedayo.android.habook.action_extra_action_search";
    static final String EXTRA_ACTION_HISTORY = "com.uedayo.android.habook.action_extra_action_history";
    static final String EXTRA_ISBN = "com.uedayo.android.habook.action_extra_isbn";

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setLayout();
        setJavaScriptEnabled();
        setLinkNotOpenWithBrowser();
    }

    @Override
    protected void onResume() {
        super.onResume();
        handleIntent();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        String action = intent.getStringExtra(EXTRA_ACTION);
        String isbn = intent.getStringExtra(EXTRA_ISBN);
        loadUrl(action, isbn);

    }

    private void loadUrl(String action, String isbn) {
        String url = "";
        if (EXTRA_ACTION_LEND.equals(action)) {
            url = ServerConnectionInterface.Lend;
            url += isbn;
        } else if (EXTRA_ACTION_RETURN.equals(action)) {
            url = ServerConnectionInterface.Return;
            url += isbn;
        } else if (EXTRA_ACTION_SEARCH.equals(action)) {
            url = ServerConnectionInterface.Search;
        } else {
            return;
        }
        Log.v(TAG, "url" + url);
        webView.loadUrl(url);
    }

    private void setLayout() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_web_view);
        webView = (WebView) findViewById(R.id.webView);
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setJavaScriptEnabled() {
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    private void setLinkNotOpenWithBrowser() {
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
