package com.nicolas.serverproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateProducts extends AppCompatActivity {

    EditText stock_update, precio_update, precio_descuento_update, url_update;
    CheckBox is_fav;
    Button update_button;

    String id, name, desc, stock, precio, URL, fav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_products);

        stock_update = findViewById(R.id.stock_update);
        precio_update = findViewById(R.id.precio_update);
        precio_descuento_update = findViewById(R.id.precio_descuento_update);
        url_update = findViewById(R.id.url_update);
        update_button = findViewById(R.id.update_button);
        is_fav = findViewById(R.id.Fav);

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase db = new DataBase(UpdateProducts.this);

                boolean is_favChecked = is_fav.isChecked();
                db.updateData(id, is_favChecked);
            }
        });
        getIntentData();

    }

    void getIntentData() {
        if (getIntent().hasExtra("name") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("author") && getIntent().hasExtra("pages")) {
            //Getting Data from Intent
            name = getIntent().getStringExtra("title");
            desc = getIntent().getStringExtra("author");
            stock = getIntent().getStringExtra("pages");
            precio = getIntent().getStringExtra("pages");
            URL = getIntent().getStringExtra("pages");

            //Setting Intent Data
            title_input.setText(title);
            author_input.setText(author);
            pages_input.setText(pages);

        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }

    }
}