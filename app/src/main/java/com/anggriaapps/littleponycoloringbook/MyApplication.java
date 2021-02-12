package com.anggriaapps.littleponycoloringbook;

import android.app.Application;
import android.content.Context;
import android.view.WindowManager;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.anggriaapps.littleponycoloringbook.util.ImageLoaderUtil;

public class MyApplication extends Application {

    public static final String SECRETGARDENLOCATION = "assets://SecretGarden/";
    public static final String BIGPIC = "bigpic";
    public static final String BIGPICFROMUSER = "bigpic_user";
    public static final int PaintActivityRequest = 900;
    public static final int RepaintResult = 999;
    public static final String BIGPICFROMUSERPAINTNAME = "bigpic_user_name";


    public static int screenWidth;
    public static CharSequence SHAREWORK = "share_work";


    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoader();
        screenWidth = getScreenWidth(this);
    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay().getHeight();
    }

    public void initImageLoader() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                this)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(100 * 1024 * 1024)
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoaderUtil.getInstance().init(config);
    }


}
