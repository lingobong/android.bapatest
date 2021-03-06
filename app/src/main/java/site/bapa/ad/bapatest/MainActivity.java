package site.bapa.ad.bapatest;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import site.bapa.ad.AppState.BapaPermission;
import site.bapa.ad.AppState.BapaStatic;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        if (!BapaPermission.getPermissionGranted_PACKAGE_USAGE_STATS(this)) {
            BapaPermission.allowPermission_PACKAGE_USAGE_STATS(this);
        }
        if (!BapaPermission.getPermissionGranted_Popup(this)) {
            BapaPermission.allowPermission_Popup(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (BapaPermission.getPermissionGranted_PACKAGE_USAGE_STATS(this) && BapaPermission.getPermissionGranted_Popup(this)) {
            Intent intent_type = new Intent(BapaStatic.service_name);
            intent_type.setPackage(getPackageName());
            startService(intent_type);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}