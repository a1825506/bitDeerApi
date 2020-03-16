package com.mengfei.admApijava.model;

public class HashList {


        public String getOrder_uuid() {
            return order_uuid;
        }

        public void setOrder_uuid(String order_uuid) {
            this.order_uuid = order_uuid;
        }

        public String getHash_info() {
            return hash_info;
        }

        public void setHash_info(String hash_info) {
            this.hash_info = hash_info;
        }

        public String getPoor() {
            return poor;
        }

        public void setPoor(String poor) {
            this.poor = poor;
        }

        public String getElemaining_electricity_day() {
            return elemaining_electricity_day;
        }

        public void setElemaining_electricity_day(String elemaining_electricity_day) {
            this.elemaining_electricity_day = elemaining_electricity_day;
        }

        public String getCumulative_output() {
            return cumulative_output;
        }

        public void setCumulative_output(String cumulative_output) {
            this.cumulative_output = cumulative_output;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        private String order_uuid;//相应单号

        private String hash_info;//算力信息

        private String poor;//当前矿池

        private String elemaining_electricity_day;//剩余电费天数

        private String cumulative_output;//累计产出

        private int status;//0:待生效  1：生效中  2：已结束 100：全部


}
