package com.chunppo.coininfo.viewmodel.main;

import android.databinding.BaseObservable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.widget.SearchView;
import com.chunppo.coininfo.viewmodel.BaseViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class CoinMarketListViewModel extends BaseObservable implements BaseViewModel {

    private static final String TAG = CoinMarketListViewModel.class.getName();

    public final ObservableArrayList<CoinMarketInfoViewModel> coinMarketList = new ObservableArrayList<>();
    private CompositeDisposable compositeDisposable;


    @Override
    public void onCreate() {
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void
    onResume() {
        coinMarketList.add(new CoinMarketInfoViewModel("url", "name1", "coin_name1"));
        coinMarketList.add(new CoinMarketInfoViewModel("url", "name2", "coin_name2"));
        coinMarketList.add(new CoinMarketInfoViewModel("url", "name3", "coin_name3"));
        coinMarketList.add(new CoinMarketInfoViewModel("url", "name4", "coin_name4"));
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.compositeDisposable.dispose();
    }

}
