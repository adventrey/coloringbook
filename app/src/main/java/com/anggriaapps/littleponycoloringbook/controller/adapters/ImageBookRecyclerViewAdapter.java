package com.anggriaapps.littleponycoloringbook.controller.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.anggriaapps.littleponycoloringbook.MyApplication;
import com.anggriaapps.littleponycoloringbook.R;
import com.anggriaapps.littleponycoloringbook.model.GridViewActivityModel;
import com.anggriaapps.littleponycoloringbook.model.OnRecycleViewItemClickListener;
import com.anggriaapps.littleponycoloringbook.model.bean.PictureBean;

import java.util.List;


public class ImageBookRecyclerViewAdapter extends RecyclerView.Adapter<ImageBookRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<PictureBean.Picture> pictureBeans;

    public void setOnRecycleViewItemClickListener(OnRecycleViewItemClickListener onRecycleViewItemClickListener) {
        this.onRecycleViewItemClickListener = onRecycleViewItemClickListener;
    }

    private OnRecycleViewItemClickListener onRecycleViewItemClickListener;

    public ImageBookRecyclerViewAdapter(Context context, List<PictureBean.Picture> pictureBeans, int categoryid, boolean isLocal) {
        mContext = context;
        this.pictureBeans = pictureBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.gridview_item_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String url = null;
            holder.image.setLayoutParams(new FrameLayout.LayoutParams(MyApplication.getScreenWidth(mContext) / 2, MyApplication.getScreenWidth(mContext) / 2));
            url = MyApplication.SECRETGARDENLOCATION + pictureBeans.get(position).getUri();

            GridViewActivityModel.getInstance().showGridLocalImageAsyn(holder.image, url);


        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onRecycleViewItemClickListener != null)
                    onRecycleViewItemClickListener.recycleViewItemClickListener(holder.image, position);
            }
        });

    }


    @Override
    public int getItemCount() {
            return pictureBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;


        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.gridImage);

        }
    }

}
