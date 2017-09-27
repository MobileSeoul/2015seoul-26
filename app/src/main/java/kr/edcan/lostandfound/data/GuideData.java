package kr.edcan.lostandfound.data;

/**
 * Created by kimok_000 on 2015-11-28.
 */
public class GuideData {
    private String title;
    private String content;
    private String button;

    public GuideData(String title, String content, String button){
        this.title=title;
        this.content=content;
        this.button=button;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getButton(){
        return button;
    }
}
