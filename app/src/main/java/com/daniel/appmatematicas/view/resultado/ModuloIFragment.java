package com.daniel.appmatematicas.view.resultado;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daniel.appmatematicas.R;

public class ModuloIFragment extends Fragment {

    private TextView iniciar_i;

    public ModuloIFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_modulo_i, container, false);

        initView(root);
        TextView salir;
        salir = root.findViewById(R.id.salir);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_home);

            }
        });

        return root;
    }

    private void initView(View root) {
        iniciar_i = root.findViewById(R.id.iniciar_i);
        iniciar_i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_mayor_menu);
            }
        });
    }
}