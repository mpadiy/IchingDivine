package com.wellwood.ichingdivine.model;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.BitmapFactory.Options;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.wellwood.ichingdivine.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class e_lib {
    public static int chosenWordNum;
    public static int colNum = 10;
    public static char[] compChar;
    public static String[] vData = null;
    public static int xxNum;
    public static GridView xxTextGV;




    public static char[] bin_format(int x) {
        String s = Integer.toBinaryString(x);
        char[] cs = s.toCharArray();
        if (cs.length < 6) {
            for (int i = 1; i <= 6 - cs.length; i++) {
                s = "0" + s;
            }
        }
        return s.toCharArray();
    }

    public static void alertDialogSet(Builder rbuilder, int _guaNum, String i_poetry, Context yes) {
        int i;
        int guaNum = _guaNum;
        String[] i_poetry_split = i_poetry.split("\n");
        char[] xxx = i_poetry_split[0].toCharArray();
        xxNum = i_poetry_split[0].length();
        char[] xxxe = i_poetry_split[1].toCharArray();
        int offset = xxNum % colNum == 0 ? 10 : xxNum % colNum;
        vData = new String[(((i_poetry_split[0].length() + i_poetry_split[1].length()) + colNum) - offset)];
        int tmpIdx = 0;
        for (i = 0; i < i_poetry_split[0].length(); i++) {
            vData[tmpIdx] = new String(xxx, i, 1);
            tmpIdx++;
        }
        for (i = offset; i < colNum; i++) {
            vData[tmpIdx] = " ";
            tmpIdx++;
        }
        for (i = 0; i < i_poetry_split[1].length(); i++) {
            vData[tmpIdx] = new String(xxxe, i, 1);
            tmpIdx++;
        }

        rbuilder.setCancelable(false);
        AlertDialog alert = rbuilder.create();
        alert.setCanceledOnTouchOutside(true);
        alert.show();
    }

    static char[] findGuaBin(String guaxx) {
        int i;
        String[] tmpString = guaxx.split("，");
        int xNum = 0;
        for (i = 0; i < 64; i++) {
            if (tmpString[0].contains(GuaBean.guaName[i])) {
                xNum = GuaBean.guaNum[i];
                break;
            }
        }
        String s = Integer.toBinaryString(xNum);
        char[] cs = s.toCharArray();
        if (cs.length < 6) {
            for (i = 1; i <= 6 - cs.length; i++) {
                s = "0" + s;
            }
        }
        cs = s.toCharArray();
        if (tmpString[0].contains("初")) {
            if (cs[5] == '0') {
                cs[5] = '2';
            } else {
                cs[5] = '3';
            }
        } else if (tmpString[0].contains("二")) {
            if (cs[4] == '0') {
                cs[4] = '2';
            } else {
                cs[4] = '3';
            }
        } else if (tmpString[0].contains("三")) {
            if (cs[3] == '0') {
                cs[3] = '2';
            } else {
                cs[3] = '3';
            }
        } else if (tmpString[0].contains("四")) {
            if (cs[2] == '0') {
                cs[2] = '2';
            } else {
                cs[2] = '3';
            }
        } else if (tmpString[0].contains("五")) {
            if (cs[1] == '0') {
                cs[1] = '2';
            } else {
                cs[1] = '3';
            }
        } else if (tmpString[0].contains("上")) {
            if (cs[0] == '0') {
                cs[0] = '2';
            } else {
                cs[0] = '3';
            }
        }
        return cs;
    }

    static int big_e_go_process(int bigNum) {
        int left = (GuaBean.myRand.nextInt(110000) % bigNum) + 1;
        int right = bigNum - left;
        return (((left + -1) % 4 == 0 ? 4 : (left - 1) % 4) + (right % 4 == 0 ? 4 : right % 4)) + 1;
    }

    public static int big_e_go(int bigNum) {
        int sum1 = big_e_go_process(bigNum);
        int sum2 = big_e_go_process(bigNum - sum1);
        return (((bigNum - sum1) - sum2) - big_e_go_process((bigNum - sum1) - sum2)) / 4;
    }

    public static void boldText(TextView tv) {
        tv.getPaint().setFakeBoldText(true);
    }

    public static void boldButtonText(Button tv) {
        tv.getPaint().setFakeBoldText(true);
    }

    public static void verySmallGuaArrange(LinearLayout xxGuaLayoutLinearLayout, char[] oriBin, Context yes) {
        ImageButton xxe5o = (ImageButton) xxGuaLayoutLinearLayout.findViewById(R.id.xxe5o);
        ImageButton xxe4o = (ImageButton) xxGuaLayoutLinearLayout.findViewById(R.id.xxe4o);
        ImageButton xxe3o = (ImageButton) xxGuaLayoutLinearLayout.findViewById(R.id.xxe3o);
        ImageButton xxe2o = (ImageButton) xxGuaLayoutLinearLayout.findViewById(R.id.xxe2o);
        ImageButton xxeio = (ImageButton) xxGuaLayoutLinearLayout.findViewById(R.id.xxeio);
        ((ImageButton) xxGuaLayoutLinearLayout.findViewById(R.id.xxeuo)).setBackgroundResource(GuaBean.gua_component_very_small[Integer.parseInt(new String(oriBin, 0, 1))].intValue());
        xxe5o.setBackgroundResource(GuaBean.gua_component_very_small[Integer.parseInt(new String(oriBin, 1, 1))].intValue());
        xxe4o.setBackgroundResource(GuaBean.gua_component_very_small[Integer.parseInt(new String(oriBin, 2, 1))].intValue());
        xxe3o.setBackgroundResource(GuaBean.gua_component_very_small[Integer.parseInt(new String(oriBin, 3, 1))].intValue());
        xxe2o.setBackgroundResource(GuaBean.gua_component_very_small[Integer.parseInt(new String(oriBin, 4, 1))].intValue());
        xxeio.setBackgroundResource(GuaBean.gua_component_very_small[Integer.parseInt(new String(oriBin, 5, 1))].intValue());
    }

    public static SpannableString searchxxTextSpan(String eX, String searchxxText) {
        char[] eXArray = eX.toCharArray();
        char[] searchxxArray = searchxxText.toCharArray();
        SpannableString eXSpan = new SpannableString(eX);
        for (int m = 0; m < eXArray.length - searchxxArray.length; m++) {
            int checkSearchxxNum = 0;
            for (int j = 0; j < searchxxArray.length; j++) {
                if (new String(searchxxArray, j, 1).equals(new String(eXArray, m + j, 1))) {
                    checkSearchxxNum++;
                }
                if (checkSearchxxNum == searchxxArray.length) {
                    eXSpan.setSpan(new ForegroundColorSpan(-65536), m, m + checkSearchxxNum, 33);
                }
            }
        }
        return eXSpan;
    }

    public static String timeNow() {
        return new SimpleDateFormat("yyyy_MMdd_HHmm_ss").format(new Date());
    }

    public static int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        if (width <= reqWidth) {
            return 1;
        }
        if (width > height) {
            return Math.round(((float) height) / ((float) reqHeight));
        }
        return Math.round(((float) width) / ((float) reqWidth));
    }

    public static String getQuzUpGua(char[] quzGua) {
        if (quzGua[2] == '0' && quzGua[1] == '0' && quzGua[0] == '0') {
            return "坤";
        }
        if (quzGua[2] == '1' && quzGua[1] == '0' && quzGua[0] == '0') {
            return "震";
        }
        if (quzGua[2] == '0' && quzGua[1] == '1' && quzGua[0] == '0') {
            return "坎";
        }
        if (quzGua[2] == '0' && quzGua[1] == '0' && quzGua[0] == '1') {
            return "艮";
        }
        if (quzGua[2] == '1' && quzGua[1] == '1' && quzGua[0] == '0') {
            return "兌";
        }
        if (quzGua[2] == '1' && quzGua[1] == '0' && quzGua[0] == '1') {
            return "離";
        }
        if (quzGua[2] == '0' && quzGua[1] == '1' && quzGua[0] == '1') {
            return "巽";
        }
        if (quzGua[2] == '1' && quzGua[1] == '1' && quzGua[0] == '1') {
            return "乾";
        }
        return "";
    }

    public static String getQuzDownGua(char[] quzGua) {
        if (quzGua[5] == '0' && quzGua[4] == '0' && quzGua[3] == '0') {
            return "坤";
        }
        if (quzGua[5] == '1' && quzGua[4] == '0' && quzGua[3] == '0') {
            return "震";
        }
        if (quzGua[5] == '0' && quzGua[4] == '1' && quzGua[3] == '0') {
            return "坎";
        }
        if (quzGua[5] == '0' && quzGua[4] == '0' && quzGua[3] == '1') {
            return "艮";
        }
        if (quzGua[5] == '1' && quzGua[4] == '1' && quzGua[3] == '0') {
            return "兌";
        }
        if (quzGua[5] == '1' && quzGua[4] == '0' && quzGua[3] == '1') {
            return "離";
        }
        if (quzGua[5] == '0' && quzGua[4] == '1' && quzGua[3] == '1') {
            return "巽";
        }
        if (quzGua[5] == '1' && quzGua[4] == '1' && quzGua[3] == '1') {
            return "乾";
        }
        return "";
    }
}
