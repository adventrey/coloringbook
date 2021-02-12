package com.anggriaapps.littleponycoloringbook.controller.paint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy.Builder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.anggriaapps.littleponycoloringbook.R;


import java.io.File;


public class AdvancePaintActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView iv_back;
    private ImageView iv_facebook;
    private ImageView iv_home;
    private ImageView iv_instagram;
    private ImageView iv_more;
    private ImageView iv_whatsapp;
    private ImageView imageView;
    private String imageUri;
    private File path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharepainting);


        FindControls();

        imageUri = getIntent().getStringExtra("imagepath");
        path = new File(imageUri);
        if (path.exists()) {
            this.imageView.setImageURI(Uri.fromFile(path));
        }

    }

    public void FindControls() {
        this.iv_facebook = (ImageView) findViewById(R.id.iv_facebook);
        this.iv_facebook.setOnClickListener(this);
        this.iv_whatsapp = (ImageView) findViewById(R.id.iv_whatsapp);
        this.iv_whatsapp.setOnClickListener(this);
        this.iv_instagram = (ImageView) findViewById(R.id.iv_instagram);
        this.iv_instagram.setOnClickListener(this);
        this.iv_more = (ImageView) findViewById(R.id.iv_more);
        this.iv_more.setOnClickListener(this);
        this.imageView = (ImageView) findViewById(R.id.share_img);
    }

    public void onClick(View v) {
        StrictMode.setVmPolicy(new Builder().build());
        Intent shareIntent = new Intent("android.intent.action.SEND");
        shareIntent.setType("image/*");
        shareIntent.putExtra("android.intent.extra.TEXT", "https://play.google.com/store/apps/details?id=" + this.getPackageName());
        shareIntent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(String.valueOf(path))));
        switch (v.getId()) {

            case R.id.iv_whatsapp:
                try {
                    shareIntent.setPackage("com.whatsapp");
                    startActivity(shareIntent);
                    return;
                } catch (Exception e) {
                    Toast.makeText(this, "WhatsApp doesn't installed!", Toast.LENGTH_LONG).show();
                    return;
                }
            case R.id.iv_facebook:
                try {
                    shareIntent.setPackage("com.facebook.katana");
                    startActivity(shareIntent);
                    return;
                } catch (Exception e2) {
                    Toast.makeText(this, "Facebook doesn't installed!", Toast.LENGTH_LONG).show();
                    return;
                }

            case R.id.iv_instagram:
                try {
                    shareIntent.setPackage("com.instagram.android");
                    startActivity(shareIntent);
                    return;
                } catch (Exception e4) {
                    Toast.makeText(this, "Instagram doesn't installed!", Toast.LENGTH_LONG).show();
                    return;
                }
            case R.id.iv_more:
                startActivity(Intent.createChooser(shareIntent, "Share Image using"));
                return;
            default:
                return;
        }
    }

}
