package com.travel.childern.model;


import javax.persistence.*;

@Entity
@Table(name = "settings")
public class Settings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "img_upload_dir")
    private  String imgUploadDir;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "host")
    private String host;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImgUploadDir() {
        return imgUploadDir;
    }

    public void setImgUploadDir(String imgUploadDir) {
        this.imgUploadDir = imgUploadDir;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
