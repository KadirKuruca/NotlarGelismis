package com.example.kadir.notlar;

import android.app.DialogFragment;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.kadir.notlar.Database.NotlarProvider;

public class FragmentDialogYeniNot extends android.support.v4.app.DialogFragment {

    static final Uri CONTENT_URI = NotlarProvider.CONTENT_URI;

    private EditText etNotIcerik;
    private Button btnKaydet;
    private DatePicker datePicker;
    private ImageButton btnCancel;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dialog_yeni_not,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etNotIcerik = view.findViewById(R.id.etNot);
        btnCancel = view.findViewById(R.id.btnCancel);
        btnKaydet = view.findViewById(R.id.btnEkle);
        datePicker = view.findViewById(R.id.dpTarih);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues eklenecek = new ContentValues();
                eklenecek.put("notIcerik",etNotIcerik.getText().toString());

                Uri uri = getActivity().getContentResolver().insert(CONTENT_URI,eklenecek);
                Toast.makeText(getContext(), "Not Eklendi : "+uri, Toast.LENGTH_SHORT).show();
            }
        });

    }


}
