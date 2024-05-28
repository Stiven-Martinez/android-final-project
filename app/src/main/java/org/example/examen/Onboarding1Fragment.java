package org.example.examen;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Onboarding1Fragment extends Fragment {

    Button botonSiguiente;
    Button botonSkip;
    NavController navController;
    private SharedPreferences mPreferences;
    private String sharedPrefFile = "org.example.examen";

    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_onboarding1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);

        mPreferences = requireContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        String registeredUsername = mPreferences.getString("username", null);

        if (registeredUsername != null) {
            navController.navigate(R.id.action_onboarding1Fragment_to_login);
        } else {
            botonSiguiente = view.findViewById(R.id.botonSiguiente);
            botonSiguiente.setOnClickListener(view1 -> navController.navigate(R.id.action_onboarding1Fragment_to_onboarding2Fragment));
            botonSkip = view.findViewById(R.id.botonSkip);
            botonSkip.setOnClickListener(view2 -> navController.navigate(R.id.action_onboarding1Fragment_to_login));
            checkNotificationPermission();
        }
    }

    private void checkNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13 and above
            if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                // Request the permission
                ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_NOTIFICATION_PERMISSION);
            }
        }
    }
}
