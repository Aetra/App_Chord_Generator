package iutsd.android.pt.hajek.app_piano;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /** Composant */
    private Button theme1,theme2,theme3,theme4,bacAsable;
    private TextView titreApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView titreApp=findViewById(R.id.activity_main_greeting_con);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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

    /** Click sur les items du menu */
    public void onClickMenu(View view) {
    }

    /** Navigation au sein du menu theme1 -> theme max */
    public void onClickStartThm1(View view1)
    {
        Button theme1=findViewById(R.id.activity_main_play_btnthm1);
        Intent intent= new Intent(this, theme1.class);
        startActivity(intent);
    }

    public void onClickStartThm2(View view2)
    {
        Button theme2=findViewById(R.id.activity_main_play_btnthm2);
        Intent intent= new Intent(this, theme2.class);
        startActivity(intent);
    }

    public void onClickStartThm3(View view3)
    {
        Button theme3=findViewById(R.id.activity_main_play_btnthm3);
        Intent intent= new Intent(this, theme3.class);
        startActivity(intent);
    }

    public void onClickStartThm4(View view4)
    {
        Button theme4=findViewById(R.id.activity_main_play_btnthm4);
        Intent intent= new Intent(this, theme4.class);
        startActivity(intent);
    }

    public void onClickStartPiano(View view5)
    {
        Button bacAsable=findViewById(R.id.activity_main_play_btnPiano);
        Intent intent= new Intent(this, PianoActivity.class);
        startActivity(intent);
    }
}
