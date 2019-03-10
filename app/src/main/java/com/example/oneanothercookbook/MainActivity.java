package com.example.oneanothercookbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ReceptBluda> spisokReceptov;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: переделать под загрузку из Интернета

        spisokReceptov = new ArrayList<ReceptBluda>();

        ReceptBluda recept = new ReceptBluda("Омлет","Описание","Описание");
        recept.setFoto("omlet.png");

        ArrayList<IngredientRecepta> ingredients = new ArrayList<IngredientRecepta>();

        IngredientRecepta ingredient = new IngredientRecepta("Яйцо куриное",6.0,"шт.");
        ingredients.add(ingredient);
        ingredient = new IngredientRecepta("Молоко",200.0,"мл");
        ingredients.add(ingredient);
        ingredient = new IngredientRecepta("Соль повареная");
        ingredients.add(ingredient);

        recept.setIngredienty(ingredients);
        spisokReceptov.add(recept);

        ArrayList<String> nazvanie = new ArrayList<String>();

        for (ReceptBluda rb: spisokReceptov){
            nazvanie.add(rb.getNazvanie());
        }

        // находим список
        ListView spisokView = (ListView) findViewById(R.id.spisok);

        // создаем адаптер
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, nazvanie);

        // присваиваем адаптер списку
        spisokView.setAdapter(adapter);

    }

        spisokView.setOnItemSelectedListener(new OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
            Log.d(LOG_TAG, "itemSelect: position = " + position + ", id = " + id);

            Intent i = new Intent(MainActivity.this, ReceptInfo.class);
            i.putExtra("nazvanie",spisokView.getItemPosition(position).toString());
            startActivity(i);
        }
    });
}
