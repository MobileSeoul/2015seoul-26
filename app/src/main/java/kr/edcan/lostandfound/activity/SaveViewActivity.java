package kr.edcan.lostandfound.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import kr.edcan.lostandfound.R;
import kr.edcan.lostandfound.data.SaveViewData;
import kr.edcan.lostandfound.utils.SaveViewAdapter;

public class SaveViewActivity extends AppCompatActivity implements View.OnClickListener{
    TextView view_webview, tel;
    ListView listview;
    SaveViewAdapter adapter;
    ArrayList<SaveViewData> arrayList;
    Handler confirmHandler;
    ProgressDialog dialog;
    Thread thread;

    TextView lost_view_item_name;
    TextView lost_view_item_id;
    TextView lost_view_item_status;
    TextView lostview_webview;
    TextView lostview_tel;
    String lostview_webview_url;
    String lostview_tel_num;
    RelativeLayout noimage;
    ImageView imageView;

    Intent goView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_view);
        lost_view_item_name = (TextView) findViewById(R.id.lost_view_item_name);
        lost_view_item_id = (TextView) findViewById(R.id.lost_view_item_id);
        lost_view_item_status = (TextView) findViewById(R.id.lost_view_item_status);
        lostview_webview = (TextView) findViewById(R.id.lostview_webview);
        lostview_tel = (TextView) findViewById(R.id.lostview_tel);
        goView = getIntent();

        setActionbar(getSupportActionBar());
        setDefault();
        setData();
    }

    private void setData() {
        arrayList = new ArrayList<>();
        lost_view_item_name.setText(goView.getStringExtra("title"));
        lost_view_item_id.setText(goView.getStringExtra("id"));
        lost_view_item_status.setText(goView.getStringExtra("type"));
        lostview_webview_url = goView.getStringExtra("url");
        if(!goView.getStringExtra("contact").replace(": ","").trim().equals("")) {
            lostview_tel_num = goView.getStringExtra("contact").replace(": ", "");
        }else{
            lostview_tel_num = "전화 번호 정보 없음";
        }
        lostview_tel.setText(lostview_tel_num);
        //여기에는 리스트 액티비티에서 주는 값 받아서 넣어주렴
        if (!goView.getStringExtra("thing").trim().equals(""))
            arrayList.add(new SaveViewData("내용", goView.getStringExtra("thing")));
        if(!goView.getStringExtra("take_place").trim().equals(""))
            arrayList.add(new SaveViewData("분실물 수령 가능한 곳", goView.getStringExtra("take_place")));
        if(!goView.getStringExtra("date").trim().equals(""))
            arrayList.add(new SaveViewData("분실물을 습득한 날짜", goView.getStringExtra("date")));
        if(!goView.getStringExtra("place").trim().equals(""))
            arrayList.add(new SaveViewData("분실물을 습득한 곳", goView.getStringExtra("place")));
        if(!goView.getStringExtra("position").trim().equals(""))
            arrayList.add(new SaveViewData("분실물을 습득한 곳의 회사명", goView.getStringExtra("position")));
        adapter = new SaveViewAdapter(getApplicationContext(), arrayList);
        listview.setAdapter(adapter);
        if((!goView.getStringExtra("image_url").equals(""))&&(!goView.getStringExtra("image_url").equals("aaa.jpg"))){
            noimage.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
            setImageView(goView.getStringExtra("image_url"));
        }

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView asdf = (TextView) view.findViewById(R.id.lostview_listview_title);
                switch (asdf.getText().toString().trim()) {
                    case "분실물 수령 가능한 곳": {
                        Intent goWeb = new Intent(getApplicationContext(), WebViewActivity.class);
                        goWeb.putExtra("url", "http://maps.google.com/maps?f=d&saddr=&daddr=" + goView.getStringExtra("take_place") + "&hl=ko");
                        startActivity(goWeb);
                    }
                }
            }
        });
    }

    private void setImageView(final String uri) {
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("...");
        dialog.show();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL(uri.trim());
                    URLConnection conn = url.openConnection();
                    conn.connect();
                    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                    Bitmap bm = BitmapFactory.decodeStream(bis);
                    bis.close();
                    imageView.setImageBitmap(bm);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                confirmHandler.sendEmptyMessage(0);
                dialog.dismiss();
            }
        };
        thread = new Thread(task);
        thread.start();
        try{
            thread.join();
        }catch (Exception e){
            //TODO
        }
    }

    private void setDefault() {
        listview = (ListView) findViewById(R.id.lost_view_listview);
        tel = (TextView) findViewById(R.id.lostview_tel);
        tel.setOnClickListener(this);
        lostview_webview.setOnClickListener(this);
        view_webview = (TextView) findViewById(R.id.lostview_webview);
        noimage = (RelativeLayout) findViewById(R.id.noimage);
        imageView = (ImageView) findViewById(R.id.imageview);
        confirmHandler = new Handler() {
            @Override
            public void handleMessage(Message msg){
            }
        };
        dialog = new ProgressDialog(SaveViewActivity.this);
    }


    public void setActionbar(ActionBar actionbar) {
        actionbar.setTitle("저장된 물건");
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lostview_webview : {
                if(!goView.getStringExtra("url").trim().equals("")) {
                    Intent goWeb = new Intent(getApplicationContext(), WebViewActivity.class);
                    goWeb.putExtra("url", goView.getStringExtra("url"));
                    startActivity(goWeb);
                }else{
                    Toast.makeText(getApplicationContext(),"링크 정보가 없습니다. 여기에서 찾아보세요",Toast.LENGTH_SHORT).show();
                    Intent goInfo = new Intent(getApplicationContext(),FindInfoActivitiy.class);
                    startActivity(goInfo);
                }
                break;
            }

            case R.id.lostview_tel : {
                if (lostview_tel_num.equals("전화 번호 정보 없음")) {
                    Toast.makeText(getApplicationContext(),"전화 번호가 없습니다. 여기에서 찾아보세요",Toast.LENGTH_SHORT).show();
                    Intent goInfo = new Intent(getApplicationContext(),CenterInfoActivity.class);
                    startActivity(goInfo);
                } else {
                    new MaterialDialog.Builder(SaveViewActivity.this)
                            .title("연락처로 전화")
                            .content(lostview_tel_num + "번 으로 전화를 겁니다.")
                            .positiveText("확인")
                            .negativeText("취소")
                            .backgroundColorRes(R.color.met)
                            .titleColorRes(R.color.white)
                            .contentColorRes(R.color.white)
                            .positiveColorRes(R.color.white)
                            .negativeColorRes(R.color.white)
                            .callback(new MaterialDialog.ButtonCallback() {
                                @Override
                                public void onPositive(MaterialDialog dialog) {
                                    super.onPositive(dialog);
                                    Intent goTell = new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + lostview_tel_num));
                                    startActivity(goTell);
                                }
                            }).show();
                    break;
                }
            }
        }
    }
}
