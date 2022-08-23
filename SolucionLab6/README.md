# Solución lab 6

Este proyecto contiene la solución del lab 6

## Bottom Navigation Bar

La aplicación utiliza un bottom navigation bar para su navegación. Dado a que el objetivo del app era hacer un "clon" de spotify, lo mejor era cambiar el color del nav bar y ponerlo oscuro. Para lograr esto, se siguieron los pasos (definidos en la guía de Material Desgin)[https://material.io/components/bottom-navigation/android#theming-a-bottom-navigation-bar].

### Paso 1

Lo primero que se hizo fue crear un archivo llamado `styles.xml` dentro de la carpeta `res/values`.

### Paso 2

En este archivo, creamos 2 estilos. El primer estilo fue creado para hacer un "override" de los colores. `colorPrimary` sirve para el color principal del nav bar, y `colorOnPrimary` para el color de los íconos en su estado seleccionado. Android se hace cargo de ponerles color cuando no están seleccionados.
```xml
    <style name="Theme.SolucionLab6.BottomNavigationView" parent="">
        <item name="colorPrimary">@color/dark_background</item>
        <item name="colorOnPrimary">@color/white</item>
    </style>
```

Luego se creó otro estilo, el cual será el estilo como tal que utilizará nuestro Nav Bar. Lo que hicimos aquí fue asignar nuestro estilo creado anteriormente como el `materialThemeOverlay`.
```xml
    <style name="Widget.App.BottomNavigationView" parent="Widget.MaterialComponents.BottomNavigationView.Colored">
        <item name="materialThemeOverlay">@style/Theme.SolucionLab6.BottomNavigationView</item>
    </style>
```

### Paso 3

Si corremos el app, nuestra Nav Bar se sigue viendo igual:
![image](https://user-images.githubusercontent.com/13813905/186285896-b13b74a7-7c34-4437-8719-f0bdf3376d31.png)

Para cambiar esto, en el archivo `themes.xml`, asignamos el estilo creado como estilo de nuestras nav bar.
```xml
    <item name="bottomNavigationStyle">@style/Widget.App.BottomNavigationView</item>
```

Ahora nuestra nav bar ya se ve como deseamos:
![image](https://user-images.githubusercontent.com/13813905/186285995-1afb4941-e695-41d5-931b-66b93d543bea.png)

## Menú

Nuestro `BottomNavigationView` debe tener asignado un menú, y así poder desplegar todas las opciones que el usuario podrá visitar. Para hacer esto, se hizo click derecho sobre la carpeta **res**, luego se seleccionó **Android Resource File** y por último, se creó un archivo de tipo **menu**:
![image](https://user-images.githubusercontent.com/13813905/186285651-fe635c7e-23ee-4490-8858-47342f6671c4.png)

Ya dentro de este archivo, se asignaron todas las opciones que iba a tener el menú.

## Navegación

Para la navegación utilizamos un `FragmentContainerView` que fue declarado dentro de nuestro main activity. Luego, en el activity, agregamos el código para la navegación. Este código se iba a encargar de el click listener de cuando se selecciona una opción del menú.

## Resultado Final

![image](https://user-images.githubusercontent.com/13813905/186286062-49e117c2-c467-40aa-8329-f8c1b0e5d37c.png)

![image](https://user-images.githubusercontent.com/13813905/186286079-5c1350f8-410e-46da-8df9-6d3320985341.png)

![image](https://user-images.githubusercontent.com/13813905/186286097-6274aa5f-2e8f-4271-a1d9-adf9e14dc970.png)