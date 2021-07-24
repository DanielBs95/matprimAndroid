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


public class CalcularIFragment extends Fragment {
    private SharedPreferences prefs = null;

    private Button validar;
    private String resultadoList;
    private EditText primero;

    public CalcularIFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_calcular_i, container, false);
        prefs = getActivity().getSharedPreferences("com.valdemar.appcognitivo", MODE_PRIVATE);
        resultadoList = prefs.getString("modulo_5","");


        initView(root);
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

    private void initView(View root) {
        primero = root.findViewById(R.id.primero);


        validar = root.findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean validador = false;
                String primero_ = primero.getText().toString();
                if(primero_.equalsIgnoreCase("60")){
                    validador = true;
                }


                if(!primero_.isEmpty()) {
                    if(validador == true){
                        prefs.edit().putString("modulo_5", resultadoList+",1").commit();
                        // Toast.makeText(getActivity(),"Buenazo"+primero_,Toast.LENGTH_LONG).show();
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_c);
                    }else{
                        prefs.edit().putString("modulo_5", resultadoList+",0").commit();
                        //  Toast.makeText(getActivity(),"Malo"+primero_,Toast.LENGTH_LONG).show();
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_c);
                    }
                }else{
                    Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}