package kr.edcan.lostandfound.data;

/**
 * Created by kimok_000 on 2015-11-28.
 */
public class CenterData {
    private String title;
    private String content;
    private String map;
    private String tel;

    public CenterData(String title, String content, String map, String tel){
        this.title = title;
        this.content = content;
        this.map = map;
        this.tel = tel;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getMap(){
        return map;
    }

    public String getTel(){
        return tel;
    }
}
