package com.daniel.appmatematicas.view.fragmentos.examen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.daniel.appmatematicas.R;

import static android.content.Context.MODE_PRIVATE;


public class EstimacionLibrosFragment extends Fragment {

    private Button validar;
    private int valorSeleccionado;
    private boolean seleccion;

    private RelativeLayout mPrimeroR;
    private RelativeLayout mSegundoR;
    private SharedPreferences prefs = null;
    private String resultadoList;


    public EstimacionLibrosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_estimacion_libros_i, container, false);

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
        initSeleccionEmpty(root);
        initClicks(root);
        return root;
    }


    private void initSeleccionEmpty(View root) {

        mPrimeroR = root.findViewById(R.id.seleccion_primero);
        mPrimeroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));

        mSegundoR= root.findViewById(R.id.seleccion_segundo);
        mSegundoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));



    }



    private void initClicks(View root) {

        mPrimeroR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt("20");
                seleccion = true;
                mPrimeroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
                System.out.println("30");
            }
        });
        mSegundoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt("21");
                seleccion = true;
                mSegundoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });

        Button validar = root.findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!seleccion){

                        Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();

                }else{
                    if(valorSeleccionado > 20){
                        prefs.edit().putString("modulo_5", resultadoList+",1").commit();
                    }else {
                        prefs.edit().putString("modulo_5", resultadoList + ",0").commit();

                    }

                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_n);

                }
            }
        });
    }

    public void showSnackBar(String msg) {
        //Toast.makeText(getActivity(),""+msg,Toast.LENGTH_SHORT).show();
    }

}