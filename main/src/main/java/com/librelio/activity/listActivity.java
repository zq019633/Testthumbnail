package com.librelio.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;

import com.artifex.mupdfdemo.LinkInfoExternal;
import com.artifex.mupdfdemo.MuPDFCore;
import com.artifex.mupdfdemo.domain.OutlineActivityData;
import com.artifex.mupdfdemo.domain.SearchTaskResult;
import com.artifex.mupdfdemo.view.ReaderView;
import com.librelio.adapter.PDFPreviewPagerAdapter;
import com.librelio.lib.utils.PDFParser;
import com.librelio.view.RecyclerItemClickListener;
import com.niveales.wind.R;

import java.io.Serializable;

/**
 * Created by zhouqiang on 2017/7/4.
 */

public class listActivity extends AppCompatActivity {

    private AlertDialog.Builder alertBuilder;

    private RecyclerView recycle;
    private static final String FILE_NAME = "FileName";
    private MuPDFCore core;
    private String fileName;
    private SparseArray<LinkInfoExternal[]> linkOfDocument;
    private PDFPreviewPagerAdapter pdfPreviewPagerAdapter;
    private ReaderView doc;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.zq_list_activity);
        core = (MuPDFCore) getIntent().getSerializableExtra("core");

        initData();


    }

    private void initData() {
        recycle = (RecyclerView) findViewById(R.id.list);
        LinearLayoutManager listLayoutManager = new LinearLayoutManager(this);
        listLayoutManager.setOrientation(GridLayoutManager.HORIZONTAL);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        recycle.setLayoutManager(gridLayoutManager);



        pdfPreviewPagerAdapter = new PDFPreviewPagerAdapter(this, core);
        recycle.setAdapter(pdfPreviewPagerAdapter);
        recycle.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Log.e("sd","我被点击了");
//                        doc.setDisplayedViewIndex(position);

                        Uri uri = Uri.parse("/storage/emulated/0/download/互联网周刊已购12期/互联网周刊12.pdf");
                        Intent intent = new Intent(listActivity.this,sf.class);
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.putExtra("position",position);
                        Log.e("current",""+position);


                        intent.setData(uri);
                        startActivity(intent);



                    }
                })
        );
    }


}
