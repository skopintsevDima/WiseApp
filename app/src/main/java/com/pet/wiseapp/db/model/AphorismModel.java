package com.pet.wiseapp.db.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "Aphorisms")
public class AphorismModel {

    @Id(autoincrement = true)
    private Long _id;

    @NotNull
    private String text;

    public Long get_id() {
        return this._id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Generated(hash = 111112986)
    public AphorismModel() {
    }

    @Generated(hash = 475383603)
    public AphorismModel(Long _id, @NotNull String text) {
        this._id = _id;
        this.text = text;
    }
}
