package com.max.security.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.max.security.R;
import com.max.security.adapter.base.BaseRecyclerViewAdapter;
import com.max.security.greendao.FileModel;
import com.max.security.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */
public class ImagePageAdapter extends BaseRecyclerViewAdapter<FileModel> {

    public ImagePageAdapter(List<FileModel> list, Context context) {
        super(list, context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.image_page_item, parent, false);

        return new ImagePageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ImagePageViewHolder imgHolder = (ImagePageViewHolder) holder;
        imgHolder.showImage(FileUtils.getDecryptFilePath(mList.get(position)));
    }

    @Override
    protected Animator[] getAnimators(View view) {
        if (view.getMeasuredHeight() <=0){
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 1.0f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 1.0f);
            return new ObjectAnimator[]{scaleX, scaleY};
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "scaleX", 1.05f, 1.0f),
                ObjectAnimator.ofFloat(view, "scaleY", 1.05f, 1.0f),
        };
    }

    private class ImagePageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ImagePageViewHolder(View itemView) {
            super(itemView);
        }

        public void showImage(String filePath) {
            if(imageView == null) {
                imageView = (ImageView) itemView.findViewById(R.id.iv_img);
            }

            Glide.with(imageView.getContext()).load(filePath).fitCenter().into(imageView);
        }
    }
}
