package com.example.ericagustianwinardi1822250025;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //Eric Agustian Winardi 1822250025
    EditText nama,upah;
    Spinner risk;
    TextView JHTP, JPP, TOTALP,JHTC, JKK, JKM, JPC, TOTALC,tNama, tUpah, trisk;
    Button tambah;
    double JHTCC, JHTPP, JKKK, JKMM, JPPP, JPCC, TOTALPP, TOTALCC;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        nama = findViewById(R.id.name);
        risk = findViewById(R.id.risk);
        upah = findViewById(R.id.upah);
        tambah = findViewById(R.id.input);
        tNama = findViewById(R.id.tNama);
        tUpah = findViewById(R.id.tUpah);
        JHTC = findViewById(R.id.JHTC);
        JHTP = findViewById(R.id.JHTP);
        JKK = findViewById(R.id.JKK);
        JKM = findViewById(R.id.JKM);
        JPP = findViewById(R.id.JPP);
        JPC = findViewById(R.id.JPC);
        TOTALC = findViewById(R.id.TOTALC);
        TOTALP = findViewById(R.id.TOTALP);
        tambah.setOnClickListener(this);
        showData();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.input) {
                save();
        }
    }
    public Double countJKK(String risk, double jumlah){
        Double JKK = 0.0;
        switch (risk) {
            case "Sangat Rendah":
                JKK = 0.0024;
                break;
            case "Rendah":
                JKK = 0.0054;
                break;
            case "Sedang":
                JKK = 0.0089;
                break;
            case "Tinggi":
                JKK = 0.0127;
                break;
            case "Sangat Tinggi":
                JKK = 0.0174;
                break;
        }
        JKK = JKK * jumlah;
        return JKK;
    }
    public void save(){

            Double upahT = Double.parseDouble(upah.getText().toString());
            JHTPP = 0.02 * upahT;
            JHTCC = 0.037 * upahT;
            JKKK = countJKK(risk.getSelectedItem().toString(), upahT);
            JKMM = 0.003 * upahT;
            JPPP = 0.01 * upahT;
            JPCC = 0.02 * upahT;
            TOTALPP = JHTPP + JPPP;
            TOTALCC = JHTCC + JKKK + JKMM + JPCC;

            pref = getSharedPreferences("pref", MODE_PRIVATE);
            editor = pref.edit();
            editor.putString("nama", nama.getText().toString());
            editor.putString("upah", upah.getText().toString());
            editor.putString("risk", risk.getSelectedItem().toString());
            editor.putString("JHTC", String.valueOf(JHTCC));
            editor.putString("JHTP", String.valueOf(JHTPP));
            editor.putString("JKK", String.valueOf(JKKK));
            editor.putString("JKM", String.valueOf(JKMM));
            editor.putString("JPC", String.valueOf(JPCC));
            editor.putString("JPP", String.valueOf(JPPP));
            editor.putString("totalP", String.valueOf(TOTALPP));
            editor.putString("totalC", String.valueOf(TOTALCC));
            editor.apply();
            Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            showData();
    }

    public void showData(){
        pref = getSharedPreferences("pref", MODE_PRIVATE);
        tNama.setText(pref.getString("nama", ""));
        tUpah.setText(pref.getString("upah", ""));
        JHTP.setText(pref.getString("JHTP", ""));
        JHTC.setText(pref.getString("JHTC", ""));
        JKK.setText(pref.getString("JKK", ""));
        JKM.setText(pref.getString("JKM", ""));
        JPC.setText(pref.getString("JPC", ""));
        JPP.setText(pref.getString("JPP", ""));
        TOTALP.setText(pref.getString("totalP", ""));
        TOTALC.setText(pref.getString("totalC", ""));
    }
}
