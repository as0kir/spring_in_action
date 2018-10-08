package com.apress.prospring4.ch3.replacement;

public class ReplacementTarget {
    public String formatMessage(String msg) {
        return "<hl>" + msg + "</hl>";
    }

    public String formatMessage(Object msg) {
        return "<hl>" + msg + "</hl>";
    }
}
