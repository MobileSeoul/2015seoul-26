package kr.edcan.lostandfound.data;

/**
 * Created by kimok_000 on 2015-11-28.
 */
public class SaveViewData {
    private String title;
    private String content;

    public  SaveViewData(String title, String content){
        this.title=title;
        this.content=content;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }
}
