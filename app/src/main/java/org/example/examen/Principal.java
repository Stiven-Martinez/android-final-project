package org.example.examen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Principal extends Fragment {

    Button perfil;
    Button salir;

    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_principal, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        perfil = view.findViewById(R.id.botonPerfil);
        salir = view.findViewById(R.id.botonSalir);
        perfil.setOnClickListener(v -> navController.navigate(R.id.action_principal_to_perfil));
        salir.setOnClickListener(v -> navController.navigate(R.id.action_principal_to_login));
    }
}