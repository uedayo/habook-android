
package com.uedayo.android.habook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClickLisner();
    }

    private void setOnClickLisner() {
        ((Button) findViewById(R.id.btn_search)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_history)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_add_book)).setOnClickListener(this);
        ((Button) findViewById(R.id.btn_add_user)).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_search:

                break;

            case R.id.btn_history:

                break;
            case R.id.btn_add_book:
                startAddBookActivity();
                break;
            case R.id.btn_add_user:

                break;
            default:
                break;
        }
    }

    private void startAddBookActivity() {
        Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
        startActivity(intent);
    }

}
