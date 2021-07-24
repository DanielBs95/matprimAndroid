package com.daniel.appmatematicas.view.fragmentos.examen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.daniel.appmatematicas.R;
import com.daniel.appmatematicas.rest.ReporteApiService;
import com.daniel.appmatematicas.rest.TemaResponse;
import com.daniel.appmatematicas.util.Constante;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


public class EstimacionFragment extends Fragment {
    private SharedPreferences prefs = null;
    private String resultadoList;

    private int numeroAleatorioPrincipal;
    private int valorSeleccionado;
    private boolean seleccion;

    private RelativeLayout mPrimeroR;
    private RelativeLayout mSegundoR;
    private RelativeLayout mTerceroR;
    private RelativeLayout mCuartoR;
    private RelativeLayout mQuintoR;
    private RelativeLayout mSextoR;

    private TextView mPrimero;
    private TextView mSegundo;
    private TextView mTercero;
    private TextView mCuarto;
    private TextView mQuinto;
    private TextView mSexto;
    private EditText contador;

    ReporteApiService reporteApiService;

    private List<TemaResponse> temaList = new ArrayList<>();
    private String calificacionOk;
    private String calificacionNoOk;


    private String preguntaPrincipal;
    private TextView txtPregunta;



    public EstimacionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_estimacion_i, container, false);
        prefs = getActivity().getSharedPreferences("com.valdemar.appcognitivo", MODE_PRIVATE);
        resultadoList = prefs.getString("modulo_5","");

            initGenerados(root);
        initConnect(root);
        initTemas(root);
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


    private void initTemas(View root) {
        Call<List<TemaResponse>> call = reporteApiService.listTema();
        call.enqueue(new Callback<List<TemaResponse>>() {
            @Override
            public void onResponse(Call<List<TemaResponse>> call, Response<List<TemaResponse>> response) {
                if(response.body() != null){
                    for(TemaResponse i: response.body()){
                        if(i.getPosicion().equalsIgnoreCase("2")){
                            calificacionOk = i.getPreguntas_tema();
                        }
                        if(i.getPosicion().equalsIgnoreCase("3")){
                            calificacionNoOk = i.getPreguntas_tema();
                        }
                        if(i.getPosicion().equalsIgnoreCase("20")){
                            txtPregunta = root.findViewById(R.id.pregunta);
                            preguntaPrincipal = i.getPreguntas_tema();
                            txtPregunta.setText(preguntaPrincipal);
                        }
                        temaList.add(i);
                    }

                    initGenerados(root);
                }
            }
            @Override
            public void onFailure(Call<List<TemaResponse>> call, Throwable throwable) {

                System.out.println("ErrorPreguntaTema: " + throwable.toString());

            }
        });
    }


    private void initConnect(View root) {

        String ipConfig = Constante.ip_config_;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipConfig)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reporteApiService = retrofit.create(ReporteApiService.class);

    }

    private void initGenerados(View root) {
        Random rd = new Random();
        List<Integer> generados = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            int aleatorio = 0;
            boolean generado = false;
            while (!generado) {
                int posible = rd.nextInt(100);
                if (!generados.contains(posible)) {
                    generados.add(posible);
                    aleatorio = posible;
                    generado = true;
                }
            }
            if (i == 6) {
                initFonts(generados, 15,root);
            }
            System.out.println("Aleatorio: " + aleatorio);
        }
        initSeleccionEmpty(root);

        initClicks(root);


    }
    private void initFonts(List<Integer> generados, int principal, View root) {


        numeroAleatorioPrincipal = principal;

        mPrimero = root.findViewById(R.id.primero);
        mSegundo = root.findViewById(R.id.segundo);


        //encuentra_numero_text = findViewById(R.id.encuentra_numero_text);
        //encuentra_numero_text.setText("Encuentra el número "+ this.numeroAleatorioPrincipal +":");
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
                valorSeleccionado = 80;
                seleccion = true;
                mPrimeroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
                System.out.println("80");
            }
        });
        mSegundoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = 80;
                seleccion = false;
                mSegundoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));
                System.out.println("40");

            }
        });

        Button validar = root.findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(valorSeleccionado != 0){
                    if(!seleccion){
                        prefs.edit().putString("modulo_5", resultadoList+",1").commit();
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_m);

                    }else{
                        prefs.edit().putString("modulo_5", resultadoList+",0").commit();
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_m);

                    }
                }else{
                    Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();
                }

            }
        });

    }



    public void showSnackBar(String msg) {
      //  Toast.makeText(getActivity(),""+msg,Toast.LENGTH_SHORT).show();
    }
}