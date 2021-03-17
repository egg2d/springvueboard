package com.board.practice.Vo;

import com.board.practice.Util.StatusCode;
import lombok.Data;

@Data
public class Message {

    private StatusCode status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusCode.BAD_REQUEST;
        this.data = null;
        this.message = null;
    }


}
