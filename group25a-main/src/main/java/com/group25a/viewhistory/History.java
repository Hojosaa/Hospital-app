package com.group25a.viewhistory;

import java.util.Stack;

import javax.swing.JPanel;

public class History {

    private Stack<JPanel> views;

    public History() {
        this.views = new Stack<>();
    }

    public void addView(JPanel view) {
        views.push(view);
    }

    public JPanel goBack() {
        if (views.size() > 1) {
            JPanel view = views.pop();
            return view;
        }
        return null;
    }

}
