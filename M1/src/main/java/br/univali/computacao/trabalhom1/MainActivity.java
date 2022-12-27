package br.univali.computacao.trabalhom1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    List<Franchise> listFranchise = new ArrayList<>();
    LinearLayout ll;
    LinearLayout.LayoutParams p;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ll = (LinearLayout)findViewById(R.id.linearlayout);
        p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        //Criando os Franchises com os Restaurants
        createAllFrachise();

        //Adicionando no Linear Layout
        addItens();

        buttomConfig();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void addItens() {
        ll.removeAllViews();

        for (Franchise list: listFranchise) {
            TextView text = new TextView(this);

            Button button = new Button(this);
            button.setText("Acessar");
            button.setBackgroundColor(Color.BLUE);

            text.setLayoutParams(p);
            button.setLayoutParams(p);

            text.setText(list.getName() + " | " + list.getDescription());

            ImageView image = new ImageView(getApplicationContext());
            image.setImageDrawable(getDrawable(list.getImage()));
            image.setLayoutParams(p);

            LinearLayout horizontal = new LinearLayout(this);
            horizontal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            horizontal.setOrientation(LinearLayout.VERTICAL);
            horizontal.addView(text);
            horizontal.addView(button);
            horizontal.addView(image);
            ll.addView(horizontal);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentDoce = new Intent(MainActivity.this, SecondActivity.class);
                    intentDoce.putExtra("class", list);
                    startActivity(intentDoce);
                }
            });
        }
    }

    private void createAllFrachise() {
        listFranchise.clear();

        Franchise docesGelados = new Franchise("DocesGelados", "Sorveteria", R.drawable.docesgelados_foreground);
        docesGelados.addRestaurant(new Restaurant("DocesGelados Penha", "Rua Ronaldo", "Penha", R.drawable.docesgelados_foreground));
        docesGelados.addRestaurant(new Restaurant("DocesGelados Piçarras", "Rua Florianópolis", "Piçarras", R.drawable.docesgelados_foreground));
        docesGelados.addRestaurant(new Restaurant("DocesGelados Itajaí", "Rua Ana Clara", "Itajaí", R.drawable.docesgelados_foreground));

        Franchise salgadosHot = new Franchise("SalgadosHot", "Lanchonete", R.drawable.salgadoshot_foreground);
        salgadosHot.addRestaurant(new Restaurant("SalgadosHot Penha", "Rua Ronaldo", "Penha", R.drawable.salgadoshot_foreground));
        salgadosHot.addRestaurant(new Restaurant("SalgadosHot Piçarras", "Rua Florianópolis", "Piçarras", R.drawable.salgadoshot_foreground));
        salgadosHot.addRestaurant(new Restaurant("SalgadosHot Itajaí", "Rua Ana Clara", "Itajaí", R.drawable.salgadoshot_foreground));

        Franchise sushiDelivery = new Franchise("SushiDelivery", "Sushibar", R.drawable.sushidelivery_foreground);
        sushiDelivery.addRestaurant(new Restaurant("SushiDelivery Penha", "Rua Ronaldo", "Penha", R.drawable.sushidelivery_foreground));
        sushiDelivery.addRestaurant(new Restaurant("SushiDelivery Piçarras", "Rua Florianópolis", "Piçarras", R.drawable.sushidelivery_foreground));
        sushiDelivery.addRestaurant(new Restaurant("SushiDelivery Itajaí", "Rua Ana Clara", "Itajaí", R.drawable.sushidelivery_foreground));

        Franchise virtualCafe = new Franchise("VirtualCafe", "Cafeteria", R.drawable.virtualcafe_foreground);
        virtualCafe.addRestaurant(new Restaurant("VirtualCafe Penha", "Rua Ronaldo", "Penha", R.drawable.virtualcafe_foreground));
        virtualCafe.addRestaurant(new Restaurant("VirtualCafe Piçarras", "Rua Florianópolis", "Piçarras", R.drawable.virtualcafe_foreground));
        virtualCafe.addRestaurant(new Restaurant("VirtualCafe Itajaí", "Rua Ana Clara", "Itajaí", R.drawable.virtualcafe_foreground));

        listFranchise.add(docesGelados);
        listFranchise.add(salgadosHot);
        listFranchise.add(sushiDelivery);
        listFranchise.add(virtualCafe);
    }

    private void buttomConfig() {
        Button button = findViewById(R.id.buttomSeach);
        EditText editText = findViewById(R.id.editTextSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                String userText = editText.getText().toString();
                if (!userText.isEmpty())
                    search(userText);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void search(String userText) {
        if (userText.length() == 0) {
            addItens();
        }
        else {
            //Expresão Lambda
            listFranchise = listFranchise.stream().filter(it -> it.getName().toUpperCase().contains(userText.toUpperCase()) || it.getDescription().toUpperCase().contains(userText.toUpperCase())).collect(Collectors.toList());
            addItens();
        }



    }

}