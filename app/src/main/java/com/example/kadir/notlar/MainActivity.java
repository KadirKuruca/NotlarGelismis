package com.example.kadir.notlar;

import android.app.DialogFragment;
import android.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Toolbar mtoolbar;
    Button btnYeniNot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        setSupportActionBar(mtoolbar);

        btnYeniNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notEkleDialogGoster();
            }
        });

    }

    private void init() {
        mtoolbar = findViewById(R.id.toolbar);
        btnYeniNot = findViewById(R.id.btnNotEkle);
    }

    private void notEkleDialogGoster() {

        FragmentDialogYeniNot dialogYeniNot = new FragmentDialogYeniNot();
        dialogYeniNot.show(getSupportFragmentManager(),"DialogYeniNot");

    }
}
