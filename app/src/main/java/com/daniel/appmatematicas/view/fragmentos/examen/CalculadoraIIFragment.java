package com.daniel.appmatematicas.view.fragmentos.examen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.daniel.appmatematicas.R;

import static android.content.Context.MODE_PRIVATE;


public class CalculadoraIIFragment extends Fragment {

    private SharedPreferences prefs = null;

    private Button validar;
    private EditText primero;

    public CalculadoraIIFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_calculadora_i_i, container, false);
        prefs = getActivity().getSharedPreferences("com.valdemar.appcognitivo", MODE_PRIVATE);
        validar = root.findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                primero = root.findViewById(R.id.primero);
                String primero_ = primero.getText().toString();

                if(!primero_.isEmpty()) {
                    if(primero_.equalsIgnoreCase("50")){
                        prefs.edit().putString("modulo_5", "1").commit();
                        //    Toast.makeText(getActivity(),"Buenazo"+primero_,Toast.LENGTH_LONG).show();
                    }else{
                        prefs.edit().putString("modulo_5", "0").commit();

                    }
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_b);

                }else{
                    Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();
                }

            }
        });


        ImageView btnCerrar;
        btnCerrar = root.findViewById(R.id.cerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_home);
            }
        });

        return root;
    }
}