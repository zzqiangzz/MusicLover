package com.jackpeng.oboemusic.utils;

import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class NoteRandomUtil {
    //十二音阶
    public static String[] ALL_NOTE = new String[]
            {"C","C#(Db)","D","D#(Eb)","E","F","F#(Gb)","G","G#(Ab)","A","A#(Bb)","B"};

    //音阶源：部分声音还没录进来
    public static String[] NOTE_ARRAY = new String[]
            {"xc1","xcd1","xd1","xde1","xe1","xf1","xfg1","xg1","xga1","xa1","xab1","xb1"/*大字一组*/
                    ,"xc","xcd","xd","xde","xe","xf","xfg","xg","xga","xa","xab","xb"/*大字组*/
                    ,"c","cd","d","de","e","f","fg","g","ga","a","ab","b"/*小字组*/
                    ,"c1","cd1","d1","de1","e1","f1","fg1","g1","ga1","a1","ab1","b1"/*小字一组*/
                    ,"c2","cd2","d2","de2","e2","f2","fg2","g2","ga2","a2","ab2","b2"/*小字二组*/
                    ,"c3","cd3","d3"/*,"de3","e3","f3","fg3","g3","ga3","a3","ab3","b3"*//*小字三组*/
                    /*,"c4","cd4","d4","de4","e4","f4","fg4","g4","ga4","a4","ab4","b4"*//*小字四组*/
                    /*,"c5"*//*小字五组*/
            };

    public static int getIndexByNoteStr(String noteStr){
        if (TextUtils.isEmpty(noteStr)){
            return 0;
        }
        for (int i=0;i<NOTE_ARRAY.length;i++){
            if (NOTE_ARRAY[i].equals(noteStr)){
                return i;
            }
        }
        return 0;
    }

    public static String getRandomNote(){
        Random random = new Random();
        int result = random.nextInt(NOTE_ARRAY.length-1);
        return NOTE_ARRAY[result];
    }

    public static String getRandomNoteByRange(int startIndex,int endIndex){
        Random random = new Random();
        int realStart = startIndex<=endIndex?startIndex:endIndex;
        if (startIndex==endIndex){
            if (startIndex<NOTE_ARRAY.length){
                return NOTE_ARRAY[startIndex];
            }else {
                return NOTE_ARRAY[NOTE_ARRAY.length-1];
            }
        } else {
            int result = random.nextInt(Math.abs(endIndex-startIndex));
            if (realStart+result<NOTE_ARRAY.length){
                return NOTE_ARRAY[realStart+result];
            } else {
                return NOTE_ARRAY[NOTE_ARRAY.length-1];
            }
        }
    }

    public static List<String> mRangeNoteList = new ArrayList<>();
    public static String getRandomNoteByRangeWithNotes(int startIndex, int endIndex, List<String> limitNotes){
        if (limitNotes == null || limitNotes.size()<=0){//传空的，那就全音阶都来
            String randomStr = getRandomNoteByRange(startIndex,endIndex);
            return randomStr;
        }
        //判断传入的集合跟自己的是不是一样
        Collections.sort(limitNotes);
        Collections.sort(mRangeNoteList);
        Log.d("Utils","startIndex is"+startIndex + "=endIndex is"+endIndex);
        Log.d("Utils","limitNotes.toString() "+limitNotes.toString());
        Log.d("Utils","mRangeNoteList.toString() "+mRangeNoteList.toString());
        if (limitNotes.toString().equals(mRangeNoteList.toString())){
            //一样

        } else {
            //先拿到区域内的所有音阶
            mRangeNoteList.clear();
            //List<String> rangeNotes = new ArrayList<>();
            for (int i = startIndex;i<=endIndex;i++){
                String tempNoteStr = NOTE_ARRAY[i];
                for (String str : limitNotes){
                    if (tempNoteStr.equals(str)){
                        //一样的音阶
                        mRangeNoteList.add(tempNoteStr);
                    } else if (!tempNoteStr.startsWith("x")){//小字组
                        //截取掉数字
                        String tempStr = tempNoteStr.substring(0,tempNoteStr.length()-1);
                        Log.d("Utils","tempStr is"+tempStr);
                        if (tempStr.equals(str)){
                            mRangeNoteList.add(tempNoteStr);
                        } else {
                        }
                    } else {//大字组
                        //截取掉x
                        String xTempStr = tempNoteStr.substring(1,tempNoteStr.length()-1);
                        Log.d("Utils","xTempStr is"+xTempStr);
                        if (xTempStr.equals(str)){
                            //一样了
                            mRangeNoteList.add(tempNoteStr);
                        } else {
                            //不一样，再截取
                            String noneXStr = xTempStr.substring(0,tempNoteStr.length()-1);
                            Log.d("Utils","noneXStr is"+noneXStr);
                            if (noneXStr.equals(str)){
                                mRangeNoteList.add(tempNoteStr);
                            } else {
                                //是直没匹配上
                            }
                        }
                    }
                }
            }
        }

        if (mRangeNoteList.size()>0){
            //从这个集合里随机
            Random random = new Random();
            int index = random.nextInt(mRangeNoteList.size());
            return mRangeNoteList.get(index);
        } else {
            //根本没这法练习，返回null,让提示用户
            return null;
        }
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
                case "d5":
                    return "d";
                case "xe2":
                case "xe1":
                case "e":
                case "e1":
                case "e2":
                case "e3":
                case "e4":
                case "e5":
                    return "e";
                case "xf2":
                case "xf1":
                case "f":
                case "f1":
                case "f2":
                case "f3":
                case "f4":
                case "f5":
                    return "f";
                case "xg2":
                case "xg1":
                case "g":
                case "g1":
                case "g2":
                case "g3":
                case "g4":
                case "g5":
                    return "g";
                case "xa2":
                case "xa1":
                case "a":
                case "a1":
                case "a2":
                case "a3":
                case "a4":
                case "a5":
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
                case "de5":
                    return "de";
                case "xfg2":
                case "xfg1":
                case "fg":
                case "fg1":
                case "fg2":
                case "fg3":
                case "fg4":
                case "fg5":
                    return "fg";
                case "xga2":
                case "xga1":
                case "ga":
                case "ga1":
                case "ga2":
                case "ga3":
                case "ga4":
                case "ga5":
                    return "ga";
                case "xab2":
                case "xab1":
                case "ab":
                case "ab1":
                case "ab2":
                case "ab3":
                case "ab4":
                case "ab5":
                    return "ab";
                default:
                    return "";
            }
        }
    }
}
