package com.yihu.jw.restmodel.base.wx;

/**
 * Created by Trick on 2018/10/23.
 */
public class StatisticsTitleVO {

    private Integer new_user;

    private Integer cumulate_user;

    private Integer cancel_user;

    private Integer add_user;

    private String date;

    public Integer getNew_user() {
        return new_user;
    }

    public void setNew_user(Integer new_user) {
        this.new_user = new_user;
    }

    public Integer getCumulate_user() {
        return cumulate_user;
    }

    public void setCumulate_user(Integer cumulate_user) {
        this.cumulate_user = cumulate_user;
    }

    public Integer getCancel_user() {
        return cancel_user;
    }

    public void setCancel_user(Integer cancel_user) {
        this.cancel_user = cancel_user;
    }

    public Integer getAdd_user() {
        return add_user;
    }

    public void setAdd_user(Integer add_user) {
        this.add_user = add_user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
