package com.mizing.todoapp.utils;

import android.util.Log;


/**
 * Created by choibongjae on 2017. 7. 10..
 */

public class DLog {
    static final String TAG = "todoapp";
//    private static final boolean DEBUG = BuildConfig.DEBUG;
    private static final boolean DEBUG = true;

    /**
     * Log Level Error
     **/
    public static void e(String message) {
        if (DEBUG) {
            Log.e(TAG, buildLogMsg(message));
        }
    }

    /**
     * Log Level Warning
     **/
    public static void w(String message) {
        if (DEBUG) {
            Log.w(TAG, buildLogMsg(message));
        }
    }

    /**
     * Log Level Information
     **/
    public static void i(String message) {
        if (DEBUG) {
            Log.i(TAG, buildLogMsg(message));
        }
    }

    /**
     * Log Level Debug
     **/
    public static void d(String message) {
        if (DEBUG) {
            Log.d(TAG, buildLogMsg(message));
        }
    }

    /**
     * Log Level Verbose
     **/
    public static void v(String message) {
        if (DEBUG) {
            Log.v(TAG, buildLogMsg(message));
        }
    }

    public static String buildLogMsg(String message) {
        StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(ste.getFileName().replace(".java", ""));
        sb.append("::");
        sb.append(ste.getMethodName());
        sb.append("::");
        sb.append(ste.getLineNumber());
        sb.append("]");
        sb.append(message);
        return sb.toString();
    }

}
