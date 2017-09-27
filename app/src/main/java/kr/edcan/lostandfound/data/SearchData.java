package kr.edcan.lostandfound.data;

/**
 * Created by Junseok on 2015-10-29.
 */
public class SearchData {
    private String lost_id;    //분실물 ID
    private String lost_name;   //습득물품명
    private String lost_date;   //습득 일자
    private String lost_take_place; //수령가능장소
    private String lost_cate;   //습득물 분류
    private String lost_position;   //습득위치_회사명
    private String lost_code;   //습득물품 코드

    public SearchData(
            String lost_id,
            String lost_name,
            String lost_date,
            String lost_take_place,
            String lost_cate,
            String lost_position,
            String lost_code) {

        this.lost_id = lost_id;
        this.lost_name = lost_name;
        this.lost_date = lost_date;
        this.lost_take_place = lost_take_place;
        this.lost_cate = lost_cate;
        this.lost_position = lost_position;
        this.lost_code = lost_code;
    }

    public String getLost_id() {
        return lost_id;
    }

    public String getLost_name() {
        return lost_name;
    }

    public String getLost_date() {
        return lost_date;
    }

    public String getLost_take_place() {
        return lost_take_place;
    }

    public String getLost_cate(){ return lost_cate;}

    public String getLost_position() {
        return lost_position;
    }

    public String getLost_code(){ return lost_code;     }

}
