package com.nicolas.serverproduct;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButtonToggleGroup;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList ID,Name,Desc,Stock, Av, Price, Disc_Price, URL, Fav;


    public Adaptador(Activity activity, Context context, ArrayList ID, ArrayList Name, ArrayList Desc,
                     ArrayList Stock, ArrayList Price, ArrayList Disc_Price,ArrayList Av, ArrayList URL, ArrayList Fav ){
        this.activity=activity;
        this.context=context;
        this.ID=ID;
        this.Name=Name;
        this.Desc=Desc;
        this.Stock=Stock;
        this.Price=Price;
        this.Disc_Price=Disc_Price;
        this.Av=Av;
        this.URL=URL;
        this.Fav=Fav;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.producto_lista, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.name.setText(String.valueOf(Name.get(position)));
        holder.desc.setText(String.valueOf(Desc.get(position)));
        holder.stock.setText(String.valueOf(Stock.get(position)));
        holder.precio_disc.setText(String.valueOf(Price.get(position)));
        //holder.pic.setImageResource(Integer.valueOf(URL.get(position)));
        holder.fav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Intent intent = new Intent(context, anadir_favoritos.class);


                //data to update
                intent.putExtra("id", String.valueOf(ID.get(position)));
                intent.putExtra("name", String.valueOf(Name.get(position)));
                intent.putExtra("desc", String.valueOf(Desc.get(position)));
                intent.putExtra("stock", String.valueOf(Stock.get(position)));
                intent.putExtra("price", String.valueOf(Price.get(position)));
                intent.putExtra("disc_price", String.valueOf(Disc_Price.get(position)));
                intent.putExtra("Av", String.valueOf(Av.get(position)));
                intent.putExtra("URL", String.valueOf(URL.get(position)));

                //
                if(b==true){
                    intent.putExtra("is_Fav", String.valueOf(true));
                    activity.startActivityForResult(intent, 1);
                }else{
                    intent.putExtra("is_Fav", String.valueOf(false));
                    activity.startActivityForResult(intent, 1);}

            }
        });
        holder.mainLayout.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(context, viewProducts.class);
                String id= (String.valueOf(ID.get(position))); //obtenemos la id donde estamos
                intent.putExtra("ID",id);
                activity.startActivityForResult(intent, 2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return Name.size();
        }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, desc, stock, precio_disc;
        ImageView pic;
        CheckBox fav;

        LinearLayout mainLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            desc = itemView.findViewById(R.id.desc);
            stock = itemView.findViewById(R.id.stock);
            precio_disc = itemView.findViewById(R.id.precio_disc);
            pic = itemView.findViewById(R.id.pic);
            fav = itemView.findViewById(R.id.Fav);
            //
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //View
        }

    }
}

