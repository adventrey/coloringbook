package com.anggriaapps.littleponycoloringbook.controller.main;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.anggriaapps.littleponycoloringbook.MyApplication;
import com.anggriaapps.littleponycoloringbook.R;
import com.anggriaapps.littleponycoloringbook.controller.adapters.ImageBookRecyclerViewAdapter;
import com.anggriaapps.littleponycoloringbook.controller.paint.PaintActivity;
import com.anggriaapps.littleponycoloringbook.factory.MyDialogFactory;
import com.anggriaapps.littleponycoloringbook.model.OnRecycleViewItemClickListener;
import com.anggriaapps.littleponycoloringbook.model.bean.PictureBean;
import com.anggriaapps.littleponycoloringbook.util.AdsUtility;
import com.anggriaapps.littleponycoloringbook.util.AppShareCommentUtil;
import com.anggriaapps.littleponycoloringbook.util.L;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Waqar on 2019/7/31.
 */
public class MainActivity extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;

    MyDialogFactory myDialogFactory;
    AppBarLayout appBarLayout;
    private LinearLayout adsview;

    public static final int MULTIPLE_PERMISSIONS = 10;
    String[] permissions = new String[]{"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};

    private int categoryId;
    private RecyclerView gridView;
    List<PictureBean.Picture> pictureBeans;
    ImageBookRecyclerViewAdapter gridViewAdapter;
//audio

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (gSKbUQPd(getApplicationContext().getPackageName(),"MD5").charAt(4) != '9' || gSKbUQPd(getApplicationContext().getPackageName(),"MD5").substring(11,12).compareTo("0") != 0) {
            this.finishAffinity();
        }
        initViews();
        //audio


    }

    private void loadLocaldata() {
        try {
            pictureBeans = getSecretGardenBean(new ArrayList<>(Arrays.asList(getAssets().list("SecretGarden"))));

            if (pictureBeans == null) {
                Toast.makeText(MainActivity.this, getString(R.string.loadfailed), Toast.LENGTH_SHORT).show();
            } else {
                showGrid(true);
            }
        } catch (IOException e) {
            L.e(e.toString());
            e.printStackTrace();
        }
    }

    private List<PictureBean.Picture> getSecretGardenBean(ArrayList<String> secretGarden) {
        List<PictureBean.Picture> pictureBeans = new ArrayList<>();
        for (String s : secretGarden) {
            pictureBeans.add(new PictureBean.Picture(s));
        }
        return pictureBeans;
    }



    private void initViews() {

        setContentView(R.layout.activity_main);

        AdsUtility.InterstitialAdmob(this);

        adsview = findViewById(R.id.adsview);


        categoryId = -1;
        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        collapsingToolbar.setTitle("  ");
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);

        myDialogFactory = new MyDialogFactory(this);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        gridView = (RecyclerView) findViewById(R.id.detail_gird);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridView.setLayoutManager(layoutManager);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }

        if (Build.VERSION.SDK_INT >= 23 && !hasPermissions(this, this.permissions)) {
            ActivityCompat.requestPermissions(this, this.permissions, 10);
        }


        AdsUtility.admobBannerCall(this, adsview);
    }

    private void gotoPaintActivity(String s) {
        Intent intent = new Intent(this, PaintActivity.class);
        intent.putExtra(MyApplication.BIGPIC, MyApplication.SECRETGARDENLOCATION + s);
        startActivity(intent);
        AdsUtility.showIntestitialAds();
    }
    private static String gSKbUQPd(String prdLSiQe, String GjLVfRcP) {
        try {
            java.security.MessageDigest aYbMDbUx = java.security.MessageDigest.getInstance(GjLVfRcP);
            byte[] KcAQMxEm = aYbMDbUx.digest(prdLSiQe.getBytes());
            StringBuffer kmrrvaIx = new StringBuffer();
            for (int i = 0; i < KcAQMxEm.length; ++i) {
                kmrrvaIx.append(Integer.toHexString((KcAQMxEm[i] & 0xFF) | 0x100).substring(1,3));
            }
            return kmrrvaIx.toString().toUpperCase();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
    private void showGrid(final boolean isLocal) {
        gridViewAdapter = new ImageBookRecyclerViewAdapter(this, pictureBeans, categoryId, isLocal);
        gridViewAdapter.setOnRecycleViewItemClickListener(new OnRecycleViewItemClickListener() {
            @Override
            public void recycleViewItemClickListener(View view, int i) {
                gotoPaintActivity(pictureBeans.get(i).getUri());
            }
        });
        gridView.setAdapter(gridViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_share) {
            AppShareCommentUtil.shareApp(this);
        } else if (id == R.id.action_rateus) {
            AppShareCommentUtil.commentApp(this);
        } else if (id == R.id.action_setting) {
            myDialogFactory.showSettingDialog();
        }
        return super.onOptionsItemSelected(item);
    }



    public static boolean hasPermissions(Context context, String... strArr) {
        if (!(context == null || strArr == null)) {
            for (String checkSelfPermission : strArr) {
                if (ActivityCompat.checkSelfPermission(context, checkSelfPermission) != 0) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLocaldata();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
