package com.example.nba.service;

public interface NBAListener<T> {
    void onSuccess(T items);
    void onFailed(String msg);
}
