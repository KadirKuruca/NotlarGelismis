package com.example.kadir.notlar.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.kadir.notlar.R;


public class Divider extends RecyclerView.ItemDecoration {

    private Drawable mDivider;
    private int mOrientation;

    public Divider(Context context, int orientation){

        //mDivider = context.getDrawable(R.drawable.divider.xml);

        mDivider = ContextCompat.getDrawable(context, R.drawable.divider);
        if(orientation != LinearLayoutManager.VERTICAL){
            throw new IllegalArgumentException("Orientation tipi Vertical Olmalıdır.");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {

        if(mOrientation == LinearLayoutManager.VERTICAL){

            drawHorizontalLine(c,parent,state);
        }
    }

    private void drawHorizontalLine(Canvas c, RecyclerView parent, RecyclerView.State state) {

        int sag,sol,yukari,asagi;
        sol = parent.getPaddingLeft();
        sag = parent.getWidth() - parent.getPaddingRight();
        int elemanSayisi = parent.getChildCount();

        for(int i=0;i<elemanSayisi;i++){

            View view = parent.getChildAt(i);
            yukari = view.getTop();
            asagi = yukari+mDivider.getIntrinsicHeight();

            mDivider.setBounds(sol,yukari,sag,asagi);
            mDivider.draw(c);
        }
    }
}
