package com.tudou.system.platform.base.model;

import java.util.Map;

/**
 * Created by Administrator on 2016/12/25 0025.
 */
public class StatusModel {
    // 状态
    private boolean status;
    // 状态描述
    private String msg;
    // 其他数据
    private Map<String, Object> other;

    public Map<String, Object> getOther() {
        return other;
    }

    public void setOther(Map<String, Object> other) {
        this.other = other;
    }

    public StatusModel(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public StatusModel(boolean status, Map<String, Object> other) {
        this.status = status;
        this.other = other;
    }

    public StatusModel(boolean status, String msg, Map<String, Object> other) {
        this.status = status;
        this.msg = msg;
        this.other = other;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
