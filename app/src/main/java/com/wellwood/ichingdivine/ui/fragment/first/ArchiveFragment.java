package com.wellwood.ichingdivine.ui.fragment.first;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.db.RecordDao;
import com.wellwood.ichingdivine.model.e_lib;
import com.wellwood.ichingdivine.model.GuaBean;
import com.wellwood.ichingdivine.model.Recordinfo;
import com.wellwood.ichingdivine.adapter.RecordSearchAdapter;
import com.wellwood.ichingdivine.adapter.ArchiveAdapter;
import com.wellwood.ichingdivine.ui.activity.DeduceActivity;
import java.util.ArrayList;
import java.util.List;



/*

 */
public class ArchiveFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String name;
    private ArrayList arrayList;
    private EditText edt_search;
    private TextView tv_result;
    List<Recordinfo> list;
    RecordDao Recordinfo;
    private ArchiveAdapter mArchiveAdapter;
    private RecordSearchAdapter mRecordSearchAdapter;
    private ListView listview;
    private View recordlist;
    private int menuItemID;
    private boolean searching = false;
    private static int zero = 0;
    private String s = "0";



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ArchiveFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ArchiveFragment newInstance(String param1, String param2) {
        ArchiveFragment fragment = new ArchiveFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ArchiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setHasOptionsMenu(true);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.recordlist = inflater.inflate(R.layout.fragment_archive_list, container,false);
        this.listview = (ListView) recordlist.findViewById(R.id.list_items);
        SQLiteDatabase openOrCreateDatabase = getActivity().openOrCreateDatabase("geniuzWondrousDoorHidenGaapRecords.db3", 0, null);
        openOrCreateDatabase.execSQL("create table if not exists estRecord(ID INTEGER PRIMARY KEY AUTOINCREMENT,DTIME DATETIME,NAME VARCHAR (30),REASON text,VERIFY text,ORIBIN VARCHAR (30),FURTBIN VARCHAR (30) )");
        int i = 0;
        if (openOrCreateDatabase.rawQuery("SELECT * FROM sqlite_master where type='table' and name='estRecord'",new String[0]).getCount() > 0) { //
            final Cursor rawQuery = openOrCreateDatabase.rawQuery("select ID,DTIME,NAME,REASON,VERIFY,ORIBIN,FURTBIN from estRecord order by id desc", new String[0]);
            int count = rawQuery.getCount();
            if (count <= 0) {
//                ((TextView) getActivity().findViewById(R.id.tvNoRecord)).setText("No Record");
                Toast.makeText(getActivity(), "No Record", Toast.LENGTH_SHORT).show();
                if (!(this.mArchiveAdapter == null || this.mArchiveAdapter.list == null)) {
                    //e BaseAdapter  List b
                    this.mArchiveAdapter.list.clear();
                }
                if (this.mArchiveAdapter != null) {
                    this.mArchiveAdapter.notifyDataSetChanged();    //
                }

            } else {
                this.arrayList = new ArrayList();
               // HashMap[] hashMapArr = new HashMap[count];
                rawQuery.moveToFirst();
                while (i < count) {
                    char[] ori_bin = e_lib.bin_format(Integer.parseInt(rawQuery.getString(rawQuery.getColumnIndex("ORIBIN")), 2));
                    int ori_num = Integer.parseInt(new String(ori_bin , 0, 6), 2);
                    char[] furt_bin = e_lib.bin_format(Integer.parseInt(rawQuery.getString(rawQuery.getColumnIndex("FURTBIN")), 2));
                    int furt_num = Integer.parseInt(new String(furt_bin , 0, 6), 2);
                    String f_poetry1 = getString(getResources().getIdentifier("e" + Integer.toString(ori_num)+"name", "string", getActivity().getPackageName()));
                    String f_poetry2 = getString(getResources().getIdentifier("e" + Integer.toString(furt_num)+"name", "string", getActivity().getPackageName()));
                    this.name = new StringBuilder(String.valueOf(f_poetry1 + "之" + f_poetry2)).toString();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("id", rawQuery.getString(rawQuery.getColumnIndex("ID")));
                    contentValues.put("dtime", rawQuery.getString(rawQuery.getColumnIndex("DTIME")));
                    contentValues.put("name", name);
                    contentValues.put("reason", rawQuery.getString(rawQuery.getColumnIndex("REASON")).trim().replace("\n", "<br>"));
                    contentValues.put("verify", rawQuery.getString(rawQuery.getColumnIndex("VERIFY")).trim().replace("\n", "<br>"));
                    arrayList.add(contentValues);

                   // hashMapArr[i] = new HashMap();
                   // hashMapArr[i].put("name", rawQuery.getString(rawQuery.getColumnIndex("NAME")));
                   // hashMapArr[i].put("name", name);
                   // hashMapArr[i].put("dtime", rawQuery.getString(rawQuery.getColumnIndex("DTIME")));
                    //返回的值是-1，即没找到这个字段。
                    //hashMapArr[i].put("reason", rawQuery.getString(rawQuery.getColumnIndex("REASON")));
                    //arrayList.add(hashMapArr[i]);
                    rawQuery.moveToNext();
                    i++;
                }
                mArchiveAdapter = new ArchiveAdapter(getActivity(), arrayList);
                listview.setAdapter(mArchiveAdapter);
                listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Cursor cousor = rawQuery;
                        cousor.moveToPosition((int) id);
                        Intent intent = new Intent();
                        //intent.putExtra("id", -1);
                        String ori_bin  = cousor.getString(cousor.getColumnIndex("ORIBIN"));
                        char[] oribin = e_lib.bin_format(Integer.parseInt(ori_bin, 2));
                        int ori_num = Integer.parseInt(new String(oribin , 0, 6), 2);
                        int ori_id = GuaBean.guabin2ID(ori_num);

                        String furt_bin  = cousor.getString(cousor.getColumnIndex("FURTBIN"));
                        //furt_bin    "111001"      furt_bin_int  111001
                        char[] furtbin = e_lib.bin_format(Integer.parseInt(furt_bin, 2));
                        int furt_num = Integer.parseInt(new String(furtbin , 0, 6), 2);
                        int furt_id = GuaBean.guabin2ID(furt_num);
                        intent.putExtra("id", ori_id);
                        intent.putExtra("furt",furt_id);
                        String f_poetry1 = getString(getResources().getIdentifier("e" + Integer.toString(ori_num)+"name", "string", getActivity().getPackageName()));
                        String f_poetry2 = getString(getResources().getIdentifier("e" + Integer.toString(furt_num)+"name", "string", getActivity().getPackageName()));
                        name = new StringBuilder(String.valueOf(f_poetry1 + "之" + f_poetry2)).toString();
                        intent.putExtra("name",name);
                        //intent.putExtra("reason", ((ContentValues)arrayList.get(-1)).getAsString("REASON"));
                        intent.putExtra("reason",rawQuery.getString(rawQuery.getColumnIndex("REASON")).trim().replace("<br>","\n"));
                        intent.putExtra("verify",rawQuery.getString(rawQuery.getColumnIndex("VERIFY")).trim().replace("<br>","\n"));
                        //ori_num = 25
                        //ori_id = 17    011 001
                        Log.e("id", Integer.toString(ori_id));
                        Log.e("furt", Integer.toString(furt_id));
                        intent.setClass(getActivity(), DeduceActivity.class);
                        //startActivityForResult(intent, 0);
                        startActivity(intent);
                        Toast.makeText(getActivity(), "startActivity...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
        else {
            //((TextView) getActivity().findViewById(R.id.tvNoRecord)).setText("xxxxxxxxxxxxxx");
            Toast.makeText(getActivity(), "No Record xxx", Toast.LENGTH_SHORT).show();
        }

        //长按listview ITME
//        当为一个视图注册了上下文菜单之后，
//        长按（2 秒左右）这个视图对象就会弹出一个浮动菜单，即上下文菜单。
//        任何视图都可以注册上下文菜单，不过，最常见的是用于列表视图ListView的item
        registerForContextMenu(listview);
        listview.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener(){
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){
                contextMenu.setHeaderTitle(getResources().getString(R.string.operation));
                contextMenu.add(0, 1, 1, R.string.del);
                contextMenu.add(0, 2, 2, R.string.enter);
            }

        });


















        //搜索框 文字监听
        this.tv_result = (TextView) recordlist.findViewById(R.id.tv_result);
        this.edt_search = (EditText) recordlist.findViewById(R.id.edt_search);
        this.edt_search.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence arg0, int arg1, int position, int id) {
            }

            public void beforeTextChanged(CharSequence arg0, int arg1, int position, int id) {
            }

            public void afterTextChanged(Editable arg0) {
                Recordinfo = new RecordDao(getActivity());
                String key = edt_search.getText().toString();
                list = Recordinfo.query(key);
                mRecordSearchAdapter = new RecordSearchAdapter(getActivity() ,list);
                listview.setAdapter(mRecordSearchAdapter);
                if (key == null || key.equals("")) {
                    ArchiveFragment.this.searching = false;
                    ArchiveFragment.this.tv_result.setText("");
                    ArchiveFragment.this.tv_result.setVisibility(8);
                } else {
                    ArchiveFragment.this.searching = true;
                    ArchiveFragment.this.tv_result.setVisibility(0);
                    ArchiveFragment.this.tv_result.setText("搜索结果:共搜索到" + ArchiveFragment.this.list.size() + "条结果");
                }
            }
        });




        openOrCreateDatabase.close();
        return recordlist;
    }


    //长按
    public boolean onContextItemSelected(MenuItem menuItem) {
       this.menuItemID = (int) ((AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo()).id;
        //是否确认删除记录
        if (menuItem.getItemId() == 1) {
            new AlertDialog.Builder(getActivity()).setMessage(R.string.label05).setPositiveButton(R.string.btnok, new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int intValue = ((ContentValues)arrayList.get(menuItemID)).getAsInteger("id").intValue();
                            SQLiteDatabase openOrCreateDatabase = getActivity().openOrCreateDatabase("geniuzWondrousDoorHidenGaapRecords.db3", 0, null);
                            openOrCreateDatabase.delete("estRecord", "ID='" + intValue + "'", new String[0]);
                            openOrCreateDatabase.close();
                            arrayList.remove(menuItemID);
                            menuItemID = -1;
                            mArchiveAdapter.notifyDataSetChanged();
//                            if (arrayList.isEmpty()) {
//                                ((TextView)mArchiveAdapter.findViewById(R.id.tvNoRecord)).setText(R.string.tips06);
//                            }
                        }

            }).setNegativeButton(R.string.cancel, null).show();
        }
        // 进入
        else if (menuItem.getItemId() == 2) {
        //    e();
        }
        return super.onContextItemSelected(menuItem);
    }


    //popup window
    public void onCreateOptionsMenu(Menu menu ,MenuInflater inflater) {
        menu.add(0, 1, 1, R.string.back);
       // menu.add(0, 1, 1, R.string.exportdb);
       // menu.add(0, 2, 2, R.string.clear);
       // menu.add(0, 3, 3, R.string.importdb);
        super.onCreateOptionsMenu(menu ,inflater);
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 1) {
            onDestroy();
        }
        if (menuItem.getItemId() == 2) {
            //new AlertDialog.Builder(this).setMessage(R.string.labels17).setPositiveButton(R.string.confirm, new ah(this)).setNegativeButton(R.string.cancel, null).show();
        }
        return super.onOptionsItemSelected(menuItem);
    }



    public static ArchiveFragment newInstance(String param) {
        ArchiveFragment fragment = new ArchiveFragment();
        return fragment;
    }



}
