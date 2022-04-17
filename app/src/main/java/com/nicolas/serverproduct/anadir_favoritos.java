package com.nicolas.serverproduct;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class anadir_favoritos extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String row_id = getIntent().getStringExtra("ID");
        String name = getIntent().getStringExtra("name");
        String desc = getIntent().getStringExtra("desc");
        String stock = getIntent().getStringExtra("stock");
        String price = getIntent().getStringExtra("price");
        String price_disc = getIntent().getStringExtra("disc_price");

        String Av = getIntent().getStringExtra("Av");
        String URL = getIntent().getStringExtra("URL");


        DataBase db = new DataBase(anadir_favoritos.this);

        String is_fav = getIntent().getStringExtra("is_Fav");
        db.updateData(row_id,name, desc, Integer.valueOf(stock),Double.valueOf(price), Double.valueOf(price_disc),Boolean.valueOf(Av),
                URL, Boolean.valueOf(is_fav));
    }
}
