package com.tebsonatishop;

public class RecyclerModel {
    private String id;
    private String onvan;
    private String matn;
    private String picture;
    private String city;
    private String position;
    private float rate;
    private String countRateAndComment;
    private String idTimeTakhir;
    public RecyclerModel(String id, String onvan, String matn , String picture, String city,
                         String position, float rate, String countRateAndComment, String idTimeTakhir) {
        this.id = id;
        this.onvan = onvan;
        this.matn = matn;
        this.picture = picture;
        this.city = city;
        this.position = position;
        this.rate = rate;
        this.countRateAndComment = countRateAndComment;
        this.idTimeTakhir = idTimeTakhir;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getId() {
        return id;
    }



    public String getCountRateAndComment() {
        return countRateAndComment;
    }

    public String getOnvan() {
        return onvan;
    }
    public String getMatn() {
        return matn;
    }

    public void setMatn(String matn) {
        this.matn = matn;
    }

    public String getPicture() {
        return picture;
    }

    public String getCity() {
        return city;
    }

    public String getPosition() {
        return position;
    }

    public String getIdTimeTakhir() {
        return idTimeTakhir;
    }
}