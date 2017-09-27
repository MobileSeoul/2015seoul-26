package kr.edcan.lostandfound.data;

/**
 * Created by kimok_000 on 2015-11-28.
 */
public class FindInfoData {
    private String title;
    private String content;
    private String button0;
    private String button1;

    public FindInfoData(String title, String content, String button0,String button1){
        this.title=title;
        this.content=content;
        this.button0=button0;
        this.button1=button1;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getButton0(){
        return button0;
    }

    public String getButton1(){
        return button1;
    }
}
