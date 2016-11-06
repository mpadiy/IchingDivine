package com.wellwood.ichingdivine.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences工具类
 * 保存 夜间模式
 * Created by tomchen on 2/3/16.
 */
public class PrefUtils {


    private static PrefUtils PrefUtils = null;
    private SharedPreferences sharedPreferences;

    private SharedPreferences.Editor shareEditor;

    //日间或者夜间模式
    private static final String PRE_THEME_MODE = "dark_mode";

    //省流量模式 这儿和R.string.save_net_mode相同
    private static final String PRE_SAVE_NET_MODE = "save_net_mode";

    private static final String PRE_NAME = "com.studychen.ichingdivine";


    private static final String RED_THEME_MODE = "red_mode";
    private static final String BROWN_THEME_MODE = "brown_mode";
    private static final String BLUE_THEME_MODE = "blue_mode";
    private static final String BLUEGREY_THEME_MODE = "bluegrey_mode";
    private static final String YELLOW_THEME_MODE = "yellow_mode";
    private static final String DP_THEME_MODE = "dp_mode";
    private static final String PINK_THEME_MODE = "pink_mode";
    private static final String GREEN_THEME_MODE = "green_mode";





    /**
     * 获取本应用的SharePreferences
     *
     * @param mContext
     * @return
     */
    private static SharedPreferences getPreferences(Context mContext) {
        String preferenceName = "beacon_service";
        return mContext.getSharedPreferences(preferenceName,
                Context.MODE_PRIVATE);
    }

    public static int getIntValue(String key, int defValue, Context mContext) {
        return getPreferences(mContext).getInt(key, defValue);
    }
    private static SharedPreferences getSharedPreferences() {
        return BaseApplication.getContext()
                .getSharedPreferences(PRE_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isRedTheme() {
        return getSharedPreferences().getBoolean(RED_THEME_MODE, false);
    }
    public static boolean isBrownTheme() {
        return getSharedPreferences().getBoolean(BROWN_THEME_MODE, false);
    }
    public static boolean isBlueTheme() {
        return getSharedPreferences().getBoolean(BLUE_THEME_MODE, false);
    }
    public static boolean isBlueGreyTheme() {
        return getSharedPreferences().getBoolean(BLUEGREY_THEME_MODE, false);
    }
    public static boolean isYellowTheme() {
        return getSharedPreferences().getBoolean(YELLOW_THEME_MODE, false);
    }
    public static boolean isDeepPurpleTheme() {
        return getSharedPreferences().getBoolean(DP_THEME_MODE, false);
    }
    public static boolean isPinkTheme() {
        return getSharedPreferences().getBoolean(PINK_THEME_MODE, false);
    }
    public static boolean isGreenTheme() {
        return getSharedPreferences().getBoolean(GREEN_THEME_MODE, false);
    }
    public static boolean isDarkMode() {
        return getSharedPreferences().getBoolean(PRE_THEME_MODE, false);
    }



    /**
     * 设置模式
     *
     *
     */
    public static void setThemeMode(int position) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        switch (position){
            case 0:
                editor.putBoolean(RED_THEME_MODE, true);
                editor.commit();
                break;
            case 1:
                editor.putBoolean(BROWN_THEME_MODE, true);
                editor.commit();
                break;
            case 2:
                editor.putBoolean(BLUE_THEME_MODE, true);
                editor.commit();
                break;
            case 3:
                editor.putBoolean(BLUEGREY_THEME_MODE, true);
                editor.commit();
                break;
            case 4:
                editor.putBoolean(YELLOW_THEME_MODE, true);
                editor.commit();
                break;
            case 5:
                editor.putBoolean(DP_THEME_MODE, true);
                editor.commit();
                break;
            case 6:
                editor.putBoolean(PINK_THEME_MODE, true);
                editor.commit();
                break;
            case 7:
                editor.putBoolean(GREEN_THEME_MODE, true);
                editor.commit();
                break;
            default:
                editor.putBoolean(GREEN_THEME_MODE, true);
                editor.commit();
                break;
        }
    }




    /**
     * 夜间模式
     *
     * @param isDarkMode true为夜间模式
     */
    public static void setDarkMode(boolean isDarkMode) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PRE_THEME_MODE, isDarkMode);
        editor.commit();
    }

    /**
     * 省流量模式
     *
     * @return
     */
    public static boolean isSaveNetMode() {
        return getSharedPreferences().getBoolean(PRE_SAVE_NET_MODE, false);
    }

    /**
     * @param isSaveNetMode true为省流量模式，不加载图片
     */
    public static void setSaveNetMode(boolean isSaveNetMode) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.putBoolean(PRE_SAVE_NET_MODE, isSaveNetMode);
        editor.commit();
    }

    /**
     * 删除 SharedPreferences 的某个 key
     *
     * @param key
     */
    public static void removeFromPrefs(String key) {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.remove(key);
        editor.commit();
    }







}