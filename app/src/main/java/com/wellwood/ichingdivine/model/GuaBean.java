package com.wellwood.ichingdivine.model;




import com.wellwood.ichingdivine.R;

import java.util.Random;

public class GuaBean {
    public static int disHeight = 0;
    public static int disWidth = 0;
    public static int e2 = 0;
    public static int e3 = 0;
    public static int e4 = 0;
    public static int e5 = 0;
    public static final String[] earth = new String[]{"胃", "先天卦之北方", "後天卦之西南方", "先天數八", "後天數二", "致役", "順", "牛", "腹", "母", "地", "布", "釜", "吝嗇", "均", "子母牛", "大輿", "文", "眾", "柄", "其於地也為黑"};
    public static int ei = 0;
    public static int eu = 0;
    public static final String[] fire = new String[]{"心", "先天卦之東方", "後天卦之南方", "先天數三", "後天數九", "燥萬物者", "相見", "麗", "雉", "中女", "日", "火", "電", "甲冑", "甲冑", "戈兵", "大腹", "鱉", "蟹", "蠃", "蚌", "龜", "科上槁"};
    public static boolean flagAlreadyInit = false;
    public static boolean flagChooseGuaMenu = true;
    public static boolean guaMapThreadOut = false;
    public static final CharSequence[] guaName = new CharSequence[]{"乾", "坤", "屯", "蒙", "需", "訟", "師", "比", "小畜", "履", "泰", "否", "同人", "大有", "謙", "豫", "隨", "蠱", "臨", "觀", "噬嗑", "賁", "剝", "復", "无妄", "大畜", "頤", "大過", "坎", "離", "咸", "恒", "遯", "大壯", "晉", "明夷", "家人", "睽", "蹇", "解", "損", "益", "夬", "姤", "萃", "升", "困", "井", "革", "鼎", "震", "艮", "漸", "歸妹", "豐", "旅", "巽", "兌", "渙", "節", "中孚", "小過", "既濟", "未濟"};
    public static final int[] guaNum;
    public static final int[] guaTranslateGifun64;
    public static final int[] guaTranslateKan64;
    public static final int[] guaTranslateLis64;
    public static final int[] guaTranslateMonsi64;
    public static final int[] guaTranslateZendau64;
    public static final Integer[] gua_component = new Integer[]{Integer.valueOf(R.drawable.ying), Integer.valueOf(R.drawable.yang)};
    public static final Integer[] gua_component_1 = new Integer[]{Integer.valueOf(R.drawable.small_ying), Integer.valueOf(R.drawable.small_yang)};
    //0 small_ying 1 small_yang
    public static final Integer[] gua_component_3 = new Integer[]{Integer.valueOf(R.drawable.ch_small_ying), Integer.valueOf(R.drawable.ch_small_yang)};
    public static final Integer[] gua_component_2 = new Integer[]{Integer.valueOf(R.drawable.very_small_ying), Integer.valueOf(R.drawable.very_small_yang)};
    public static Integer[] gua_component_very_small = new Integer[]{Integer.valueOf(R.drawable.very_small_ying), Integer.valueOf(R.drawable.very_small_yang), Integer.valueOf(R.drawable.ch_very_small_ying), Integer.valueOf(R.drawable.ch_very_small_yang)};
    public static final String[] heaven = new String[]{"大腸", "先天卦之南方", "後天卦之西北方", "先天數一", "後天數六", "戰", "健", "馬", "首", "父", "天", "圜", "君", "玉", "金", "寒", "冰", "大赤", "良馬", "老馬", "瘠馬", "駁馬", "木果"};
    public static final String[] keyWord = new String[]{"初", "履", "至", "方", "大", "不", "習", "无", "利", "三", "含", "章", "可", "貞", "或", "從", "王", "事", "成", "有", "終", "咎", "譽", "黃", "元", "吉", "上", "龍", "于", "其", "血", "九", "復", "悔", "休", "頻", "厲", "中", "行", "獨", "敦", "凶", "災", "眚", "用", "師", "以", "國", "君", "十", "年", "克", "征", "出", "否", "在", "錫", "命", "輿", "左", "次", "田", "禽", "執", "言", "子", "承", "家", "小", "人", "勿", "咸", "甘", "攸", "既", "之", "涉", "川", "鳴", "無", "富", "鄰", "伐", "邑", "明", "夷", "飛", "日", "食", "往", "主", "股", "拯", "馬", "壯", "得", "首", "疾", "入", "獲", "心", "門", "庭", "登", "天", "後", "允", "升", "孚", "乃", "禴", "亨", "山", "冥", "拔", "茅", "茹", "彙", "包", "朋", "亡", "尚", "平", "艱", "恤", "福", "戒", "帝", "乙", "歸", "妹", "祉", "自", "告", "吝", "介", "石", "遲", "由", "疑", "恒", "死", "渝", "震", "來", "笑", "喪", "陵", "逐", "七", "遂", "泥", "視", "躬", "婚", "媾", "矢", "負", "且", "乘", "致", "寇", "而", "拇", "斯", "維", "公", "射", "高", "墉", "跛", "能", "眇", "幽", "須", "反", "如", "良", "月", "幾", "望", "女", "實", "士", "羊", "鳥", "過", "遇", "臣", "弗", "永", "雨", "我", "西", "郊", "取", "穴", "離", "是", "見", "發", "若", "折", "闚", "戶", "歲", "覿", "德", "羞", "婦", "夫", "趾", "罔", "羸", "角", "輹", "易", "退", "則", "盈", "缶", "它", "匪", "失", "前", "磐", "居", "侯", "即", "虞", "舍", "求", "膏", "泣", "酒", "納", "係", "嗟", "安", "碩", "曳", "輪", "濡", "尾", "宗", "鬼", "牛", "祭", "受", "舊", "谷", "為", "恆", "敬", "號", "一", "引", "涕", "交", "隨", "道", "何", "嘉", "臀", "困", "木", "享", "宮", "妻", "金", "車", "劓", "說", "曰", "和", "未", "喜", "剝", "腓", "爾", "思", "輔", "革", "已", "虎", "白", "生", "滅", "勝", "惕", "莫", "戎", "夬", "膚", "牽", "陸", "床", "足", "魚", "龜", "觀", "顛", "丘", "童", "擊", "禦", "益", "違", "快", "翰", "考", "母", "裕", "閑", "豕", "噬", "乾", "耳", "光", "飲", "厥", "載", "先", "所", "資", "焚", "處", "斧", "雉", "咷", "鼓", "歌", "妾", "威", "進", "賓", "去", "好", "攣", "武", "繫"};
    public static final String[] lake = new String[]{"肺", "先天卦之東南方", "後天卦之西方", "先天數二", "後天數七", "說萬物者", "說言", "說", "羊", "口", "少女", "澤", "巫", "口舌", "毀折", "附決", "剛鹵", "妾"};
    public static final String[] mountain = new String[]{"脾", "先天卦之西北方", "後天卦之東北方", "先天數七", "後天數八", "終萬物、始萬物者", "成言", "止", "狗", "手", "少男", "山", "徑路", "小石", "門闕", "果蓏", "閽寺", "指", "鼠", "黔喙之屬", "堅多節"};
    public static Random myRand = new Random();
    public static String path = null;
    public static int guaidtmp = 0 ;
    public static String path_SD = null;
    public static final String pre = "mySharePreferences";
    public static String quzStart = "b";
    public static boolean speedGuaExists = false;
    public static final String[] thunder = new String[]{"肝", "先天卦之東北方", "後天卦之東方", "先天數四", "後天數三", "動萬物者", "動", "龍", "足", "長男", "雷", "玄黃", "敷", "大涂", "決躁", "蒼筤竹", "萑葦", "善鳴馬", "馵足馬", "的顙", "作足馬", "反生", "其究為健", "蕃鮮", "帝"};
    public static long trackerRecStartTiming;
    public static final String[] water = new String[]{"腎", "先天卦之西方", "後天卦之北方", "先天數六", "後天數一", "潤萬物者", "勞", "陷", "豕", "耳", "中男", "水", "溝瀆", "隱伏", "矯輮", "弓輪", "加憂", "心病", "耳痛", "血卦", "赤", "美脊馬", "亟心", "下首", "薄蹄", "曳", "多眚", "通", "月", "盜", "堅多心"};
    public static final String[] wind = new String[]{"膽", "先天卦之西南方", "後天卦之東南", "先天數五", "後天數四", "橈萬物者", "齊", "入", "雞", "股", "長女", "木", "風", "繩直", "工", "白", "長", "高", "進退", "不果", "臭", "寡髮", "廣顙", "多白眼", "近利市三倍", "躁"};
    static {
        int[] iArr = new int[64];
        iArr[0] = 60;
        iArr[1] = 28;
        iArr[2] = 44;
        iArr[3] = 12;
        iArr[4] = 52;
        iArr[5] = 20;
        iArr[6] = 36;
        iArr[7] = 4;
        iArr[8] = 56;
        iArr[9] = 24;
        iArr[10] = 40;
        iArr[11] = 8;
        iArr[12] = 48;
        iArr[13] = 16;
        iArr[14] = 32;
        iArr[16] = 1;
        iArr[17] = 33;
        iArr[18] = 17;
        iArr[19] = 49;
        iArr[20] = 9;
        iArr[21] = 41;
        iArr[22] = 25;
        iArr[23] = 57;
        iArr[24] = 5;
        iArr[25] = 37;
        iArr[26] = 21;
        iArr[27] = 53;
        iArr[28] = 13;
        iArr[29] = 45;
        iArr[30] = 29;
        iArr[31] = 61;
        iArr[32] = 3;
        iArr[33] = 35;
        iArr[34] = 19;
        iArr[35] = 51;
        iArr[36] = 11;
        iArr[37] = 43;
        iArr[38] = 27;
        iArr[39] = 59;
        iArr[40] = 7;
        iArr[41] = 39;
        iArr[42] = 23;
        iArr[43] = 55;
        iArr[44] = 15;
        iArr[45] = 47;
        iArr[46] = 31;
        iArr[47] = 63;
        iArr[48] = 62;
        iArr[49] = 30;
        iArr[50] = 46;
        iArr[51] = 14;
        iArr[52] = 54;
        iArr[53] = 22;
        iArr[54] = 38;
        iArr[55] = 6;
        iArr[56] = 58;
        iArr[57] = 26;
        iArr[58] = 42;
        iArr[59] = 10;
        iArr[60] = 50;
        iArr[61] = 18;
        iArr[62] = 34;
        iArr[63] = 2;
        guaTranslateKan64 = iArr;
        iArr = new int[64];
        iArr[0] = 52;
        iArr[1] = 51;
        iArr[2] = 59;
        iArr[3] = 43;
        iArr[4] = 35;
        iArr[5] = 39;
        iArr[6] = 37;
        iArr[7] = 36;
        iArr[8] = 16;
        iArr[9] = 23;
        iArr[10] = 31;
        iArr[11] = 15;
        iArr[12] = 7;
        iArr[13] = 3;
        iArr[14] = 1;
        iArr[16] = 25;
        iArr[17] = 30;
        iArr[18] = 22;
        iArr[19] = 6;
        iArr[20] = 14;
        iArr[21] = 10;
        iArr[22] = 8;
        iArr[23] = 9;
        iArr[24] = 61;
        iArr[25] = 58;
        iArr[26] = 50;
        iArr[27] = 34;
        iArr[28] = 42;
        iArr[29] = 46;
        iArr[30] = 44;
        iArr[31] = 45;
        iArr[32] = 11;
        iArr[33] = 12;
        iArr[34] = 4;
        iArr[35] = 20;
        iArr[36] = 28;
        iArr[37] = 24;
        iArr[38] = 26;
        iArr[39] = 27;
        iArr[40] = 47;
        iArr[41] = 40;
        iArr[42] = 32;
        iArr[43] = 48;
        iArr[44] = 56;
        iArr[45] = 60;
        iArr[46] = 62;
        iArr[47] = 63;
        iArr[48] = 38;
        iArr[49] = 33;
        iArr[50] = 41;
        iArr[51] = 57;
        iArr[52] = 49;
        iArr[53] = 53;
        iArr[54] = 55;
        iArr[55] = 54;
        iArr[56] = 2;
        iArr[57] = 5;
        iArr[58] = 13;
        iArr[59] = 29;
        iArr[60] = 21;
        iArr[61] = 17;
        iArr[62] = 19;
        iArr[63] = 18;
        guaTranslateGifun64 = iArr;
        iArr = new int[64];
        iArr[0] = 63;
        iArr[1] = 31;
        iArr[2] = 47;
        iArr[3] = 15;
        iArr[4] = 55;
        iArr[5] = 23;
        iArr[6] = 39;
        iArr[7] = 7;
        iArr[8] = 58;
        iArr[9] = 26;
        iArr[10] = 42;
        iArr[11] = 10;
        iArr[12] = 50;
        iArr[13] = 18;
        iArr[14] = 34;
        iArr[15] = 2;
        iArr[16] = 4;
        iArr[17] = 36;
        iArr[18] = 20;
        iArr[19] = 52;
        iArr[20] = 12;
        iArr[21] = 44;
        iArr[22] = 28;
        iArr[23] = 60;
        iArr[24] = 1;
        iArr[25] = 33;
        iArr[26] = 17;
        iArr[27] = 49;
        iArr[28] = 9;
        iArr[29] = 41;
        iArr[30] = 25;
        iArr[31] = 57;
        iArr[32] = 6;
        iArr[33] = 38;
        iArr[34] = 22;
        iArr[35] = 54;
        iArr[36] = 14;
        iArr[37] = 46;
        iArr[38] = 30;
        iArr[39] = 62;
        iArr[40] = 5;
        iArr[41] = 37;
        iArr[42] = 21;
        iArr[43] = 53;
        iArr[44] = 13;
        iArr[45] = 45;
        iArr[46] = 29;
        iArr[47] = 61;
        iArr[48] = 56;
        iArr[49] = 24;
        iArr[50] = 40;
        iArr[51] = 8;
        iArr[52] = 48;
        iArr[53] = 16;
        iArr[54] = 32;
        iArr[56] = 59;
        iArr[57] = 27;
        iArr[58] = 43;
        iArr[59] = 11;
        iArr[60] = 51;
        iArr[61] = 19;
        iArr[62] = 35;
        iArr[63] = 3;
        guaTranslateZendau64 = iArr;
        iArr = new int[64];
        iArr[0] = 39;
        iArr[1] = 37;
        iArr[2] = 48;
        iArr[3] = 11;
        iArr[4] = 57;
        iArr[5] = 5;
        iArr[6] = 26;
        iArr[7] = 32;
        iArr[8] = 36;
        iArr[9] = 21;
        iArr[10] = 41;
        iArr[11] = 30;
        iArr[13] = 42;
        iArr[14] = 20;
        iArr[15] = 33;
        iArr[16] = 18;
        iArr[17] = 51;
        iArr[18] = 1;
        iArr[19] = 17;
        iArr[20] = 4;
        iArr[21] = 43;
        iArr[22] = 6;
        iArr[23] = 3;
        iArr[24] = 12;
        iArr[25] = 34;
        iArr[26] = 49;
        iArr[27] = 52;
        iArr[28] = 7;
        iArr[29] = 23;
        iArr[30] = 25;
        iArr[31] = 40;
        iArr[32] = 9;
        iArr[33] = 10;
        iArr[34] = 15;
        iArr[35] = 8;
        iArr[36] = 58;
        iArr[37] = 38;
        iArr[38] = 29;
        iArr[39] = 31;
        iArr[40] = 44;
        iArr[41] = 2;
        iArr[42] = 16;
        iArr[43] = 55;
        iArr[44] = 63;
        iArr[45] = 47;
        iArr[46] = 53;
        iArr[47] = 22;
        iArr[48] = 45;
        iArr[49] = 28;
        iArr[50] = 62;
        iArr[51] = 46;
        iArr[52] = 13;
        iArr[53] = 50;
        iArr[54] = 59;
        iArr[55] = 60;
        iArr[56] = 14;
        iArr[57] = 19;
        iArr[58] = 61;
        iArr[59] = 35;
        iArr[60] = 56;
        iArr[61] = 54;
        iArr[62] = 24;
        iArr[63] = 27;
        guaTranslateMonsi64 = iArr;
        iArr = new int[64];
        iArr[0] = 2;
        iArr[1] = 34;
        iArr[2] = 18;
        iArr[3] = 50;
        iArr[4] = 10;
        iArr[5] = 42;
        iArr[6] = 26;
        iArr[7] = 58;
        iArr[8] = 6;
        iArr[9] = 38;
        iArr[10] = 22;
        iArr[11] = 54;
        iArr[12] = 14;
        iArr[13] = 46;
        iArr[14] = 30;
        iArr[15] = 62;
        iArr[16] = 1;
        iArr[17] = 33;
        iArr[18] = 17;
        iArr[19] = 49;
        iArr[20] = 9;
        iArr[21] = 41;
        iArr[22] = 25;
        iArr[23] = 57;
        iArr[24] = 5;
        iArr[25] = 37;
        iArr[26] = 21;
        iArr[27] = 53;
        iArr[28] = 13;
        iArr[29] = 45;
        iArr[30] = 29;
        iArr[31] = 61;
        iArr[32] = 3;
        iArr[33] = 35;
        iArr[34] = 19;
        iArr[35] = 51;
        iArr[36] = 11;
        iArr[37] = 43;
        iArr[38] = 27;
        iArr[39] = 59;
        iArr[40] = 7;
        iArr[41] = 39;
        iArr[42] = 23;
        iArr[43] = 55;
        iArr[44] = 15;
        iArr[45] = 47;
        iArr[46] = 31;
        iArr[47] = 63;
        iArr[49] = 32;
        iArr[50] = 16;
        iArr[51] = 48;
        iArr[52] = 8;
        iArr[53] = 40;
        iArr[54] = 24;
        iArr[55] = 56;
        iArr[56] = 4;
        iArr[57] = 36;
        iArr[58] = 20;
        iArr[59] = 52;
        iArr[60] = 12;
        iArr[61] = 44;
        iArr[62] = 28;
        iArr[63] = 60;
        guaTranslateLis64 = iArr;

        // 卦序歌   guaNum = iArr;
        iArr = new int[64];
        iArr[0] = 63;   //乾111 111    guabin 63
        iArr[2] = 17;   //屯 010 001   17
        iArr[3] = 34;
        iArr[4] = 23;
        iArr[5] = 58;
        iArr[6] = 2;
        iArr[7] = 16;
        iArr[8] = 55;
        iArr[9] = 59;
        iArr[10] = 7;
        iArr[11] = 56;
        iArr[12] = 61;
        iArr[13] = 47;
        iArr[14] = 4;
        iArr[15] = 8;
        iArr[16] = 25;
        iArr[17] = 38;
        iArr[18] = 3;
        iArr[19] = 48;
        iArr[20] = 41;
        iArr[21] = 37;
        iArr[22] = 32;
        iArr[23] = 1;
        iArr[24] = 57;
        iArr[25] = 39;
        iArr[26] = 33;
        iArr[27] = 30;
        iArr[28] = 18;
        iArr[29] = 45;
        iArr[30] = 28;
        iArr[31] = 14;
        iArr[32] = 60;
        iArr[33] = 15;
        iArr[34] = 40;
        iArr[35] = 5;
        iArr[36] = 53;
        iArr[37] = 43;
        iArr[38] = 20;
        iArr[39] = 10;
        iArr[40] = 35;
        iArr[41] = 49;
        iArr[42] = 31;
        iArr[43] = 62;
        iArr[44] = 24;
        iArr[45] = 6;
        iArr[46] = 26;
        iArr[47] = 22;
        iArr[48] = 29;
        iArr[49] = 46;
        iArr[50] = 9;
        iArr[51] = 36;
        iArr[52] = 52;
        iArr[53] = 11;
        iArr[54] = 13;
        iArr[55] = 44;
        iArr[56] = 54;
        iArr[57] = 27;
        iArr[58] = 50;
        iArr[59] = 19;
        iArr[60] = 51;
        iArr[61] = 12;
        iArr[62] = 21;
        iArr[63] = 42;
        guaNum = iArr;

    }



    public static int guabin2ID(int guabin) {
        int id = 0 ;
        switch (guabin) {

            case 63:
                id = 1;
                break;
            case 0:
                id = 2;
                break;
            case 17:
                id = 3;
                break;
            case 34:
                id = 4;
                break;
            case 23:
                id = 5;
                break;
            case 58:
                id = 6;
                break;
            case 2:
                id = 7;
                break;
            case 16:
                id = 8;
                break;
            case 55:
                id = 9;
                break;
            case 59:
                id = 10;
                break;
            case 7:
                id = 11;
                break;
            case 56:
                id = 12;
                break;
            case 61:
                id = 13;
                break;
            case 47:
                id = 14;
                break;
            case 4:
                id = 15;
                break;
            case 8:
                id = 16;
                break;
            case 25:
                id = 17;
                break;
            case 38:
                id = 18;
                break;
            case 3:
                id = 19;
                break;
            case 48:
                id = 20;
                break;
            case 41:
                id = 21;
                break;
            case 37:
                id = 22;
                break;
            case 32:
                id = 23;
                break;
            case 1:
                id = 24;
                break;
            case 57:
                id = 25;
                break;
            case 39:
                id = 26;
                break;
            case 33:
                id = 27;
                break;
            case 30:
                id = 28;
                break;
            case 18:
                id = 29;
                break;
            case 45:
                id = 30;
                break;
            case 28:
                id = 31;
                break;
            case 14:
                id = 32;
                break;
            case 60:
                id = 33;
                break;
            case 15:
                id = 34;
                break;
            case 40:
                id = 35;
                break;
            case 5:
                id = 36;
                break;
            case 53:
                id = 37;
                break;
            case 43:
                id = 38;
                break;
            case 20:
                id = 39;
                break;
            case 10:
                id = 40;
                break;
            case 35:
                id = 41;
                break;
            case 49:
                id = 42;
                break;
            case 31:
                id = 43;
                break;
            case 62:
                id = 44;
                break;
            case 24:
                id = 45;
                break;
            case 6:
                id = 46;
                break;
            case 26:
                id = 47;
                break;
            case 22:
                id = 48;
                break;
            case 29:
                id = 49;
                break;
            case 46:
                id = 50;
                break;
            case 9:
                id = 51;
                break;
            case 36:
                id = 52;
                break;
            case 52:
                id = 53;
                break;
            case 11:
                id = 54;
                break;
            case 13:
                id = 55;
                break;
            case 44:
                id = 56;
                break;
            case 54:
                id = 57;
                break;
            case 27:
                id = 58;
                break;
            case 50:
                id = 59;
                break;
            case 19:
                id = 60;
                break;
            case 51:
                id = 61;
                break;
            case 12:
                id = 62;
                break;
            case 21:
                id = 63;
                break;
            case 42:
                id = 64;
                break;
        }
        return id ;
    }

}
