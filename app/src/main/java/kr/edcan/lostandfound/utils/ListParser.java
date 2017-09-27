package kr.edcan.lostandfound.utils;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import kr.edcan.lostandfound.data.LostListData;

/**
 * Created by kimok_000 on 2015-10-16.
 */
public class ListParser {        //분실물 데이터 조횐
    private final static String URL = "http://openapi.seoul.go.kr:8088";   //DB URL
    private static String KEY = "684c6153436b696d38374c4c484b59"; //공공DB 접근을 위한 인증키
    private final static String TYPE = "xml";   //DB출력 형식
    private final static String SERVICE = "ListLostArticleService";     //서비스명
    private String wb_code;    //물품 코드
    private String url;

    private Thread thread;

    private String code = null;
    private String message = null;
    private String lost_list_count = null;

    private String[] lost_id;    //분실물 ID
    private String[] lost_name;   //습득물품명
    private String[] lost_url;    //원문링크주소
    private String[] lost_title;  //습득물 분류
    private String[] lost_date;   //습득 일자
    private String[] lost_take_place; //수령가능장소
    private String[] lost_contact;    //수령가능장소 연락처
    private String[] lost_cate;   //습득물 분류
    private String[] lost_position;   //습득위치_회사명
    private String[] lost_place;  //습득위치
    private String[] lost_thing;  //습득 물품 상세
    private String[] lost_status; //분실물 상태
    private String[] lost_code;   //습득물품 코드
    private String[] lost_image_url;  //이미지URL

    private Elements parse;
    private Document doc;

    private ArrayList<LostListData>  arrayList;

    public ListParser(String wb_code) {      //생성시 분류코드를 받아와서 링크 작성
        this.wb_code = wb_code;
    }

    public void initData(){     //어레이 초기화
        arrayList= new ArrayList<>();
    }

    public  void loadData(final int start_index, final int end_index) {                 //데이터를 가져올 링크를 생성하고 데이터를 가져와서 어레이에 넣음
        Runnable task = new Runnable() {
            @Override
            public void run() {
                url = URL + "/" + KEY + "/" + TYPE + "/" + SERVICE + "/" + start_index + "/" + end_index + "/" + wb_code;

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
                lost_url = exParse(doc, "url", lost_url);
                lost_title = exParse(doc, "title", lost_title);
                lost_date = exParse(doc, "get_date", lost_date);
                lost_take_place = exParse(doc, "take_place", lost_take_place);
                lost_contact = exParse(doc, "contact", lost_contact);
                lost_cate = exParse(doc, "cate", lost_cate);
                lost_position = exParse(doc, "get_position", lost_position);
                lost_place = exParse(doc, "get_place", lost_place);
                lost_thing = exParse(doc, "get_thing", lost_thing);
                lost_status = exParse(doc, "status", lost_status);
                lost_code = exParse(doc, "row code", lost_code);
                lost_image_url = exParse(doc, "image_url", lost_image_url);

                for (int i = 0; i < lost_id.length; i++) {
                    arrayList.add(new LostListData(
                            lost_id[i].replace(":", ""),
                            lost_name[i].replace(":", ""),
                            lost_url[i],
                            lost_title[i].replace(":", ""),
                            lost_date[i].replace(":", ""),
                            lost_take_place[i].replace(":", ""),
                            lost_contact[i].replace(":", ""),
                            lost_cate[i].replace(":", ""),
                            lost_position[i].replace(":", ""),
                            lost_place[i].replace(":", ""),
                            lost_thing[i].replace("<br>","\n"),
                            lost_status[i].replace(":", ""),
                            lost_code[i].replace(":", ""),
                            lost_image_url[i]
                    ));

                    Log.e("TTT","몇 번 호출 되는지 확인하시오");
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

    public ArrayList<LostListData> getArrayList(){      //파싱한 데이터는 모두 이 클래스에 저장되어 있으므로 다른 곳에서 사용하려면 사용
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
