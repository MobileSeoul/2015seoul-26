package kr.edcan.lostandfound.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import kr.edcan.lostandfound.R;
import kr.edcan.lostandfound.data.SaveData;
import kr.edcan.lostandfound.utils.DataSaver;
import kr.edcan.lostandfound.utils.ListParser;
import kr.edcan.lostandfound.utils.SaveAdapter;

public class SaveListActivity extends AppCompatActivity {
    DataSaver saver;
    ListView listview;
    SaveAdapter adapter;
    ListParser listParser;
    ArrayList<SaveData> arrayList;
    MaterialDialog a;
    SharedPreferences save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_list);
        saver = new DataSaver(getApplicationContext());
        setActionbar(getSupportActionBar());
        Intent asdf = getIntent();
        setDefault();
        if(save.getInt("max",0)==0){
            Toast.makeText(getApplicationContext(),"저장된 항목이 없음",Toast.LENGTH_SHORT).show();
            finish();
        }
        setData();
    }

    private void setData() {
        a = new MaterialDialog.Builder(SaveListActivity.this)
                .title("데이터를 로드합니다")
                .content("잠시만 기다려주세요")
                .progress(true, 0)
                .show();
        arrayList = new ArrayList<>();
        //TODO : 저장된 데이터들 불러와서 어레이에 투척하기
        for(int i=0;i<save.getInt("max",0);i++){
            arrayList.add(new SaveData(
                    save.getString("title"+i,null).replace(":", ""),
                    save.getString("content"+i,null).replace(":", ""),
                    save.getString("type"+i,null).replace(":", ""),
                    save.getString("id"+i,null).replace(":", ""),
                    save.getString("url"+i,null),
                    save.getString("date"+i,null).replace(":", ""),
                    save.getString("take_place"+i,null).replace(":", ""),
                    save.getString("contact"+i,null).replace(":", ""),
                    save.getString("position0"+i,null).replace(":", ""),
                    save.getString("place"+i,null).replace(":", ""),
                    save.getString("thing"+i,null).replace("<br>", "\n"),
                    save.getString("image_url"+i,null)
            ));
        }
        adapter = new SaveAdapter(getApplicationContext(), arrayList);
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

                Intent goView = new Intent(getApplicationContext(),SaveViewActivity.class);
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
        save = getSharedPreferences("save",MODE_PRIVATE);
        listview = (ListView) findViewById(R.id.listview);
    }

    public void setActionbar(ActionBar actionbar) {
        actionbar.setTitle("저장된 물건 목록");
        actionbar.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save_list, menu);
        return true;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.reset :{
                saver.resetData();
                arrayList = new ArrayList<>();
                adapter = new SaveAdapter(getApplicationContext(), arrayList);
                listview.setAdapter(adapter);
                Toast.makeText(getApplicationContext(),"저장된 항목이 초기화되었습니다.",Toast.LENGTH_SHORT).show();
                finish();
                return true;
            }
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
