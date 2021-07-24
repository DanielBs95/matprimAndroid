package com.daniel.appmatematicas.view.fragmentos.examen;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.appmatematicas.R;
import com.daniel.appmatematicas.rest.ReporteApiService;
import com.daniel.appmatematicas.rest.ReporteRequest;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class MotivacionFragment extends Fragment {

    private int valorSeleccionado;
    private boolean seleccion;

    private RelativeLayout mPrimeroR;
    private RelativeLayout mSegundoR;
    private RelativeLayout mTerceroR;
    private RelativeLayout mCuartoR;
    private RelativeLayout mQuintoR;

    private TextView mPrimero;
    private TextView mSegundo;
    private TextView mTercero;
    private TextView mCuarto;
    private TextView mQuinto;
    private TextView mSexto;

    private SharedPreferences prefs = null;
    private String resultadoList;

    ReporteApiService reporteApiService;


    public MotivacionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_motivacion, container, false);
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



        mTerceroR= root.findViewById(R.id.seleccion_tercero);
        mTerceroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));

        mCuartoR = root.findViewById(R.id.seleccion_cuarto);

        mCuartoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));

        mQuintoR = root.findViewById(R.id.seleccion_quinto);

        mQuintoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));

        mPrimero = root.findViewById(R.id.primero);
        mSegundo = root.findViewById(R.id.segundo);
        mTercero = root.findViewById(R.id.tercero);
        mCuarto = root.findViewById(R.id.cuarto);
        mQuinto = root.findViewById(R.id.quinto);
        mSexto = root.findViewById(R.id.sexto);
    }
    private void initClicks(View root) {

        mPrimeroR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt(mPrimero.getText().toString());
                seleccion = true;
                mPrimeroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });
        mSegundoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt(mSegundo.getText().toString());
                seleccion = true;
                mSegundoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });
        mTerceroR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt(mTercero.getText().toString());
                seleccion = true;
                mTerceroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });
        mCuartoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt(mCuarto.getText().toString());
                seleccion = true;
                mCuartoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });
        mQuintoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt(mQuinto.getText().toString());
                seleccion = true;
                mQuintoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });

        Button validar = root.findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!seleccion){

                        Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();

                }else{

                        prefs.edit().putString("modulo_5", resultadoList+",-"+valorSeleccionado).commit();

                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_satisfaccion);


                }
            }

            private void subirNota(int valorSeleccionado, Boolean status) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                ReporteRequest obj;

                if(status){
                    obj = new ReporteRequest(user.getEmail(),"Muy bien, nota 20, respuesta: "+valorSeleccionado);
                }else{
                    obj = new ReporteRequest(user.getEmail(),"Que pena, nota 10, respuesta: "+valorSeleccionado);

                }
                reporteApiService.saveNota(obj).enqueue(new Callback<ReporteRequest>() {
                    @Override
                    public void onResponse(Call<ReporteRequest> call, Response<ReporteRequest> response) {

                        if(response.isSuccessful()) {
                            showSnackBar(response.body().toString());
                            Log.i("TAG", "---" + response.body().toString());

                            System.out.println("--------------------" );
                            System.out.println("---: " +  response.body().getNombre() );
                            System.out.println("---: " +  response.body().getNota() );
                            System.out.println("--------------------" );

                        }
                    }

                    @Override
                    public void onFailure(Call<ReporteRequest> call, Throwable t) {
                        Log.e("TAG", "------");
                    }
                });


            }
        });

    }

    public void showSnackBar(String msg) {
        // Toast.makeText(getActivity(),""+msg,Toast.LENGTH_SHORT).show();
    }
}