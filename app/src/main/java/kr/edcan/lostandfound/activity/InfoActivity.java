package kr.edcan.lostandfound.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import kr.edcan.lostandfound.R;
import kr.edcan.lostandfound.data.GuideData;
import kr.edcan.lostandfound.utils.Guide_Adapter;

public class InfoActivity extends AppCompatActivity {

    ArrayList<GuideData> arrayList;
    Guide_Adapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("class opened","InfoActivity");
        setContentView(R.layout.activity_info);
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
                "지하철",
                "1. 열차번호 / 하차시점 / 몇번째 칸에서 내렸는지 확인\n2. 탔던 지하철의 종착역과 내린역에 연락(환승시 환승 구간 모두의 종착역에 연락)\n3. 경찰청 유실물 센터 나 각 지하철회사 별로 유실물 접수",
                "유실물 조회"));

        arrayList.add(new GuideData(
                "버스",
                "1. 버스 회사 연락\n2. 버스 노선, 버스 번호, 놓고 내린 버스 내 위치, 물건의 형태알려 접수",
                "유실물 조회"
        ));
        arrayList.add(new GuideData(
                "택시",
                "1. 택시조합에 연락\n2. 현금, 영수증 없을 때 - >\n    물건의 형태, 택시 차량 번호, 시각 알려 접수\n    카드, 영수증 있을 때 ->\n    택시 조회, 탑승 시각 알려 접수",
                "유실물 조회"
        ));
        arrayList.add(new GuideData(
                "어플에서 분실물을 찾은 경우",
                "우선 상세보기를 눌러 해당 게시글을 저장\n원문 링크를 통해서 분실물이 일치하는지 확인\n연락처를 통해 연락\n분실물 수령 가능 장소에서 분실물 수령",
                "유실물 조회"
        ));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }
    public void setActionbar(ActionBar actionbar) {
        actionbar.setTitle("분실물 처리 절차 안내");
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
