package com.anggriaapps.littleponycoloringbook.factory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.anggriaapps.littleponycoloringbook.util.AdsUtility;

import com.anggriaapps.littleponycoloringbook.R;
import com.anggriaapps.littleponycoloringbook.model.AsynImageLoader;
import com.anggriaapps.littleponycoloringbook.util.FileUtils;
import com.anggriaapps.littleponycoloringbook.view.MyDialogStyle;
import com.anggriaapps.littleponycoloringbook.view.MyProgressDialog;


public class MyDialogFactory extends MyDialogStyle {


    public MyDialogFactory(Context context) {
        super(context);
    }

    public void FinishSaveImageDialog(View.OnClickListener savelistener, View.OnClickListener quitlistener) {
        showTwoButtonDialog(context.getString(R.string.quitorsave), context.getString(R.string.save), context.getString(R.string.quit), savelistener, quitlistener, true);
    }

    public void showSettingDialog() {

        View layout = LayoutInflater.from(context).inflate(R.layout.setting_view, null);
        Button button = (Button) layout.findViewById(R.id.clearcache);
        button.setText(context.getString(R.string.clearcache));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProgressDialog.show(context, null, context.getString(R.string.clearcacheing));
                AsynImageLoader.setOnClearCacheFinishListener(new AsynImageLoader.OnClearCacheFinishListener() {
                    @Override
                    public void clearCacheFinish() {
                        MyProgressDialog.DismissDialog();
                        Toast.makeText(context, context.getString(R.string.clearcachesuccess), Toast.LENGTH_SHORT).show();
                    }
                });
                AsynImageLoader.clearCache();
                AdsUtility.showIntestitialAds();
            }
        });
        Button deletePaint = (Button) layout.findViewById(R.id.deletePaint);
        deletePaint.setText(context.getString(R.string.deleteAllPaints));
        deletePaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogFactory myDialogFactory = new MyDialogFactory(context);
                myDialogFactory.showDeletePaintsDialog();

            }
        });

        showBlankDialog(context.getString(R.string.action_settings), layout);
    }

    private void showDeletePaintsDialog() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(context.getString(R.string.confirmDeleteAllPaints) + "\n");
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissDialog();
            }
        };
        View.OnClickListener listener1 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissDialog();
                FileUtils.deleteAllPaints();
                Toast.makeText(context, context.getString(R.string.deleteAllPaintsSuccess), Toast.LENGTH_SHORT).show();
                AdsUtility.showIntestitialAds();
            }
        };
        showTwoButtonDialog(buffer, context.getString(R.string.delete), context.getString(R.string.cancel), listener1, listener2, true);
    }


    public void showPaintFirstOpenDialog() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(context.getString(R.string.paintHint));
        showOnceTimesContentDialog(context.getString(R.string.welcomeusethis), buffer, SharedPreferencesFactory.PaintHint);
    }

    public void showPaintFirstOpenSaveDialog() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(context.getString(R.string.paintHint2));
        showOnceTimesContentDialog(context.getString(R.string.welcomeusethis), buffer, SharedPreferencesFactory.PaintHint2);
    }

    public void showRepaintDialog(View.OnClickListener confirm) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(context.getString(R.string.confirmRepaint) + "\n");
        View.OnClickListener listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissDialog();
            }
        };
        showTwoButtonDialog(buffer, context.getString(R.string.repaint), context.getString(R.string.cancel), confirm, listener2, true);
    }

    public void showBuxianButtonClickDialog() {
        showOnceTimesContentDialog(context.getString(R.string.buxianfunction), context.getString(R.string.buxianfunctionhint), SharedPreferencesFactory.BuXianButtonClickDialogEnable);
    }

    public void showBuxianFirstPointSetDialog() {
        showOnceTimesContentDialog(context.getString(R.string.buxianfunction), context.getString(R.string.buxianfirstpointsethint), SharedPreferencesFactory.BuXianFirstPointDialogEnable);
    }

    public void showBuxianNextPointSetDialog() {
        showOnceTimesContentDialog(context.getString(R.string.buxianfunction), context.getString(R.string.buxiannextpointsethint), SharedPreferencesFactory.BuXianNextPointDialogEnable);
    }

    public void showPickColorHintDialog() {
        showOnceTimesContentDialog(context.getString(R.string.pickcolor), context.getString(R.string.pickcolorhint), SharedPreferencesFactory.PickColorDialogEnable);
    }

    private void showOnceTimesContentDialog(String title, CharSequence contentstr, final String whichDialog) {
        if (SharedPreferencesFactory.getBoolean(context, whichDialog, true)) {
            View layout = LayoutInflater.from(context).inflate(R.layout.checkbox_view, null);
            TextView content = (TextView) layout.findViewById(R.id.content);
            final CheckBox checkBox = (CheckBox) layout.findViewById(R.id.checkbox_dontshow);
            content.setText(contentstr);
            View.OnClickListener listener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        SharedPreferencesFactory.saveBoolean(context, whichDialog, false);
                    }
                    dismissDialog();
                }
            };
            showBlankDialog(title, layout, listener);
        }
    }

    public void showPaintHintDialog() {
        showPaintFirstOpenDialog();
        if (!dialog.isShowing()) {
            showPaintFirstOpenSaveDialog();
        }
    }

    public void showGradualHintDialog() {
        showOnceTimesContentDialog(context.getString(R.string.gradualModel), context.getString(R.string.gradualModelHint), SharedPreferencesFactory.GradualModel);
    }
}
