package com.example.kadir.notlar;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.example.kadir.notlar.Database.Notlar;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NotlarRecyclerView extends RecyclerView {

    List<View> notYoksaSaklanacak = Collections.emptyList();
    List<View> notYoksaGosterilecek = Collections.emptyList();

    private AdapterDataObserver observer = new AdapterDataObserver() {
        @Override
        public void onChanged() {
            viewGosterGizle();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            viewGosterGizle();
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, @Nullable Object payload) {
            viewGosterGizle();
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            viewGosterGizle();
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            viewGosterGizle();
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            viewGosterGizle();
        }
    };

    private void viewGosterGizle() {
        if(getAdapter() != null && !notYoksaGosterilecek.isEmpty() && !notYoksaSaklanacak.isEmpty()){

            if(getAdapter().getItemCount() == 0)
            {
                for(View view : notYoksaSaklanacak){
                    view.setVisibility(View.GONE);
                }

                for(View view : notYoksaGosterilecek){
                    view.setVisibility(View.VISIBLE);
                }
            }
            else
            {
                for(View view : notYoksaSaklanacak){
                    view.setVisibility(View.VISIBLE);
                }

                for(View view : notYoksaGosterilecek){
                    view.setVisibility(View.GONE);
                }
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);

        if(adapter != null){
            adapter.registerAdapterDataObserver(observer);
        }

        observer.onChanged();
    }

    public NotlarRecyclerView(Context context) {
        super(context);
    }

    public NotlarRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NotlarRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public void notYoksaSakla(View... saklanacak) {

        notYoksaSaklanacak = Arrays.asList(saklanacak);
    }

    public void notVarsaSakla(View... gosterilecek) {

        notYoksaGosterilecek = Arrays.asList(gosterilecek);
    }
}
