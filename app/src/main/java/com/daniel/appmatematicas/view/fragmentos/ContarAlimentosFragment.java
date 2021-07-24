package com.daniel.appmatematicas.view.fragmentos;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.appmatematicas.R;
import com.daniel.appmatematicas.rest.ReporteApiService;
import com.daniel.appmatematicas.rest.ReporteRequest;
import com.daniel.appmatematicas.rest.TemaResponse;
import com.daniel.appmatematicas.util.Constante;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContarAlimentosFragment extends Fragment {
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



    public ContarAlimentosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_contar_alimentos, container, false);
        ImageView btnCerrar;
        btnCerrar = root.findViewById(R.id.cerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_home);
            }
        });

        initConnect(root);
        initTemas(root);


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
                        if(i.getPosicion().equalsIgnoreCase("14")){
                            txtPregunta = root.findViewById(R.id.pregunta);
                            preguntaPrincipal = i.getPreguntas_tema();
                            //txtPregunta.setText(preguntaPrincipal);
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
                initFonts(generados, 5,root);
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
        mTercero = root.findViewById(R.id.tercero);
        mCuarto = root.findViewById(R.id.cuarto);
        mQuinto = root.findViewById(R.id.quinto);



        mPrimero.setText(generados.get(0).toString());
        mSegundo.setText(principal + "");
        mTercero.setText(generados.get(2).toString());
        mCuarto.setText(generados.get(3).toString());
        mQuinto.setText(generados.get(4).toString());

        //encuentra_numero_text = findViewById(R.id.encuentra_numero_text);
        //encuentra_numero_text.setText("Encuentra el número "+ this.numeroAleatorioPrincipal +":");
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
                    if(valorSeleccionado == numeroAleatorioPrincipal){

                       // showSnackBar(calificacionOk);
                        //subirNota(valorSeleccionado, true);

                    }else{

                        //showSnackBar(calificacionNoOk);
                      //  subirNota(valorSeleccionado, true);

                    }

                }
            }
        });

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

                    System.out.println("--------------------" );
                    System.out.println("---: " +  response.body().getNombre() );
                    System.out.println("---: " +  response.body().getNota() );
                    System.out.println("--------------------" );

                }
            }

            @Override
            public void onFailure(Call<ReporteRequest> call, Throwable t) {
            }
        });


    }

    public void showSnackBar(String msg) {
        Toast.makeText(getActivity(),""+msg,Toast.LENGTH_SHORT).show();
    }


}