package montero.app_movil_lot5;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import montero.app_movil_lot5.fragments.RollingRulesFragment;
import montero.app_movil_lot5.fragments.HomeFragment;
import montero.app_movil_lot5.fragments.TravelRulesFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,new HomeFragment()).addToBackStack("MainActivity");
        ft.commit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // set item as selected to persist highlight
                        menuItem.setChecked(true);
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

                        switch (menuItem.getItemId()) {

                            case R.id.nav_home:
                                mDrawerLayout.closeDrawers();
                                ft.replace(R.id.content_frame,new HomeFragment()).addToBackStack("MainActivity");
                                ft.commit();
                                return true;

                            case R.id.rules_rolling:
                                mDrawerLayout.closeDrawers();
                                ft.replace(R.id.content_frame,new RollingRulesFragment()).addToBackStack("MainActivity");
                                ft.commit();
                                return true;

                            case R.id.rules_travel:
                                mDrawerLayout.closeDrawers();
                                ft.replace(R.id.content_frame,new TravelRulesFragment()).addToBackStack("MainActivity");
                                ft.commit();
                                return true;

                            case R.id.nav_map:
                                mDrawerLayout.closeDrawers();

                                return true;

                            case R.id.nav_profile:
                                mDrawerLayout.closeDrawers();

                                return true;
                        }

                        mDrawerLayout.closeDrawers();

                        // Add code here to update the UI based on the item selected
                        // For example, swap UI fragments here

                        return true;
                    }
                });


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
