package com.jackpeng.oboemusic.utils;

import android.app.Activity;
import android.content.Context;

import com.jackpeng.oboemusic.MainActivity;
import com.jackpeng.oboemusic.ear_interval.NoteEarTrainingActivity;

public class Proxy {
    public static void goMain(Activity activity){
        activity.startActivity(MainActivity.getThisIntent(activity));
    }

    public static void goNoteEarTraining(Activity activity){
        activity.startActivity(NoteEarTrainingActivity.getThisIntent(activity));
    }
}
