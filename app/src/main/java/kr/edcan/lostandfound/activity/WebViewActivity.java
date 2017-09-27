package kr.edcan.lostandfound.activity;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import kr.edcan.lostandfound.R;

public class WebViewActivity extends AppCompatActivity {
    long mBackPressed;
    WebView web;
    Intent goWeb;
    ClipboardManager clipboardManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        setActionbar(getSupportActionBar());
        goWeb = getIntent();
        if(goWeb.getStringExtra("url").trim().equals("")){
            Log.e("web open error","no url");
            Toast.makeText(getApplicationContext(),"정보 없음",Toast.LENGTH_SHORT).show();
            finish();
        }
        web = (WebView) findViewById(R.id.webview_webview);
        clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        web.loadUrl(goWeb.getStringExtra("url"));
        WebSettings webSettings = web.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(false);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        web.setWebViewClient(new NewWebViewClient(){
        });
    }
    private class NewWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public void setActionbar(ActionBar actionbar) {
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setTitle("원문보기");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.webview_link_copy:
                clipboardManager.setText(goWeb.getStringExtra("url"));
                Toast.makeText(getApplicationContext(),"클립보드에 링크가 복사되었습니다.",Toast.LENGTH_SHORT).show();
                break;
            case R.id.webview_link_share:
                Intent msg = new Intent(Intent.ACTION_SEND);
                msg.addCategory(Intent.CATEGORY_DEFAULT);
                msg.putExtra(Intent.EXTRA_SUBJECT, "분실 했어요!");
                msg.putExtra(Intent.EXTRA_TEXT, "대중교통을 이용중에 물건을 분실 했어요 ㅠㅠ\n"+goWeb.getStringExtra("url"));
                msg.putExtra(Intent.EXTRA_TITLE, "I.LOST.U");
                msg.setType("text/plain");
                startActivity(Intent.createChooser(msg, "공유"));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        if (mBackPressed + 2000 > System.currentTimeMillis()) {
            super.onBackPressed();
            finish();
            return;
        } else
            Toast.makeText(getApplicationContext(), "다시 한번 누르면 종료됩니다", Toast.LENGTH_SHORT).show();
        mBackPressed = System.currentTimeMillis();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (event.isLongPress()) {
                super.onBackPressed();
            }
            if (web.canGoBack()) {
                web.goBack();
                return true;
            } else onBackPressed();
        }
        return false;
    }

}
