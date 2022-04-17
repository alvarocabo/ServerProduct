package com.nicolas.serverproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.nicolas.serverproduct.databinding.ActivityMainBinding;

import java.io.IOException;
import java.io.InputStream;

public class viewProducts extends AppCompatActivity {

    TextView name_view, precio_view, description_view, stock_view, price_view;
    ImageView pic_view;
    String id, name, desc, stock, precio, avalible, URL;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);

        name_view = findViewById(R.id.Name_view);
        description_view = findViewById(R.id.desc_view);
        stock_view = findViewById(R.id.stock_view);
        precio_view = findViewById(R.id.precio_view);
        pic_view = findViewById(R.id.pic);

        String row_id = getIntent().getStringExtra("ID");
        DataBase db = new DataBase(viewProducts.this);

        Cursor cursor = db.viewData(row_id);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No hay productos", Toast.LENGTH_SHORT).show();

        } else {

                while (cursor.moveToNext()) {
                    String name = cursor.getString(1);
                    name_view.setText(name);
                    String desc = cursor.getString(2);
                    description_view.setText(desc);
                    String stock = cursor.getString(3);
                    stock_view.setText(stock);
                    String price = cursor.getString(6);
                    precio_view.setText(price);
                    //String URL= cursor.getString(8);
                }
            }

        }
    }
