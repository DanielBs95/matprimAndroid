package com.daniel.appmatematicas.view.fragmentos.examen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.daniel.appmatematicas.R;
import com.daniel.appmatematicas.rest.ReporteApiService;
import com.daniel.appmatematicas.rest.ReporteRequest;
import com.daniel.appmatematicas.rest.TemaResponse;
import com.daniel.appmatematicas.util.Constante;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

public class DecenasFragment extends Fragment {
    private SharedPreferences prefs = null;
    private String resultadoList;
    private EditText mPrimero;
    private EditText mSegundo;
    private int valorUno;
    private int valorDos;

    ReporteApiService reporteApiService;

    private List<TemaResponse> temaList = new ArrayList<>();
    private String calificacionOk;
    private String calificacionNoOk;

    private String preguntaPrincipal;
    private TextView txtPregunta;

    public DecenasFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_decenas, container, false);

        prefs = getActivity().getSharedPreferences("com.valdemar.appcognitivo", MODE_PRIVATE);

        resultadoList = prefs.getString("modulo_5","");
        initConnect(root);
        ImageView btnCerrar;
        btnCerrar = root.findViewById(R.id.cerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_home);
            }
        });
        initTemas(root);
        mPrimero = root.findViewById(R.id.primero);
        mSegundo = root.findViewById(R.id.segundo);
        valorDos = 0;
        valorDos = 0;



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

                        if(i.getPosicion().equalsIgnoreCase("11")){
                            txtPregunta = root.findViewById(R.id.pregunta);
                            preguntaPrincipal = i.getPreguntas_tema();
                           // txtPregunta.setText(preguntaPrincipal);
                        }
                        temaList.add(i);
                    }


                }
            }
            @Override
            public void onFailure(Call<List<TemaResponse>> call, Throwable throwable) {

                System.out.println("ErrorPreguntaTema: " + throwable.toString());

            }
        });
        Button validar = root.findViewById(R.id.validar);
        validar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorUno = Integer.parseInt(mPrimero.getText().toString());
                valorDos = Integer.parseInt(mSegundo.getText().toString());
                if(valorUno != 0){
                    if(valorUno != 0){
                        if(valorUno == 6 && valorDos == 2){
                            prefs.edit().putString("modulo_5", resultadoList+",1").commit();
                        }else{
                            prefs.edit().putString("modulo_5", resultadoList+",0").commit();
                        }
                        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_i);

                    }else{
                        Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getActivity(),"Por favor,  brinde una respuesta.",Toast.LENGTH_LONG).show();
                }
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

    private void subirNota(String valorSeleccionado, Boolean status) {
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
        //Toast.makeText(getActivity(),""+msg,Toast.LENGTH_SHORT).show();
    }

}