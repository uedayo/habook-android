
package com.uedayo.android.habook;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.HttpAuthHandler;
import android.webkit.WebViewDatabase;

public class WebViewActivity extends Activity {

    static final String TAG = WebViewActivity.class.getSimpleName();

    static final String EXTRA_ACTION = "com.uedayo.android.habook.action_extra_action";
    static final String EXTRA_ACTION_LEND = "com.uedayo.android.habook.action_extra_action_lend";
    static final String EXTRA_ACTION_RETURN = "com.uedayo.android.habook.action_extra_action_return";
    static final String EXTRA_ACTION_SEARCH = "com.uedayo.android.habook.action_extra_action_search";
    static final String EXTRA_ACTION_USER = "com.uedayo.android.habook.action_extra_action_user";
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
        } else if (EXTRA_ACTION_USER.equals(action)) {
            url = ServerConnectionInterface.User;
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
        WebViewDatabase.getInstance(this).clearHttpAuthUsernamePassword();
        webView.setHttpAuthUsernamePassword(ServerConnectionInterface.Host, ServerConnectionInterface.Realm, ServerConnectionInterface.UserName, ServerConnectionInterface.Password);
        webView.setWebViewClient(new WebViewClient() {
            private String loginCookie;
            @Override
            public void onLoadResource(WebView view, String url) {
                    CookieManager cookieManager = CookieManager.getInstance();
                    loginCookie = cookieManager.getCookie(url);
            }

            @Override
            public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm) {
                    String[] up = view.getHttpAuthUsernamePassword(host, realm);
                    handler.proceed(ServerConnectionInterface.UserName, ServerConnectionInterface.Password);
                    if (up != null && up.length == 2) {
                            handler.proceed(up[0], up[1]);
                    }
                    else {
                            Log.d("sample", "Could not find username/password for domain: " + host + "with realm = "+ realm);
                    }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                    CookieManager cookieManager = CookieManager.getInstance();
                    cookieManager.setCookie(url, loginCookie);
            }
        });
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
