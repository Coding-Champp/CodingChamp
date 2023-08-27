package com.example.codingchamp;

public class ModelClass {
    String Questions,A,B,C,D,Ans;

    public String getQuestions() {
        return Questions;
    }

    public void setQuestions(String questions) {
        Questions = questions;
    }

    public String getA() {
        return A;
    }

    public void setA(String a) {
        A = a;
    }

    public String getB() {
        return B;
    }

    public void setB(String b) {
        B = b;
    }

    public String getC() {
        return C;
    }

    public void setC(String c) {
        C = c;
    }

    public String getD() {
        return D;
    }

    public void setD(String d) {
        D = d;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }

    public ModelClass(String questions, String a, String b, String c, String d, String ans) {
        Questions = questions;
        A = a;
        B = b;
        C = c;
        D = d;
        Ans = ans;
    }
}
