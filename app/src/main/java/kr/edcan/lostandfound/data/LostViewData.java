package kr.edcan.lostandfound.data;

/**
 * Created by Junseok on 2015-10-29.
 */
public class LostViewData {
    private String title;
    private String content;

   public  LostViewData(String title, String content){
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
