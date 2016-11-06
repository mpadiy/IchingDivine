package com.wellwood.ichingdivine.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.db.GuaDetailDao;
import com.wellwood.ichingdivine.model.GuaDetail;
import com.wellwood.ichingdivine.model.e_lib;
import com.wellwood.ichingdivine.ui.customizedview.ExpandableTextView;
import com.wellwood.ichingdivine.ui.customizedview.ExpandableTextView1;
import com.wellwood.ichingdivine.ui.customizedview.myNote;
import com.wellwood.ichingdivine.model.GuaBean;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

public class IChingDivineResultActivity extends Activity {
    private int e2;
    private int e3;
    private int e4;
    private int e5;
    private int ei;
    private int eu;
    private int futureNum;
    private View guaView;
    private View svguaView;
    private Integer[] gua_component_small = new Integer[]{Integer.valueOf(R.drawable.sp_small_ying), Integer.valueOf(R.drawable.sp_small_yang), Integer.valueOf(R.drawable.ch_ying), Integer.valueOf(R.drawable.ch_yang)};
    private int oriNum;
    private Button share_button;
    private Button oriDetailBtn;
    private Button btnSave;
    private Button furtDetailBtn;
    private Button btnShowSave;
    private myNote name;
    private myNote reason;
    private myNote verify;
    private TableLayout tableLayout;
    public int origuaid ;
    public int furtguaid ;
    private GuaDetail ori_detail;
    private GuaDetail furt_detail;
    private GuaDetailDao detailDao;
    private int recordNum;
    private char[] mOriBin;
    private char[] mFurtBin;




    public void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        setContentView(R.layout.activity_iching_divine_container);
        ScrollView sv = (ScrollView) findViewById(R.id.svpage);
        final LinearLayout speed_gua = (LinearLayout) sv.findViewById(R.id.speed_gua);
        this.guaView = getLayoutInflater().inflate(R.layout.activity_iching_divine_result, null);
        //截guaView图
        LinearLayout guaLayout = (LinearLayout) this.guaView.findViewById(R.id.ll_iching_divine_result);
        this.share_button = (Button) guaLayout.findViewById(R.id.share_btn);
        this.oriDetailBtn = (Button) guaLayout.findViewById(R.id.oriDetail);
        this.furtDetailBtn = (Button) guaLayout.findViewById(R.id.furtDetail);
        ExpandableTextView expTv = (ExpandableTextView)guaView.findViewById(R.id.expand_text_view);
        ExpandableTextView1 expTv1 = (ExpandableTextView1)guaView.findViewById(R.id.expand_text_view1);
        this.btnShowSave= (Button)guaView.findViewById(R.id.btnShowSave);
        this.btnSave= (Button)guaView.findViewById(R.id.btnSave);
        this.name = (myNote)guaView.findViewById(R.id.edtSaveName);
        this.reason = (myNote)guaView.findViewById(R.id.edtSaveDetail);
        this.verify = (myNote)guaView.findViewById(R.id.edtSaveFeedback);
        this.tableLayout = (TableLayout)guaView.findViewById(R.id.tbSave);
        TextView xx = (TextView) guaLayout.findViewById(R.id.xx);
        TextView guaChange = (TextView) guaLayout.findViewById(R.id.gua_change);
        speed_gua.removeAllViews();
        initDB();




        
        
        //产生六爻
        if (GuaBean.speedGuaExists) {
            this.ei = GuaBean.ei;
            this.e2 = GuaBean.e2;
            this.e3 = GuaBean.e3;
            this.e4 = GuaBean.e4;
            this.e5 = GuaBean.e5;
            this.eu = GuaBean.eu;
        } else {
            this.ei = e_lib.big_e_go(49);
            this.e2 = e_lib.big_e_go(49);
            this.e3 = e_lib.big_e_go(49);
            this.e4 = e_lib.big_e_go(49);
            this.e5 = e_lib.big_e_go(49);
            this.eu = e_lib.big_e_go(49);
            GuaBean.ei = this.ei;
            GuaBean.e2 = this.e2;
            GuaBean.e3 = this.e3;
            GuaBean.e4 = this.e4;
            GuaBean.e5 = this.e5;
            GuaBean.eu = this.eu;
        }
        String tmp = "";
        String i_poetry = "";
        int[] chosenXX = new int[6];
        int[] chosenXX4 = new int[6];
        int[] chosenXX5 = new int[6];
        this.oriNum = ((((((this.eu % 2) * 32) + ((this.e5 % 2) * 16)) + ((this.e4 % 2) * 8)) + ((this.e3 % 2) * 4)) + ((this.e2 % 2) * 2)) + (this.ei % 2);
        if (this.ei == 6) {
            this.ei = 1;
            chosenXX[0] = 1;
        }
        if (this.ei == 9) {
            this.ei = 0;
            chosenXX[0] = 1;
        }
        if (this.e2 == 6) {
            this.e2 = 1;
            chosenXX[1] = 1;
        }
        if (this.e2 == 9) {
            this.e2 = 0;
            chosenXX[1] = 1;
        }
        if (this.e3 == 6) {
            this.e3 = 1;
            chosenXX[2] = 1;
        }
        if (this.e3 == 9) {
            this.e3 = 0;
            chosenXX[2] = 1;
        }
        if (this.e4 == 6) {
            this.e4 = 1;
            chosenXX[3] = 1;
        }
        if (this.e4 == 9) {
            this.e4 = 0;
            chosenXX[3] = 1;
        }
        if (this.e5 == 6) {
            this.e5 = 1;
            chosenXX[4] = 1;
        }
        if (this.e5 == 9) {
            this.e5 = 0;
            chosenXX[4] = 1;
        }
        if (this.eu == 6) {
            this.eu = 1;
            chosenXX[5] = 1;
        }
        if (this.eu == 9) {
            this.eu = 0;
            chosenXX[5] = 1;
        }
        this.futureNum = ((((((this.eu % 2) * 32) + ((this.e5 % 2) * 16)) + ((this.e4 % 2) * 8)) + ((this.e3 % 2) * 4)) + ((this.e2 % 2) * 2)) + (this.ei % 2);
        int changeXXNum = ((((chosenXX[0] + chosenXX[1]) + chosenXX[2]) + chosenXX[3]) + chosenXX[4]) + chosenXX[5];
        if (changeXXNum == 4) {    //4个动摇
            boolean gotIt = false;
            for (i = 0; i < chosenXX.length; i++) {
                if (chosenXX[i] == 0 && !gotIt) {  //如果 静幺则  chosenXX4[i] = 1;
                    chosenXX4[i] = 1;
                    gotIt = true;
                }
            }
        } else if (changeXXNum == 5) {   //5个动摇
            for (i = 0; i < chosenXX.length; i++) {
                if (chosenXX[i] == 1) {   //如果当前要为动摇， chosenXX5[i] = 0;
                    chosenXX5[i] = 0;
                } else if (chosenXX[i] == 0) {
                    chosenXX5[i] = 1;
                }
            }
        }
        String oriGuaName = getString(getResources().getIdentifier("e" + Integer.toString(this.oriNum) + "name", "string", getPackageName())) + "之爻";
        String futureGuaName = getString(getResources().getIdentifier("e" + Integer.toString(this.futureNum) + "name", "string", getPackageName())) + "之爻";
        guaChange.setText(getString(getResources().getIdentifier("e" + Integer.toString(this.oriNum) + "name", "string", getPackageName())) + " 之 " + getString(getResources().getIdentifier("e" + Integer.toString(this.futureNum) + "name", "string", getPackageName())));
        char[] oriBin = e_lib.bin_format(this.oriNum);
        char[] futureBin = e_lib.bin_format(this.futureNum);
        this.mOriBin = oriBin ;
        this.mFurtBin = futureBin ;
        ImageButton e5o = (ImageButton) guaLayout.findViewById(R.id.e5o);
        ImageButton e4o = (ImageButton) guaLayout.findViewById(R.id.e4o);
        ImageButton e3o = (ImageButton) guaLayout.findViewById(R.id.e3o);
        ImageButton e2o = (ImageButton) guaLayout.findViewById(R.id.e2o);
        ImageButton eio = (ImageButton) guaLayout.findViewById(R.id.eio);

        ImageButton euf = (ImageButton) guaLayout.findViewById(R.id.euf);
        ImageButton e5f = (ImageButton) guaLayout.findViewById(R.id.e5f);
        ImageButton e4f = (ImageButton) guaLayout.findViewById(R.id.e4f);
        ImageButton e3f = (ImageButton) guaLayout.findViewById(R.id.e3f);
        ImageButton e2f = (ImageButton) guaLayout.findViewById(R.id.e2f);
        ImageButton eif = (ImageButton) guaLayout.findViewById(R.id.eif);


        ((ImageButton) guaLayout.findViewById(R.id.euo)).setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(oriBin, 0, 1))].intValue());

        e5o.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(oriBin, 1, 1))].intValue());
        e4o.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(oriBin, 2, 1))].intValue());
        e3o.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(oriBin, 3, 1))].intValue());
        e2o.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(oriBin, 4, 1))].intValue());
        eio.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(oriBin, 5, 1))].intValue());


        String tmpGuaName = "";
        for (int ii = 0; ii < 2; ii++) {
            i = 0;
            while (i < chosenXX.length) {
                char[] tmpGuaBin;
                int tmpGuaNum;
                if (ii == 1) {             //后变挂
                    tmpGuaBin = futureBin;
                    tmpGuaNum = this.futureNum;
                    tmpGuaName = futureGuaName;
                } else {
                    tmpGuaBin = oriBin;   //先本挂
                    tmpGuaNum = this.oriNum;
                    tmpGuaName = oriGuaName;
                }
                if (i == 0) {
                    tmp = "_i";
                }
                if (i == 1) {
                    tmp = "_2";
                }
                if (i == 2) {
                    tmp = "_3";
                }
                if (i == 3) {
                    tmp = "_4";
                }
                if (i == 4) {
                    tmp = "_5";
                }
                if (i == 5) {
                    tmp = "_u";
                }
                if (chosenXX[i] != 1) {    //当前是静爻
                    if (chosenXX4[i] == 1 || chosenXX5[i] == 1) {   //如果4，5个 动 爻  ，显示静幺爻辞，否则不显示
                        i_poetry = getString(getResources().getIdentifier("e" + Integer.toString(tmpGuaNum) + tmp, "string", getPackageName())) + "\n" + i_poetry;
                    }
                    switch (i) {
                        case 0:
                            eif.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 5, 1))].intValue());
                            break;
                        case 1:
                            e2f.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 4, 1))].intValue());
                            break;
                        case 2:
                            e3f.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 3, 1))].intValue());
                            break;
                        case 3:
                            e4f.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 2, 1))].intValue());
                            break;
                        case 4:
                            e5f.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 1, 1))].intValue());
                            break;
                        case 5:
                            euf.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 0, 1))].intValue());
                            break;
                        default:
                            break;
                    }

                }

                if (chosenXX[i] == 1) {  ////当前是动爻
                    if (!(changeXXNum == 4 || changeXXNum == 5)) {    //非4，5个 动 爻,比如3个动摇则全部显示爻辞并且变红色， 如果4，5个 动 爻 ，动摇变红但是不显示爻辞
                        i_poetry = getString(getResources().getIdentifier("e" + Integer.toString(tmpGuaNum) + tmp, "string", getPackageName())) + "\n" + i_poetry;
                    }
                    switch (i) {
                        case 0:
                            eif.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 5, 1)) + 2].intValue());
                            break;
                        case 1:
                            e2f.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 4, 1)) + 2].intValue());
                            break;
                        case 2:
                            e3f.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 3, 1)) + 2].intValue());
                            break;
                        case 3:
                            e4f.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 2, 1)) + 2].intValue());
                            break;
                        case 4:
                            e5f.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 1, 1)) + 2].intValue());
                            break;
                        case 5:
                            euf.setBackgroundResource(this.gua_component_small[Integer.parseInt(new String(tmpGuaBin, 0, 1)) + 2].intValue());
                            break;
                        default:
                            break;
                    }
                }
                i++;
            }
            int countXX = 0;                           //注释掉后 xxtextview只有爻辞，没有挂名
            for (int i2 : chosenXX) {
                countXX += i2;

            }
            i_poetry = "\n" + tmpGuaName + "\n" + i_poetry;
            if (countXX == 0) {
                i_poetry = "\n";
            }
        }
        xx.setText(new StringBuilder(String.valueOf(i_poetry)));
        speed_gua.addView(this.guaView);
        this.origuaid  =  GuaBean.guabin2ID(oriNum);
        this.furtguaid  = GuaBean.guabin2ID(futureNum);
        this.detailDao = new GuaDetailDao(IChingDivineResultActivity.this);
        ori_detail = detailDao.query(origuaid);
        furt_detail = detailDao.query(furtguaid);
        expTv.setText("本卦："+ Html.fromHtml(ori_detail.getContent()));
        expTv1.setText("变卦："+ Html.fromHtml(furt_detail.getContent()));

        furtDetailBtn.setOnClickListener(new OnClickListener() {    //变卦详解
            public void onClick(View v) {
                Toast.makeText(IChingDivineResultActivity.this, "变卦详解...",
                        Toast.LENGTH_SHORT).show();;

//                从string里找到对应的html
                String webLink = IChingDivineResultActivity.this.getString(IChingDivineResultActivity.this.getResources().getIdentifier("gua" + furtguaid , "string", IChingDivineResultActivity.this.getPackageName()));
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("webLink", webLink);
                intent.putExtras(bundle);
                intent.setClass(IChingDivineResultActivity.this, TranslationActivity.class);
                IChingDivineResultActivity.this.startActivity(intent);
            }
        });
        oriDetailBtn.setOnClickListener(new OnClickListener() {    //原卦详解
            public void onClick(View v) {
                Toast.makeText(IChingDivineResultActivity.this, "原卦详解...",
                        Toast.LENGTH_SHORT).show();
                String webLink = IChingDivineResultActivity.this.getString(IChingDivineResultActivity.this.getResources().getIdentifier("gua" + origuaid , "string", IChingDivineResultActivity.this.getPackageName()));
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("webLink", webLink);
                intent.putExtras(bundle);
                intent.setClass(IChingDivineResultActivity.this, TranslationActivity.class);
                IChingDivineResultActivity.this.startActivity(intent);

            }
        });

        this.share_button.setOnClickListener(new OnClickListener() {   //分享按钮
            public void onClick(View v) {
                IChingDivineResultActivity.this.share_button.setTextColor(getResources().getColor(R.color.deepskyblue));
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        IChingDivineResultActivity.this.share_button.setTextColor(getResources().getColor(R.color.deepskyblue));


                    }
                }, 300);
                GuaBean.path = new StringBuilder(String.valueOf(IChingDivineResultActivity.this.getFilesDir().getParent())).append(File.separator).append(IChingDivineResultActivity.this.getFilesDir().getName()).toString();
                File imageFile = new File(GuaBean.path + File.separator + "DroEd_" + e_lib.timeNow() + ".jpg");

                //new way
                int sumHeight = 0;
                for(int j=0 ; j< speed_gua.getChildCount();j++)
                {
                    sumHeight += speed_gua.getChildAt(j).getHeight();
                }
                Bitmap bmp = Bitmap.createBitmap(speed_gua.getWidth(),sumHeight, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bmp);
                speed_gua.draw(canvas);
               /* View vImage = IChingDivineResultActivity.this.guaView;
                vImage.setDrawingCacheEnabled(true);
                Bitmap bm = Bitmap.createBitmap(vImage.getDrawingCache());
                vImage.setDrawingCacheEnabled(false);*/
                try {
                    FileOutputStream imageFout = IChingDivineResultActivity.this.openFileOutput(imageFile.getName(), 1);
                    bmp.compress(CompressFormat.JPEG, 100, imageFout);
                    imageFout.close();
                } catch (IOException e) {
                    Log.e("panel", "IOEception", e);
                }
                Uri imageUri = Uri.fromFile(imageFile);
                Intent shareIntent = new Intent("android.intent.action.SEND");
                shareIntent.setType("image/*");
                // shareIntent.putExtra("android.intent.extra.SUBJECT", IChingDivineResultActivity.this.getResources().getString(R.string.速占));
                //shareIntent.putExtra("android.intent.extra.TEXT", IChingDivineResultActivity.this.getResources().getString(R.string.速占));
                //保留上面俩个就不会直接显示图片
                shareIntent.putExtra("android.intent.extra.STREAM", imageUri);
                IChingDivineResultActivity.this.startActivity(Intent.createChooser(shareIntent, "\u5206\u4eab\u5230\u2026"));
            }
        });






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
                if (IChingDivineResultActivity.this.recordNum >= 0) {
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
                        IChingDivineResultActivity.this.recordNum = rawQuery.getInt(rawQuery.getColumnIndex("seq"));
                        Log.e("recordNum", Integer.toString(recordNum));
                    }
                } else if(IChingDivineResultActivity.this.recordNum <= 0){
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
                Toast.makeText(IChingDivineResultActivity.this, "收起、展开...", Toast.LENGTH_SHORT).show();
                if (tableLayout.getVisibility() == 0) {
                    btnShowSave.setText("展开");
                    tableLayout.setVisibility(8);
                    return;
                }
                btnShowSave.setText("收起");
                tableLayout.setVisibility(0);
            }
        });




       this.name.setText(getString(getResources().getIdentifier("e" + Integer.toString(this.oriNum) + "name", "string", getPackageName())) + " 之 " + getString(getResources().getIdentifier("e" + Integer.toString(this.futureNum) + "name", "string", getPackageName())));








    }


    private void initDB() {
        String[] filenames = fileList();
        if (filenames == null || filenames.length == 0) {
            createdb();
            return;
        }
        int length = filenames.length;
        int i = 0;
        while (i < length) {
            if (!filenames[i].equals("gua.db")) {
                i++;
            } else {
                return;
            }
        }
        createdb();
    }

    private void createdb() {
        Logger.e("createdb");
        try {
            InputStream is = getResources().getAssets().open("gua.db");
            byte[] buffer = new byte[1024];
            FileOutputStream fos = openFileOutput("gua.db", 0);
            while (true) {
                int len = is.read(buffer);
                if (len == -1) {
                    is.close();
                    fos.close();
                    return;
                }
                fos.write(buffer, 0, len);
                fos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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