package com.example.asus.probazaizpit;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
@Entity(tableName = "cats")
public class Cat {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "imgUrl")
    private String imgUrl;

    @ColumnInfo(name = "txtNameCat")
    private String txtNameCat;

    @ColumnInfo(name = "txtInfoCat")
    private String txtInfoCat;

    @ColumnInfo(name = "isLiked")
    private boolean isLiked;

    public Cat(String imgUrl, String txtNameCat, String txtInfoCat, boolean isLiked) {
        this.imgUrl = imgUrl;
        this.txtNameCat = txtNameCat;
        this.txtInfoCat = txtInfoCat;
        this.isLiked = false;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTxtNameCat() {
        return txtNameCat;
    }

    public String getTxtInfoCat() {
        return txtInfoCat;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
