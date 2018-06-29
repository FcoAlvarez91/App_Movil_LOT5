package montero.app_movil_lot5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;

import montero.app_movil_lot5.Models.Character;
import montero.app_movil_lot5.Models.Profile;
import montero.app_movil_lot5.fragments.HomeFragment;
import montero.app_movil_lot5.fragments.LogInFragment;
import montero.app_movil_lot5.fragments.NewCharacterFragment;
import montero.app_movil_lot5.fragments.ProfileFragment;
import montero.app_movil_lot5.fragments.RollingRulesFragment;
import montero.app_movil_lot5.fragments.StatsRulesFragment;
import montero.app_movil_lot5.fragments.TravelRulesFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    public ArrayList<Character> characters = new ArrayList<>();
    public Profile profile = new Profile();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if(checkSave()) {
            navigationView.setCheckedItem(R.id.nav_profile);
            ProfileFragment pf = new ProfileFragment();
            pf.profile = profile;
            ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
            ft.commit();
        }
        else {
            ft.replace(R.id.content_frame, new HomeFragment()).addToBackStack("MainActivity");
            ft.commit();
        }

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

                            case R.id.rules_stats:
                                mDrawerLayout.closeDrawers();
                                ft.replace(R.id.content_frame,new StatsRulesFragment()).addToBackStack("MainActivity");
                                ft.commit();
                                return true;

                            case R.id.nav_map:
                                mDrawerLayout.closeDrawers();

                                return true;

                            case R.id.nav_profile:
                                mDrawerLayout.closeDrawers();
                                if(profile.check) {
                                    ProfileFragment pf = new ProfileFragment();
                                    pf.profile = profile;
                                    ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
                                    ft.commit();
                                }
                                else{
                                    LogInFragment lif = new LogInFragment();
                                    ft.replace(R.id.content_frame, lif).addToBackStack("MainActivity");
                                    ft.commit();
                                }
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

    public void clickLogIn(View view){
        EditText username = (EditText)findViewById(R.id.username);
        EditText password = (EditText)findViewById(R.id.password);
        profile.username = username.getText().toString();
        profile.password = password.getText().toString();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ProfileFragment pf = new ProfileFragment();
        pf.profile=profile;
        ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
        ft.commit();

        runSave();
    }

    public void clickSave(View view){
        EditText name = (EditText)findViewById(R.id.new_name);
        Spinner spinnerRace = (Spinner) findViewById(R.id.new_race);
        Spinner spinnerArch = (Spinner) findViewById(R.id.new_arch);
        EditText role = (EditText)findViewById(R.id.new_role);
        String newName = name.getText().toString();
        String newRace = spinnerRace.getSelectedItem().toString();
        String newArch = spinnerArch.getSelectedItem().toString();
        String newRole = role.getText().toString();
        Character ph = new Character(newName,newRace,newArch,newRole);
        ph.buildCharacter();
        profile.characters.add(ph);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ProfileFragment pf = new ProfileFragment();
        pf.profile=profile;
        ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
        ft.commit();

        runSave();
    }

    public void clickNew(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        NewCharacterFragment cf = new NewCharacterFragment();
        ft.replace(R.id.content_frame, cf).addToBackStack("MainActivity");
        ft.commit();

        runSave();
    }

    public void clickLogOut(View view){
        SharedPreferences save = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.clear();
        editor.apply();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,new HomeFragment()).addToBackStack("MainActivity");
        ft.commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);

        profile = new Profile("null","null", null);
    }

    public void runSave() {
        SharedPreferences save = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        profile.check=true;
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        editor.putString("Profile", json);
        editor.apply();
    }

    public boolean checkSave(){
        SharedPreferences save = getSharedPreferences("profile", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = save.getString("Profile", "");
        Profile p = gson.fromJson(json, Profile.class);
        if(p!=null) {
            profile = p;
            if(p.characters==null){
                profile.characters = characters;
            }
            return p.check;
        }
        else{
            return false;
        }
    }

}