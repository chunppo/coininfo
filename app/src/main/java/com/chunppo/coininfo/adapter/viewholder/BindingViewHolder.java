package com.chunppo.binance.adapter.viewholder;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class BindingViewHolder<T> extends RecyclerView.ViewHolder {

    private final T binding;

    public BindingViewHolder(View itemView) {
        super(itemView);
        this.binding = (T) DataBindingUtil.bind(itemView);
    }

    public T binding() {
        return binding;
    }
}