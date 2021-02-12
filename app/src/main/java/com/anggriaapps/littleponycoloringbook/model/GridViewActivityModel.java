package com.anggriaapps.littleponycoloringbook.model;

import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;


public class GridViewActivityModel {
    private String root = Environment.getExternalStorageDirectory().getPath() + "/littleponycoloringbook/";
    private static GridViewActivityModel gridViewActivityModel;

    private GridViewActivityModel() {
    }

    public static GridViewActivityModel getInstance() {
        if (gridViewActivityModel == null) {
            gridViewActivityModel = new GridViewActivityModel();
        }
        return gridViewActivityModel;
    }

    public void showGridLocalImageAsyn(ImageView imageView, String url) {
        if (hasSavedFile(url)) {
            Log.e("saved path", "" + "file:/" + root + url.hashCode() + ".png");
            AsynImageLoader.showImageAsynWithoutCache(imageView, "file:/" + root + url.hashCode() + ".png");
        } else {
            Log.e("saved path url", url);
            AsynImageLoader.showImageAsynWithAllCacheOpen(imageView, url);
        }
    }

    private boolean hasSavedFile(String s) {
        int hashCode = s.hashCode();
        String path = root + hashCode + ".png";
        File file = new File(path);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

}
