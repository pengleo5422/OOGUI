package com.example.myapplication.ui.dashboard;

public class ResultMemento {
    private boolean result;

    public ResultMemento(boolean result) {
        this.result = result;
    }

    public boolean getSavedResult() {
        return result;
    }
}
