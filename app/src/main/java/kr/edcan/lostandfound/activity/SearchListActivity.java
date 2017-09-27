package kr.edcan.lostandfound.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import kr.edcan.lostandfound.R;
import kr.edcan.lostandfound.data.SearchData;
import kr.edcan.lostandfound.utils.SearchData_Adapter;
import kr.edcan.lostandfound.utils.SearchParser;

public class SearchListActivity extends AppCompatActivity {
    ListView listview;
    SearchData_Adapter adapter;
    SearchParser searchParser;
    ArrayList<SearchData> arrayList;
    MaterialDialog a;
    Intent asdf;
    SharedPreferences setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_list);
        setActionbar(getSupportActionBar());
        asdf = getIntent();
        Log.e("tts",asdf.getStringExtra("type")+" "+asdf.getStringExtra("cate"));
        searchParser = new SearchParser(asdf.getStringExtra("type"),asdf.getStringExtra("cate"),asdf.getStringExtra("value")); //파서가 가져올 거는 이것들이다!
        Log.e("tts",asdf.getStringExtra("type")+" "+asdf.getStringExtra("cate"));
        setDefault();
        setData();
    }

    private void setData() {
        Log.e("T","cc1");
        a = new MaterialDialog.Builder(SearchListActivity.this)
                .title("데이터를 로드합니다")
                .content("잠시만 기다려주세요")
                .progress(true, 0)
                .show();
        arrayList = new ArrayList<>();
        searchParser.initData();  //파서 안의 어레이를 초기화 하겠음
        searchParser.loadData(1, setting.getInt("max",500));  //1번에서 20번까지 가져올 거임
        Log.e("T", "cc2");
        arrayList = searchParser.getArrayList();  //가져온 데이터를 어레이에 투척
        Log.e("T", "cc3");
        adapter = new SearchData_Adapter(getApplicationContext(), arrayList);
        listview.setAdapter(adapter);
        a.dismiss();
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView title = (TextView) view.findViewById(R.id.lostlist_listview_title);
                TextView content= (TextView) view.findViewById(R.id.lostlist_listview_content);
                TextView type = (TextView) view.findViewById(R.id.lostlist_listview_type);
                TextView id_ = (TextView) view.findViewById(R.id.lostlist_listview_id);
                TextView url = (TextView) view.findViewById(R.id.lostlist_listview_url);
                TextView date = (TextView) view.findViewById(R.id.lostlist_listview_date);
                TextView take_place = (TextView) view.findViewById(R.id.lostlist_listview_take_plcae);
                TextView contact = (TextView) view.findViewById(R.id.lostlist_listview_contact);
                TextView position0 = (TextView) view.findViewById(R.id.lostlist_listview_position);
                TextView plcae = (TextView) view.findViewById(R.id.lostlist_listview_place);
                TextView thing = (TextView) view.findViewById(R.id.lostlist_listview_thing);
                TextView image_url = (TextView) view.findViewById(R.id.lostlist_listview_image_url);

                Intent goView = new Intent(getApplicationContext(),SearchViewActivity.class);
                goView.putExtra("title",title.getText().toString());
                goView.putExtra("content",content.getText().toString());
                goView.putExtra("type",type.getText().toString());
                goView.putExtra("id",id_.getText().toString());
                goView.putExtra("url",url.getText().toString());
                goView.putExtra("date",date.getText().toString());
                goView.putExtra("take_place",take_place.getText().toString());
                goView.putExtra("contact",contact.getText().toString());
                goView.putExtra("position",position0.getText().toString());
                goView.putExtra("place",plcae.getText().toString());
                goView.putExtra("thing",thing.getText().toString());
                goView.putExtra("image_url",image_url.getText().toString());
                startActivity(goView);
            }
        });
    }

    private void setDefault() {
        listview = (ListView) findViewById(R.id.listview);
        setting = getSharedPreferences("setting",MODE_PRIVATE);
    }

    public void setActionbar(ActionBar actionbar) {
        asdf = getIntent();
        actionbar.setTitle(asdf.getStringExtra("value")+"에 대한 검색 결과");
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lostlist, menu);
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
}
