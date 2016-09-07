package com.max.security.adapter.base;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import com.max.security.utils.ViewAnimatorHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/9/5.
 */
public abstract class BaseRecyclerViewAdapter<E> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected List<E> mList;

    private int mDuration = 300;
    private Interpolator mInterpolator = new LinearInterpolator();
    private int mLastPosition = -1;
    private boolean isFirstOnly = true;
    private Map<Integer, onInternalClickListener<E>> canClickItem;

    public BaseRecyclerViewAdapter(List<E> list) {
        this(list, null);
    }

    public BaseRecyclerViewAdapter(List<E> list, Context context) {
        mContext = context;
        mList = list;
    }

    public void add(E e) {
        mList.add(0, e);
        notifyItemInserted(0);
    }

    public void update(E e, int fromPosition, int toPosition) {
        mList.remove(fromPosition);
        mList.add(toPosition, e);
        if(fromPosition == toPosition) {
            notifyItemChanged(fromPosition);
        } else {
            notifyItemRemoved(fromPosition);
            notifyItemInserted(toPosition);
        }
    }

    public void update(E e, int fromPosition) {
        update(e, fromPosition, 0);
    }

    public void update(E e) {
        int fromPosition = mList.indexOf(e);
        update(e, fromPosition);
    }

    public void remove(E e) {
        int position = mList.indexOf(e);
        remove(position);
    }

    public void remove(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void setList(List<E> list) {
        mList.clear();
        mList.addAll(list);
    }

    public List<E> getList() {
        return mList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        addInternalClickListener(holder.itemView, position, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private void addInternalClickListener(final View itemV, final Integer position, final E valuesMap) {
        if (canClickItem != null) {
            for (Integer key : canClickItem.keySet()) {
                View inView = itemV.findViewById(key);
                final onInternalClickListener<E> listener = canClickItem.get(key);
                if (inView != null && listener != null) {
                    inView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(listener != null) {
                                listener.onClickListener(itemV, v, position, valuesMap);
                            }
                        }
                    });

                    inView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            if(listener != null) {
                                listener.onLongClickListener(itemV, v, position, valuesMap);
                            }
                            return true;
                        }
                    });
                }
            }
        }
    }

    public void setOnInViewClickListener(Integer key, onInternalClickListener<E> onClickListener) {
        if (canClickItem == null)
            canClickItem = new HashMap<>();
        canClickItem.put(key, onClickListener);
    }

    public interface onInternalClickListener<T> {
        void onClickListener(View parentV, View v, int position, T values);
        void onLongClickListener(View parentV, View v, int position, T values);
    }

    public void setDuration(int duration) {
        mDuration = duration;
    }

    public void setInterpolator(Interpolator interpolator) {
        mInterpolator = interpolator;
    }

    public void setStartPosition(int start) {
        mLastPosition = start;
    }

    public void setFirstOnly(boolean firstOnly) {
        isFirstOnly = firstOnly;
    }

    protected void animate(RecyclerView.ViewHolder holder, int position){
        if (!isFirstOnly || position > mLastPosition) {
            for (Animator anim : getAnimators(holder.itemView)) {
                anim.setDuration(mDuration).start();
                anim.setInterpolator(mInterpolator);

            }
            mLastPosition = position;
        } else {
            ViewAnimatorHelper.clear(holder.itemView);
        }
    }

    protected abstract Animator[] getAnimators(View view);
}
