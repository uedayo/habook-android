
package com.uedayo.android.habook;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

public class AddBookActivity extends Activity {

    static final String TAG = AddBookActivity.class.getSimpleName();

    static final int REQUEST_CODE = 10000;
    static final String RESULT_KEY = "SCAN_RESULT";
    static final String QR_SCAN_PACKAGE_NAME = "com.google.zxing.client.android";
    static final String ACION_QR_SCAN = QR_SCAN_PACKAGE_NAME + ".SCAN";
    static final String URI_QR_SCAN = "market://details?id=" + QR_SCAN_PACKAGE_NAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startQRCodeScanner();
    }

    private void startQRCodeScanner() {
        Intent intent = new Intent(ACION_QR_SCAN);
        intent.putExtra("SCAN_MODE", "PRODUCT_MODE");

        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            Log.v(TAG, "QR Code Scanner isn't installed");
            goToMarket();
        }
    }

    private void goToMarket() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(URI_QR_SCAN));
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "Can't launch Google Play App!");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String barCode = data.getStringExtra(RESULT_KEY);
                Log.v(TAG, "barCode: " + barCode);
                searchRequest(barCode);
                finish();
            } else {
                startMainActivity();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void startMainActivity() {
        Intent intent = new Intent(AddBookActivity.this, WebViewActivity.class);
        startActivity(intent);
    }

    private void searchRequest(String barCode) {
        Intent intent = new Intent(AddBookActivity.this, WebViewActivity.class);
        intent.putExtra(WebViewActivity.EXTRA_ACTION, WebViewActivity.EXTRA_ACTION_LEND);
        intent.putExtra(WebViewActivity.EXTRA_ISBN, barCode);
        startActivity(intent);
    }
}
