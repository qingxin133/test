package com.ty.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.reflect.generics.tree.Tree;

import java.util.Locale;
import java.util.TreeMap;

@SpringBootTest
class TestApplicationTests {

    @Test
    void contextLoads() {
        TreeMap treeMap;
    }

    @Test
    void teststr(){
        String str="MDX-393OTIM-157.MP4";
        String repStr = "MDX-";
        String replace = str.replace(repStr,"");
        System.out.println(replace);
    }
    @Test
    void testIndex(){
        String oldFileName = "STARS-818.mp4.mp4";
        String s = oldFileName.substring(oldFileName.lastIndexOf(".")).toLowerCase(Locale.ROOT);
        System.out.println(s);
    }

}
