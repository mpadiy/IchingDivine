package com.wellwood.ichingdivine.model;

/**
 * Created by waterwoodwell on 2016/6/15.
 */
public class Recordinfo {
    String name;
    String reason;
    String verify;
    String dtime;

    public Recordinfo(String name, String reason, String verify, String dtime) {
        this.name = name;
        this.reason = reason;
        this.verify = verify;
        this.dtime = dtime;
    }
    public Recordinfo() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVerify() {
        return this.verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getdtime() {
        return this.dtime;
    }

    public void setdtime(String dtime) {
        this.dtime = dtime;
    }




}
