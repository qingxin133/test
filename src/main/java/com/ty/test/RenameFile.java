package com.ty.test;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class RenameFile {

    private static final String filePath = "F:\\av\\ready16\\1";
    public static List<String> nameList = new ArrayList();
    public static List<String> symbolList = new ArrayList();
    private final static String replaceMent="";

    static{
        symbolList.add("[");
        symbolList.add("]");
        symbolList.add("(");
        symbolList.add(")");
        symbolList.add("#_");
    }

    static{
        nameList.add("hhd800.com@");
        nameList.add("【入微】");
        nameList.add("(1080P)");
        nameList.add("【中文字幕】");
        nameList.add("[44x.me]");
        nameList.add("[FHD]");
        nameList.add("[Initial D] ");
        nameList.add("[thz.la]");
        nameList.add("118877.xyz");
        nameList.add("ZZPP08.COM@");
        nameList.add("@蜂鳥@FENGNIAO131.");
        nameList.add("THZ.");
        nameList.add("第一會所新片@SIS001@");
        nameList.add("(BLACK_BABY)");
        nameList.add("(FITCH)");
        nameList.add("(JET映像)");
        nameList.add("(MADONNA)");
        nameList.add("(MAX-A)");
        nameList.add("(龍縛)");
        nameList.add("BBSXV.XYZ-");
        nameList.add("guochan2048.com -");
        nameList.add("GUOCHAN2048.COM-");
        nameList.add("8社区 - BIG2048.COM@");
        nameList.add("MD-X");
        nameList.add("hhd800.com@393");
        nameList.add("freedl.org@");
        nameList.add("[Mosaic Removed Uncensored]");
        nameList.add("[168x.me]");
        nameList.add("rh2048.com@354");
        nameList.add("hhd800.com@354");
        nameList.add("rh2048.com@");
        nameList.add("hhd800.com@");
        nameList.add("MDX-393");
        nameList.add("MDX-");
        nameList.add("[LA]");
        nameList.add("(BAZOOKA)(");
        nameList.add("OLO#SIS001#");
        nameList.add("KCKC17.COM@");
        nameList.add("JAVZIP.NET-");
        nameList.add("kpkp3.com-");
        nameList.add("@蜂鳥@FENGNIAO151.VIP-");
        nameList.add(".mp4.mp4");
        nameList.add("AVFAP.NET-");
    }

    public static void main(String[] args) {
//        System.out.println(1<<30);
        RenameFile renameFile = new RenameFile();
//        System.out.println("hhd800.com@390JAC-161.mp4".replace("hhd800.com@",""));
//        System.out.println(renameFile.myIndexOf("hhd800.com@390JAC-161.mp4","hhd800.com"));
//        System.out.println(RenameFile.hasName("[SKYHD127] Double Penetrations 12Girls".toUpperCase(Locale.ROOT),"]"));
        renameFile.reNameFile(filePath);
        System.out.println("-------------------end-----------------------");
    }

    public String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    public void reNameFile(String filePath) {
        File files = new File(filePath);
        if (files.isDirectory()) {
            File[] fs = files.listFiles();
            File newFile = null;
            for (File oldFile : fs) {
                try {
                    String oldFileName = oldFile.getName().toUpperCase(Locale.ROOT);

                    oldFileName = oldFileName.trim().toUpperCase(Locale.ROOT);
                    //前三个是不是数字
                    String pattern = "^[0-9]{3,}.+$";
                    boolean isMatch = Pattern.matches(pattern, oldFileName);
                    if (isMatch) {
                        //是有后缀的，并且大于6位
                        if (oldFileName.length() > 7) {
                            if (oldFileName.lastIndexOf(".JPG") != -1) {
                                continue;
                            }
                            oldFileName = oldFileName.substring(3);
                        }
                    }

                    for (String replaceStr : nameList) {
                        replaceStr = replaceStr.toUpperCase(Locale.ROOT);
                        if (hasName(oldFileName, replaceStr)) {
                            changeName(oldFileName,replaceStr,oldFile,newFile);
                        }

                    }
                    for(String replaceStr:symbolList){
                        if (hasName(oldFileName, replaceStr)) {
                            changeName(oldFileName,replaceStr,oldFile,newFile);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (oldFile.isDirectory()) {
                    reNameFile(oldFile.getAbsolutePath());
                }
                if (null!= newFile && newFile.isDirectory()) {
                    reNameFile(newFile.getAbsolutePath());
                }
            }
        }

    }

    public void changeName(String oldFileName,String replaceStr,File oldFile,File newFile){
        String newAbsPath = "";
        String newFileName = replaceName(oldFileName, replaceStr);
        if (oldFile.isFile()) {
            if (oldFileName.lastIndexOf(".") != -1) {
                newAbsPath = oldFile.getParentFile().getPath() + "\\" + newFileName + oldFileName.substring(oldFileName.lastIndexOf(".")).toLowerCase(Locale.ROOT);
            } else {
                newAbsPath = oldFile.getParentFile().getPath() + "\\" + newFileName;
            }
        } else {
            newAbsPath = oldFile.getParentFile().getPath() + "\\" + newFileName.trim().toUpperCase(Locale.ROOT);
        }

//        newAbsPath = f.getParentFile().getPath() + "\\" + getFileNameNoEx(newName).toUpperCase(Locale.ROOT);
//        if (f.isFile()) {
//            if (newName.lastIndexOf(".") != -1) {
//                newAbsPath += newName.substring(newName.lastIndexOf(".")).toLowerCase(Locale.ROOT);
//            }
//        }
        //
        newFile = new File(newAbsPath);
        try {
            if (oldFile.renameTo(newFile)) {
                System.out.println("修改成功："+oldFileName+" -> " + newAbsPath);
            } else {
                System.out.println("修改失败：" +oldFileName+" -> " + newAbsPath);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean hasName(String fileName, String name) {
        if (StringUtils.isEmpty(fileName)) {
            return false;
        }
        int index = fileName.toUpperCase(Locale.ROOT).indexOf(name);
        if (index != -1 && fileName.length() > name.length()) {
            String substring = fileName.substring(0, index);
            return true;
        }
        return false;
    }

    public String replaceName(String fileName, String replaceStr) {
        String newFileName = fileName.replace(replaceStr, replaceMent);
        return newFileName;
    }

    public int myIndexOf(String haystack, String needle) {
        int len = haystack.length();
        int len2 = needle.length();
        if (len2 == 0)
            return 0;
        if (len2 > len)
            return -1;
        int i = 0, j = 0;
        int Ind = -1;
        boolean f = false;
        while (i < len && j < len2) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                if (f == false) {
                    Ind = i;
                    i++;
                    j++;
                    f = true;
                } else {
                    i++;
                    j++;
                }
            } else {
                i = (Ind == -1) ? (i + 1) : (Ind + 1);
                j = 0;
                f = false;
                Ind = -1;
            }
        }
        if (j < len2)
            Ind = -1;
        return Ind;
    }

}

