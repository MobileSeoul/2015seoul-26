package kr.edcan.lostandfound.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import kr.edcan.lostandfound.R;
import kr.edcan.lostandfound.data.GuideData;
import kr.edcan.lostandfound.utils.Guide_Adapter;

public class GuideActivity extends AppCompatActivity {
    ArrayList<GuideData> arrayList;
    Guide_Adapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        setActionbar(getSupportActionBar());
        setDefault();
    }

    private void setDefault() {
        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        //여기에는 리스트 액티비티에서 주는 값 받아서 넣어주렴
        adapter = new Guide_Adapter(getApplicationContext(), arrayList);
        listView.setAdapter(adapter);
        arrayList.add(new GuideData(
                "분실당일 15시가 안된 경우",
                "아직 분실물 센터에 인계되지 않았을 수 있습니다.\n조금만 더 기다려보세요.",
                "분실물 처리 절차 안내"
        ));
        arrayList.add(new GuideData(
                "분실한지 하루가 지난 경우",
                "분실물이 접수되지 않았을 수도 있습니다.\n분실물 등록을 하거나 분실물이 접수될 때까지 기다려주세요.",
                "분실물 조회/ 등록"
        ));
        arrayList.add(new GuideData(
                "분실한지 일주일이 지난 경우",
                "분실물이 경찰서로 인계되었을 수 있습니다.\n경찰청 분실물 조회 서비스를 이용해보세요.",
                "LOST112"
        ));
        arrayList.add(new GuideData(
                "분실한지 6개월이 지난 경우",
                " 경찰서에서 분실물을 조회할 수 없다면\n이미 환부 처리되거나 폐기 또는 양여된 상태일 수 있습니다. ",
                "죄송합니다"
        ));


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    public void setActionbar(ActionBar actionbar) {
        actionbar.setTitle("분실물 도움말");
        actionbar.setDisplayHomeAsUpEnabled(true);
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
