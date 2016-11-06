package com.wellwood.ichingdivine.ui.fragment.first;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.orhanobut.logger.Logger;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.adapter.BookFragmentAdapter;
import com.wellwood.ichingdivine.model.BookInfo;
import com.wellwood.ichingdivine.ui.activity.bookContents.GDContentsActivity;
import com.wellwood.ichingdivine.ui.activity.bookContents.IchingTodayContentsActivity;
import com.wellwood.ichingdivine.ui.activity.bookContents.KZContentsActivity;

import java.util.ArrayList;

/**
 * @描述 在Fragment中要使用ListView，就要用ListFragment
 *  显示书籍的列表
 *
 * */


public class BookFragment extends ListFragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String TAG = BookFragment.class.getName();
    private ListView mlistview ;
    private BookFragmentAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //arrayList 实现键值对存储
        ArrayList<BookInfo> arrayList = new ArrayList<BookInfo>();
        BookInfo info0 = new BookInfo("高岛易断","高岛吞象");
        BookInfo info1 = new BookInfo("十翼","孔子");
        BookInfo info2 = new BookInfo("易经今解","易学网");
        arrayList.add(info0);
        arrayList.add(info1);
        arrayList.add(info2);

//        //HashMap 实现键值对存储
//        ArrayList arrayList = new ArrayList();
//        HashMap[] hashMapArr = new HashMap[2];
//        hashMapArr[0].put(0, book0);
//        hashMapArr[1].put(1, book0);
//        arrayList.add(hashMapArr);
//        Book s=(Book)hm.get("0");//通过键名来取
//        Book s1=(Book)arrayList.get(0);//类似数组通过下标来取
        adapter = new BookFragmentAdapter(getActivity(),arrayList);
        setListAdapter(adapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment_about_me = inflater.inflate(R.layout.fragment_book, container,false);
        mlistview = (ListView) fragment_about_me.findViewById(android.R.id.list);
        return fragment_about_me;
    }

    public static BookFragment newInstance(String param1, String param2) {
        /*
        给Fragment添加newInstance方法，将需要的参数传入，设置到bundle中，然后setArguments(bundle)，最后在onCreate中进行获取；
        这样就完成了Fragment和Activity间的解耦。当然了这里需要注意：
        setArguments方法必须在fragment创建以后，添加给Activity前完成。千万不要，首先调用了add，然后设置arguments
        */
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public static BookFragment newInstance(String param) {
        BookFragment fragment = new BookFragment();
        return fragment;
    }





    @Override
    public void onListItemClick(ListView lv, View v, int position, long id) {

        BookInfo bookInfo = (BookInfo)getListAdapter().getItem(position);
        super.onListItemClick(lv, v, position, id);

        switch(position){
            case 0: /*高岛*/
                //获取fragment的实例,fragment跳转传值, 可以不传值
                toBookContentsActivity(GDContentsActivity.class);
                 Logger.d("position ="+ position);
                break;
            case 1: /*孔子十翼*/
                toBookContentsActivity(KZContentsActivity.class);
                Logger.d("position ="+ position);
                break;
            case 2: /*易经今解*/
                toBookContentsActivity(IchingTodayContentsActivity.class);
                Logger.d("position ="+ position);
                break;


            default:
                break;
        }

    }


//    private void toBookContentsFragment(Fragment fragment){
//        //获取Fragment的管理器 , 必须getActivity获取MainActivity实例
//        FragmentManager fragmentManager=getActivity().getSupportFragmentManager();
//        //开启fragment的事物,在这个对象里进行fragment的增删替换等操作。
//        FragmentTransaction ft = fragmentManager.beginTransaction();
//        //跳转到fragment，第一个参数为所要替换的位置id，第二个参数是替换后的fragment
//        //寄存于MainActivity,但是找不到R.id.fragment_container , 必须getActivity获取MainActivity实例
//        ft.replace(R.id.fragment_container,fragment).addToBackStack("BookContentsFragment").commit();
////        ft.addToBackStack(null);
////        ft.commit();
//    }


    private void toBookContentsActivity(Class clazz){
        startActivity(new Intent(getActivity() ,clazz));
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(TAG, "--------onActivityCreated");

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.i(TAG, "----------onAttach");
    }





}  