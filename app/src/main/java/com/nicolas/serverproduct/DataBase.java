package com.nicolas.serverproduct;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

import androidx.annotation.Nullable;


public class DataBase extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME= "Productos.db";
    private static final int DATABASE_VERSION=4;

    /*Nombre del producto
    Descripción del producto
    Imagen
    Total de unidades disponibles
    precio
        si existe, precio con descuento que destaque sobre el precio regular
        si esta disponible indicarlo, si no, no poner nada*/


    private static final String TABLE_NAME="product";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="name";
    private static final String COLUMN_DESC="description";
    private static final String COLUMN_STOCK="stock";
    private static final String COLUMN_PRICE="price";
    private static final String COLUMN_PRICE_DISC="discount_price";

    private static final String COLUMN_AVALIBLE="available";
    private static final String COLUMN_URL="image_url";

    private static final String COLUMN_FAV="is_fav";


    public DataBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query=
                "CREATE TABLE "  + TABLE_NAME +
                        "("  + COLUMN_ID + "  INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DESC  +" TEXT, "+
                        COLUMN_STOCK + " INTEGER, "+
                        COLUMN_PRICE +" DOUBLE, "+
                        COLUMN_PRICE_DISC  +" DOUBLE, "+
                        COLUMN_AVALIBLE + " BOOLEAN, " +
                        COLUMN_URL + " TEXT, " +
                        COLUMN_FAV + " BOOLEAN );";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " +  TABLE_NAME);
        onCreate(db);
    }


    //funcion de añadir productos nuevos
    void addProduct(String nombre, String desc, int stock, double precio, double precio_disc, boolean avalible, String url, boolean fav){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues  cv = new ContentValues ();

        cv.put(COLUMN_NAME, nombre);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_STOCK, stock);
        cv.put(COLUMN_PRICE, precio);
        cv.put(COLUMN_PRICE_DISC, precio_disc);
        cv.put(COLUMN_AVALIBLE, avalible);
        cv.put(COLUMN_URL, url);
        cv.put(COLUMN_FAV, fav);

        //Control de fallos de inserción

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //EXTRAER DATOS DE LA BASE

    Cursor readAllData(){
        String query = "SELECT * FROM "  +TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    //Solo favoritos
    Cursor viewFav(){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        String request= "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_FAV +  " = 1";

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(request, null);
        }
        return cursor;
    }
    //Insertar los datos de la base dada

    Cursor viewData(String row_id){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        String request= "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ID +  " = " + row_id;

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(request, null);
        }
        return cursor;
    }

    //Cambiar los datos de una fila (Marcar como fav)
    void updateData(String row_id,String nombre, String desc, int stock, double precio, double precio_disc, boolean avalible, String url, boolean fav){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues  cv = new ContentValues ();
        cv.put(COLUMN_NAME, nombre);
        cv.put(COLUMN_DESC, desc);
        cv.put(COLUMN_STOCK, stock);
        cv.put(COLUMN_PRICE, precio);
        cv.put(COLUMN_PRICE_DISC, precio_disc);

        cv.put(COLUMN_AVALIBLE, avalible);
        cv.put(COLUMN_URL, url);
        cv.put(COLUMN_FAV, fav);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            if(fav == true){
                Toast.makeText(context, "Añadido a favoritos", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context, "Sacado de favoritos", Toast.LENGTH_SHORT).show();
            }

        }

    }


}
