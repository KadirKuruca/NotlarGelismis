package com.example.kadir.notlar;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.kadir.notlar.Database.Notlar;
import com.example.kadir.notlar.Database.NotlarProvider;
import com.example.kadir.notlar.adapter.Divider;
import com.example.kadir.notlar.adapter.NotlarListesiAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final Uri CONTENT_URI = NotlarProvider.CONTENT_URI;

    Toolbar mtoolbar;
    Button btnYeniNot;
    NotlarRecyclerView recyclerNotlar;
    NotlarListesiAdapter notlarListesiAdapter;
    ArrayList<Notlar> tumNotlar= new ArrayList<>();
    Notlar gecici = null;
    ImageView ivLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setSupportActionBar(mtoolbar);

        recyclerNotlar = findViewById(R.id.recyclerView);
        recyclerNotlar.addItemDecoration(new Divider(this,LinearLayoutManager.VERTICAL));

        btnYeniNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notEkleDialogGoster();
            }
        });
        recyclerNotlar.notYoksaSakla(mtoolbar);
        recyclerNotlar.notVarsaSakla(ivLogo);

        notGuncelle();
    }

    public void notGuncelle() {
        tumNotlar.clear();
        tumNotlariGetir();
        notlarListesiAdapter = new NotlarListesiAdapter(this,tumNotlar);
        recyclerNotlar.setAdapter(notlarListesiAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerNotlar.setLayoutManager(manager);
    }

    private void init() {
        mtoolbar = findViewById(R.id.toolbar);
        btnYeniNot = findViewById(R.id.btnNotEkle);
        ivLogo = findViewById(R.id.imgLogo);
    }

    private void notEkleDialogGoster() {

        FragmentDialogYeniNot dialogYeniNot = new FragmentDialogYeniNot();
        dialogYeniNot.show(getSupportFragmentManager(),"DialogYeniNot");

    }

    private ArrayList<Notlar> tumNotlariGetir(){

        Cursor cursor = getContentResolver().query(CONTENT_URI,new String[]{"id","notIcerik"},null,null,null);

        if(cursor != null){

            while(cursor.moveToNext()){
                gecici = new Notlar();
                //Toast.makeText(this, ""+cursor.getString(1), Toast.LENGTH_SHORT).show();
                gecici.setId(cursor.getInt(cursor.getColumnIndex("id")));
                gecici.setNotIcerik(cursor.getString(cursor.getColumnIndex("notIcerik")));
                tumNotlar.add(gecici);
            }
        }

        return tumNotlar;
    }
}
