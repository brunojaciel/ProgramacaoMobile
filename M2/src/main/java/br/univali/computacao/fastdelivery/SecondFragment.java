package br.univali.computacao.fastdelivery;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;

import com.squareup.picasso.Picasso;


import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.List;

import DataAcessObject.PratoDAOREST;
import DataAcessObject.PratoDAOSQLite;
import Entidades.Prato;
import br.univali.computacao.fastdelivery.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {
    List<Prato> cardapio = new ArrayList<>();
    LinearLayout.LayoutParams p;

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        PratoDAOREST pDao = new PratoDAOREST();
        //cardapio = pDao.obterTodosOsPratosXML();

        //if(cardapio.isEmpty()) cardapio = pDao.obterTodosOsPratosJSON();

        if(cardapio.isEmpty()) cardapio = PratoDAOSQLite.lerTodosOsPratos(getContext());

        addPratos(view, binding);
        PratoDAOSQLite.salvarPratos(cardapio, getContext());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void addPratos(View containers, FragmentSecondBinding binding){
        binding.linearlayout.removeAllViews();

        for (Prato list: cardapio) {
            ImageView foto = new ImageView(getContext());
            Picasso.get().load(list.getImageID()).resize(235,235).into(foto);
            foto.setPadding(2,15,2,2);


            LinearLayout horizontal = new LinearLayout(containers.getContext());
            horizontal.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            horizontal.setOrientation(LinearLayout.HORIZONTAL);

            horizontal.addView(foto);

            TextView nome = new TextView(containers.getContext());
            nome.setLayoutParams(p);
            nome.setText(list.getNome());
            nome.setTypeface(nome.getTypeface(), Typeface.BOLD);
            nome.setTextSize(22f);
            nome.setPadding(2,0,2, 0);

            TextView descricao = new TextView(containers.getContext());
            descricao.setLayoutParams(p);
            descricao.setText(list.getDescricao());
            descricao.setTextSize(18f);
            descricao.setPadding(2,2,2, 0);

            TextView preco = new TextView(containers.getContext());
            preco.setLayoutParams(p);
            preco.setText(list.getPreco().equals(BigDecimal.ZERO) ? "A consultar" : "R$ " + list.getPreco());
            //preco.setText("R$ " + String.valueOf(list.getPreco()));
            preco.setTextSize(18f);
            preco.setPadding(2,0,2, 0);

            TextView gluten = new TextView(containers.getContext());
            gluten.setLayoutParams(p);
            if (list.getGluten())
                gluten.setText("Há glúten");
            else
                gluten.setText("Não há glúten");
            gluten.setTextSize(18f);
            gluten.setPadding(2,0,2, 0);

            TextView calorias = new TextView(containers.getContext());
            calorias.setLayoutParams(p);
            calorias.setText(String.valueOf(list.getCalorias() + " Calorias"));
            calorias.setTextSize(18f);
            calorias.setPadding(2,0,2, 0);

            LinearLayout vertical = new LinearLayout(containers.getContext());
            vertical.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            vertical.setOrientation(LinearLayout.VERTICAL);
            vertical.addView(nome);
            vertical.addView(descricao);
            vertical.addView(preco);
            vertical.addView(gluten);
            vertical.addView(calorias);

            horizontal.addView(vertical);
            binding.linearlayout.addView(horizontal);
        }
    }

    }