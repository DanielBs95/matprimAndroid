package com.daniel.appmatematicas.autenticación;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.daniel.appmatematicas.R;
import com.daniel.appmatematicas.view.EncuentraNumeroActivity;
import com.daniel.appmatematicas.view.UiProtoipoActivity;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

public class IdentificateActivity extends AppCompatActivity {
    private SharedPreferences prefs = null;

    private Button btnAcceder;
    private TextInputEditText nombre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identificate);

        prefs = getSharedPreferences("com.valdemar.appcognitivo", MODE_PRIVATE);

        nombre = findViewById(R.id.nombre_principal);

        btnAcceder = findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideSoftKeyboard();
                if(nombre.getText().toString().isEmpty()){
                    showSnackBar("No puede ser vacio");
                    return;
                }
                prefs.edit().putString("prefs_notificacion", nombre.getText().toString()).commit();
                showSnackBar("¡!"+prefs.getString("prefs_notificacion",""));

                startActivity(new Intent(IdentificateActivity.this, EncuentraNumeroActivity.class));
                finish();
            }
        });


    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void showSnackBar(String msg) {
        Snackbar
                .make(findViewById(R.id.container), msg, Snackbar.LENGTH_LONG)
                .show();
    }
}