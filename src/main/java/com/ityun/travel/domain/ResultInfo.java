package com.ityun.travel.domain;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 封装后端返回给前端的数据对象
 */
public class ResultInfo implements Serializable {
    private boolean flag;
    private Object data = null;
    private String msg;

    public ResultInfo() {
    }

    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    public ResultInfo(boolean flag, String msg) {
        this.flag = flag;
        this.msg = msg;
    }

    public ResultInfo(boolean flag, Object data, String msg) {
        this.flag = flag;
        this.data = data;
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
