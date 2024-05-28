package org.example.examen;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends Fragment {

    Button next;
    NavController navController;
    TextView nombreUsuario;
    private String sharedPrefFile = "org.example.examen";
    SharedPreferences sharedPref;

    private NotificationManager mNotifyManager;
    private static final int NOTIFICATION_ID = 0;
    private static final String CHANNEL_ID = "notify_me_channel";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        next = view.findViewById(R.id.continuar_btn);
        nombreUsuario = view.findViewById(R.id.nombreUsuario);

        sharedPref = requireContext().getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE);
        nombreUsuario.setText("Bienvenido " + sharedPref.getString("username", "Nombre usuario"));

        // Initialize the NotificationManager
        mNotifyManager = (NotificationManager) requireContext().getSystemService(Context.NOTIFICATION_SERVICE);

        // Create the notification channel
        createNotificationChannel();

        // Set up the next button listener
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_menu_to_principal);
            }
        });

        sendNotification();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification Channel";
            String description = "Channel for notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            mNotifyManager.createNotificationChannel(channel);
        }
    }

    private void sendNotification() {
        Intent notificationIntent = new Intent(requireContext(), MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity(
                requireContext(),
                NOTIFICATION_ID,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(requireContext(), CHANNEL_ID)
                .setContentTitle("¡Bienvenido a Notflix!")
                .setContentText("Disfruta de tu descuento del 30% en películas taquilleras!")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());
    }
}
