package com.chunppo.coininfo.viewmodel.main;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import com.chunppo.coininfo.viewmodel.BaseViewModel;
import io.reactivex.disposables.CompositeDisposable;

public class CoinMarketInfoViewModel extends BaseObservable implements BaseViewModel {

    private static final String TAG = CoinMarketInfoViewModel.class.getName();

    public final ObservableField<String> exchange_img_url = new ObservableField<>();
    public final ObservableField<String> exchange_name = new ObservableField<>();
    public final ObservableField<String> coin_name = new ObservableField<>();

    private CompositeDisposable compositeDisposable;

    public CoinMarketInfoViewModel(String exchange_img_url, String exchange_name, String coin_name) {
        this.exchange_img_url.set(exchange_img_url);
        this.exchange_name.set(exchange_name);
        this.coin_name.set(coin_name);
    }

    @Override
    public void onCreate() {
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onResume() {
        // 중지 후 액티비티가 재활성화가 되는 경우 데이타 세팅을 다시한다.
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        compositeDisposable.dispose();
    }

    private void changeCoinMarketPriceDataSet() {

    }
}