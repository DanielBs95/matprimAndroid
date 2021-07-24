package com.daniel.appmatematicas.view.fragmentos;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
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


public class AnimalMedidaFragment extends Fragment {

    //reclaración de variables

    private int numeroAleatorioPrincipal;
    private int valorSeleccionado;
    private boolean seleccion;

    private RelativeLayout mPrimeroR;
    private RelativeLayout mSegundoR;
    private RelativeLayout mTerceroR;


    private TextView mPrimero;
    private TextView mSegundo;
    private TextView mTercero;

    ReporteApiService reporteApiService;

    private List<TemaResponse> temaList = new ArrayList<>();
    private String calificacionOk;
    private String calificacionNoOk;


    public AnimalMedidaFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_animal_medida, container, false);
        //llamada de métodos principales
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

    //lista temas del backend
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

    //inicia conexión
    private void initConnect(View root) {
        String ipConfig = Constante.ip_config_;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipConfig+":8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reporteApiService = retrofit.create(ReporteApiService.class);

    }

    //realiza los generados
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
    //setea las conexiones de xml a class java
    private void initFonts(List<Integer> generados, int principal, View root) {


        numeroAleatorioPrincipal = principal;

        mPrimero = root.findViewById(R.id.primero);
        mSegundo = root.findViewById(R.id.segundo);
        mTercero = root.findViewById(R.id.tercero);



        mPrimero.setText("A");
        mSegundo.setText("B");
        mTercero.setText("C");

        //encuentra_numero_text = findViewById(R.id.encuentra_numero_text);
        //encuentra_numero_text.setText("Encuentra el número "+ this.numeroAleatorioPrincipal +":");
    }

    //realiza una limpieza de selección
    private void initSeleccionEmpty(View root) {

        mPrimeroR = root.findViewById(R.id.seleccion_primero);
        mPrimeroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));

        mSegundoR= root.findViewById(R.id.seleccion_segundo);
        mSegundoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));

        mTerceroR= root.findViewById(R.id.seleccion_tercero);
        mTerceroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.white));




    }

    //Método que se activa para realizar acciones
    private void initClicks(View root) {

        mPrimeroR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt("10");
                seleccion = true;
                mPrimeroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });
        mSegundoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt("2");
                seleccion = true;
                mSegundoR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });
        mTerceroR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSeleccionEmpty(root);
                valorSeleccionado = Integer.parseInt("5");
                seleccion = true;
                mTerceroR.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.gray));

            }
        });


        Button validar = root.findViewById(R.id.validar);
        //validamos los datos ingresados
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!seleccion){

                        Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();

                }else{
                    if(valorSeleccionado == 10){
                        //Toast.makeText(BuscarNumeroActivity.this,"Seleccionó "+valorSeleccionado,Toast.LENGTH_SHORT).show();
                        showSnackBar(calificacionOk);
                        //subirNota(valorSeleccionado, true);

                        //startActivity(new Intent(getActivity(), PerfilActivity.class));
                        // listaCalificacion.add(true);
                    }else{
                        //Toast.makeText(BuscarNumeroActivity.this,"Incorrecto "+valorSeleccionado,Toast.LENGTH_SHORT).show();
                        showSnackBar(calificacionNoOk);
                        subirNota(valorSeleccionado, false);

                    }


                }
            }
        });

    }

    //sube la nota al servidor
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
    //mensaje de texto
    public void showSnackBar(String msg) {
        Toast.makeText(getActivity(),""+msg,Toast.LENGTH_SHORT).show();
    }
}