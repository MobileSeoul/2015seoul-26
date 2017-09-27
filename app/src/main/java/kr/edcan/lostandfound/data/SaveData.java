package kr.edcan.lostandfound.data;

/**
 * Created by kimok_000 on 2015-11-28.
 */
public class SaveData {
    private String title;
    private String content;
    private String type;
    private String id;
    private String url;
    private String date;
    private String take_place;
    private String contact;
    private String position0;
    private String place;
    private String thing;
    private String image_url;

    public SaveData(
            String title,
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

        this.title = title;
        this.content = content;
        this.type = type;
        this.id = id;
        this.url = url;
        this.date = date;
        this.take_place = take_place;
        this.contact = contact;
        this.position0 = position0;
        this.place = place;
        this.thing = thing;
        this.image_url = image_url;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){return content;}

    public String getType(){return type;}

    public String getId(){return id;}

    public String getUrl(){
        return url;
    }

    public String getDate(){ return date;}

    public String getTake_place(){return take_place;}

    public String getContact(){return contact;}

    public String getPosition0(){return position0;}

    public String getPlace(){return place;}

    public String getThing(){return thing;}

    public String getImage_url(){return image_url;}
}
