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

public class Registro extends Fragment {

    private EditText mUsername;
    private EditText mPassword;
    private Button mBtnLogin;
    private Button mBtnRegresar;
    private NavController navController;
    private String sharedPrefFile = "org.example.examen";
    private SharedPreferences mPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        mUsername = view.findViewById(R.id.username_input);
        mPassword = view.findViewById(R.id.password_input);
        mBtnLogin = view.findViewById(R.id.login_btn);
        mBtnRegresar = view.findViewById(R.id.register_btn);

        mBtnLogin.setOnClickListener(v -> {
            mPreferences = requireContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = mPreferences.edit();
            editor.putString("username", mUsername.getText().toString().trim());
            editor.putString("password", mPassword.getText().toString().trim());
            editor.putBoolean("newAccount", true);
            editor.putBoolean("logged", false);
            editor.apply();

            navController.navigate(R.id.action_registro_to_login);
        });

        mBtnRegresar.setOnClickListener(v -> {
            navController.navigate(R.id.action_registro_to_login);
        });
    }
}
