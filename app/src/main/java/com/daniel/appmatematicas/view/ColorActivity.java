package com.daniel.appmatematicas.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daniel.appmatematicas.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ColorActivity extends AppCompatActivity {
    private int numeroAleatorioPrincipal;
    private int valorSeleccionado;
    private boolean seleccion;

    private RelativeLayout mPrimeroR;
    private RelativeLayout mSegundoR;
    private RelativeLayout mTerceroR;
    private RelativeLayout mCuartoR;
    private RelativeLayout mQuintoR;
    private RelativeLayout mSextoR;

    private TextView mPrimero;
    private TextView mSegundo;
    private TextView mTercero;
    private TextView mCuarto;
    private TextView mQuinto;
    private TextView mSexto;
    private EditText contador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contar);

        initGenerados();
    }

    private void initGenerados() {
        Random rd = new Random();
        List<Integer> generados = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            int aleatorio = 0;
            boolean generado = false;
            while (!generado) {
                int posible = rd.nextInt(100);
                if (!generados.contains(posible)) {
                    generados.add(posible);
                    aleatorio = posible;
                    generado = true;
                }
            }
            if (i == 6) {
                initFonts(generados, generados.get(2));
            }
            System.out.println("Aleatorio: " + aleatorio);
        }
        initSeleccionEmpty();

        initClicks();


    }
    private void initFonts(List<Integer> generados, int a) {


        numeroAleatorioPrincipal = 15;

        mPrimero = findViewById(R.id.primero);
        mSegundo = findViewById(R.id.segundo);
        mTercero = findViewById(R.id.tercero);
        mCuarto = findViewById(R.id.cuarto);
        mQuinto = findViewById(R.id.quinto);



        mPrimero.setText(generados.get(0).toString());
        mSegundo.setText(generados.get(1).toString());
        mTercero.setText(generados.get(2).toString());
        mCuarto.setText(generados.get(3).toString());
        mQuinto.setText(generados.get(4).toString());

        //encuentra_numero_text = findViewById(R.id.encuentra_numero_text);
        //encuentra_numero_text.setText("Encuentra el número "+ this.numeroAleatorioPrincipal +":");
    }


    private void initSeleccionEmpty() {

        mPrimeroR = findViewById(R.id.seleccion_primero);
        mPrimeroR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.white));

        mSegundoR= findViewById(R.id.seleccion_segundo);
        mSegundoR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.white));

        mTerceroR= findViewById(R.id.seleccion_tercero);
        mTerceroR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.white));

        mCuartoR = findViewById(R.id.seleccion_cuarto);

        mCuartoR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.white));

        mQuintoR = findViewById(R.id.seleccion_quinto);

        mQuintoR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.white));



    }

    private void initClicks() {

        mPrimeroR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty();
                valorSeleccionado = Integer.parseInt(mPrimero.getText().toString());
                seleccion = true;
                mPrimeroR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.gray));

            }
        });
        mSegundoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty();
                valorSeleccionado = Integer.parseInt(mSegundo.getText().toString());
                seleccion = true;
                mSegundoR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.gray));

            }
        });
        mTerceroR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty();
                valorSeleccionado = Integer.parseInt(mTercero.getText().toString());
                seleccion = true;
                mTerceroR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.gray));

            }
        });
        mCuartoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty();
                valorSeleccionado = Integer.parseInt(mCuarto.getText().toString());
                seleccion = true;
                mCuartoR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.gray));

            }
        });
        mQuintoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty();
                valorSeleccionado = Integer.parseInt(mQuinto.getText().toString());
                seleccion = true;
                mQuintoR.setBackgroundColor(ContextCompat.getColor(ColorActivity.this, R.color.gray));

            }
        });

        Button validar = findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!seleccion){
                    //Toast.makeText(BuscarNumeroActivity.this,"Por favor seleccione una opcción.",Toast.LENGTH_LONG).show();
                    showSnackBar("¡Por favor seleccione una opcción valida!");
                }else{
                    if(valorSeleccionado == numeroAleatorioPrincipal){
                        //Toast.makeText(BuscarNumeroActivity.this,"Seleccionó "+valorSeleccionado,Toast.LENGTH_SHORT).show();
                        showSnackBar("¡Muy bien!");
                        startActivity(new Intent(ColorActivity.this, PerfilActivity.class));
                        // listaCalificacion.add(true);
                    }else{
                        //Toast.makeText(BuscarNumeroActivity.this,"Incorrecto "+valorSeleccionado,Toast.LENGTH_SHORT).show();
                        showSnackBar("¡Oh no fallaste!");
                        //listaCalificacion.add(false);
                        startActivity(new Intent(ColorActivity.this, PerfilActivity.class));

                    }

                   /* contador = findViewById(R.id.contador);
                    int contador_ = Integer.parseInt(contador.getText().toString());

                    iniciarNuevamente();
                    int contador2 = contador_+1;
                    if(contador2 > 9){
                        prefs.edit().putString("prefs_puntaje", listaCalificacion.toString()).commit();
                        showSnackBar(listaCalificacion.toString());
                        startActivity(new Intent(BuscarNumeroActivity.this, ResultadoFinal.class));
                        finish();
                    }
                    contador.setText(contador2+ "");

                    */
                }
            }
        });

    }

    public void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.container), msg, Snackbar.LENGTH_LONG)
                .show();
    }


}