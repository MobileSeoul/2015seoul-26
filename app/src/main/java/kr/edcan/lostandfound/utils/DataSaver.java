package kr.edcan.lostandfound.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import kr.edcan.lostandfound.data.SaveData;

/**
 * Created by kimok_000 on 2015-11-28.
 */
public class DataSaver {
    private ArrayList<SaveData> arrayList;
    private int count;

    private SharedPreferences save;
    private SharedPreferences.Editor editor;
    private Context context;

    public DataSaver(Context context) {
        this.context = context;
        arrayList = new ArrayList<>();
        save = context.getSharedPreferences("save", Context.MODE_PRIVATE);
        editor = save.edit();
    }

    public void SetData(String title,
                        String content,
                        String type,
                        String id,
                        String url,
                        String date,
                        String take_place,
                        String contact,
                        String position0,
                        String place,
                        String thing,
                        String image_url) {
        count = save.getInt("max",0);

        editor.putString("title"+count,title);
        editor.putString("content"+count,content);
        editor.putString("type"+count,type);
        editor.putString("id"+count,id);
        editor.putString("url"+count,url);
        editor.putString("date"+count,date);
        editor.putString("take_place"+count,take_place);
        editor.putString("contact"+count,contact);
        editor.putString("position0"+count,position0);
        editor.putString("place"+count,place);
        editor.putString("thing"+count,thing);
        editor.putString("image_url"+count,image_url);

        count++;
        editor.putInt("max",count);
        editor.commit();
    }

    public boolean checkData(String id){
        for(int i=0;i<save.getInt("max",0);i++){
            if(save.getString("id"+i,null).equals(id)) return true;
        }
        return false;
    }

    public void resetData(){
        editor.putInt("max",0);
        editor.commit();
    }

}
