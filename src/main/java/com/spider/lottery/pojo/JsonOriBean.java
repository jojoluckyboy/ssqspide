package com.spider.lottery.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class JsonOriBean {

    private String page;
    private String timer;
    private List<JsonThredBean> list;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTimer() {
        return timer;
    }

    public void setTimer(String timer) {
        this.timer = timer;
    }

    public List<JsonThredBean> getList() {
        return list;
    }

    public void setList(List<JsonThredBean> list) {
        this.list = list;
    }

    public class JsonThredBean {




        private Ret ret;
        private String uid;
        private String nickname;
        private String url;
        private String is_details;


        public Ret getRet() {
            return ret;
        }

        public void setRet(Ret ret) {
            this.ret = ret;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIs_details() {
            return is_details;
        }

        public void setIs_details(String is_details) {
            this.is_details = is_details;
        }



    }

    public class Ret  implements Serializable {

        public static final long serialVersionUID = 1L;

        @SerializedName("22")
        public String r2;

        @SerializedName("23")
        private String r3;

        @SerializedName("25")
        private String r5;

        @SerializedName("26")
        private String r6;

        @SerializedName("27")
        private String r7;

        @SerializedName("28")
        private String r8;

        @SerializedName("29")
        private String r9;

        @SerializedName("30")
        private String s0;

        @SerializedName("31")
        private String s1;

        @SerializedName("32")
        private String s2;

        @SerializedName("34")
        private String s4;

        @SerializedName("36")
        private String s6;



        public String getR2() {
            return r2;
        }

        public void setR2(String r2) {
            this.r2 = r2;
        }

        public String getR3() {
            return r3;
        }

        public void setR3(String r3) {
            this.r3 = r3;
        }

        public String getR5() {
            return r5;
        }

        public void setR5(String r5) {
            this.r5 = r5;
        }

        public String getR6() {
            return r6;
        }

        public void setR6(String r6) {
            this.r6 = r6;
        }

        public String getR7() {
            return r7;
        }

        public void setR7(String r7) {
            this.r7 = r7;
        }

        public String getR8() {
            return r8;
        }

        public void setR8(String r8) {
            this.r8 = r8;
        }

        public String getR9() {
            return r9;
        }

        public void setR9(String r9) {
            this.r9 = r9;
        }

        public String getS0() {
            return s0;
        }

        public void setS0(String s0) {
            this.s0 = s0;
        }

        public String getS1() {
            return s1;
        }

        public void setS1(String s1) {
            this.s1 = s1;
        }

        public String getS2() {
            return s2;
        }

        public void setS2(String s2) {
            this.s2 = s2;
        }

        public String getS4() {
            return s4;
        }

        public void setS4(String s4) {
            this.s4 = s4;
        }

        public String getS6() {
            return s6;
        }

        public void setS6(String s6) {
            this.s6 = s6;
        }


    }


}