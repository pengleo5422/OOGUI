package com.example.myapplication.ui.dashboard;

import java.util.HashMap;

public class ResultCareTaker {
    private HashMap<String, ResultMemento> resultMementoList = new HashMap<>();
    public void addMemento(String date, ResultMemento m) {
        resultMementoList.put(date, m);
    }
    public ResultMemento getMemento(String date) {
        return resultMementoList.get(date);
    }
    public boolean hasMemento(String date) {
        return resultMementoList.containsKey(date);
    }
}
