package com.example.test.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.test.Fragments.HomeFragment;
import com.example.test.Fragments.Meditate;
import com.example.test.Fragments.Music;
import com.example.test.Fragments.Profile;
import com.example.test.Fragments.Sleep;

public class Viewpager_2 extends FragmentStateAdapter {

    public Viewpager_2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch (position){
            case 0:
                return new HomeFragment();

            case 1:
                return  new Sleep();

            case 2:
                return new Meditate();

            case 3:
                return new Music();
            case 4:
                return new Profile();
            default:
                return new HomeFragment();

        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
