package com.wellwood.ichingdivine.ui.fragment.first;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.orhanobut.logger.Logger;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.adapter.ListViewAdapter;
import com.wellwood.ichingdivine.adapter.GridViewAdapter;
import com.wellwood.ichingdivine.db.GuaInfoDao;
import com.wellwood.ichingdivine.model.GuaInfo;
import com.wellwood.ichingdivine.ui.activity.DeduceActivity;
import com.wellwood.ichingdivine.ui.customizedview.FastBar;
import com.wellwood.ichingdivine.ui.customizedview.FastBar.OnFastBarChangleListener;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
//问题出在不懂fragment和activity的数据交换
//FileOutputStream fos =  openFileOutput("gua.db", 0);
// filepath =  getApplicationContext().getFilesDir().getPath();
//提示找不到方法


//ViewPager默认加载第一二项, 加载db导致启动慢



public class Gua64Fragment extends Fragment {
    CheckBox btn_switching;
    EditText edt_search;
    FastBar fastBar;
    GridView gridView;
    public static String fileName = "gua.db";
    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    Gua64Fragment.this.edt_search.requestFocus();
                    return;
                default:
                    return;
            }
        }
    };
    GuaInfoDao infoDao;
    List<GuaInfo> list;
    ListViewAdapter listViewAdapter;
    ListView listview;
    FragmentActivity ma;
    GridViewAdapter gridViewAdapter;
    RelativeLayout ryt_content;
    RelativeLayout rl_search;
    boolean searching = false;
    TextView txt_result;
    String filepath ;






    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view_home = inflater.inflate(R.layout.fragment_64gua, null);
       // filepath = getApplicationContext().getFilesDir().getPath();  必须在activity中，
        this.ma = (FragmentActivity) getActivity();
        filepath = ma.getApplicationContext().getFilesDir().getPath();
         List<GuaInfo> list = new ArrayList<GuaInfo>();
        this.btn_switching = (CheckBox) view_home.findViewById(R.id.cb_switch_layout);
        this.rl_search = (RelativeLayout) view_home.findViewById(R.id.rl_search);

        this.btn_switching.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                if (isChecked) {
//                    点击显示list布局
                    Gua64Fragment.this.gridView.setVisibility(View.GONE);
                    Gua64Fragment.this.listview.setVisibility(View.VISIBLE);
                    Gua64Fragment.this.fastBar.setSilder(Gua64Fragment.this.listview.getFirstVisiblePosition());
                    return;
                }
                Gua64Fragment.this.gridView.setVisibility(View.VISIBLE);
                Gua64Fragment.this.listview.setVisibility(View.GONE);
                Gua64Fragment.this.fastBar.setSilder(Gua64Fragment.this.gridView.getFirstVisiblePosition());
            }
        });
        this.txt_result = (TextView) view_home.findViewById(R.id.tv_search_resluts);
        this.txt_result.setText("");
        this.txt_result.setVisibility(View.GONE);
        this.listview = (ListView) view_home.findViewById(R.id.gua64_listview);
        this.gridView = (GridView) view_home.findViewById(R.id.gua64_gridview);


        this.gridView.setOnScrollListener(new OnScrollListener() {
            int scrollState = 0;
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollState = scrollState;
            }
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (Gua64Fragment.this.fastBar != null && this.scrollState != 0) {
                    Gua64Fragment.this.fastBar.setSilder(firstVisibleItem);
                }
            }
        });
        //  GuaInfo display in  gridview
        //    List<GuaInfo> list;  arraylist
        // intent.putExtra name id to DetailActivity
        //list conllection interface
        //position is not gridview/listview item
        //Inserts the specified object into this {@code List} at the specified location(position)
 //List<GuaInfo> list   elememt:{object1 , ...    }
        //List中的get(i)方法是获取List中的第i+1个对象




        this.gridView.setOnItemClickListener(new OnItemClickListener() {
            //void onItemClick(AdapterView<?> parent, View customizedview, int position, long id);
            public void onItemClick(AdapterView<?> adapterView, View arg1, int position, long id) {
                GuaInfo info = (GuaInfo) Gua64Fragment.this.list.get(position);
                Intent intent = new Intent(Gua64Fragment.this.ma, DeduceActivity.class);
                intent.putExtra("name", info.getName());
                intent.putExtra("id", info.getId());
                intent.putExtra("furt", info.getId());
                Toast.makeText(getActivity(), "请点击变卦开始演卦...", Toast.LENGTH_LONG).show();
                Log.e("name", info.getName());
                Log.e("id", Integer.toString(info.getId()));
                Gua64Fragment.this.startActivity(intent);
            }
        });
        this.listview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View arg1, int position, long id) {
                GuaInfo info = (GuaInfo) Gua64Fragment.this.list.get(position);
                Intent intent = new Intent(Gua64Fragment.this.ma, DeduceActivity.class);
                intent.putExtra("name", info.getName());
                intent.putExtra("id", info.getId());
                Gua64Fragment.this.startActivity(intent);
            }
        });
        this.listview.setOnScrollListener(new OnScrollListener() {
            int scrollState = 0;

            public void onScrollStateChanged(AbsListView view, int scrollState) {
                this.scrollState = scrollState;
            }

            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (Gua64Fragment.this.fastBar != null && this.scrollState != 0) {
                    Gua64Fragment.this.fastBar.setSilder(firstVisibleItem);
                }
            }
        });



        this.fastBar = new FastBar(this.ma, 64, 8, 8);
        this.fastBar.setGridView(this.gridView);
        this.fastBar.setListView(this.listview);
        this.fastBar.setOnFastBarChangleListener(new OnFastBarChangleListener() {
            public void onFastBarChangeled(int now) {
                if (Gua64Fragment.this.searching) {
                    Gua64Fragment.this.txt_result.setText("");
                    Gua64Fragment.this.txt_result.setVisibility(View.GONE);
                    Gua64Fragment.this.edt_search.setText("");
                }
            }
        });


//        fastBar 设置参数, -1 matchparent
        LayoutParams params = new LayoutParams(40, -1);
        params.addRule(11);

        this.fastBar.setLayoutParams(params);
        this.ryt_content = (RelativeLayout) view_home.findViewById(R.id.rl_64gua_content);
        this.ryt_content.addView(this.fastBar);



        this.edt_search = (EditText) view_home.findViewById(R.id.gua64_edt_search);
        this.edt_search.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence arg0, int arg1, int position, int id) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int position, int id) {
            }

            public void afterTextChanged(Editable arg0) {
                String key = Gua64Fragment.this.edt_search.getText().toString();
                Gua64Fragment.this.list = Gua64Fragment.this.infoDao.query(key);
                //fragment 和activity 挂钩
                Gua64Fragment.this.gridViewAdapter = new GridViewAdapter(Gua64Fragment.this.ma, Gua64Fragment.this.list);
                Gua64Fragment.this.gridView.setAdapter(Gua64Fragment.this.gridViewAdapter);

                Gua64Fragment.this.listViewAdapter = new ListViewAdapter(Gua64Fragment.this.ma, Gua64Fragment.this.list);
                Gua64Fragment.this.listview.setAdapter(Gua64Fragment.this.listViewAdapter);
                if (key == null || key.equals("")) {
                    Gua64Fragment.this.searching = false;
                    Gua64Fragment.this.txt_result.setText("");
                    Gua64Fragment.this.txt_result.setVisibility(View.GONE);
                } else {
                    Gua64Fragment.this.searching = true;
                    Gua64Fragment.this.txt_result.setVisibility(View.VISIBLE);
                    Gua64Fragment.this.txt_result.setText("搜索结果:共搜索到" + Gua64Fragment.this.list.size() + "条结果");
                    Gua64Fragment.this.fastBar.setSilder(0);
                }
                Gua64Fragment.this.handler.sendEmptyMessageDelayed(1, 500);
            }
        });
        hidenSearch();
        Logger.e("initDB ");
        initDB();
        return view_home;
    }



    public static Gua64Fragment newInstance(String param) {
        Gua64Fragment fragment = new Gua64Fragment();
        return fragment;
    }



    private void initDB() {
//        searchDB();
        openDB();

        initData();
//        猴急的fragment,开进程initData() 还不能显示db数据

    }
    private void initData() {
        this.infoDao = new GuaInfoDao(this.ma);
        //new size=0
        this.list = this.infoDao.queryAll();
        this.gridViewAdapter = new GridViewAdapter(this.ma, this.list);
        //this.ma context
        //error setAdapter list.size = 0
        this.gridView.setAdapter(this.gridViewAdapter);
        this.listViewAdapter = new ListViewAdapter(this.ma, this.list);
        this.listview.setAdapter(this.listViewAdapter);
    }

    private void searchDB() {

//        获取package 文件列表
        String[] filenames = ma.getApplicationContext().fileList();

//        如果没有文件就打开db
        if (filenames == null || filenames.length == 0) {
            openDB();
            return;
        }
//        如果有的话就查找gua.db
        int length = filenames.length;
        int i = 0;
        while (i < length) {
            if (!filenames[i].equals("gua.db")) {
                i++;
            } else {
                return;
            }
        }
//        找到就打开
        openDB();


    }

    private void openDB() {
        Logger.e("open db");
        try {
            InputStream is = getResources().getAssets().open("gua.db");
            byte[] buffer = new byte[1024];

            FileOutputStream fos = ma.getApplicationContext().openFileOutput("gua.db", 0);
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


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.ma.getWindow().setSoftInputMode(35);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void hidenSearch() {
        if (this.searching) {
            this.txt_result.setText("");
            this.txt_result.setVisibility(View.GONE);
            this.edt_search.setText("");
        }
    }

    public void onResume() {
        hidenSearch();
        super.onResume();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static Gua64Fragment newInstance(String param1, String param2) {

        /*
        给Fragment添加newInstance方法，将需要的参数传入，设置到bundle中，然后setArguments(bundle)，最后在onCreate中进行获取；
        这样就完成了Fragment和Activity间的解耦。当然了这里需要注意：
        setArguments方法必须在fragment创建以后，添加给Activity前完成。千万不要，首先调用了add，然后设置arguments
        */
        Bundle args = new Bundle();
//      args.putString(ARG_PARAM1, param1);
//       args.putString(ARG_PARAM2, param2);
        Gua64Fragment fragment = new Gua64Fragment();
        fragment.setArguments(args);
        return fragment;
    }


}
