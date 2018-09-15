package com.kajohnsen.shared;

import java.io.Serializable;

public class Results implements Serializable {
    boolean success;
    Object data;
    String errorInfo = null;

    public Results(boolean success, Object data, String errorInfo) {
        this.success = success;
        this.data = data;
        this.errorInfo = errorInfo;
    }

    public Results(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }
}
