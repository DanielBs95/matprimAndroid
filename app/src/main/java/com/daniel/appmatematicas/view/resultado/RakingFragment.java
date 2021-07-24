package com.daniel.appmatematicas.view.resultado;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daniel.appmatematicas.R;
import com.daniel.appmatematicas.rest.ReporteApiService;
import com.daniel.appmatematicas.rest.ReporteRequest;
import com.daniel.appmatematicas.util.Constante;
import com.daniel.appmatematicas.util.CuentosViewHolder;
import com.daniel.appmatematicas.util.RecyclerAdapter;
import com.daniel.appmatematicas.util.Reporte;
import com.daniel.appmatematicas.util.Usuario;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RakingFragment extends Fragment {

    private RecyclerView mRecyclerPrincipal;
    private DatabaseReference mDatabase;
    private TextView txt;
    public RakingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_raking, container, false);
        list(root);
        return root;
    }

    private void list(View root) {

        List<Reporte> movieList =  new ArrayList<>();
        RecyclerView recyclerView;
        RecyclerAdapter recyclerAdapter;
        String ipConfig = Constante.ip_config_;

        ReporteApiService reporteApiService;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ipConfig)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        reporteApiService = retrofit.create(ReporteApiService.class);


        mRecyclerPrincipal = root.findViewById(R.id.resultados_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerPrincipal.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter(getActivity(),movieList);
        mRecyclerPrincipal.setAdapter(recyclerAdapter);

        Call<List<Reporte>> call = reporteApiService.getMovie();



        call.enqueue(new Callback<List<Reporte>>() {
            @Override
            public void onResponse(Call<List<Reporte>> call, Response<List<Reporte>> response) {
                List<Reporte> myList = response.body();
                recyclerAdapter.setMovieList(myList);

            }

            @Override
            public void onFailure(Call<List<Reporte>> call, Throwable t) {
                System.out.println("ca: "+call +" "+ "t: "+t.getMessage());
                System.out.println("ca: "+call +" "+ "t: "+t.getMessage());
            }


        });
    }

    public void showResponse(String response) {
       //Toast.makeText(getActivity(),""+response,Toast.LENGTH_LONG).show();
    }

}