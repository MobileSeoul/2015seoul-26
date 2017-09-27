package kr.edcan.lostandfound.data;

/**
 * Created by kimok_000 on 2015-11-27.
 */
public class SettingData {
    private String title;
    private String content;
    private String comment;

    public  SettingData(String title, String content,String comment){
        this.title=title;
        this.content=content;
        this.comment=comment;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getComment(){
        return comment;
    }
}
