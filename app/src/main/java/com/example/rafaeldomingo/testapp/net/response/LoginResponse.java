package com.example.rafaeldomingo.testapp.net.response;


public class LoginResponse extends BaseResponse {

    public String agente;
    public String id_user;
    public String token;

    @Override
    public String toString() {
        return "LoginResponse{" +
                "agente='" + agente + '\'' +
                ", id_user='" + id_user + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
