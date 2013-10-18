
package com.uedayo.android.habook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        setOnClickLisner();
    }

	private void setOnClickLisner() {
        ((Button) findViewById(R.id.btn_lend_book)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_return_book)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_search)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_user)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_lend_book:
                lendBook();
                break;
            case R.id.btn_return_book:
                returnBook();
                break;
            case R.id.btn_search:
                searchBook();
                break;
            case R.id.btn_user:
                showUser();
                break;
            default:
                break;
        }
    }

    private void lendBook() {
        Intent intent = new Intent(MainActivity.this, BookCodeActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_ACTION, WebViewActivity.EXTRA_ACTION_LEND);
        startActivity(intent);
    }

    private void returnBook() {
        Intent intent = new Intent(MainActivity.this, BookCodeActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_ACTION, WebViewActivity.EXTRA_ACTION_RETURN);
        startActivity(intent);
    }

    private void searchBook() {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_ACTION, WebViewActivity.EXTRA_ACTION_SEARCH);
        startActivity(intent);
    }

    private void showUser() {
        Intent intent = new Intent(MainActivity.this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_ACTION, WebViewActivity.EXTRA_ACTION_USER);
        startActivity(intent);
    }
}
