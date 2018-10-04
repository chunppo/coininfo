package com.chunppo.coininfo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.Toast;
import com.bumptech.glide.RequestManager;
import com.chunppo.coininfo.R;
import com.chunppo.coininfo.adapter.viewholder.BindingViewHolder;
import com.chunppo.coininfo.databinding.RvMainItemBinding;
import com.chunppo.coininfo.viewmodel.main.CoinMarketInfoViewModel;
import jp.wasabeef.glide.transformations.CropCircleTransformation;

import java.util.ArrayList;
import java.util.List;

public class CoinMarketListRecyclerAdapter extends RecyclerView.Adapter<BindingViewHolder<RvMainItemBinding>> implements Filterable {

    private static final String TAG = CoinMarketListRecyclerAdapter.class.getName();

    @SuppressLint("StaticFieldLeak")
    private static RequestManager requestManager;
    private ArrayList<CoinMarketInfoViewModel> coinMarketList = new ArrayList<>();
    private ArrayList<CoinMarketInfoViewModel> coinMarketListCopy = new ArrayList<>();
    private Context context;
    ValueFilter valueFilter;

    public CoinMarketListRecyclerAdapter(RequestManager requestManager) {
        CoinMarketListRecyclerAdapter.requestManager = requestManager;
    }

    @NonNull
    @Override
    public BindingViewHolder<RvMainItemBinding> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.rv_main_item, parent, false);
        return new BindingViewHolder<>(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder<RvMainItemBinding> holder, int position) {
        holder.binding().setModel(coinMarketList.get(position));
        holder.binding().setListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final UserDialog userDialog = new UserDialog(context, String.valueOf(v.getTag()));
//                userDialog.setOnShowListener(new DialogInterface.OnShowListener() {
//                    @Override
//                    public void onShow(DialogInterface dialog) {
//                    }
//                });
//                userDialog.show();

                Toast.makeText(context, "TEST", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public long getItemId(int posotion) {
        return posotion;
    }

    @Override
    public int getItemCount() {
        return coinMarketList.size();
    }

    public void addItems(ArrayList<CoinMarketInfoViewModel> coinMarketList) {
        this.coinMarketList.addAll(coinMarketList);
        this.coinMarketListCopy.addAll(coinMarketList);
        notifyItemRangeInserted(0, this.coinMarketList.size() - 1);
    }

    @BindingAdapter({"imageUrl", "error"})
    public static void loadImage(ImageView avartar_url, String url, Drawable errorDrawable) {
        requestManager.load(url)
                .error(errorDrawable)
                .bitmapTransform(new CropCircleTransformation(avartar_url.getContext()))
                .into(avartar_url);
    }

    public void filter(String text) {
        if(text.isEmpty()){
            this.coinMarketList.clear();
            this.coinMarketList.addAll(this.coinMarketListCopy);
        } else{
            ArrayList<CoinMarketInfoViewModel> result = new ArrayList<>();
            text = text.toLowerCase();
            for(CoinMarketInfoViewModel item: coinMarketListCopy){
                //match by name or phone
                if(item.coin_name.get().toLowerCase().contains(text) || item.coin_name.get().toLowerCase().contains(text)){
                    result.add(item);
                }
            }
            this.coinMarketList.clear();
            this.coinMarketList.addAll(result);
        }
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }

    private class ValueFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                List<CoinMarketInfoViewModel> filterList = new ArrayList<>();
                for (int i = 0; i < coinMarketListCopy.size(); i++) {
                    if ((coinMarketListCopy.get(i).coin_name.get().toUpperCase()).contains(constraint.toString().toUpperCase())) {
                        filterList.add(coinMarketListCopy.get(i));
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = coinMarketListCopy.size();
                results.values = coinMarketListCopy;
            }
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {
            coinMarketList = (ArrayList<CoinMarketInfoViewModel>) results.values;
            notifyDataSetChanged();
        }

    }

}
