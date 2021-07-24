package com.daniel.appmatematicas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daniel.appmatematicas.R;

public class UiProtoipoActivity extends AppCompatActivity {
    private Button validar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_protoipo);

        validar = findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  startActivity(new Intent(Menusss.this, MainActivity.class));
                setContentView(R.layout.contar);
                validar = findViewById(R.id.validar);
                validar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //  startActivity(new Intent(Menusss.this, MainActivity.class));
                        setContentView(R.layout.numeros_colores);
                        validar = findViewById(R.id.validar);
                        validar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //  startActivity(new Intent(Menusss.this, MainActivity.class));
                                setContentView(R.layout.resta);
                                validar = findViewById(R.id.validar);
                                validar.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        //  startActivity(new Intent(Menusss.this, MainActivity.class));
                                        setContentView(R.layout.encontrar_numero);
                                        validar = findViewById(R.id.validar);
                                        validar.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                //  startActivity(new Intent(Menusss.this, MainActivity.class));
                                                setContentView(R.layout.activity_resultado_final);

                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                });
            }
        });
    }
}