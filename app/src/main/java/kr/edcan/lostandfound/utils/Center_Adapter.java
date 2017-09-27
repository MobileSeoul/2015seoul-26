package kr.edcan.lostandfound.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import kr.edcan.lostandfound.R;
import kr.edcan.lostandfound.activity.WebViewActivity;
import kr.edcan.lostandfound.data.CenterData;

/**
 * Created by kimok_000 on 2015-11-28.
 */
public class Center_Adapter extends ArrayAdapter<CenterData> {
    private LayoutInflater mInflater;
    private Context context;

    public Center_Adapter(Context context, ArrayList<CenterData> object) {
        super(context, 0, object);
        this.context = context;
        mInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View view = null;
        // 현재 리스트의 하나의 항목에 보일 컨트롤 얻기
        if (v == null) {
            // XML 레이아웃을 직접 읽어서 리스트뷰에 넣음
            view = mInflater.inflate(R.layout.center_info_content, null);
        } else {
            view = v;
        }
        final CenterData data = this.getItem(position);
        if (data != null) {

            //화면 출력
            TextView title = (TextView) view.findViewById(R.id.title);
            final TextView content = (TextView) view.findViewById(R.id.content);
            RelativeLayout button0 = (RelativeLayout) view.findViewById(R.id.button0);
            RelativeLayout button1 = (RelativeLayout) view.findViewById(R.id.button1);
            final LinearLayout vis = (LinearLayout) view.findViewById(R.id.vis);
            RelativeLayout grid = (RelativeLayout) view.findViewById(R.id.grid);
            TextView button_text = (TextView) view.findViewById(R.id.button_text);
            final ImageView icon = (ImageView) view.findViewById(R.id.icon);

            grid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (vis.getVisibility() == View.GONE) {
                        icon.setImageResource(R.drawable.ic_spinner);
                        vis.setVisibility(View.VISIBLE);
                    } else {
                        icon.setImageResource(R.drawable.ic_spinner_hold);
                        vis.setVisibility(View.GONE);
                    }
                }
            });

            button0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent goWeb = new Intent(getContext(), WebViewActivity.class);
                    goWeb.putExtra("url", "http://maps.google.com/maps?f=d&saddr=&daddr=" + data.getMap() + "&hl=ko"); //지도 출력
                    goWeb.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(goWeb);
                }
            });

            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goTell = new Intent(Intent.ACTION_CALL).setData(Uri.parse("tel:" + data.getTel()));   //전화 걸기
                    goTell.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(goTell);
                }
            });

            title.setText(data.getTitle());
            content.setText(data.getContent());
        }

        return view;
    }
}
