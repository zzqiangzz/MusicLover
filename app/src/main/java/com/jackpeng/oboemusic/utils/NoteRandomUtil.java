package com.jackpeng.oboemusic.utils;

import java.util.Random;

public class NoteRandomUtil {
    //升a与b声音还没录进来
    public static String[] NOTE_ARRAY = new String[]{"c","cd","d","de","e","f","fg","g","ga","a"/*,"ab","b"*/};

    public static String getRandomNote(){
        Random random = new Random();
        int result = random.nextInt(NOTE_ARRAY.length-1);
        return NOTE_ARRAY[result];
    }
}
