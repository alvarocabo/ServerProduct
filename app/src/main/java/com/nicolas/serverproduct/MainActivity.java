package com.nicolas.serverproduct;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    //DataBase access
    private RecyclerView recyclerView;
    private Button btn_add;
    private CheckBox soloFavs;
    //Data
    DataBase db;
    ArrayList<String> ID, Name, Desc, Stock, Price, Disc_Price, Av, URL, Fav;
    //Visualizador de fotos
    private ImageView imageView;
    //Adaptador
    Adaptador adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.lista);
        btn_add = findViewById(R.id.btn_add);
        soloFavs = findViewById(R.id.soloFavs);

        //Añadir productos
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent -> try
                Intent intent = new Intent(MainActivity.this, AddProducts.class);
                startActivity(intent);
            }
        });

        //solo favs


        //Visualizador de datos en el recyclerview
        db = new DataBase(MainActivity.this);
        ID= new ArrayList<>();
        Name = new ArrayList<>();
        Desc = new ArrayList<>();
        Stock = new ArrayList<>();
        Price = new ArrayList<>();
        Disc_Price = new ArrayList<>();
        Av = new ArrayList<>();
        URL = new ArrayList<>();
        Fav=new ArrayList<>();

        storeDataInArrays();



        adaptador = new Adaptador(MainActivity.this,this, ID, Name, Desc,Stock,
                Price, Disc_Price,Av, URL, Fav);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }
         void storeDataInArrays(){
                    Cursor cursor = db.readAllData();
                if (cursor.getCount() == 0) {
                    Toast.makeText(this, "No hay productos", Toast.LENGTH_SHORT).show();
                    //empty_imageview.setVisibility(View.VISIBLE);
                    // no_data.setVisibility(View.VISIBLE);
                } else {
                    while (cursor.moveToNext()) {
                        ID.add(cursor.getString(0));
                        Name.add(cursor.getString(1));
                        Desc.add(cursor.getString(2));
                        Stock.add(cursor.getString(3));
                        Price.add(cursor.getString(4));
                        Disc_Price.add(cursor.getString(5));
                        Av.add(cursor.getString(6));
                        URL.add(cursor.getString(7));
                        Fav.add(cursor.getString(8));

                    }
                    //empty_imageview.setVisibility(View.GONE);
                    //no_data.setVisibility(View.GONE);
                }
        }

    void storeDataInArrays_fav(){
        Cursor cursor = db.viewFav();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay productos", Toast.LENGTH_SHORT).show();
            //empty_imageview.setVisibility(View.VISIBLE);
            // no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                ID.add(cursor.getString(0));
                Name.add(cursor.getString(1));
                Desc.add(cursor.getString(2));
                Stock.add(cursor.getString(3));
                Price.add(cursor.getString(4));
                Disc_Price.add(cursor.getString(5));
                Av.add(cursor.getString(6));
                URL.add(cursor.getString(7));
                Fav.add(cursor.getString(8));

            }
            //empty_imageview.setVisibility(View.GONE);
            //no_data.setVisibility(View.GONE);
        }
    }
    }