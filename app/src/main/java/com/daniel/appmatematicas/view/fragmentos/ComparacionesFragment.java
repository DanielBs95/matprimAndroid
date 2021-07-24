package com.daniel.appmatematicas.view.fragmentos;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.daniel.appmatematicas.R;

import static android.content.Context.MODE_PRIVATE;

public class ComparacionesFragment extends Fragment {
    private SharedPreferences prefs = null;

    private Button validar;

    private EditText primero;
    private EditText segundo;
    private EditText tercero;

    public ComparacionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_comparaciones_i, container, false);
        prefs = getActivity().getSharedPreferences("com.valdemar.appcognitivo", MODE_PRIVATE);

        initView(root);
        ImageView  btnCerrar;
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
        segundo = root.findViewById(R.id.segundo);
        tercero = root.findViewById(R.id.tercero);



        validar = root.findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String primero_ = primero.getText().toString();
                String segundo_ = segundo.getText().toString();
                String tercero_ = tercero.getText().toString();


                Boolean validador = false;
                if(primero_.equalsIgnoreCase("5")){
                    if(segundo_.equalsIgnoreCase("4")){
                        if(tercero_.equalsIgnoreCase("2")){
                            validador = true;
                        }
                    }
                }

                if(!primero_.isEmpty() && !segundo_.isEmpty() && !tercero_.isEmpty()) {
                    if(validador == true){
                        prefs.edit().putString("modulo_4", "1").commit();

                        // Toast.makeText(getActivity(),"Buenazo"+primero_+segundo_+tercero_,Toast.LENGTH_LONG).show();
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_calculadoraI);
                    }else{
                        prefs.edit().putString("modulo_4", "0").commit();

                        // Toast.makeText(getActivity(),"Malo"+primero_+segundo_+tercero_,Toast.LENGTH_LONG).show();
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_calculadoraI);
                    }
                }else{
                    Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}