package com.wellwood.ichingdivine.ui.fragment.first;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.orhanobut.logger.Logger;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.ui.activity.DivineReadMeActivity;
import com.wellwood.ichingdivine.ui.activity.IChingDivineResultActivity;




public class DivineFragment extends Fragment implements OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mTextView;
    private String mArgument;
    public static final int MATCH_PARENT = 50;
    public static final int WRAP_CONTENT = 50;
    public static final String ARGUMENT = "argument";
    public String TAG = "DivineFragment";
    public static final String RESPONSE = "response";
    public static final String EVALUATE_DIALOG = "evaluate_dialog";
    public static final int REQUEST_EVALUATE = 0X110;
    private Button btn_iching_divine;
    private Button btn_etree_divine;
    private Button btn_iching_practise;
    private Button btn_divine_readme;
    private Context mContext;


    public static DivineFragment newInstance(String param) {
        DivineFragment fragment = new DivineFragment();
        return fragment;
    }



    public DivineFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View Speedgua = inflater.inflate(R.layout.fragment_divine, container, false);
        btn_iching_divine = (Button) Speedgua.findViewById(R.id.iching_divine);
        btn_etree_divine = (Button) Speedgua.findViewById(R.id.etree_divine);
        btn_iching_practise = (Button) Speedgua.findViewById(R.id.gua_practise);
        btn_divine_readme = (Button) Speedgua.findViewById(R.id.divine_readme);


        btn_etree_divine.setOnClickListener(this);
        btn_iching_divine.setOnClickListener(this);
        btn_iching_practise.setOnClickListener(this);
        btn_divine_readme.setOnClickListener(this);



        return Speedgua;
    }


    @Override
    public void onClick(View v) {
/*        问题就出在Fragment身上，前面说了它不是布局器，所以它不具备渲染视图的能力，
        它管理的布局器最终要加载到一个ViewGroup对象内，由ViewGroup对象来渲染。
        而视图树并不知道每一个子控件来源于哪里，这就导致了一个结果：不管是在什么地方定义的onClick属性，
        都必须在包含该Button的Activity中去寻找OnClick()方法
        API中对onClick早有说明：设置点击时从上下文中调用指定的方法
        让使用该Fragment的Activity实现一个包含所有OnClick()函数的接口*/

        switch (v.getId()) {
            case R.id.iching_divine:
                Logger.e(TAG, "startActivity:IChingDivineActivity1111");
                divineDialog();
                break;
            case R.id.etree_divine:
                Toast.makeText(getActivity(), "未做", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gua_practise:
                Toast.makeText(getActivity(), "未做", Toast.LENGTH_SHORT).show();
                break;
            case R.id.divine_readme:
                Intent intentReadMe = new Intent(getActivity(), DivineReadMeActivity.class);
                startActivity(intentReadMe);
                break;



        }
    }

    private void divineDialog(){
        new MaterialDialog.Builder(getActivity()).iconRes(
                R.mipmap.ic_launcher).limitIconToDefaultSize() // limits the displayed icon size to 48dp
                .title(R.string.divine_dialog_title)
                .content(R.string.divine_dialog_hint)
                .positiveText(R.string.divine_dialog_positive)
                .negativeText(R.string.divine_dialog_negative)
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        Intent intentDivineResult = new Intent(getActivity(), IChingDivineResultActivity.class);
                        startActivity(intentDivineResult);
                        Logger.e(TAG, "startActivity:IChingDivineActivity22222");
                    }
                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                    }
                })
                .show();
    }







}
