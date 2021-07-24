package com.daniel.appmatematicas;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daniel.appmatematicas.autenticación.auth.AccessRelato;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    public LogoutFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_logout, container, false);

        LoginManager.getInstance().logOut();
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getActivity(), AccessRelato.class)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));

        return root;
    }
}