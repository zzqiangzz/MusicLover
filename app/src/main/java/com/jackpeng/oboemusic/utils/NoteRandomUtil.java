package com.jackpeng.oboemusic.utils;

import android.text.TextUtils;

import java.util.Random;

public class NoteRandomUtil {
    //部分声音还没录进来
    public static String[] NOTE_ARRAY = new String[]
            {"xc1","xcd1","xd1","xde1","xe1","xf1","xfg1","xg1","xga1","xa1"/*,"xab1","xb1"*/
                    ,"c1","cd1","d1","de1","e1","f1",/*"fg1",*/"g1","ga1","a1"/*,"xab1","xb1"*/
            };

    public static String getRandomNote(){
        Random random = new Random();
        int result = random.nextInt(NOTE_ARRAY.length-1);
        return NOTE_ARRAY[result];
    }

    //不同组的音符最终只有12个音名
    public static String getNoteByGroupNote(String groupNote){
        if (TextUtils.isEmpty(groupNote)){
            return "";
        } else {
            switch (groupNote){
                case "xc2":
                case "xc1":
                case "c":
                case "c1":
                case "c2":
                case "c3":
                case "c4":
                case "c5":
                    return "c";
                case "xd2":
                case "xd1":
                case "d":
                case "d1":
                case "d2":
                case "d3":
                case "d4":
                    return "d";
                case "xe2":
                case "xe1":
                case "e":
                case "e1":
                case "e2":
                case "e3":
                case "e4":
                    return "e";
                case "xf2":
                case "xf1":
                case "f":
                case "f1":
                case "f2":
                case "f3":
                case "f4":
                    return "f";
                case "xg2":
                case "xg1":
                case "g":
                case "g1":
                case "g2":
                case "g3":
                case "g4":
                    return "g";
                case "xa2":
                case "xa1":
                case "a":
                case "a1":
                case "a2":
                case "a3":
                case "a4":
                    return "a";
                case "xb2":
                case "xb1":
                case "b":
                case "b1":
                case "b2":
                case "b3":
                case "b4":
                case "b5":
                    return "b";
                case "xcd2":
                case "xcd1":
                case "cd":
                case "cd1":
                case "cd2":
                case "cd3":
                case "cd4":
                case "cd5":
                    return "cd";
                case "xde2":
                case "xde1":
                case "de":
                case "de1":
                case "de2":
                case "de3":
                case "de4":
                    return "de";
                case "xfg2":
                case "xfg1":
                case "fg":
                case "fg1":
                case "fg2":
                case "fg3":
                case "fg4":
                    return "fg";
                case "xga2":
                case "xga1":
                case "ga":
                case "ga1":
                case "ga2":
                case "ga3":
                case "ga4":
                    return "ga";
                case "xab2":
                case "xab1":
                case "ab":
                case "ab1":
                case "ab2":
                case "ab3":
                case "ab4":
                    return "ab";
                default:
                    return "";
            }
        }
    }
}
