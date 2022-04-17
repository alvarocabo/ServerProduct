package com.nicolas.serverproduct;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class AddProducts extends AppCompatActivity {
    EditText nombre_input,descripcion_input,stock_input,precio_input,precio_descuento_input,url_input;
    CheckBox  avalible;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_add_products);

        //Inputs del formulario
        nombre_input = findViewById(R.id.nombre_input);
        descripcion_input = findViewById(R.id.descripcion_input);
        stock_input = findViewById(R.id.stock_input);
        precio_input = findViewById(R.id.precio_input);
        precio_descuento_input = findViewById(R.id.precio_descuento_input);
        avalible = findViewById(R.id.avalible_input);
        url_input = findViewById(R.id.url_input);


        //botón
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBase db = new DataBase(AddProducts.this);

                int real_stock= Integer.valueOf(stock_input.getText().toString().trim());
                double price = Double.valueOf(precio_input.getText().toString().trim());
                double discount= Double.valueOf(precio_descuento_input.getText().toString().trim());

                db.addProduct(nombre_input.getText().toString().trim(),
                        descripcion_input.getText().toString().trim(),
                        real_stock,
                        price,
                        discount,
                        avalible.isChecked(),
                        url_input.getText().toString().trim(),
                        false);
            }
        });
    }
}