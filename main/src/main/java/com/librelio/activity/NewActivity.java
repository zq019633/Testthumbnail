package com.librelio.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.librelio.storage.DataBaseHelper;
import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpOptions;
import com.niveales.wind.R;

/**
 * Created by zhouqiang on 2017/7/4.
 */

public class NewActivity extends AppCompatActivity {

    private Button but;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);

        Acp.getInstance(this).request(new AcpOptions.Builder()
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE
                                , Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        //以下为自定义提示语、按钮文字
                        .setDeniedMessage("您拒绝存储权限申请，下载功能将不能正常使用，您可以去设置页面重新授权")
                        .setDeniedCloseBtn("关闭")
                        .setDeniedSettingBtn("设置权限")
                        .setRationalMessage("下载功能需要您授权，否则将不能正常使用")
                        .setRationalBtn("我知道了")
                        .build(),
                null);
        but = (Button) findViewById(R.id.bt);
        initData();
    }

    private void initData() {
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("/storage/emulated/0/download/互联网周刊已购12期/互联网周刊12.pdf");
                Intent intent = new Intent(NewActivity.this,MuPDFActivity.class);
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
}
