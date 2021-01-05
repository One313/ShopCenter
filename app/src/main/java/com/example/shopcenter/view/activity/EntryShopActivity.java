package com.example.shopcenter.view.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.example.shopcenter.R;
import com.example.shopcenter.databinding.ActivityEntryShopBinding;

public class EntryShopActivity extends AppCompatActivity {

    private ActivityEntryShopBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_entry_shop);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_container);
        mBinding.bottomNav.setSelectedItemId(R.id.nav_host_fragment_container);
        NavigationUI.setupWithNavController(mBinding.bottomNav, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                if (destination.getId() == R.id.searchFragment) {
                    mBinding.bottomNav.setVisibility(View.GONE);
                } else mBinding.bottomNav.setVisibility(View.VISIBLE);
            }
        });
    }
}
