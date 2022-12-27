package br.univali.computacao.trabalhom1;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Vector;

public class SecondActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        Franchise franchise = (Franchise) intent.getSerializableExtra("class");

        //Adicionando no Linear Layout
        LinearLayout llRestaurant = (LinearLayout)findViewById(R.id.linear_layout_restaurant);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //Adicionando no Linear as informações
        for (Restaurant restaurants : franchise.getListRestaurants())
        {
            LinearLayout horizontalLayout = new LinearLayout(this);
            horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            horizontalLayout.setOrientation(LinearLayout.VERTICAL);

            llRestaurant.addView(horizontalLayout);

            TextView text = new TextView(this);
            text.setLayoutParams(p);
            text.setText(restaurants.getName() + " | " + restaurants.getAndress() + " | " + restaurants.getCity());

            ImageView image = new ImageView(getApplicationContext());
            image.setImageDrawable(getDrawable((Integer) restaurants.getImage()));
            horizontalLayout.addView(text);
            horizontalLayout.addView(image);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.app_bar_search){
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
