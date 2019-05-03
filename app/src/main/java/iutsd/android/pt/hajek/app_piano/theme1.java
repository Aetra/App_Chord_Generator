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

public class theme1 extends AppCompatActivity {
    ArrayList<String> chordlist =new ArrayList<>();
    private TextView titrethm1;
    private TextView stringTextView;


    private ListView listSad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme1);
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
                JSONArray jsonArray1 = new JSONArray(json);

                for(int i= 0;i< jsonArray1.length();i++)
                {
                    JSONObject obj = jsonArray1.getJSONObject(i);
                    if (obj.getString("name").equals("sad")) {
                        chordlist.add(obj.getString("chords"));
                    }
                }
                /* Rendu de l'array List */
                stringTextView = (TextView)findViewById(R.id.textChord1);
                for(int i=0; i < chordlist.size(); i++){
                    stringTextView.setText(stringTextView.getText() + chordlist.get(i) + " , ");
                }
                listSad = (ListView) findViewById(R.id.listViewthm1);
                System.out.printf(String.valueOf(chordlist));

            }catch (JSONException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

};


