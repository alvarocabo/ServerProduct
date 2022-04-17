package com.nicolas.serverproduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

public class ver_favoritos extends AppCompatActivity {

    RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_favoritos);

        DataBase db = new DataBase(ver_favoritos.this);
        lista = findViewById(R.id.lista_fav);

        }

    }


