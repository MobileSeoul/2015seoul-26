package kr.edcan.lostandfound.utils;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import kr.edcan.lostandfound.R;
import kr.edcan.lostandfound.data.LostListData;

/**
 * Copyright by 2015 Sunrin Internet High School EDCAN
 * Created by kimok_000 on 2015-10-13.
 */
public class LostList_Adapter extends ArrayAdapter<LostListData> {
    // 레이아웃 XML을 읽어들이기 위한 객체
    private LayoutInflater mInflater;

    public LostList_Adapter(Context context, ArrayList<LostListData> object) {
        // 상위 클래스의 초기화 과정
        // context, 0, 자료구조
        super(context, 0, object);
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // 보여지는 스타일을 자신이 만든 xml로 보이기 위한 구문
    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View view = null;
        // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
        if (v == null) {
            // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
            view = mInflater.inflate(R.layout.lostlist_listview_content, null);
        } else {
            view = v;
        }
        // 자료를 받는다.
        final LostListData data = this.getItem(position);
        if (data != null) {
            //화면 출력
            TextView title = (TextView) view.findViewById(R.id.lostlist_listview_title);
            TextView content= (TextView) view.findViewById(R.id.lostlist_listview_content);
            TextView type = (TextView) view.findViewById(R.id.lostlist_listview_type);
            TextView id = (TextView) view.findViewById(R.id.lostlist_listview_id);
            TextView url = (TextView) view.findViewById(R.id.lostlist_listview_url);
            TextView date = (TextView) view.findViewById(R.id.lostlist_listview_date);
            TextView take_place = (TextView) view.findViewById(R.id.lostlist_listview_take_plcae);
            TextView contact = (TextView) view.findViewById(R.id.lostlist_listview_contact);
            TextView position0 = (TextView) view.findViewById(R.id.lostlist_listview_position);
            TextView plcae = (TextView) view.findViewById(R.id.lostlist_listview_place);
            TextView thing = (TextView) view.findViewById(R.id.lostlist_listview_thing);
            TextView image_url = (TextView) view.findViewById(R.id.lostlist_listview_image_url);

            title.setText(data.getLost_name());
            content.setText(data.getLost_date()+" "+data.getLost_place()+" "+data.getLost_position());
            type.setText(data.getLost_status());
            id.setText(data.getLost_id());
            url.setText(data.getLost_url());
            date.setText(data.getLost_date());
            take_place.setText(data.getLost_take_place());
            contact.setText(data.getLost_contact());
            position0.setText(data.getLost_position());
            plcae.setText(data.getLost_place());
            thing.setText(data.getLost_thing());
            image_url.setText(data.getLost_image_url());



        }
        return view;
    }
}
