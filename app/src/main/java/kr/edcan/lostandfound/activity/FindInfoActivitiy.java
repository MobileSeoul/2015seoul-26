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
import kr.edcan.lostandfound.data.FindInfoData;
import kr.edcan.lostandfound.utils.FindInfo_Adapter;

public class FindInfoActivitiy extends AppCompatActivity {
    ArrayList<FindInfoData> arrayList;
    FindInfo_Adapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("class opened","FindInfoActivity");
        setContentView(R.layout.activity_find_info_activitiy);
        setActionbar(getSupportActionBar());
        setDefault();
    }

    private void setDefault() {
        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        //여기에는 리스트 액티비티에서 주는 값 받아서 넣어주렴
        adapter = new FindInfo_Adapter(getApplicationContext(), arrayList);
        listView.setAdapter(adapter);
        arrayList.add(new FindInfoData(
                "지하철 1~4호선",
                "1577-1234",
                "http://www.seoulmetro.co.kr/page.action?mCode=A010050000&cidx=21",
                "http://www.seoulmetro.co.kr/metro/lost/list.action?mCode=A040010000"
        ));
        arrayList.add(new FindInfoData(
                "지하철 5~8호선",
                "1577-5678",
                "http://www.smrt.co.kr/main/publish/view.jsp?menuID=001001003001",
                "http://www.smrt.co.kr/program/Train/Lost/Lost_search.jsp?menuID=001001003003"
        ));
        arrayList.add(new FindInfoData(
                "코레일",
                "1544-7788",
                "http://www.letskorail.com/ebizcom/cs/guide/lost/lost02.do",
                "http://www.letskorail.com/ebizcom/cs/guide/lost/lost01.do"
        ));
        arrayList.add(new FindInfoData(
                "9호선",
                "02 - 2656 - 0009",
                "https://www.metro9.co.kr/cs/lost01.do",
                "http://www.metro9.co.kr/customer/pickup/list.do"
        ));
        arrayList.add(new FindInfoData(
                "서울시 버스운송사업조합",
                "02 - 415 -4101",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));

        arrayList.add(new FindInfoData(
                "금호 고속",
                "02 - 5260 - 114",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));
        arrayList.add(new FindInfoData(
                "중앙 고속",
                "02 - 418 - 6811",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));
        arrayList.add(new FindInfoData(
                "동부 고속",
                "02 - 3484 - 4160",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));
        arrayList.add(new FindInfoData(
                "한일 고속",
                "02 - 535 - 2101",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));
        arrayList.add(new FindInfoData(
                "삼화 고속",
                "02 - 753 - 2408",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));
        arrayList.add(new FindInfoData(
                "천일 고속",
                "051 - 254 - 9115",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));
        arrayList.add(new FindInfoData(
                "동양 고속",
                "02 - 535 - 3118",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));
        arrayList.add(new FindInfoData(
                "속리산 고속",
                "043 - 230 - 1600",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));
        arrayList.add(new FindInfoData(
                "코오롱 TNS",
                "02 - 3701 - 4646",
                "http://www.sbus.or.kr/lost/lost_02.htm",
                "http://www.sbus.or.kr/lost/lost_01.htm"
        ));
        arrayList.add(new FindInfoData(
                "서울특별시택시운송사업조합(법인 택시)",
                "02 - 2033 - 9200",
                "http://www.stj.or.kr/customer_03_1.html",
                "http://www.stj.or.kr/customer_03.html"
        ));
        arrayList.add(new FindInfoData(
                "개인 택시",
                "02 - 2084 - 6300",
                null,
                null
        ));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,  int position, long id) {

            }
        });
    }
    public void setActionbar(ActionBar actionbar) {
        actionbar.setTitle("분실물 신고 / 조회");
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
