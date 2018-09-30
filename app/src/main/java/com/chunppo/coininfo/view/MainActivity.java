package com.chunppo.coininfo.view;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.chunppo.coininfo.databinding.ActivityMainBinding;
import com.chunppo.coininfo.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        super.onCreate(savedInstanceState);
//        usersViewModel = new UsersViewModel(new UserApiRequest());
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        binding.setModel(usersViewModel);
        binding.setView(this);
    }
}
