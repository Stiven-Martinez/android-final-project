package org.example.examen;

import android.content.Intent;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Fragment {

    EditText username;
    EditText password;
    Button login;
    Button register;

    NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        username = view.findViewById(R.id.username_input);
        password = view.findViewById(R.id.password_input);
        login = view.findViewById(R.id.login_btn);
        register = view.findViewById(R.id.register_btn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(username.getText().toString().equals("Luis")
                && password.getText().toString().equals("123"))
                    navController.navigate(R.id.action_login_to_menu);
                else
                    Toast.makeText(getActivity(), "Datos inválidos", Toast.LENGTH_SHORT).show();
            }});
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ingenieria.mxl.uabc.mx/pe_lsc/"));
                startActivity(browserIntent);
            }});

    }
}