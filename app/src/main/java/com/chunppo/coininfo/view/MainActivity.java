package com.chunppo.coininfo.view;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.chunppo.coininfo.adapter.CoinMarketListRecyclerAdapter;
import com.chunppo.coininfo.databinding.ActivityMainBinding;
import com.chunppo.coininfo.R;
import com.chunppo.coininfo.viewmodel.main.CoinMarketInfoViewModel;
import com.chunppo.coininfo.viewmodel.main.CoinMarketListViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    private CoinMarketListViewModel coinMarketListViewModel;
    static CoinMarketListRecyclerAdapter coinMarketListRecyclerAdapter;
    static RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coinMarketListViewModel = new CoinMarketListViewModel();
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setModel(coinMarketListViewModel);
        binding.setView(this);

        requestManager = Glide.with(this);
        coinMarketListRecyclerAdapter = new CoinMarketListRecyclerAdapter(requestManager);
        // notifyDataSet을 하는 경우 깜빡거리임을 없애기 위해 리스트에 대하 해쉬코드를 저장하도록 설정함
        coinMarketListRecyclerAdapter.setHasStableIds(true);

        binding.mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                coinMarketListRecyclerAdapter.getFilter().filter(newText);

                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        coinMarketListViewModel.onResume();

        Log.i(TAG, "TTTTTT onResume : " + coinMarketListViewModel.coinMarketList.size());
    }

    @BindingAdapter({"items"})
    public static void setCoinMarketList(RecyclerView recyclerView, ArrayList<CoinMarketInfoViewModel> coinMarketList) {
        if (recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(coinMarketListRecyclerAdapter);
        } else {
            coinMarketListRecyclerAdapter = (CoinMarketListRecyclerAdapter) recyclerView.getAdapter();
        }
        Log.i(TAG, "TTTTTT : " + coinMarketList.size());
        coinMarketListRecyclerAdapter.addItems(coinMarketList);
    }
}
