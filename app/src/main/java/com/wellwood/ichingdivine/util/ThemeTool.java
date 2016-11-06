package com.wellwood.ichingdivine.util;

import android.app.Activity;

import com.wellwood.ichingdivine.R;

/**
 * 改变主题工具类
 * 全局变量可以利用 类静态变量 或 preference
 */
public class ThemeTool {


    public static void changeTheme(Activity activity) {
        if (PrefUtils.isDarkMode()) {
            activity.setTheme(R.style.AppThemeDark);
        }
    }



    public static void changeTheme(Activity activity , String mode) {

        if (activity == null)
            return;

        int style = R.style.RedTheme;

        switch (mode){
            case "red":
                style = R.style.RedTheme;
                break;
            case "brown":
                style = R.style.BrownTheme;
                break;
            case "blue":
                style = R.style.BlueTheme;
                break;
            case "bluegrey":
                style = R.style.BlueGreyTheme;
                break;
            case "yellow":
                style = R.style.YellowTheme;
                break;
            case "deeppurple":
                style = R.style.DeepPurpleTheme;
                break;
            case "pink":
                style = R.style.PinkTheme;
                break;
            case "green":
                style = R.style.GreenTheme;
                break;
            case "dark":
                style = R.style.AppThemeDark;
                break;
            default:
                break;
        }
        activity.setTheme(style);

    }




}
