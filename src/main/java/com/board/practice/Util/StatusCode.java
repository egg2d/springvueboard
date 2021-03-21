package com.board.practice.Util;

public enum StatusCode {

    // 응담관련 코드
    BAD_REQUEST(0000,"잘못된 요청입니다."),
    SUCCESS(0001,"성공하였습니다."),

    // User관련 코드
    ALREADY_REGISTERED(1000, "이미 가입된 회원입니다"),
    NOT_ID(1004, "생년월일을 입력하세요."),
    NOT_USERNAME(1001, "이름을 입력하세요."),
    NOT_EMAIL(1002, "이메일을 입력하세요."),
    NOT_PASSWORD(1003, "패스워드를 입력하세요."),
    NOT_BIRTHDAY(1004, "생년월일을 입력하세요."),

    // Login 관련 코드
    LOGIN_SUCCESS(2000,"로그인에 성공하였습니다"),
    LOGIN_FAIL(2001,"로그인에 실패하였습니다."),
    LOGIN_ERROR(2002,"로그인 진행시 에러가 발생하였습니다");


    private int code;
    private String message;

    private StatusCode(int code, String message) {

        this.code = code;
        this.message = message;

    }

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

}
