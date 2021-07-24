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


public class EstrategiaFragment extends Fragment {
    private SharedPreferences prefs = null;
    private String resultadoList;
    private Button validar;
    private EditText resultado;
    private EditText resultado2;

    public EstrategiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_estrategia, container, false);
        prefs = getActivity().getSharedPreferences("com.valdemar.appcognitivo", MODE_PRIVATE);
        resultadoList = prefs.getString("modulo_5","");
        ImageView btnCerrar;
        btnCerrar = root.findViewById(R.id.cerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_home);
            }
        });

        validar = root.findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });
        resultado = root.findViewById(R.id.resultado);
        resultado2 = root.findViewById(R.id.resultado2);

        return root;
    }

    private void init() {
        String resultado_= resultado.getText().toString();
        String resultado2_= resultado2.getText().toString();

        if(!resultado_.isEmpty()  && !resultado2_.isEmpty()  ) {

            if(resultado_.equalsIgnoreCase("9") && resultado2_.equalsIgnoreCase("0")){
                prefs.edit().putString("modulo_5", resultadoList+",1").commit();
            }else{
                prefs.edit().putString("modulo_5", resultadoList+",0").commit();
            }
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_o);
        }else{
            Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();
        }

    }
}