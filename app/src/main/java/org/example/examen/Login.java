package org.example.examen;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Toast;

public class Login extends Fragment {

    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private NavController navController;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "org.example.examen";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
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
                loginUser();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_login_to_registro);
            }
        });
    }

    private void loginUser() {
        String inputUsername = username.getText().toString().trim();
        String inputPassword = password.getText().toString().trim();

        if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
            Toast.makeText(getActivity(), "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mPreferences = requireContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        String registeredUsername = mPreferences.getString("username", null);
        String registeredPassword = mPreferences.getString("password", null);
        Boolean newAccount = mPreferences.getBoolean("newAccount", false);

        if (registeredUsername == null || registeredPassword == null) {
            Toast.makeText(getActivity(), "No hay cuentas registradas", Toast.LENGTH_SHORT).show();
            return;
        }

        if (inputUsername.equals(registeredUsername) && inputPassword.equals(registeredPassword)) {
            SharedPreferences.Editor editor = mPreferences.edit();

            if (newAccount) {
                editor.putBoolean("newAccount", false);
                editor.apply();
                navController.navigate(R.id.action_login_to_menu);
            } else {
                navController.navigate(R.id.action_login_to_principal);
            }
        } else {
            Toast.makeText(getActivity(), "Datos inválidos", Toast.LENGTH_SHORT).show();
        }
    }
}
