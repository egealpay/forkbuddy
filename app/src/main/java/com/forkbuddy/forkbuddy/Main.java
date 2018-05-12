package com.forkbuddy.forkbuddy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class Main extends AppCompatActivity {

    private ProfileFragment profileFragment;
    private ForkbuddyFragment forkbuddyFragment;
    private HistoryFragment historyFragment;
    private BottomNavigationView bottomNavigationView;
    private ViewPager viewPager;
    MenuItem prevMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);


        //bottomNavigationView.setSelectedItemId(R.id.action_forkbuddy);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_profile:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.action_forkbuddy:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.action_history:
                                viewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        setupViewPager(viewPager);
        viewPager.setCurrentItem(1); // set default fragment to find fork buddy page.

    }

    private void setupViewPager(ViewPager viewPager) // Setup view Pager (Swipe gesture for fragments)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        profileFragment=new ProfileFragment();
        forkbuddyFragment = new ForkbuddyFragment();
        historyFragment = new HistoryFragment();
        adapter.addFragment(profileFragment);
        adapter.addFragment(forkbuddyFragment);
        adapter.addFragment(historyFragment);
        viewPager.setAdapter(adapter);
    }
}
