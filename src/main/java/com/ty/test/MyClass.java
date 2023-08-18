package com.ty.test;

public class MyClass {
    long var;

    public  MyClass(long param) { var = param; }//(1)

    public static void main(String[] args) {
        MyClass a, b,c;
        a =new MyClass(5L);//(2)
        b =new MyClass(5L);//(3)
        c =b;
        long s =42L;
        System.out.println(a==b);
//        System.out.println(s==a);
        System.out.println(b==c);
        System.out.println(a.equals(s));
    }
}