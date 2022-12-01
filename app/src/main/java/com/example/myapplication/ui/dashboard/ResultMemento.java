package com.example.myapplication.ui.dashboard;

import java.util.HashMap;
import java.util.List;

public class ResultMemento {
    private List<String> result;

    public ResultMemento(List<String> result) {
        this.result = result;
    }

    public List<String> getSavedState() {
        return result;
    }
}
