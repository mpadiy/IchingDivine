package com.wellwood.ichingdivine.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.text.Html;
import android.text.SpannableString;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.db.GuaDetailDao;
import com.wellwood.ichingdivine.model.GuaDetail;
import com.wellwood.ichingdivine.model.GuaInfo;
import com.wellwood.ichingdivine.model.e_lib;
import com.wellwood.ichingdivine.ui.activity.first.DetailActivity;
import com.wellwood.ichingdivine.ui.customizedview.ExpandableTextView;
import com.wellwood.ichingdivine.ui.customizedview.ExpandableTextView1;
import com.wellwood.ichingdivine.ui.customizedview.myNote;
import com.wellwood.ichingdivine.util.ImgbtnListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import com.wellwood.ichingdivine.model.GuaBean;





//设置 成员变量，整个类的方法都可以调用 ，构造方法方便赋值调用
//布局是容器 是装 view的

public class DeduceActivity extends Activity {
    private int futureNum;
    private View guaView;
    private LinearLayout sv_main_layout;
    private Integer[] gua_component_small = new Integer[]{Integer.valueOf(R.drawable.small_ying), Integer.valueOf(R.drawable.small_yang), Integer.valueOf(R.drawable.ch_small_ying), Integer.valueOf(R.drawable.ch_small_yang)};
    public static String DB_NAME = "tmpDB";
    public static String DB_PATH_NAME = null;
    public static String TAG = "HIPPO_DEBUG";
    public static final String[] ff = new String[]{"e_id", "e_bin"};
    public static final String[] tables = new String[]{"te_go"};
    public static final int version = 1;
    int[] Confucius = new int[66];
    public String DB_PATH_NAME_USER = null;
    public File fileDir;
    CharSequence[] items = new CharSequence[66];
    LinearLayout layout;
    Random myRand;
    public float oldTouchValue;
    public String saveFileName;
    public File sdcardDir;
    TextView tmp;
    EditText mynote;
    public SpannableString eXSpan;
    public TextView origuatext;
    public ScrollView sv;
    List<GuaInfo> list;
    private int[] n;
    private Context d;
    private boolean K;
    private String P;
    private int M;
    private Button gua_detail_button;
    private Button oriDetailbtn;
    private Button furtDetailbtn;
    private int origua_dec;
    private int furtgua_dec;
    private Button muDetailBtn;
    private Button upsDetailBtn;
    private Button exchDetailBtn;
    private TextView oriColortext_i;
    private TextView oriColortext_2;
    private TextView oriColortext_3;
    private TextView oriColortext_4;
    private TextView oriColortext_5;
    private TextView oriColortext_u;
    private TextView furtColortext_i;
    private TextView furtColortext_2;
    private TextView furtColortext_3;
    private TextView furtColortext_4;
    private TextView furtColortext_5;
    private TextView furtColortext_u;
    private ImageButton ei;
    private ImageButton e2;
    private ImageButton e3;
    private ImageButton e4;
    private ImageButton e5;
    private ImageButton eu;
    private ImageButton eio;
    private ImageButton e2o;
    private ImageButton e3o;
    private ImageButton e4o;
    private ImageButton e5o;
    private ImageButton euo;
    private char[] mOriBin;
    private char[] mFurtBin;
    private ExpandableTextView expTv;
    private ExpandableTextView1 expTv1;
    private Button btnShowSave;
    private Button btnSave;
    private myNote name;
    private myNote reason;
    private myNote verify;
    private TableLayout tableLayout;
    private Button shareBtn;
    private LinearLayout ll;
    private LinearLayout speed_gua;
    private TextView oriGuaCi;
    private TextView furtGuaCi;
    private int recordNum;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(1024, 1024);
        requestWindowFeature(1);
        setContentView(R.layout.activity_deduce_container);
        this.fileDir = getFilesDir();
        this.sdcardDir = Environment.getExternalStorageDirectory();
        GuaBean.path = new StringBuilder(String.valueOf(this.fileDir.getParent())).append(File.separator).append(this.fileDir.getName()).append(File.separator).append("andro易d").toString();
        GuaBean.path_SD = new StringBuilder(String.valueOf(this.sdcardDir.getParent())).append(File.separator).append(this.sdcardDir.getName()).append(File.separator).append("andro易d").toString();
        ll = (LinearLayout) this.findViewById(R.id.ll);
        shareBtn = (Button)ll.findViewById(R.id.sharebutton);
        gua_detail_button = (Button)ll.findViewById(R.id.gua_detail);
        oriDetailbtn = (Button)ll.findViewById(R.id.oriDetail);
        furtDetailbtn = (Button)ll.findViewById(R.id.furtDetail);
        ScrollView sv = (ScrollView) findViewById(R.id.svpage);
        this.speed_gua = (LinearLayout) sv.findViewById(R.id.speed_gua);
        this.guaView = getLayoutInflater().inflate(R.layout.deduce_layout, null);



        shareBtn.setOnClickListener(new OnClickListener() {   //分享按钮
            public void onClick(View v) {
                shareBtn.setTextColor(-65536);
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        shareBtn.setTextColor(-256);
                    }
                }, 300);
                GuaBean.path = new StringBuilder(String.valueOf(DeduceActivity.this.getFilesDir().getParent())).append(File.separator).append(DeduceActivity.this.getFilesDir().getName()).toString();
                File imageFile = new File(GuaBean.path + File.separator + "DroEd_" + e_lib.timeNow() + ".jpg");
               /* View vImage = guaView;
                vImage.setDrawingCacheEnabled(true);
                Bitmap bm = Bitmap.createBitmap(vImage.getDrawingCache());
                vImage.setDrawingCacheEnabled(false);*/
                int sumHeight = 0;
                for(int j=0 ; j< speed_gua.getChildCount();j++)
                {
                    sumHeight += speed_gua.getChildAt(j).getHeight();
                }
                Bitmap bmp = Bitmap.createBitmap(speed_gua.getWidth(),sumHeight, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bmp);
                speed_gua.draw(canvas);
                try {
                    FileOutputStream imageFout = DeduceActivity.this.openFileOutput(imageFile.getName(), 1);
                    bmp.compress(CompressFormat.JPEG, 100, imageFout);
                    imageFout.close();
                } catch (IOException e) {
                    Log.e("panel", "IOEception", e);
                }
                Uri imageUri = Uri.fromFile(imageFile);
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(imageFile));
                //Intent intent = new Intent("android.intent.action.SEND");
                // intent.putExtra("android.intent.extra.STREAM", imageUri);
                DeduceActivity.this.startActivity(Intent.createChooser(intent, "\u5206\u4eab\u5230\u2026"));
            }
        });

        gua_detail_button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(DeduceActivity.this, "原卦详解...",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(DeduceActivity.this,DetailActivity.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("id", getIntent().getIntExtra("id", -1));
                startActivity(intent);
            }
        });

        oriDetailbtn.setOnClickListener(new OnClickListener() {    //原卦详解
            public void onClick(View v) {
                Toast.makeText(DeduceActivity.this, "原卦详解...",
                        Toast.LENGTH_SHORT).show();
                int guaid = getIntent().getIntExtra("id", -1);
                String webLink = DeduceActivity.this.getString(DeduceActivity.this.getResources().getIdentifier("gua" + guaid , "string", DeduceActivity.this.getPackageName()));
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("webLink", webLink);
                intent.putExtras(bundle);
                intent.setClass(DeduceActivity.this, TranslationActivity.class);
                DeduceActivity.this.startActivity(intent);

            }
        });


        //数据传递
        int ori = getIntent().getIntExtra("id",-1);
        int furt = getIntent().getIntExtra("furt",-1);
        Log.e("ori", Integer.toString(ori));
        Log.e("furt", Integer.toString(ori));
        this.origua_dec  = GuaBean.guaNum[(ori-1)];
        this.furtgua_dec  = GuaBean.guaNum[(furt-1)];
        char[] ori_bin = e_lib.bin_format(origua_dec);
        char[] furt_bin = e_lib.bin_format(furtgua_dec);
        DeduceActivity.this.auto_gen_layout(ori_bin , furt_bin);
    }

//11 46 泰之升


    public void auto_gen_layout(char[] ori_bin ,char[]  furt_bin) {
        mOriBin = ori_bin ;
        mFurtBin = furt_bin ;
        initUI();
        initData();
        eu.setOnClickListener(new ImgbtnListener(this,mOriBin,mFurtBin ));
        e5.setOnClickListener(new ImgbtnListener(this,mOriBin,mFurtBin ));
        e4.setOnClickListener(new ImgbtnListener(this,mOriBin,mFurtBin ));
        e3.setOnClickListener(new ImgbtnListener(this,mOriBin,mFurtBin ));
        e2.setOnClickListener(new ImgbtnListener(this,mOriBin,mFurtBin ));
        ei.setOnClickListener(new ImgbtnListener(this,mOriBin,mFurtBin ));

        btnSave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                        if (name.getText().toString().trim().equals("")) {
                            Toast.makeText(getApplicationContext(), "名称不可留空", 0).show();
                            name.requestFocus();
                            return;
                        }
                        Calendar instance = Calendar.getInstance();
                        String str = instance.get(1) + "-" + (instance.get(2) + 1) + "-" + instance.get(5) + " " + instance.get(11) + ":" + instance.get(12) + ":" + instance.get(13);
                        SQLiteDatabase openOrCreateDatabase = openOrCreateDatabase("geniuzWondrousDoorHidenGaapRecords.db3", 0, null);
                if (DeduceActivity.this.recordNum >= 0) {
                    openOrCreateDatabase.execSQL("create table if not exists estRecord(ID INTEGER PRIMARY KEY AUTOINCREMENT,DTIME DATETIME,NAME VARCHAR (30),ORIBIN VARCHAR (30),FURTBIN VARCHAR (30),REASON TEXT,VERIFY TEXT)");
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("DTIME", str);
                    contentValues.put("ORIBIN", String.valueOf(mOriBin));
                    contentValues.put("FURTBIN", String.valueOf(mFurtBin));
                    //<br>换行符  将字符串的/n转换为<Br>
                    //过滤回车和换行符
                    contentValues.put("NAME", name.getText().toString().trim().replace("\n", "<br>"));
                    contentValues.put("REASON", reason.getText().toString().trim().replace("\n", "<br>"));
                    contentValues.put("VERIFY", verify.getText().toString().trim().replace("\n", "<br>"));
                    if (openOrCreateDatabase.insert("estRecord", "ID", contentValues) == -1) {
                        Toast.makeText(getApplicationContext(), "保存失败", 0).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "保存成功", 0).show();
                        Cursor rawQuery = openOrCreateDatabase.rawQuery("select seq from sqlite_sequence where name='estRecord' order by seq desc limit 1", new String[0]);
                        rawQuery.moveToFirst();
                        //recordNum = 0 ?
                        DeduceActivity.this.recordNum = rawQuery.getInt(rawQuery.getColumnIndex("seq"));
                        Log.e("recordNum", Integer.toString(recordNum));
                    }
                } else if(DeduceActivity.this.recordNum <= 0){
                            ContentValues contentValues2 = new ContentValues();
                            contentValues2.put("DTIME", str);
                            Log.e("DTIME", str);
                            contentValues2.put("NAME", name.getText().toString().trim());
                            contentValues2.put("ORIBIN", String.valueOf(mOriBin));
                            contentValues2.put("FURTBIN", String.valueOf(mFurtBin));
                            contentValues2.put("REASON", reason.getText().toString().trim().replace("\n", "<br>"));
                            contentValues2.put("VERIFY", verify.getText().toString().trim().replace("\n", "<br>"));
                            if (openOrCreateDatabase.update("estRecord", contentValues2, "ID='" + recordNum + "'", new String[0]) > 0) {
                                Toast.makeText(getApplicationContext(), "保存成功成功2", 0).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "保存失败2", 0).show();
                            }
                        }
                        openOrCreateDatabase.close();
            }
        });



        btnShowSave.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(DeduceActivity.this, "收起、展开...", Toast.LENGTH_SHORT).show();
                if (tableLayout.getVisibility() == 0) {
                    btnShowSave.setText("展开");
                    tableLayout.setVisibility(8);
                    return;
                }
                btnShowSave.setText("收起");
                tableLayout.setVisibility(0);
            }
        });

        //互卦，综错卦
        mutual();
        upsidedown();
        exchange();
        sv_main_layout.addView(guaView);
        getWindow().setSoftInputMode(3);
    }




    public void initUI(){
         /*null pointer错误原因是 ego.findViewById  非  guaView.findViewById*/
        this.sv_main_layout = (LinearLayout) DeduceActivity.this.findViewById(R.id.speed_gua);
        //sv_main_layout.removeAllViews();
        this.euo = (ImageButton) guaView.findViewById(R.id.euo);
        this.e5o = (ImageButton) guaView.findViewById(R.id.e5o);
        this.e4o = (ImageButton) guaView.findViewById(R.id.e4o);
        this.e3o = (ImageButton) guaView.findViewById(R.id.e3o);
        this.e2o = (ImageButton) guaView.findViewById(R.id.e2o);
        this.eio = (ImageButton) guaView.findViewById(R.id.eio);

        this.eu = (ImageButton) guaView.findViewById(R.id.euf);
        this.e5 = (ImageButton) guaView.findViewById(R.id.e5f);
        this.e4 = (ImageButton) guaView.findViewById(R.id.e4f);
        this.e3 = (ImageButton) guaView.findViewById(R.id.e3f);
        this.e2 = (ImageButton) guaView.findViewById(R.id.e2f);
        this.ei = (ImageButton) guaView.findViewById(R.id.eif);

        this.oriGuaCi = (TextView) guaView.findViewById(R.id.oritext);
        this.furtGuaCi = (TextView) guaView.findViewById(R.id.furtext);
        this.expTv = (ExpandableTextView)guaView.findViewById(R.id.expand_text_view);
        this.expTv1 = (ExpandableTextView1)guaView.findViewById(R.id.expand_text_view1);

        this.muDetailBtn = (Button) guaView.findViewById(R.id.mudetail);
        this.upsDetailBtn = (Button) guaView.findViewById(R.id.upsdetail);
        this.exchDetailBtn = (Button) guaView.findViewById(R.id.exchdetail);

        this.oriColortext_u = (TextView) guaView.findViewById(R.id.oriColortext_u);
        this.oriColortext_5 = (TextView) guaView.findViewById(R.id.oriColortext_5);
        this.oriColortext_4 = (TextView) guaView.findViewById(R.id.oriColortext_4);
        this.oriColortext_3 = (TextView) guaView.findViewById(R.id.oriColortext_3);
        this.oriColortext_2 = (TextView) guaView.findViewById(R.id.oriColortext_2);
        this.oriColortext_i = (TextView) guaView.findViewById(R.id.oriColortext_i);
        this.furtColortext_u = (TextView) guaView.findViewById(R.id.furtColortext_u);
        this.furtColortext_5 = (TextView) guaView.findViewById(R.id.furtColortext_5);
        this.furtColortext_4 = (TextView) guaView.findViewById(R.id.furtColortext_4);
        this.furtColortext_3 = (TextView) guaView.findViewById(R.id.furtColortext_3);
        this.furtColortext_2 = (TextView) guaView.findViewById(R.id.furtColortext_2);
        this.furtColortext_i = (TextView) guaView.findViewById(R.id.furtColortext_i);


        this.btnShowSave= (Button)guaView.findViewById(R.id.btnShowSave);
        this.btnSave= (Button)guaView.findViewById(R.id.btnSave);
        this.name = (myNote)guaView.findViewById(R.id.edtSaveName);
        this.reason = (myNote)guaView.findViewById(R.id.edtSaveDetail);
        this.verify = (myNote)guaView.findViewById(R.id.edtSaveFeedback);
        this.tableLayout = (TableLayout)guaView.findViewById(R.id.tbSave);




           //点击改变的控件才需要设置ID ，避免动态添加时重复
        eu.setId(6);
        e5.setId(5);
        e4.setId(4);
        e3.setId(3);
        e2.setId(2);
        ei.setId(1);
        furtGuaCi.setId( 8);
        expTv.setId( 18);
        expTv1.setId( 24);
        name.setId( 19);
        oriColortext_u.setId(30);
        oriColortext_5.setId(31);
        oriColortext_4.setId(32);
        oriColortext_3.setId(33);
        oriColortext_2.setId(34);
        oriColortext_i.setId(35);
        furtColortext_u.setId(36);
        furtColortext_5.setId(37);
        furtColortext_4.setId(38);
        furtColortext_3.setId(39);
        furtColortext_2.setId(40);
        furtColortext_i.setId(41);
    }
    public void initData(){
        GuaDetail detail;
        GuaDetailDao detailDao;
        int id = getIntent().getIntExtra("id",-1);;
        detailDao = new GuaDetailDao(DeduceActivity.this);
        detail =  detailDao.query(id);
        name.setText(getIntent().getStringExtra("name"));
        reason.setText(getIntent().getStringExtra("reason"));
        verify.setText(getIntent().getStringExtra("verify"));

        //本卦六爻显示
        euo.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(mOriBin, 0, 1))].intValue());
        e5o.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(mOriBin, 1, 1))].intValue());
        e4o.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(mOriBin, 2, 1))].intValue());
        e3o.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(mOriBin, 3, 1))].intValue());
        e2o.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(mOriBin, 4, 1))].intValue());
        eio.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(mOriBin, 5, 1))].intValue());

        //本变卦卦辞显示，下拉文本显示
        String origuaci = "本卦为" + getString(getResources().getIdentifier("e" + Integer.toString(origua_dec), "string", getPackageName()))  +"\n"+ getString(getResources().getIdentifier("e" + Integer.toString(origua_dec)+"e", "string", getPackageName())) ;
        oriGuaCi.setText(new StringBuilder(String.valueOf(origuaci)));
        String furtguaci = "变卦为" + getString(getResources().getIdentifier("e" + Integer.toString(furtgua_dec), "string", getPackageName())) +"\n"+ getString(getResources().getIdentifier("e" + Integer.toString(furtgua_dec)+"e", "string", getPackageName())) ;
        furtGuaCi.setText(new StringBuilder(String.valueOf(furtguaci)));
        expTv.setText("本卦：" + Html.fromHtml(detail.getContent()));
        //expTv1.setText(Html.fromHtml(detail.getContent()));


        //判断本变卦哪一爻不同就显示红色
        //遍历6爻
        for(int i=0 ; i<6 ; i++) {
            String tmp_yao = "";
            int j = 6 - i;
            //设置本变 6爻爻辞
            switch (j) {
                case 1:
                    tmp_yao = "_i";
                    oriColortext_i.setText(getString(getResources().getIdentifier("e" + Integer.toString(origua_dec) + tmp_yao, "string", getPackageName())));
                    furtColortext_i.setText(getString(getResources().getIdentifier("e" + Integer.toString(furtgua_dec) + tmp_yao, "string", getPackageName())));
                    break;
                case 2:
                    tmp_yao = "_2";
                    oriColortext_2.setText(getString(getResources().getIdentifier("e" + Integer.toString(origua_dec) + tmp_yao, "string", getPackageName())));
                    furtColortext_2.setText(getString(getResources().getIdentifier("e" + Integer.toString(furtgua_dec) + tmp_yao, "string", getPackageName())));
                    break;
                case 3:
                    tmp_yao = "_3";
                    oriColortext_3.setText(getString(getResources().getIdentifier("e" + Integer.toString(origua_dec) + tmp_yao, "string", getPackageName())));
                    furtColortext_3.setText(getString(getResources().getIdentifier("e" + Integer.toString(furtgua_dec) + tmp_yao, "string", getPackageName())));
                    break;
                case 4:
                    tmp_yao = "_4";
                    oriColortext_4.setText(getString(getResources().getIdentifier("e" + Integer.toString(origua_dec) + tmp_yao, "string", getPackageName())));
                    furtColortext_4.setText(getString(getResources().getIdentifier("e" + Integer.toString(furtgua_dec) + tmp_yao, "string", getPackageName())));
                    break;
                case 5:
                    tmp_yao = "_5";
                    oriColortext_5.setText(getString(getResources().getIdentifier("e" + Integer.toString(origua_dec) + tmp_yao, "string", getPackageName())));
                    furtColortext_5.setText(getString(getResources().getIdentifier("e" + Integer.toString(furtgua_dec) + tmp_yao, "string", getPackageName())));
                    break;
                case 6:
                    tmp_yao = "_u";
                    oriColortext_u.setText(getString(getResources().getIdentifier("e" + Integer.toString(origua_dec) + tmp_yao, "string", getPackageName())));
                    furtColortext_u.setText(getString(getResources().getIdentifier("e" + Integer.toString(furtgua_dec) + tmp_yao, "string", getPackageName())));
                    break;
            }

            //设置 本变 变爻背景红色
            if (String.valueOf(mOriBin).charAt(i) != String.valueOf(mFurtBin).charAt(i)) {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(getResources().getColor(R.color.red));
                        oriColortext_i.setTextColor(getResources().getColor(R.color.red));
                        ei.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(mFurtBin, 5, 1))].intValue());
                        break;
                    case 2:
                        furtColortext_2.setTextColor(getResources().getColor(R.color.red));
                        oriColortext_2.setTextColor(getResources().getColor(R.color.red));
                        e2.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(mFurtBin, 4, 1))].intValue());
                        break;
                    case 3:
                        furtColortext_3.setTextColor(getResources().getColor(R.color.red));
                        oriColortext_3.setTextColor(getResources().getColor(R.color.red));
                        e3.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(mFurtBin, 3, 1))].intValue());
                        break;
                    case 4:
                        furtColortext_4.setTextColor(getResources().getColor(R.color.red));
                        oriColortext_4.setTextColor(getResources().getColor(R.color.red));
                        e4.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(mFurtBin, 2, 1))].intValue());
                        break;
                    case 5:
                        furtColortext_5.setTextColor(getResources().getColor(R.color.red));
                        oriColortext_5.setTextColor(getResources().getColor(R.color.red));
                        e5.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(mFurtBin, 1, 1))].intValue());
                        break;
                    case 6:
                        furtColortext_u.setTextColor(getResources().getColor(R.color.red));
                        oriColortext_u.setTextColor(getResources().getColor(R.color.red));
                        eu.setBackgroundResource(GuaBean.gua_component_3[Integer.parseInt(new String(mFurtBin, 0, 1))].intValue());
                        break;
                }
            }

            //设置不变爻背景黑色
            else {
                switch (j) {
                    case 1:
                        furtColortext_i.setTextColor(getResources().getColor(R.color.control_back));
                        oriColortext_i.setTextColor(getResources().getColor(R.color.control_back));
                        ei.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(mFurtBin, 5, 1))].intValue());
                        break;
                    case 2:
                        furtColortext_2.setTextColor(getResources().getColor(R.color.control_back));
                        oriColortext_2.setTextColor(getResources().getColor(R.color.control_back));
                        e2.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(mFurtBin, 4, 1))].intValue());
                        break;
                    case 3:
                        furtColortext_3.setTextColor(getResources().getColor(R.color.control_back));
                        oriColortext_3.setTextColor(getResources().getColor(R.color.control_back));
                        e3.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(mFurtBin, 3, 1))].intValue());
                        break;
                    case 4:
                        furtColortext_4.setTextColor(getResources().getColor(R.color.control_back));
                        oriColortext_4.setTextColor(getResources().getColor(R.color.control_back));
                        e4.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(mFurtBin, 2, 1))].intValue());
                        break;
                    case 5:
                        furtColortext_5.setTextColor(getResources().getColor(R.color.control_back));
                        oriColortext_5.setTextColor(getResources().getColor(R.color.control_back));
                        e5.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(mFurtBin, 1, 1))].intValue());
                        break;
                    case 6:
                        furtColortext_u.setTextColor(getResources().getColor(R.color.control_back));
                        oriColortext_u.setTextColor(getResources().getColor(R.color.control_back));
                        eu.setBackgroundResource(GuaBean.gua_component_1[Integer.parseInt(new String(mFurtBin, 0, 1))].intValue());
                        break;
                }
            }
        }





    }
    public void mutual(){
        //互卦
        char[] cse_mut = e_lib.bin_format(origua_dec);
        char[] cs_mut =e_lib.bin_format(origua_dec);
        cs_mut[5] = cse_mut[4];
        cs_mut[4] = cse_mut[3];
        cs_mut[3] = cse_mut[2];
        cs_mut[2] = cse_mut[3];
        cs_mut[1] = cse_mut[2];
        cs_mut[0] = cse_mut[1];
        ImageButton eumut = (ImageButton) guaView.findViewById(R.id.eumut);
        ImageButton e5mut = (ImageButton) guaView.findViewById(R.id.e5mut);
        ImageButton e4mut = (ImageButton) guaView.findViewById(R.id.e4mut);
        ImageButton e3mut = (ImageButton) guaView.findViewById(R.id.e3mut);
        ImageButton e2mut = (ImageButton) guaView.findViewById(R.id.e2mut);
        ImageButton eimut = (ImageButton) guaView.findViewById(R.id.eimut);
        eumut.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_mut, 0, 1))].intValue());
        e5mut.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_mut, 1, 1))].intValue());
        e4mut.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_mut, 2, 1))].intValue());
        e3mut.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_mut, 3, 1))].intValue());
        e2mut.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_mut, 4, 1))].intValue());
        eimut.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_mut, 5, 1))].intValue());
        int mu_num = Integer.parseInt(new String(cs_mut, 0, 6), 2);
        final int mu_id =  GuaBean.guabin2ID(mu_num);
        muDetailBtn.setOnClickListener(new OnClickListener() {    //互卦详解
            public void onClick(View v) {
                Toast.makeText(DeduceActivity.this, "互卦详解...", Toast.LENGTH_SHORT).show();
                String webLink = DeduceActivity.this.getString(DeduceActivity.this.getResources().getIdentifier("gua" + mu_id , "string", DeduceActivity.this.getPackageName()));
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("webLink", webLink);
                intent.putExtras(bundle);
                intent.setClass(DeduceActivity.this, TranslationActivity.class);
                DeduceActivity.this.startActivity(intent);

            }
        });
    }
    public void upsidedown(){
        //综卦
        char[] cse_ups = e_lib.bin_format(origua_dec);
        char[] cs_ups =e_lib.bin_format(origua_dec);
        cs_ups[5] = cse_ups[0];
        cs_ups[4] = cse_ups[1];
        cs_ups[3] = cse_ups[2];
        cs_ups[2] = cse_ups[3];
        cs_ups[1] = cse_ups[4];
        cs_ups[0] = cse_ups[5];
        ImageButton euups = (ImageButton) guaView.findViewById(R.id.euups);
        ImageButton e5ups = (ImageButton) guaView.findViewById(R.id.e5ups);
        ImageButton e4ups = (ImageButton) guaView.findViewById(R.id.e4ups);
        ImageButton e3ups = (ImageButton) guaView.findViewById(R.id.e3ups);
        ImageButton e2ups = (ImageButton) guaView.findViewById(R.id.e2ups);
        ImageButton eiups = (ImageButton) guaView.findViewById(R.id.eiups);
        euups.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ups, 0, 1))].intValue());
        e5ups.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ups, 1, 1))].intValue());
        e4ups.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ups, 2, 1))].intValue());
        e3ups.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ups, 3, 1))].intValue());
        e2ups.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ups, 4, 1))].intValue());
        eiups.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ups, 5, 1))].intValue());
        int up_num = Integer.parseInt(new String(cs_ups, 0, 6), 2);
        final int up_id =  GuaBean.guabin2ID(up_num);
        exchDetailBtn.setOnClickListener(new OnClickListener() {    //综卦
            public void onClick(View v) {
                Toast.makeText(DeduceActivity.this, "综卦详解...",
                        Toast.LENGTH_SHORT).show();
                String webLink = DeduceActivity.this.getString(DeduceActivity.this.getResources().getIdentifier("gua" + up_id , "string", DeduceActivity.this.getPackageName()));
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("webLink", webLink);
                intent.putExtras(bundle);
                intent.setClass(DeduceActivity.this, TranslationActivity.class);
                DeduceActivity.this.startActivity(intent);
            }
        });
    }
    public void exchange(){
        //错卦
        char[] cse_ex = e_lib.bin_format(origua_dec);
        char[] cs_ex =e_lib.bin_format(origua_dec);
        for(int i = 0; i < 6 ; i++) {
            if (cse_ex[i] == '1') {
                cs_ex[i] = '0';
            } else if(cse_ex[i] == '0') {
                cs_ex[i] = '1';
            }
        }
        ImageButton euex = (ImageButton) guaView.findViewById(R.id.euex);
        ImageButton e5ex = (ImageButton) guaView.findViewById(R.id.e5ex);
        ImageButton e4ex = (ImageButton) guaView.findViewById(R.id.e4ex);
        ImageButton e3ex = (ImageButton) guaView.findViewById(R.id.e3ex);
        ImageButton e2ex = (ImageButton) guaView.findViewById(R.id.e2ex);
        ImageButton eiex = (ImageButton) guaView.findViewById(R.id.eiex);
        euex.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ex, 0, 1))].intValue());
        e5ex.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ex, 1, 1))].intValue());
        e4ex.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ex, 2, 1))].intValue());
        e3ex.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ex, 3, 1))].intValue());
        e2ex.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ex, 4, 1))].intValue());
        eiex.setBackgroundResource(GuaBean.gua_component_2[Integer.parseInt(new String(cs_ex, 5, 1))].intValue());
        int exc_num = Integer.parseInt(new String(cs_ex, 0, 6), 2);
        final int ex_id =  GuaBean.guabin2ID(exc_num);
        upsDetailBtn.setOnClickListener(new OnClickListener() {    //错卦
            public void onClick(View v) {
                Toast.makeText(DeduceActivity.this, "错卦详解...",
                        Toast.LENGTH_SHORT).show();
                String webLink = DeduceActivity.this.getString(DeduceActivity.this.getResources().getIdentifier("gua" + ex_id , "string", DeduceActivity.this.getPackageName()));
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("webLink", webLink);
                intent.putExtras(bundle);
                intent.setClass(DeduceActivity.this, TranslationActivity.class);
                DeduceActivity.this.startActivity(intent);

            }
        });
    }
    public void onDestroy() {
        super.onDestroy();
    }
    protected void onResume() {
        super.onResume();
    }
    public void onBackPressed() {
        finish();
    }
}