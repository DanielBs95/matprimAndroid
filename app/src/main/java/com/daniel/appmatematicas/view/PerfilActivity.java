package com.daniel.appmatematicas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.daniel.appmatematicas.R;
import com.daniel.appmatematicas.autenticación.auth.AccessRelato;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilActivity extends AppCompatActivity {

    private CircleImageView foto;
    private TextView nombre;
    private TextView email;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        Uri url = user.getPhotoUrl();
        foto = findViewById(R.id.foto);
        nombre = findViewById(R.id.nombre);
        email = findViewById(R.id.email);

        button = findViewById(R.id.cerrar_sesion);

        Glide.with(PerfilActivity.this)
                .load(url)
                .thumbnail(Glide.with(getApplicationContext()).load(R.drawable.item_placeholder))
                .into(foto);

        nombre.setText(user.getDisplayName());
        email.setText(user.getEmail());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user != null) {
                    LoginManager.getInstance().logOut();
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(PerfilActivity.this, AccessRelato.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }else {
                    startActivity(new Intent(PerfilActivity.this, AccessRelato.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                }
            }
        });



    }
}