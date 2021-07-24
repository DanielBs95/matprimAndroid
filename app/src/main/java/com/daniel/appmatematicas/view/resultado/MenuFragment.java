package com.daniel.appmatematicas.view.resultado;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daniel.appmatematicas.MenuActivity;
import com.daniel.appmatematicas.R;
import com.daniel.appmatematicas.autenticación.auth.AccessRelato;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;


public class MenuFragment extends Fragment {

    private TextView modulo1;
    private TextView modulo2;
    private TextView modulo3;
    private TextView modulo4;
    private TextView modulo5;
    private TextView salirr;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_menu, container, false);

        initView(root);


        return root;
    }

    private void initView(View root) {
        modulo1 = root.findViewById(R.id.modulo1);
        modulo2 = root.findViewById(R.id.modulo2);
        modulo3 = root.findViewById(R.id.modulo3);
        modulo4 = root.findViewById(R.id.modulo4);
        modulo5 = root.findViewById(R.id.modulo5);
        salirr = root.findViewById(R.id.salirr);

        modulo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_modulo_i);
            }
        });

        modulo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_modulo_i_i);
            }
        });

        modulo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_modulo_i_i_i);
            }
        });

        modulo4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_modulo_i_v);
            }
        });

        modulo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_examen);
            }
        });
        salirr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getActivity(), AccessRelato.class)
                        .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
            }
        });

    }
}