package uz.pop.uzbekkoreyssuzlashgich;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import dbhelper.DBHelper;
import uz.pop.uzbekkoreyssuzlashgich.adapter.CategoryAdapter;
import uz.pop.uzbekkoreyssuzlashgich.grammar.test.TestCategoryList;
import uz.pop.uzbekkoreyssuzlashgich.grammar.alphabet.Alphabet;
import uz.pop.uzbekkoreyssuzlashgich.grammar.tezAyt.TezAytish;
import uz.pop.uzbekkoreyssuzlashgich.grammar.topishmoq.Topishmoq;
import uz.pop.uzbekkoreyssuzlashgich.model.Category;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ListView listView;
    Activity context;
    DBHelper dbHelper;

//
    private static final int TIME_DELAY = 2000;
    private  long back_pressed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listView = findViewById(R.id.list_view);
        context = this;
        dbHelper = new DBHelper(context);
        showAllCategories();

        chooseCategory();

       // dbHelper.creatDB();
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.menu_gramma){
                  dialogMenu();
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void dialogMenu(){
        Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_alert);
        dialog.setTitle("Bo'limni tanlang...");
        dialog.show();

        final LinearLayout linearAlphabet = dialog.findViewById(R.id.liner_alphabet);
        final LinearLayout linearTopishmoq = dialog.findViewById(R.id.liner_topishmoq);
        final LinearLayout linearTezAyt = dialog.findViewById(R.id.liner_tezAyt);
        final LinearLayout linearTest = dialog.findViewById(R.id.liner_test);

        final Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.my_animation);
        linearAlphabet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Alphabet.class);
                startActivity(intent);
                finish();

                linearAlphabet.startAnimation(animation);

            }
        });

        linearTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestCategoryList.class);
                startActivity(intent);
                linearTest.startAnimation(animation);
            }
        });
        linearTezAyt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TezAytish.class);
                startActivity(intent);
                linearTezAyt.startAnimation(animation);

            }
        });

        linearTopishmoq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Topishmoq.class);
                startActivity(intent);
                linearTopishmoq.startAnimation(animation);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if (back_pressed + TIME_DELAY > System.currentTimeMillis()){
            super.onBackPressed();
        }else {
            Toast.makeText(this, getResources().getString(R.string.ilova_chiq_uchun), Toast.LENGTH_SHORT).show();
        }

        back_pressed = System.currentTimeMillis();
    }
    private void showAllCategories(){
        dbHelper.creatDB();
        CategoryAdapter adapter = new CategoryAdapter(context, dbHelper.allCategories());
        listView.setAdapter(adapter);
    }
    private void chooseCategory(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Category category = (Category) parent.getItemAtPosition(position);
                Intent intent = new Intent(context, WordsList.class);
                intent.putExtra("categoryId", category.getId());
                //Animatsiya bn o'tish
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(context);
                startActivity(intent, options.toBundle());
            }
        });
    }
}
