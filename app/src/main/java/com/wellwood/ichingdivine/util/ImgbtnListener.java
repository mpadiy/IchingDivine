package com.wellwood.ichingdivine.util;

import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.db.GuaDetailDao;
import com.wellwood.ichingdivine.model.GuaDetail;
import com.wellwood.ichingdivine.ui.activity.DeduceActivity;
import com.wellwood.ichingdivine.ui.customizedview.ExpandableTextView1;
import com.wellwood.ichingdivine.ui.customizedview.myNote;
import com.wellwood.ichingdivine.model.GuaBean;



/**
 * Created by waterwoodwell on 2016/6/13.
 * problem:  mDeduceActivity.getString(mDeduceActivity.getResources() cannot resolve
 *         switch (viewId) {
 //viewId = 6 why not R.id.euf
 *
 */
public class ImgbtnListener implements OnClickListener {
    DeduceActivity mDeduceActivity;
    private char[] furt_bin;
    private char[] ori_bin;
    public ImgbtnListener(DeduceActivity activity , char[] ori_bin, char[] furt_bin){
        this.mDeduceActivity = activity ;
        this.furt_bin = furt_bin ;
        this.ori_bin = ori_bin ;
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        Logger.e("View Id:",Integer.toString(viewId));

        switch (viewId) {
            //why not R.id.euf
            case 6:
                euBtnClick();
                break;
            case 5:
                e5BtnClick();
                break;
            case 4:
                e4BtnClick();
                break;
            case 3:
                e3BtnClick();
                break;
            case 2:
                e2BtnClick();
                break;
            case 1:
                eiBtnClick();
                break;

        }
    }
    public void euBtnClick(){
        //initUI
        ImageButton eu = (ImageButton) mDeduceActivity.findViewById(6);
        TextView furguatext = (TextView) mDeduceActivity.findViewById(8);
        String tmp_yao="" ;
        TextView oriColortext_u = (TextView) mDeduceActivity.findViewById(30);
        TextView oriColortext_5 = (TextView) mDeduceActivity.findViewById(31);
        TextView oriColortext_4 = (TextView) mDeduceActivity.findViewById(32);
        TextView oriColortext_3 = (TextView) mDeduceActivity.findViewById(33);
        TextView oriColortext_2 = (TextView) mDeduceActivity.findViewById(34);
        TextView oriColortext_i = (TextView) mDeduceActivity.findViewById(35);
        TextView furtColortext_u = (TextView) mDeduceActivity.findViewById(36);
        TextView furtColortext_5 = (TextView) mDeduceActivity.findViewById(37);
        TextView furtColortext_4 = (TextView) mDeduceActivity.findViewById(38);
        TextView furtColortext_3 = (TextView) mDeduceActivity.findViewById(39);
        TextView furtColortext_2 = (TextView) mDeduceActivity.findViewById(40);
        TextView furtColortext_i = (TextView) mDeduceActivity.findViewById(41);
        ExpandableTextView1 expTv1 = (ExpandableTextView1) mDeduceActivity.findViewById(24);
        //logic
        if (furt_bin[0] == '1') {
            furt_bin[0] = '0';
        } else {
            furt_bin[0] = '1';
        }
        int origuanum = Integer.parseInt(new String(ori_bin, 0, 6), 2);
        int cur_furt_num = Integer.parseInt(new String(furt_bin, 0, 6), 2);
        int id =  GuaBean.guabin2ID(cur_furt_num);
        GuaDetail detail;
        GuaDetailDao detailDao;
        detailDao = new GuaDetailDao(mDeduceActivity);
        detail = detailDao.query(id);
        expTv1.setText(Html.fromHtml("变卦："+ detail.getContent()));
        myNote name = (myNote)mDeduceActivity.findViewById(19);
        name.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum)+"name", "string", mDeduceActivity.getPackageName())) + "之" + mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"name", "string", mDeduceActivity.getPackageName())) );

        String f_poetry = "变卦为"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num), "string", mDeduceActivity.getPackageName()))  +"\n"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"e", "string", mDeduceActivity.getPackageName()));
        furguatext.setText(new StringBuilder(String.valueOf(f_poetry)));

        //本变6爻显示
        for(int i=0 ; i<6 ; i++){
            int j = 6 - i;
            switch (j) {
                case 1:
                    tmp_yao = "_i";
                    oriColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 2:
                    tmp_yao = "_2";
                    oriColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 3:
                    tmp_yao = "_3";
                    oriColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 4:
                    tmp_yao = "_4";
                    oriColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 5:
                    tmp_yao = "_5";
                    oriColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 6:
                    tmp_yao = "_u";
                    oriColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
            }

            //本变 变爻 红色 显示
            if( String.valueOf(ori_bin).charAt(i) != String.valueOf(furt_bin).charAt(i) ) {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        eu.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(furt_bin, 0, 1))].intValue());
                        break;
                }
            }

            //本变 静爻 黑色 显示
            else {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        eu.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(furt_bin, 0, 1))].intValue());
                        break;
                }
            }
        }
    }
    public void e5BtnClick(){
        //initUI
        ImageButton e5 = (ImageButton) mDeduceActivity.findViewById(5);
        TextView furguatext = (TextView) mDeduceActivity.findViewById(8);
        String tmp_yao="" ;
        TextView oriColortext_u = (TextView) mDeduceActivity.findViewById(30);
        TextView oriColortext_5 = (TextView) mDeduceActivity.findViewById(31);
        TextView oriColortext_4 = (TextView) mDeduceActivity.findViewById(32);
        TextView oriColortext_3 = (TextView) mDeduceActivity.findViewById(33);
        TextView oriColortext_2 = (TextView) mDeduceActivity.findViewById(34);
        TextView oriColortext_i = (TextView) mDeduceActivity.findViewById(35);
        TextView furtColortext_u = (TextView) mDeduceActivity.findViewById(36);
        TextView furtColortext_5 = (TextView) mDeduceActivity.findViewById(37);
        TextView furtColortext_4 = (TextView) mDeduceActivity.findViewById(38);
        TextView furtColortext_3 = (TextView) mDeduceActivity.findViewById(39);
        TextView furtColortext_2 = (TextView) mDeduceActivity.findViewById(40);
        TextView furtColortext_i = (TextView) mDeduceActivity.findViewById(41);
        ExpandableTextView1 expTv1 = (ExpandableTextView1) mDeduceActivity.findViewById(24);

        //logic
        if (furt_bin[1] == '1') {
            furt_bin[1] = '0';
        } else {
            furt_bin[1] = '1';
        }
        int origuanum = Integer.parseInt(new String(ori_bin, 0, 6), 2);
        int cur_furt_num = Integer.parseInt(new String(furt_bin, 0, 6), 2);
        int id =  GuaBean.guabin2ID(cur_furt_num);
        GuaDetail detail;
        GuaDetailDao detailDao;
        detailDao = new GuaDetailDao(mDeduceActivity);
        detail = detailDao.query(id);
        expTv1.setText(Html.fromHtml("变卦："+ detail.getContent()));
        myNote name = (myNote)mDeduceActivity.findViewById(19);
        name.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum)+"name", "string", mDeduceActivity.getPackageName())) + "之" + mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"name", "string", mDeduceActivity.getPackageName())) );


        String f_poetry = "变卦为"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num), "string", mDeduceActivity.getPackageName()))  +"\n"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"e", "string", mDeduceActivity.getPackageName()));
        furguatext.setText(new StringBuilder(String.valueOf(f_poetry)));

        //本变6爻显示
        for(int i=0 ; i<6 ; i++){
            int j = 6 - i;
            switch (j) {
                case 1:
                    tmp_yao = "_i";
                    oriColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 2:
                    tmp_yao = "_2";
                    oriColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 3:
                    tmp_yao = "_3";
                    oriColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 4:
                    tmp_yao = "_4";
                    oriColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 5:
                    tmp_yao = "_5";
                    oriColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 6:
                    tmp_yao = "_u";
                    oriColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
            }

            //本变 变爻 红色 显示
            if( String.valueOf(ori_bin).charAt(i) != String.valueOf(furt_bin).charAt(i) ) {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        e5.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(furt_bin, 1, 1))].intValue());
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                }
            }

            //本变 静爻 黑色 显示
            else {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        e5.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(furt_bin, 1, 1))].intValue());
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                }
            }
        }

    }
    public void e4BtnClick(){
        //initUI
        ImageButton e4 = (ImageButton) mDeduceActivity.findViewById(4);
        TextView furguatext = (TextView) mDeduceActivity.findViewById(8);
        String tmp_yao="" ;
        TextView oriColortext_u = (TextView) mDeduceActivity.findViewById(30);
        TextView oriColortext_5 = (TextView) mDeduceActivity.findViewById(31);
        TextView oriColortext_4 = (TextView) mDeduceActivity.findViewById(32);
        TextView oriColortext_3 = (TextView) mDeduceActivity.findViewById(33);
        TextView oriColortext_2 = (TextView) mDeduceActivity.findViewById(34);
        TextView oriColortext_i = (TextView) mDeduceActivity.findViewById(35);
        TextView furtColortext_u = (TextView) mDeduceActivity.findViewById(36);
        TextView furtColortext_5 = (TextView) mDeduceActivity.findViewById(37);
        TextView furtColortext_4 = (TextView) mDeduceActivity.findViewById(38);
        TextView furtColortext_3 = (TextView) mDeduceActivity.findViewById(39);
        TextView furtColortext_2 = (TextView) mDeduceActivity.findViewById(40);
        TextView furtColortext_i = (TextView) mDeduceActivity.findViewById(41);
        ExpandableTextView1 expTv1 = (ExpandableTextView1) mDeduceActivity.findViewById(24);

        //logic
        if (furt_bin[2] == '1') {
            furt_bin[2] = '0';
        } else {
            furt_bin[2] = '1';
        }
        int origuanum = Integer.parseInt(new String(ori_bin, 0, 6), 2);
        int cur_furt_num = Integer.parseInt(new String(furt_bin, 0, 6), 2);
        int id =  GuaBean.guabin2ID(cur_furt_num);
        GuaDetail detail;
        GuaDetailDao detailDao;
        detailDao = new GuaDetailDao(mDeduceActivity);
        detail = detailDao.query(id);
        expTv1.setText(Html.fromHtml("变卦："+ detail.getContent()));
        myNote name = (myNote)mDeduceActivity.findViewById(19);
        name.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum)+"name", "string", mDeduceActivity.getPackageName())) + "之" + mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"name", "string", mDeduceActivity.getPackageName())) );


        String f_poetry = "变卦为"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num), "string", mDeduceActivity.getPackageName()))  +"\n"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"e", "string", mDeduceActivity.getPackageName()));
        furguatext.setText(new StringBuilder(String.valueOf(f_poetry)));

        //本变6爻显示
        for(int i=0 ; i<6 ; i++){
            int j = 6 - i;
            switch (j) {
                case 1:
                    tmp_yao = "_i";
                    oriColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 2:
                    tmp_yao = "_2";
                    oriColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 3:
                    tmp_yao = "_3";
                    oriColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 4:
                    tmp_yao = "_4";
                    oriColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 5:
                    tmp_yao = "_5";
                    oriColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 6:
                    tmp_yao = "_u";
                    oriColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
            }

            //本变 变爻 红色 显示
            if( String.valueOf(ori_bin).charAt(i) != String.valueOf(furt_bin).charAt(i) ) {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        e4.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(furt_bin, 2, 1))].intValue());
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                }
            }

            //本变 静爻 黑色 显示
            else {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        e4.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(furt_bin, 2, 1))].intValue());
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                }
            }
        }


    }
    public void e3BtnClick(){
        //initUI
        ImageButton e3 = (ImageButton) mDeduceActivity.findViewById(3);
        TextView furguatext = (TextView) mDeduceActivity.findViewById(8);
        String tmp_yao="" ;
        TextView oriColortext_u = (TextView) mDeduceActivity.findViewById(30);
        TextView oriColortext_5 = (TextView) mDeduceActivity.findViewById(31);
        TextView oriColortext_4 = (TextView) mDeduceActivity.findViewById(32);
        TextView oriColortext_3 = (TextView) mDeduceActivity.findViewById(33);
        TextView oriColortext_2 = (TextView) mDeduceActivity.findViewById(34);
        TextView oriColortext_i = (TextView) mDeduceActivity.findViewById(35);
        TextView furtColortext_u = (TextView) mDeduceActivity.findViewById(36);
        TextView furtColortext_5 = (TextView) mDeduceActivity.findViewById(37);
        TextView furtColortext_4 = (TextView) mDeduceActivity.findViewById(38);
        TextView furtColortext_3 = (TextView) mDeduceActivity.findViewById(39);
        TextView furtColortext_2 = (TextView) mDeduceActivity.findViewById(40);
        TextView furtColortext_i = (TextView) mDeduceActivity.findViewById(41);
        ExpandableTextView1 expTv1 = (ExpandableTextView1) mDeduceActivity.findViewById(24);

        //logic
        if (furt_bin[3] == '1') {
            furt_bin[3] = '0';
        } else {
            furt_bin[3] = '1';
        }
        int origuanum = Integer.parseInt(new String(ori_bin, 0, 6), 2);
        int cur_furt_num = Integer.parseInt(new String(furt_bin, 0, 6), 2);
        int id =  GuaBean.guabin2ID(cur_furt_num);
        GuaDetail detail;
        GuaDetailDao detailDao;
        detailDao = new GuaDetailDao(mDeduceActivity);
        detail = detailDao.query(id);
        expTv1.setText(Html.fromHtml("变卦："+ detail.getContent()));
        myNote name = (myNote)mDeduceActivity.findViewById(19);
        name.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum)+"name", "string", mDeduceActivity.getPackageName())) + "之" + mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"name", "string", mDeduceActivity.getPackageName())) );


        String f_poetry = "变卦为"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num), "string", mDeduceActivity.getPackageName()))  +"\n"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"e", "string", mDeduceActivity.getPackageName()));
        furguatext.setText(new StringBuilder(String.valueOf(f_poetry)));

        //本变6爻显示
        for(int i=0 ; i<6 ; i++){
            int j = 6 - i;
            switch (j) {
                case 1:
                    tmp_yao = "_i";
                    oriColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 2:
                    tmp_yao = "_2";
                    oriColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 3:
                    tmp_yao = "_3";
                    oriColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 4:
                    tmp_yao = "_4";
                    oriColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 5:
                    tmp_yao = "_5";
                    oriColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 6:
                    tmp_yao = "_u";
                    oriColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
            }

            //本变 变爻 红色 显示
            if( String.valueOf(ori_bin).charAt(i) != String.valueOf(furt_bin).charAt(i) ) {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        e3.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(furt_bin, 3, 1))].intValue());
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                }
            }

            //本变 静爻 黑色 显示
            else {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        e3.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(furt_bin, 3, 1))].intValue());
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                }
            }
        }

    }
    public void e2BtnClick(){
        //initUI
        ImageButton e2 = (ImageButton) mDeduceActivity.findViewById(2);
        TextView furguatext = (TextView) mDeduceActivity.findViewById(8);
        String tmp_yao="" ;
        TextView oriColortext_u = (TextView) mDeduceActivity.findViewById(30);
        TextView oriColortext_5 = (TextView) mDeduceActivity.findViewById(31);
        TextView oriColortext_4 = (TextView) mDeduceActivity.findViewById(32);
        TextView oriColortext_3 = (TextView) mDeduceActivity.findViewById(33);
        TextView oriColortext_2 = (TextView) mDeduceActivity.findViewById(34);
        TextView oriColortext_i = (TextView) mDeduceActivity.findViewById(35);
        TextView furtColortext_u = (TextView) mDeduceActivity.findViewById(36);
        TextView furtColortext_5 = (TextView) mDeduceActivity.findViewById(37);
        TextView furtColortext_4 = (TextView) mDeduceActivity.findViewById(38);
        TextView furtColortext_3 = (TextView) mDeduceActivity.findViewById(39);
        TextView furtColortext_2 = (TextView) mDeduceActivity.findViewById(40);
        TextView furtColortext_i = (TextView) mDeduceActivity.findViewById(41);
        ExpandableTextView1 expTv1 = (ExpandableTextView1) mDeduceActivity.findViewById(24);

        //logic
        if (furt_bin[4] == '1') {
            furt_bin[4] = '0';
        } else {
            furt_bin[4] = '1';
        }
        int origuanum = Integer.parseInt(new String(ori_bin, 0, 6), 2);
        int cur_furt_num = Integer.parseInt(new String(furt_bin, 0, 6), 2);
        int id =  GuaBean.guabin2ID(cur_furt_num);
        GuaDetail detail;
        GuaDetailDao detailDao;
        detailDao = new GuaDetailDao(mDeduceActivity);
        detail = detailDao.query(id);
        expTv1.setText(Html.fromHtml("变卦："+ detail.getContent()));
        myNote name = (myNote)mDeduceActivity.findViewById(19);
        name.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum)+"name", "string", mDeduceActivity.getPackageName())) + "之" + mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"name", "string", mDeduceActivity.getPackageName())) );


        String f_poetry = "变卦为"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num), "string", mDeduceActivity.getPackageName()))  +"\n"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"e", "string", mDeduceActivity.getPackageName()));
        furguatext.setText(new StringBuilder(String.valueOf(f_poetry)));

        //本变6爻显示
        for(int i=0 ; i<6 ; i++){
            int j = 6 - i;
            switch (j) {
                case 1:
                    tmp_yao = "_i";
                    oriColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 2:
                    tmp_yao = "_2";
                    oriColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 3:
                    tmp_yao = "_3";
                    oriColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 4:
                    tmp_yao = "_4";
                    oriColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 5:
                    tmp_yao = "_5";
                    oriColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 6:
                    tmp_yao = "_u";
                    oriColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
            }

            //本变 变爻 红色 显示
            if( String.valueOf(ori_bin).charAt(i) != String.valueOf(furt_bin).charAt(i) ) {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        e2.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(furt_bin, 4, 1))].intValue());
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                }
            }

            //本变 静爻 黑色 显示
            else {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        e2.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(furt_bin, 4, 1))].intValue());
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                }
            }
        }

    }
    public void eiBtnClick(){
        //initUI
        ImageButton ei = (ImageButton) mDeduceActivity.findViewById(1);
        TextView furguatext = (TextView) mDeduceActivity.findViewById(8);
        String tmp_yao="" ;
        TextView oriColortext_u = (TextView) mDeduceActivity.findViewById(30);
        TextView oriColortext_5 = (TextView) mDeduceActivity.findViewById(31);
        TextView oriColortext_4 = (TextView) mDeduceActivity.findViewById(32);
        TextView oriColortext_3 = (TextView) mDeduceActivity.findViewById(33);
        TextView oriColortext_2 = (TextView) mDeduceActivity.findViewById(34);
        TextView oriColortext_i = (TextView) mDeduceActivity.findViewById(35);
        TextView furtColortext_u = (TextView) mDeduceActivity.findViewById(36);
        TextView furtColortext_5 = (TextView) mDeduceActivity.findViewById(37);
        TextView furtColortext_4 = (TextView) mDeduceActivity.findViewById(38);
        TextView furtColortext_3 = (TextView) mDeduceActivity.findViewById(39);
        TextView furtColortext_2 = (TextView) mDeduceActivity.findViewById(40);
        TextView furtColortext_i = (TextView) mDeduceActivity.findViewById(41);
        ExpandableTextView1 expTv1 = (ExpandableTextView1) mDeduceActivity.findViewById(24);

        //logic
        if (furt_bin[5] == '1') {
            furt_bin[5] = '0';
        } else {
            furt_bin[5] = '1';
        }
        int origuanum = Integer.parseInt(new String(ori_bin, 0, 6), 2);
        int cur_furt_num = Integer.parseInt(new String(furt_bin, 0, 6), 2);
        int id =  GuaBean.guabin2ID(cur_furt_num);
        GuaDetail detail;
        GuaDetailDao detailDao;
        detailDao = new GuaDetailDao(mDeduceActivity);
        detail = detailDao.query(id);
        expTv1.setText(Html.fromHtml("变卦："+ detail.getContent()));

        myNote name = (myNote)mDeduceActivity.findViewById(19);
        name.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum)+"name", "string", mDeduceActivity.getPackageName())) + "之" + mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"name", "string", mDeduceActivity.getPackageName())) );

        String f_poetry = "变卦为"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num), "string", mDeduceActivity.getPackageName()))  +"\n"+ mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num)+"e", "string", mDeduceActivity.getPackageName()));
        furguatext.setText(new StringBuilder(String.valueOf(f_poetry)));

        //本变6爻显示
        for(int i=0 ; i<6 ; i++){
            int j = 6 - i;
            switch (j) {
                case 1:
                    tmp_yao = "_i";
                    oriColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_i.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 2:
                    tmp_yao = "_2";
                    oriColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_2.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 3:
                    tmp_yao = "_3";
                    oriColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_3.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 4:
                    tmp_yao = "_4";
                    oriColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_4.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 5:
                    tmp_yao = "_5";
                    oriColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_5.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
                case 6:
                    tmp_yao = "_u";
                    oriColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(origuanum) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    furtColortext_u.setText(mDeduceActivity.getString(mDeduceActivity.getResources().getIdentifier("e" + Integer.toString(cur_furt_num) + tmp_yao, "string", mDeduceActivity.getPackageName())));
                    break;
            }

            //本变 变爻 红色 显示
            if( String.valueOf(ori_bin).charAt(i) != String.valueOf(furt_bin).charAt(i) ) {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        ei.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(furt_bin, 5, 1))].intValue());
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.red));
                        break;
                }
            }

            //本变 静爻 黑色 显示
            else {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_i.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        ei.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(furt_bin, 5, 1))].intValue());
                        break;
                    case 2:
                        furtColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_2.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 3:
                        furtColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_3.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 4:
                        furtColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_4.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 5:
                        furtColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_5.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                    case 6:
                        furtColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        oriColortext_u.setTextColor(mDeduceActivity.getResources().getColor(R.color.control_back));
                        break;
                }
            }
        }
    }
}
