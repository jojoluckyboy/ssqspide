package com.spider.lottery.pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SanDJsonOriBean {

    private String page;
    private String timer;
    private List<JsonSanDBean> list;

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

    public List<JsonSanDBean> getList() {
        return list;
    }

    public void setList(List<JsonSanDBean> list) {
        this.list = list;
    }

    public class JsonSanDBean {




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

        @SerializedName("2")
        public String r2;

        @SerializedName("3")
        private String r3;

        @SerializedName("4")
        private String r4;

        @SerializedName("5")
        private String r5;

        @SerializedName("7")
        private String r7;

        @SerializedName("8")
        private String r8;

        @SerializedName("9")
        private String r9;

        @SerializedName("11")
        private String s11;

        @SerializedName("12")
        private String s12;

        @SerializedName("13")
        private String s13;

        @SerializedName("14")
        private String s14;

        @SerializedName("15")
        private String s15;

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

        public String getR4() {
            return r4;
        }

        public void setR4(String r4) {
            this.r4 = r4;
        }

        public String getR5() {
            return r5;
        }

        public void setR5(String r5) {
            this.r5 = r5;
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

        public String getS11() {
            return s11;
        }

        public void setS11(String s11) {
            this.s11 = s11;
        }

        public String getS12() {
            return s12;
        }

        public void setS12(String s12) {
            this.s12 = s12;
        }

        public String getS13() {
            return s13;
        }

        public void setS13(String s13) {
            this.s13 = s13;
        }

        public String getS14() {
            return s14;
        }

        public void setS14(String s14) {
            this.s14 = s14;
        }

        public String getS15() {
            return s15;
        }

        public void setS15(String s15) {
            this.s15 = s15;
        }




    }


}