package com.example.asus.probazaizpit;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PagerAdapter pagerAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        toolbar = findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );

        viewPager = findViewById( R.id.viewpager );
        tabLayout = findViewById( R.id.tablayout );

        tabLayout.addTab( tabLayout.newTab().setText( "Най-красивите котки" ) );
        tabLayout.addTab( tabLayout.newTab().setText( "Моите любими котки" ) );

        tabLayout.setTabGravity( TabLayout.GRAVITY_FILL );

        pagerAdapter = new PagerAdapter
                ( getSupportFragmentManager(), tabLayout.getTabCount() );
        viewPager.setAdapter( pagerAdapter );

        viewPager.addOnPageChangeListener( new TabLayout.TabLayoutOnPageChangeListener( tabLayout ) );
        tabLayout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem( tab.getPosition() );
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );
    }
}

