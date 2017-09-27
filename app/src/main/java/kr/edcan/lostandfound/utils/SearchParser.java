package kr.edcan.lostandfound.utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import kr.edcan.lostandfound.data.SearchData;

/**
 * Created by kimok_000 on 2015-11-27.
 */
public class SearchParser {
    private final static String URL = "http://openapi.seoul.go.kr:8088";   //DB URL
    private static String KEY = "49444a6c546b696d38307665587259"; //공공DB 접근을 위한 인증키
    private final static String TYPE = "xml";   //DB출력 형식
    private final static String SERVICE = "SearchLostArticleService";     //서비스명
    private String wb_code;    //물품 코드
    private String cate;
    private String get_name;
    private String url;

    private Thread thread;

    private String code = null;
    private String message = null;
    private String lost_list_count = null;

    private String[] lost_id;    //분실물 ID
    private String[] lost_name;   //습득물품명
    private String[] lost_date;   //습득 일자
    private String[] lost_take_place; //수령가능장소
    private String[] lost_position;   //습득위치_회사명

    private Elements parse;
    private Document doc;

    private ArrayList<SearchData> arrayList;

    public SearchParser(String wb_code, String cate, String get_name) {      //생성시 분류코드와 분류, 검색어를 받아와서 링크 작성
        this.wb_code = wb_code;
        this.cate = cate;
        if(get_name==null){     //검색어가 없을 경우에 대한 예외 처리
            this.get_name = "";
        }else{
            this.get_name=get_name;
        }
        Log.e("tt",wb_code+" "+cate+" "+get_name);
    }

    public void initData(){     //어레이 초기화
        arrayList= new ArrayList<>();
    }

    public  void loadData(final int start_index, final int end_index) {                 //데이터를 가져올 링크를 생성하고 데이터를 가져와서 어레이에 넣음
        Runnable task = new Runnable() {
            @Override
            public void run() {
                url = URL + "/" + KEY + "/" + TYPE + "/" + SERVICE + "/" + start_index + "/" + end_index + "/" + wb_code + "/" + cate + "/" + get_name;
                Log.e("turl",url);
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    //TODO 인터넷 연결을 확인하세요
                }

                message = doc.select("message").text().toString();
                code = doc.select("result code").text().toString();
                lost_list_count=doc.select("list_total_count").text().toString();

                lost_id = exParse(doc, "ID", lost_id);  //데이터 받아오기
                lost_name = exParse(doc, "get_name", lost_name);
                lost_date = exParse(doc, "get_date", lost_date);
                lost_take_place = exParse(doc, "take_place", lost_take_place);
                lost_position = exParse(doc, "get_position", lost_position);

                for (int i = 0; i < lost_id.length; i++) {
                    Log.e("t",lost_id[i]);
                    arrayList.add(new SearchData(
                            lost_id[i].replace(":", ""),
                            lost_name[i].replace(":", ""),
                            lost_date[i].replace(":", ""),
                            lost_take_place[i].replace(":", ""),
                            wb_code.replace(":", ""),
                            lost_position[i].replace(":", ""),
                            cate.replace(":", "")
                    ));

                    Log.e("TTT", "몇 번 호출 되는지 확인하시오");
                }
            }
        };
        thread = new Thread(task);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            //todo
        }
    }   //분실물 목록 파싱 함수

    public ArrayList<SearchData> getArrayList(){      //파싱한 데이터는 모두 이 클래스에 저장되어 있으므로 다른 곳에서 사용하려면 사용
        return arrayList;
    }   //파싱 함수에서 저장한 어레이 리스트를 반환

    private String[] exParse(Document doc, String select, String[] array) {  //가져온 데이터를 쪼개서 배열에 넣는 메소드
        parse = doc.select(select);
        array = new String[parse.size()];
        for (int i = 0; i < parse.size(); i++) {
            array[i] = parse.get(i).text().toString();
        }
        return array;
    }

    public String getCode(){        //에러 코드 가져올 때 사용
        return code;
    }
}
