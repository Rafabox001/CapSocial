package com.example.rafaeldomingo.testapp.net.request;


public class LoginRequest {

    public Data data;

    public LoginRequest(String user, String pass) {
        data = new Data(pass,user);
    }

    private LoginRequest(){}

    public class Data{

        public Data(String pass, String user) {
            this.pass = pass;
            this.user = user;
        }

        public String pass;
        public String user;
    }
}
