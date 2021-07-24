package com.daniel.appmatematicas.view.resultado;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.daniel.appmatematicas.R;
import com.daniel.appmatematicas.rest.ReporteApiService;
import com.daniel.appmatematicas.rest.ReporteRequest;
import com.daniel.appmatematicas.util.Constante;
import com.daniel.appmatematicas.util.Usuario;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;


public class Resultado5Fragment extends Fragment {
    private SharedPreferences prefs = null;
    private String resultadoList;
    private TextView resultado;
    TextView resultado_textual;

    public Resultado5Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_resultado, container, false);
        prefs = getActivity().getSharedPreferences("com.valdemar.appcognitivo", MODE_PRIVATE);
        resultadoList = prefs.getString("modulo_5","");
        String[] notas = resultadoList.split(",");
        String[] notas_ = resultadoList.split(",-");
        System.out.println(resultadoList);

        ImageView btnCerrar;

        resultado = root.findViewById(R.id.resultado);
        resultado_textual = root.findViewById(R.id.resultado_textual);

        initNota(notas,20, notas_);
        btnCerrar = root.findViewById(R.id.cerrar);

        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.nav_home);
            }
        });


        //Back pressed Logic for fragment
        root.setFocusableInTouchMode(true);
        root.requestFocus();
        root.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {

                        return true;
                    }
                }
                return false;
            }
        });


        return root;
    }

    private String initNota(String[] notas, int cantidad, String[] notas_) {
        List<String> buenas = new ArrayList<>();

        for (int i = 0; i < cantidad; i++){
            try {
                if(notas[i].replace(",","").equalsIgnoreCase("1")){
                    buenas.add("1");
                }
            }catch (Exception e){

            }

        }

        String motivacion = notas_[1];
        String satisfaccion = notas_[2];

        subirNota(buenas.size(), cantidad+"", motivacion,satisfaccion);

        if(cantidad/2 < buenas.size()){
            resultado_textual.setText("Eres increíble.\n¡Lo lograste!");


        }else{
            resultado_textual.setText("Vamos tú puedes,\nintenta una vez más");


        }
        resultado.setText(buenas.size()+"/"+cantidad);


        prefs.edit().remove("modulo_5").commit();

        return "";
    }


    private void subirNota(int buenas, String cantidad, String motivacion, String satisfaccion) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


        FirebaseDatabase database = FirebaseDatabase.getInstance();

       // Usuario usuario = new Usuario(buenas+"-"+cantidad,user.getDisplayName() );
        //  DatabaseReference myRef = database.getReference("usuarios");
        //  myRef.push().setValue(usuario);

        ReporteRequest obj = new ReporteRequest();
        obj.setNombre(user.getDisplayName());
        obj.setNota(buenas+"");
        obj.setCantidad(cantidad);
        obj.setMotivacion(motivacion);
        obj.setSatisfaccion(satisfaccion);

        ReporteApiService reporteApiService;

        String ipConfig = Constante.ip_config_;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipConfig)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reporteApiService = retrofit.create(ReporteApiService.class);

        reporteApiService.saveNota(obj).enqueue(new Callback<ReporteRequest>() {
            @Override
            public void onResponse(Call<ReporteRequest> call, Response<ReporteRequest> response) {

                if(response.isSuccessful()) {

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


}