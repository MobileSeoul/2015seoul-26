package kr.edcan.lostandfound.data;

/**
 * Created by Junseok on 2015-10-29.
 */
public class LostListData {
    private String lost_id;    //분실물 ID
    private String lost_name;   //습득물품명
    private String lost_url;    //원문링크주소
    private String lost_title;  //습득물 분류
    private String lost_date;   //습득 일자
    private String lost_take_place; //수령가능장소
    private String lost_contact;    //수령가능장소 연락처
    private String lost_cate;   //습득물 분류
    private String lost_position;   //습득위치_회사명
    private String lost_place;  //습득위치
    private String lost_thing;  //습득 물품 상세
    private String lost_status; //분실물 상태
    private String lost_code;   //습득물품 코드
    private String lost_image_url;  //이미지URL

    public LostListData(
            String lost_id,
            String lost_name,
            String lost_url,
            String lost_title,
            String lost_date,
            String lost_take_place,
            String lost_contact,
            String lost_cate,
            String lost_position,
            String lost_place,
            String lost_thing,
            String lost_status,
            String lost_code,
            String lost_image_url){

        this.lost_id = lost_id;
        this.lost_name = lost_name;
        this.lost_url = lost_url;
        this.lost_title = lost_title;
        this.lost_date = lost_date;
        this.lost_take_place = lost_take_place;
        this.lost_contact = lost_contact;
        this.lost_cate = lost_cate;
        this.lost_position = lost_position;
        this.lost_place = lost_place;
        this.lost_thing = lost_thing;
        this.lost_status = lost_status;
        this.lost_code = lost_code;
        this.lost_image_url = lost_image_url;

    }

    public String getLost_id() {
        return lost_id;
    }

    public String getLost_name() {
        return lost_name;
    }

    public String getLost_url() {
        return lost_url;
    }

    public String getLost_title() {
        return lost_title;
    }

    public String getLost_date() {
        return lost_date;
    }

    public String getLost_take_place() {
        return lost_take_place;
    }

    public String getLost_contact() {
        return lost_contact;
    }

    public String getLost_cate() {
        return lost_cate;
    }

    public String getLost_position() {
        return lost_position;
    }

    public String getLost_place() {
        return lost_place;
    }

    public String getLost_thing() {
        return lost_thing;
    }

    public String getLost_status() {
        return lost_status;
    }

    public String getLost_code() {
        return lost_code;
    }

    public String getLost_image_url() {
        return lost_image_url;
    }
}
