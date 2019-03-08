package my_fisrt_project.test2;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import my_fisrt_project.test2.fregment.first;
import my_fisrt_project.test2.fregment.second;
import my_fisrt_project.test2.fregment.third;

public class mainFragment extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    first one = new first();
    second two = new second();
    third tree = new third();
    long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (((MyApplication) this.getApplication()).getSomeVariable()==1)
            setTheme(R.style.Custom1);
        else
            setTheme(R.style.Custom);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);

        setviewpager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        Toolbar toolbar=(Toolbar)findViewById(R.id.main);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.exite){
                new AlertDialog.Builder(mainFragment.this)
                        .setMessage("آیا میخواهید از نرم افزار خارج شوید؟")
                        .setCancelable(true)
                        .setPositiveButton("بله", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences sharedPreference= PreferenceManager.getDefaultSharedPreferences(mainFragment.this);
                                SharedPreferences.Editor editor = sharedPreference.edit();
                                editor.remove("email");
                                editor.remove("password");
                                editor.remove("token");
                                editor.commit();
                                finish();
                            }
                        })
                        .setNegativeButton("خیر", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .show();
        }else {
            if (((MyApplication) this.getApplication()).getSomeVariable()==1)
                ((MyApplication)this.getApplication()).setSomeVariable(0);
            else
                ((MyApplication)this.getApplication()).setSomeVariable(1);

            Intent homeintent = new Intent(mainFragment.this,MainActivity.class);
            startActivity(homeintent);
            mainFragment.this.finish();



        }

        return true;
    }

    @Override
    public void onBackPressed() {
        if (back_pressed + 1000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(mainFragment.this,"برای خروج یک بار دیگر دکمه ی back را بزنید",Toast.LENGTH_SHORT).show();
        }
        back_pressed = System.currentTimeMillis();
    }

    public void setviewpager(ViewPager viewPager) {
        //Util.ViewPagerAdapter adapter=new Util.ViewPagerAdapter(getSupportFragmentManager());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(one, "کنترل");
        adapter.addFragment(two, "سناریوها");
        adapter.addFragment(tree, "تنظیمات و داده ها");
        viewPager.setAdapter(adapter);

    }
}
