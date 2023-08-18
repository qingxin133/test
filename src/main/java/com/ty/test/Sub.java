package com.ty.test;


import java.util.HashMap;
import java.util.Map;

class Supper {
    public int get() {
        System.out.println("Supper");
        return 5;
    }
}

public class Sub {


    public int get() {
        System.out.println("Sub");
        return new Integer("5");
    }

    public static void main(String[] args) {
        for(int i=1;i<=5;i++){
            int p=1; p*=i;
        }
    }
}