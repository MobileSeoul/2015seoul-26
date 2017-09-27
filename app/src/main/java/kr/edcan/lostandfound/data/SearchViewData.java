package kr.edcan.lostandfound.data;

/**
 * Created by kimok_000 on 2015-11-27.
 */
public class SearchViewData {
    private String title;
    private String content;

    public  SearchViewData(String title, String content){
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
