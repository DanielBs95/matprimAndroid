package com.daniel.appmatematicas.view.fragmentos;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.appmatematicas.R;

import static android.content.Context.MODE_PRIVATE;


public class ComparacionIIFragment extends Fragment {
    private SharedPreferences prefs = null;
    private String resultadoList;

    private TextView mPrimero;
    private TextView mSegundo;
    private TextView mTercero;

    private RelativeLayout mPrimeroR;
    private RelativeLayout mSegundoR;
    private RelativeLayout mTerceroR;

    private String valorSeleccionado;
    private boolean seleccion;

    public ComparacionIIFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_comparacion_i_i_, container, false);
        ImageView btnCerrar;


        prefs = getActivity().getSharedPreferences("com.valdemar.appcognitivo", MODE_PRIVATE);
        resultadoList = prefs.getString("modulo_2","");


        mPrimero = root.findViewById(R.id.primero);
        mSegundo = root.findViewById(R.id.segundo);
        mTercero = root.findViewById(R.id.tercero);
        initSeleccionEmpty(root);
        initClicks(root);
        btnCerrar = root.findViewById(R.id.cerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_home);
            }
        });
        return root;
    }


    private void initSeleccionEmpty(View root) {

        mPrimeroR = root.findViewById(R.id.seleccion_primero);
        mPrimeroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));

        mSegundoR= root.findViewById(R.id.seleccion_segundo);
        mSegundoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));

        mTerceroR= root.findViewById(R.id.seleccion_tercero);
        mTerceroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));

    }

    private void initClicks(View root) {

        mPrimeroR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = "71 es mayor que 69";
                seleccion = true;
                mPrimeroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });
        mSegundoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = "69 es mayor que 71";
                seleccion = true;
                mSegundoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });
        mTerceroR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = "71 es menor que 69";
                seleccion = true;
                mTerceroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });

        Button validar = root.findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!seleccion){
                        Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();
                }else{
                    if(valorSeleccionado.equalsIgnoreCase("71 es mayor que 69")){
                        prefs.edit().putString("modulo_2", resultadoList+",1").commit();
                    }else{
                        prefs.edit().putString("modulo_2", resultadoList+",0").commit();
                    }
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_resultado2);
                }
            }
        });

    }

    public void showSnackBar(String msg) {
        Toast.makeText(getActivity(),""+msg,Toast.LENGTH_SHORT).show();
    }

}