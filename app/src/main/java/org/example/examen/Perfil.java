package org.example.examen;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import android.widget.TextView;

public class Perfil extends Fragment {

    Button btnPerfil;
    Button btnPagina;
    Button btnRegresar;
    TextView nombreUsuario;

    NavController navController;
    private String sharedPrefFile = "org.example.examen";
    SharedPreferences sharedPref;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_perfil, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        btnPerfil = view.findViewById(R.id.botonPerfil);
        btnPagina = view.findViewById(R.id.botonPagina);
        btnRegresar = view.findViewById(R.id.botonRegresar);
        nombreUsuario = view.findViewById(R.id.nombreUsuario);

        sharedPref = requireContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        nombreUsuario.setText(sharedPref.getString("username", "Nombre usuario"));

        btnPerfil.setOnClickListener(v -> {
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            startActivity(intent);
        });
        btnPagina.setOnClickListener(v -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ingenieria.mxl.uabc.mx/pe_lsc/"));
            startActivity(browserIntent);
        });
        btnRegresar.setOnClickListener(v -> navController.navigate(R.id.action_perfil_to_principal));
    }
}
