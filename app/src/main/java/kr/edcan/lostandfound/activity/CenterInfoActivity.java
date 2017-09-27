package kr.edcan.lostandfound.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import kr.edcan.lostandfound.R;
import kr.edcan.lostandfound.data.CenterData;
import kr.edcan.lostandfound.utils.Center_Adapter;

public class CenterInfoActivity extends AppCompatActivity {
    ArrayList<CenterData> arrayList;
    Center_Adapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("class opened","CenterInfoActivity");
        setContentView(R.layout.activity_center_info);
        setActionbar(getSupportActionBar());
        setDefault();
    }

    private void setDefault() {
        listView = (ListView) findViewById(R.id.listview);
        arrayList = new ArrayList<>();
        //여기에는 리스트 액티비티에서 주는 값 받아서 넣어주렴
        adapter = new Center_Adapter(getApplicationContext(), arrayList);
        listView.setAdapter(adapter);
        arrayList.add(new CenterData(
                "1,2호선 분실물 센터",
                "시청역 분실물 센터\n02 - 6110 - 1122",
                "시청역",
                "02 - 6110 - 1122"
        ));
        arrayList.add(new CenterData(
            "3,4호선 분실물 센터",
                "충무로역 분실물 센터\n02 - 6110 - 3344",
                "충무로역",
                "02 - 6110 - 3344"
        ));
        arrayList.add(new CenterData(
                "5,8호선 분실물 센터",
                "왕십리역 분실물 센터\n02 - 6311 - 6765\n02 - 6311 - 6768",
                "왕십리역",
                "02 - 6311 - 6765"
        ));
        arrayList.add(new CenterData(
                "6,7호선 분실물 센터",
                "태릉입구역 분실물 센터\n02 - 6311 - 6766\n02 - 6311 - 6767",
                "태릉입구역",
                "02 - 6311 - 6767\n"
        ));
        arrayList.add(new CenterData(
                "코레일 분실물 센터",
                "구로역 : 02 -869 - 0089\n" +
                        "대곡역 : 031 - 965 - 8516\n" +
                        "광운대역 : 02 - 917 - 7445\n" +
                        "안산역 : 031 - 497 - 7788\n" +
                        "선릉역 : 02 - 568 - 7715\n" +
                        "병점역 : 031 - 234 - 7788\n" +
                        "문산역 : 031 - 952 -7788 \n" +
                        "김포공항역 : 032 - 745 - 7777\n" +
                        "서울역 - 경부선 : 02 - 755 - 7108\n" +
                        "문산역 - 경의선 : 031 - 952 - 7788\n" +
                        "구로역 - 경인선 : 02 - 869 - 0089\n" +
                        "인천역 - 경인선 : 032 - 772 - 0784\n" +
                        "왕십리역 - 분당선 : 02 - 2291 - 7787\n" +
                        "청량리역 - 중앙선 : 02 - 969 - 8003\n" +
                        "의정부 - 경원선 : 031 - 872 - 7788\n" +
                        "인천 국제 공항 : 032 - 742 - 3114\n" +
                        "대곡역 - 일산선 : 031 - 965 - 8516\n" +
                        "영등포역 - 경부선 : 02 - 2639 - 3310\n" +
                        "부평역 - 경인선 : 032 - 528 - 1439\n" +
                        "수원역 - 경부선 : 031 - 256 - 2723\n" +
                        "안산역 - 안산선 : 031 - 491 - 7788\n" +
                        "광운대역 - 경원선 : 02 - 917 - 7445\n" +
                        "천안역 - 경부선 : 041 - 562 - 7034\n" +
                        "김포공항 : 02 - 660 - 4097",
                "",
                ""
        ));
        arrayList.add(new CenterData(
                "9호선 분실물 센터",
                "동작역 분실물 센터\n02 - 2656 - 0009",
                "동작역",
                "02 - 2656 - 0009"
        ));
    }
    public void setActionbar(ActionBar actionbar) {
        actionbar.setTitle("오프라인 분실물 센터 안내");
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
