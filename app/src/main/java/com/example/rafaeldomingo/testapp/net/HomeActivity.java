package com.example.rafaeldomingo.testapp.net;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.rafaeldomingo.testapp.DetallePromocion;
import com.example.rafaeldomingo.testapp.R;
import com.example.rafaeldomingo.testapp.adapter.MyAdapter;
import com.example.rafaeldomingo.testapp.data.ItemData;
import com.example.rafaeldomingo.testapp.listener.ItemOnClickHandler;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements ItemOnClickHandler, SearchView.OnQueryTextListener {

    @BindView(R.id.mRecycler)RecyclerView mRecycler;
    @BindView(R.id.mSearchView)SearchView mSearchView;

    public ItemData[] itemDatas;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        //Changing design of recyclerview depending orientation
        GridLayoutManager layoutManager;
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        }
        else{
            layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        }

        mRecycler.setLayoutManager(layoutManager);
        mRecycler.setHasFixedSize(true);
        myAdapter = new MyAdapter(this);
        mRecycler.setAdapter(myAdapter);

        mSearchView.setOnQueryTextListener(this);
        loadData();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        ItemData[] newItems;
        ArrayList<ItemData> arrayList = new ArrayList<>();
        for (int i = 0; i < itemDatas.length; i++) {
            if(itemDatas[i].getTitle().toLowerCase().contains(newText.toLowerCase())){
                arrayList.add(itemDatas[i]);

            }
        }
        newItems = new ItemData[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            newItems[i] = arrayList.get(i);
        }
        myAdapter.setItemData(newItems);
        return false;
    }

    @Override
    public void onClick(ItemData itemData) {
        System.out.println(itemData.getTitle());
        Intent intent = new Intent(this, DetallePromocion.class);
        Bundle bundle = new Bundle();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        itemData.getImageBitmap().compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        itemData.setImage(byteArray);
        bundle.putSerializable("data",itemData);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void loadData(){
        itemDatas = new ItemData[10];

        for (int i = 0; i < 10; i++) {
            itemDatas[i]= new ItemData();

        }


        //region loadign data
        itemDatas[0].setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.promopapajohns));
        itemDatas[0].setTitle("Papa Jhon's");
        itemDatas[0].setDescription("20% en pizzas grandes y extragrandes");
        itemDatas[1].setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promoidea));
        itemDatas[1].setImageHBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promoidea_15x));
        itemDatas[1].setTitle("Idea Interior");
        itemDatas[1].setDescription("Obtén 3% en tu compra total");
        itemDatas[2].setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promoburguerking));
        itemDatas[2].setImageHBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promoburguerking_15x));
        itemDatas[2].setTitle("Burger King");
        itemDatas[2].setDescription("¡Café Americano Gratis!");
        itemDatas[3].setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promobenavides));
        itemDatas[3].setImageHBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promobenavides_15x));
        itemDatas[3].setTitle("Farmacioas Buenavides");
        itemDatas[3].setDescription("10% en medicamentos genéricos");
        itemDatas[4].setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promotizoncito));
        itemDatas[4].setImageHBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promotizoncito_15x));
        itemDatas[4].setTitle("El Tizoncito");
        itemDatas[4].setDescription("2x1 en !Ah Dorados!");
        itemDatas[5].setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promochilis));
        itemDatas[5].setImageHBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promochilis_15x));
        itemDatas[5].setTitle("Chili's");
        itemDatas[5].setDescription("Ahorra 10% en el total de tu Cuenta");
        itemDatas[6].setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promozonafitness));
        itemDatas[6].setImageHBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promozonafitness_15x));
        itemDatas[6].setTitle("Zona Fitnes");
        itemDatas[6].setDescription("Precio especial en tu anualidad");
        itemDatas[7].setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promocinepolis));
        itemDatas[7].setImageHBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promocinepolis_15x));
        itemDatas[7].setTitle("Cinépolis");
        itemDatas[7].setDescription("¡GRATIS una entrada cada mes!");
        itemDatas[8].setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promowingstop));
        itemDatas[8].setImageHBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promowingstop_15x));
        itemDatas[8].setTitle("Wingstop");
        itemDatas[8].setDescription("$50 de descuento en la compra de $250");
        itemDatas[9].setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promoitaliannis));
        itemDatas[9].setImageHBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.promoitaliannis_15x));
        itemDatas[9].setTitle("Italianni's");
        itemDatas[9].setDescription("Ahorra 10% en el total de tu cuenta");
        myAdapter.setItemData(itemDatas);
        //endregion

    }
}
