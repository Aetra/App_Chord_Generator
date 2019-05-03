package iutsd.android.pt.hajek.app_piano;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class theme4 extends AppCompatActivity {
    ArrayList<String> chordlist4=new ArrayList<>();
    private TextView stringTextView;
    private ListView listRelaxed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme4);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        get_json();

    }

    /** Recup contenue du fichier chord.json */
    public void get_json()
    {
        String json;
        try {
            InputStream is = getAssets().open("chord.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            JSONArray jsonArray2 = new JSONArray(json);

            for(int i = 0; i< jsonArray2.length(); i++)
            {
                JSONObject obj = jsonArray2.getJSONObject(i);
                if (obj.getString("name").equals("Energic")) {
                    chordlist4.add(obj.getString("chords"));
                }
            }

            /** Rendu de l'array List */
            stringTextView = (TextView)findViewById(R.id.textChord4);
            for(int i=0; i < chordlist4.size(); i++){
                stringTextView.setText(stringTextView.getText() + chordlist4.get(i) + " , ");
            }
            listRelaxed = (ListView) findViewById(R.id.listViewthm2);
            System.out.printf(String.valueOf(chordlist4));


        }catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
