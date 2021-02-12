package com.anggriaapps.littleponycoloringbook;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ToggleButton;
import android.app.AlertDialog;
import android.content.DialogInterface;


import com.anggriaapps.littleponycoloringbook.controller.main.MainActivity;

public class Transtition extends AppCompatActivity {
    ImageView xxPlay, xxRate,xxPolicy,xxlain;
    //au
    MediaPlayer ulala;
    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transtition);
        //au
        ulala = MediaPlayer.create(this, R.raw.xx);
        ulala.setLooping(true);
        ulala.setVolume(1,1);
        ulala.start();


        xxRate = (ImageView)findViewById(R.id.xx_rate);
        xxPolicy = (ImageView)findViewById(R.id.xx_policy);
        xxPlay = (ImageView) findViewById(R.id.xx_play);
        xxlain = (ImageView) findViewById(R.id.xx_lain);

        xxlain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://play.google.com/store/apps/details?id=com.anggriaapps.dollcoloringbook";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        xxRate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String package_name = getPackageName();
                Intent r = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + package_name));
                startActivity(r);
            }
        });
        xxPolicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://sites.google.com/view/privacy-policy-anggriaapps";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        xxPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPlay = new Intent(Transtition.this, MainActivity.class);
                startActivity(intentPlay);
            }
        });

    }
   //au
    public void onToggleCicked(View view) {
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            ulala.setVolume(0, 0);
        } else {
            ulala.setVolume(1, 1);
        }
    }


    @Override
//exit
    public void onBackPressed() {
        exit();//Pergi ke method exit
    }
    private void exit() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are You Sure Want to Exit?")
                .setCancelable(false)//tidak bisa tekan tombol back
                //jika pilih yess
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        //au
                        ulala.stop();
                    }
                })
                //jika pilih no
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                }).show();

}}

