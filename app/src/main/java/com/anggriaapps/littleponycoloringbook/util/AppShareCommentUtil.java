package com.anggriaapps.littleponycoloringbook.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.anggriaapps.littleponycoloringbook.R;

public class AppShareCommentUtil {

    public static void commentApp(Context context) {
        try {
            context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
        } catch (Exception e) {
        }
    }


    public static void shareApp(Context context) {
        String mAddress = "https://play.google.com/store/apps/details?id=" + context.getPackageName();
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");

        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name));
        sharingIntent.putExtra(Intent.EXTRA_TEXT, mAddress);
        context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.pleaseselect)));

    }

}
