package montero.app_movil_lot5;

import android.arch.persistence.room.Room;
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
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import montero.app_movil_lot5.Models.Character;
import montero.app_movil_lot5.Models.Lot5Database;
import montero.app_movil_lot5.Models.Profile;
import montero.app_movil_lot5.fragments.HomeFragment;
import montero.app_movil_lot5.fragments.LogInFragment;
import montero.app_movil_lot5.fragments.NewCharacterFragment;
import montero.app_movil_lot5.fragments.ProfileFragment;
import montero.app_movil_lot5.fragments.RollingRulesFragment;
import montero.app_movil_lot5.fragments.StatsRulesFragment;
import montero.app_movil_lot5.fragments.TravelRulesFragment;

public class MainActivity extends AppCompatActivity {

    public static Lot5Database lot5Database;
    private static final String DATABASE_NAME = "lot5_db";
    private DrawerLayout mDrawerLayout;
    public List<Character> characters = new ArrayList<>();
    public int profile_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lot5Database = Room.databaseBuilder(getApplicationContext(), Lot5Database.class, DATABASE_NAME).build();
        final String username = "null";
        final String password = "null";
        new Thread(new Runnable() {
            @Override
            public void run() {
                Profile ph = new Profile();
                ph.setUsername(username);
                ph.setPassword(password);
                ph.setId(0);
                lot5Database.daoProfile().insertOnlySingleProfile(ph);
            }
        }) .start();

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
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Profile ph = new Profile();
                    ph = lot5Database.daoProfile().fetchOneProfilebyProfileId(profile_id);
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Profile Loaded!", Toast.LENGTH_LONG).show();
                        }
                    });
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ProfileFragment pf = new ProfileFragment();
                    pf.profile=ph;
                    ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
                    ft.commit();

                    runSave();
                }
            }) .start();
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
                        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

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
                                if(checkSave()) {

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

    public void clickSignUp(View view){
        EditText hold1 = (EditText)findViewById(R.id.username);
        EditText hold2 = (EditText)findViewById(R.id.password);
        final String username = hold1.getText().toString();
        final String password = hold2.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Profile ph = new Profile();
                ph.setUsername(username);
                ph.setPassword(password);
                lot5Database.daoProfile().insertOnlySingleProfile(ph);
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Profile Saved!", Toast.LENGTH_LONG).show();

                    }
                });
                profile_id = ph.getId();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ProfileFragment pf = new ProfileFragment();
                pf.profile=ph;
                ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
                ft.commit();

                runSave();
            }
        }) .start();
    }

    public void clickLogIn(View view){
        EditText hold1 = (EditText)findViewById(R.id.username);
        EditText hold2 = (EditText)findViewById(R.id.password);
        final String username = hold1.getText().toString();
        final String password = hold2.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Profile ph = new Profile();
                ph = lot5Database.daoProfile().fetchOneProfilebyUsername(username);
                if(ph!=null && ph.getPassword().equals(password)) {
                    profile_id = ph.getId();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Profile Loaded!", Toast.LENGTH_LONG).show();
                        }
                    });
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ProfileFragment pf = new ProfileFragment();
                    pf.profile=ph;
                    ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
                    ft.commit();

                    runSave();
                }
                else{
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Invalid Credentials!", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }) .start();
    }

    public void clickSaveCharacter(View view){
        EditText name = (EditText)findViewById(R.id.new_name);
        Spinner spinnerRace = (Spinner) findViewById(R.id.new_race);
        Spinner spinnerArch = (Spinner) findViewById(R.id.new_arch);
        Spinner spinnerStat = (Spinner) findViewById(R.id.new_stat);
        EditText role = (EditText)findViewById(R.id.new_role);
        final String newName = name.getText().toString();
        final String newRace = spinnerRace.getSelectedItem().toString();
        final String newArch = spinnerArch.getSelectedItem().toString();
        final int newStat = spinnerStat.getSelectedItemPosition();
        final String newRole = role.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Character ph = new Character();
                Profile ph2 = lot5Database.daoProfile().fetchOneProfilebyProfileId(profile_id);
                ph.setName(newName);
                ph.setRace(newRace);
                ph.setArch(newArch);
                ph.setRole(newRole);
                if(newStat==0){
                    ph.setStr(1);
                }
                else if(newStat==1){
                    ph.setVit(1);
                }
                else if(newStat==2){
                    ph.setSma(1);
                }
                else if(newStat==3){
                    ph.setDex(1);
                }
                else if(newStat==4){
                    ph.setAgi(1);
                }
                else if(newStat==5){
                    ph.setWis(1);
                }
                else if(newStat==6){
                    ph.setCha(1);
                }
                ph.setProfile_id(profile_id);
                lot5Database.daoCharacter().insertOnlySingleCharacter(ph);
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Character Created!", Toast.LENGTH_LONG).show();
                    }
                });
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ProfileFragment pf = new ProfileFragment();
                pf.profile=ph2;
                ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
                ft.commit();

                runSave();
            }
        }) .start();
    }

    public void clickNewCharacter(View view){
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

        profile_id = 0;
    }

    public void runSave() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences save = getSharedPreferences("profile", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = save.edit();
                editor.putInt("Profile", profile_id);
                editor.apply();
            }
        }) .start();
    }

    public boolean checkSave(){
        SharedPreferences save = getSharedPreferences("profile", Context.MODE_PRIVATE);
        int id = save.getInt("Profile",0);
        if(id!=0) {
            profile_id = id;
            return true;
        }
        else{
            return false;
        }
    }

}