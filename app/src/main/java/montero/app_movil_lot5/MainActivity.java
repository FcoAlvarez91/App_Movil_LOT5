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

import java.util.ArrayList;
import java.util.List;

import montero.app_movil_lot5.Models.Character;
import montero.app_movil_lot5.Models.Lot5Database;
import montero.app_movil_lot5.Models.Monster;
import montero.app_movil_lot5.Models.Profile;
import montero.app_movil_lot5.fragments.HomeFragment;
import montero.app_movil_lot5.fragments.LogInFragment;
import montero.app_movil_lot5.fragments.BestiaryFragment;
import montero.app_movil_lot5.fragments.NewCharacterFragment;
import montero.app_movil_lot5.fragments.ProfileFragment;
import montero.app_movil_lot5.fragments.RollingRulesFragment;
import montero.app_movil_lot5.fragments.StatsRulesFragment;
import montero.app_movil_lot5.fragments.TravelRulesFragment;

public class MainActivity extends AppCompatActivity {

    public static Lot5Database lot5Database;
    private static final String DATABASE_NAME = "lot5_db";
    private DrawerLayout mDrawerLayout;
    public static int profileID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        lot5Database = Room.databaseBuilder(getApplicationContext(), Lot5Database.class, DATABASE_NAME).build();
        if(checkSave()){
            ProfileFragment pf = new ProfileFragment();
            ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
            ft.commit();
        }
        else {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if(lot5Database.daoMonster().fetchAllMonsters().size()<1) {
                        Monster goblin = new Monster();
                        goblin.setName("Goblin");
                        goblin.setFamily("Humanoid");
                        goblin.setLvl(1);
                        goblin.setAbility("Take the Disengage Action as a Free.");
                        Monster flame = new Monster();
                        flame.setName("Flame Fiend");
                        flame.setFamily("Elemental");
                        flame.setLvl(5);
                        flame.setAbility("Burn enemies on contact.");
                        Monster centaur = new Monster();
                        centaur.setName("Centaur");
                        centaur.setFamily("Monstrosity");
                        centaur.setLvl(3);
                        centaur.setAbility("Runs at double speed.");
                        List<Monster> monsters = new ArrayList<>();
                        monsters.add(goblin);
                        monsters.add(flame);
                        monsters.add(centaur);
                        lot5Database.daoMonster().insertMultipleMonsters(monsters);
                    }
                }
            }).start();

            ft.replace(R.id.content_frame, new HomeFragment()).addToBackStack("MainActivity");
            ft.commit();
        }
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
                                ft.replace(R.id.content_frame,new MapFragment()).addToBackStack("MainActivity");
                                ft.commit();

                                return true;

                            case R.id.nav_monsters:
                                mDrawerLayout.closeDrawers();
                                ft.replace(R.id.content_frame,new BestiaryFragment()).addToBackStack("MainActivity");
                                ft.commit();
                                return true;

                            case R.id.nav_profile:
                                mDrawerLayout.closeDrawers();
                                if(checkSave()) {
                                    ProfileFragment pf = new ProfileFragment();
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

    public void clickSignUp(View view){
        EditText hold1 = (EditText)findViewById(R.id.username);
        EditText hold2 = (EditText)findViewById(R.id.password);
        final String username = hold1.getText().toString();
        final String password = hold2.getText().toString();
        new Thread(new Runnable() {
            @Override
            public void run() {
                if(lot5Database.daoProfile().fetchOneProfilebyUsername(username)==null) {
                    Profile ph = new Profile();
                    ph.setUsername(username);
                    ph.setPassword(password);
                    lot5Database.daoProfile().insertOnlySingleProfile(ph);
                    runSave();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Profile Saved! Please Log-In to Confirm", Toast.LENGTH_LONG).show();
                            runSave();
                        }
                    });
                }
                else{
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Username Taken!", Toast.LENGTH_LONG).show();
                            runSave();
                        }
                    });
                }
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
                Profile ph = lot5Database.daoProfile().fetchOneProfilebyUsername(username);
                if(ph!=null && ph.getPassword().equals(password)) {
                    profileID = ph.getId();
                    MainActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Profile Loaded!", Toast.LENGTH_LONG).show();
                            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                            ProfileFragment pf = new ProfileFragment();
                            ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
                            ft.commit();
                            runSave();
                        }
                    });
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
                ph.setProfile_id(profileID);
                lot5Database.daoCharacter().insertOnlySingleCharacter(ph);
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Character Created!", Toast.LENGTH_LONG).show();
                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ProfileFragment pf = new ProfileFragment();
                        ft.replace(R.id.content_frame, pf).addToBackStack("MainActivity");
                        ft.commit();
                        runSave();
                    }
                });

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
        editor.putInt("profile", -1);
        editor.apply();

        profileID = -1;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame,new HomeFragment()).addToBackStack("MainActivity");
        ft.commit();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_home);
    }

    public void runSave() {
        SharedPreferences save = getSharedPreferences("profile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = save.edit();
        editor.putInt("profile", profileID);
        editor.apply();
    }

    public boolean checkSave(){
        SharedPreferences save = getSharedPreferences("profile", Context.MODE_PRIVATE);
        final int id = save.getInt("profile",-1);
        if(id>=0) {
            profileID = id;
            Toast.makeText(getApplicationContext(), "Profile Loaded!", Toast.LENGTH_LONG).show();
            return true;
        }
        else{
            profileID = -1;
            return false;
        }
    }

}