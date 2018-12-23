package com.spikeysanju98gmail.complaint;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.spikeysanju98gmail.complaint.Adapters.ViewPagerAdapter;
import com.spikeysanju98gmail.complaint.Fragments.CategoryFragment;
import com.spikeysanju98gmail.complaint.Fragments.FavouritesFragment;
import com.spikeysanju98gmail.complaint.Fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        tabLayout = (TabLayout)findViewById(R.id.tablayout_id);
        viewPager = (ViewPager)findViewById(R.id.viewpager_id);
        viewPager.setOffscreenPageLimit(3);

        adapter = new ViewPagerAdapter(getSupportFragmentManager());



        //Fragments Here
        adapter.AddFragment(new HomeFragment(),"");
        adapter.AddFragment(new CategoryFragment(),"");
        adapter.AddFragment(new FavouritesFragment(),"");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_category);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_favorite);




    }

}
