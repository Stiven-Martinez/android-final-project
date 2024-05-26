# Android App Project

## Descripción

Este proyecto es una aplicación de Android desarrollada en Java que proporciona una experiencia de usuario completa desde la introducción, el registro, y el inicio de sesión hasta la navegación por la información del perfil de usuario y el cierre de sesión.

## Características

1. **Pantallas de Onboarding**:
    - Al iniciar la aplicación, se presenta una secuencia introductoria de 2 a 4 pantallas.
    - Las pantallas de onboarding tienen la opción de avanzar a la siguiente pantalla o saltar directamente a la pantalla principal.
  
2. **Pantalla Principal - Login**:
    - Se muestra un formulario de inicio de sesión donde se solicitan credenciales (correo electrónico o nombre de usuario y contraseña).
    - Opción para registrarse si el usuario no tiene una cuenta.
    - Al registrarse, se muestra un formulario para ingresar la información básica del usuario.
    - Las credenciales del usuario se almacenan usando `SharedPreferences` para mantener la sesión incluso después de reiniciar la aplicación.
    - Mensajes de error si las credenciales son incorrectas.
    - Redirección a una pantalla de inicio con una bienvenida personalizada si las credenciales son correctas.
  
3. **Notificaciones**:
    - Al registrarse por primera vez, el usuario recibe una notificación confirmando el registro y ofreciendo una promoción.
    - Al pulsar la notificación, se abre el navegador con detalles de la promoción.

4. **Pantalla de Inicio**:
    - Después del inicio de sesión exitoso, se muestra una pantalla de bienvenida personalizada.
    - Redirección a una pantalla con información relacionada al perfil del usuario.

5. **Perfil de Usuario**:
    - Muestra información personalizada del usuario.
    - Incluye una opción para lanzar una nueva actividad (ej. abrir un sitio web, la cámara del dispositivo, la aplicación de correo, etc.).
    - Botón para cerrar sesión que redirige a la pantalla de inicio de sesión.

## Estructura del Proyecto

```
/src
  /main
    /java
      /com/example/yourapp
        - MainActivity.java
        - OnboardingActivity.java
        - LoginActivity.java
        - RegisterActivity.java
        - HomeActivity.java
        - ProfileActivity.java
        - NotificationReceiver.java
    /res
      /layout
        - activity_main.xml
        - activity_onboarding.xml
        - activity_login.xml
        - activity_register.xml
        - activity_home.xml
        - activity_profile.xml
      /drawable
      /values
        - strings.xml
        - colors.xml
        - styles.xml
```

## Instalación

1. Clonar el repositorio:
   ```sh
   git clone https://github.com/tu-usuario/tu-repo.git
   ```

2. Abrir el proyecto en Android Studio.

3. Compilar y ejecutar en un emulador o dispositivo real.

## Dependencias

Asegúrate de tener las siguientes dependencias en tu archivo `build.gradle`:

```groovy
dependencies {
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'com.google.android.material:material:1.4.0'
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
}
```

## Uso

### Pantallas de Onboarding
- Al iniciar la aplicación, se mostrará una serie de pantallas introductoras con botones para avanzar o saltar.

### Pantalla de Login
- Ingresar credenciales para iniciar sesión.
- Opción para registrarse si no se tiene una cuenta.

### Registro
- Llenar el formulario de registro.
- Las credenciales se guardarán en `SharedPreferences`.

### Inicio de Sesión
- Después de un inicio de sesión exitoso, se muestra una pantalla de bienvenida personalizada.
- Recibir una notificación de bienvenida con una promoción.

### Perfil de Usuario
- Información personalizada del usuario.
- Opciones para lanzar nuevas actividades (ej. abrir sitio web, cámara, correo).
- Botón para cerrar sesión.

## Notificaciones

Utiliza `NotificationManager` para enviar notificaciones. El código de ejemplo para enviar una notificación puede verse así:

```java
NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
    .setSmallIcon(R.drawable.notification_icon)
    .setContentTitle("Registro Exitoso")
    .setContentText("¡Gracias por registrarte! Obtén un 10% de descuento en tu primera compra.")
    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.ejemplo.com"));
PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
builder.setContentIntent(contentIntent);

NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
notificationManager.notify(NOTIFICATION_ID, builder.build());
```

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.

---
