package com.wellwood.ichingdivine.ui.fragment.second;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.jorgecastilloprz.FABProgressCircle;
import com.wellwood.ichingdivine.R;
import com.wellwood.ichingdivine.SampleListActivity;

import java.io.InputStream;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 测试夜间模式
 */
public class TestNightFragment extends Fragment implements View.OnClickListener {
    private static final String SALE_PARAM = "param";
    @InjectView(R.id.news_header)
    ImageView newsHeader;
    @InjectView(R.id.img_source)
    TextView imgSource;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @InjectView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.webView)
    WebView webView;
//    @InjectView(R.aid.nsv_content)
//    NestedScrollView nsvContent;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.fabProgressCircle)
    FABProgressCircle fabProgressCircle;
    @InjectView(R.id.gson_content)
    CoordinatorLayout gsonContent;
    private String mParam;

    public static Fragment newInstance(String param) {
        TestNightFragment fragment = new TestNightFragment();
        Bundle args = new Bundle();
        args.putString(SALE_PARAM, param);
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(SALE_PARAM);
        }

        startActivity(new Intent(getActivity() , SampleListActivity.class));

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_detail, null);
        Log.i(SALE_PARAM, "in SaleFragment");
        ButterKnife.inject(this, view);

        new DownloadImageTask(newsHeader)
                .execute("http://ocw.aca.ntu.edu.tw/ntu-ocw/files/logo.png");

//        nsvContent.setBackgroundColor(getResources().getColor(R.color.window_background_dark));


        webView.getSettings().setSupportZoom(false);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setDefaultFontSize(18);
        String data = "<div aid=\"article_content\"><p>\n" +
                "\t<strong>测试....</strong> " +
                "易之为书也，不可远。给对易经研究有兴趣的伙伴\n" +
                "</p>\n" +
                "<p style=\"text-align:center;\">\n" +
                "\t<img src=\"file:///android_asset/2.jpg\" alt=\"\"> \n" +
                "</p>\n" +
                "<p>\n" +
                "\t<span style=\"line-height:1.5;\"></span> \n" +
                "</p> </div>";
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadDataWithBaseURL(null, data, "text/html", "UTF-8", null);
        return view;
    }


    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null)
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
    }






    @Override
    public void onResume() {
        super.onResume();
    }





    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }




    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "button", Toast.LENGTH_LONG).show();
    }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }







}