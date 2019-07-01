package com.query.Exception;

public class HasLoginException extends Exception{
    @Override
    public void printStackTrace() {
        System.out.println("用户已登陆！");
    }
}
