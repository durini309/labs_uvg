# Programación platafórmas móviles

Este es un repo en donde encontrarán todos los laboratorios resueltos del curso, al igual que algunos proyectos demo usados en clase.


# Utilizando resources

Una de las prácticas más recomendadas en el desarrollo de Android es utilizar la carpeta de resources para definir nuestros textos, colores, tamaños de fuentes, tamaños de widgets y de spacing. La intención de hacer esto es que, si en algún punto del ciclo de vida del app se decide realizar un cambio visual, sea más fácil realizar el cambio desde un sólo lugar y no tener que ir archivo por archivo realizando todos los cambios.

## ¿Qué merece la pena ser creado como un resource?

#### **Textos**

Todos los textos que usamos en nuestra UI, ya sea que los asignemos desde XML o desde código, necesitan tener un String resource. Esto nos permite tener todo centralizado, y si en algún momento necesitamos internacionalizar nuestra app, el proceso será mucho más sencillo.

**✅ Uso correcto:**

`res/layout/activity_main.xml`
```xml
<TextView
    ...
    android:text="@string/app_name"
    ...
/>
```

`res/values/strings.xml`
```xml
<string name="app_name">My Application</string>
```

### **Colores**

Todos los colores que utilicemos en el app es necesario que los declaremos en su archivo respectivo.

**✅ Uso correcto:**

`res/layout/activity_main.xml`
```xml
<TextView
    ...
    android:textColor="@color/primary_text"
    ...
/>
```

`res/values/colors.xml`
```xml
<color name="primary_text">#F3F3F3</string>
```

### **Tamaños de textos o widgets**

Siempre que queramos definir el height o width de un widget, o el tamaño de algún texto, debemos crear nuestro resource.

**✅ Uso correcto:**

`res/layout/activity_main.xml`
```xml
<ImageView
    ...
    android:width="@dimen/icon_size_xl"
    ...
/>

<TextView
    ...
    android:textSize="@dimen/title_size"
/>
```

`res/values/dimens.xml`
```xml
<dimen name="icon_size_xl">48dp</dimen>
<dimen name="title_size">48sp</dimen>
```

> Es importante notar que los tamaños de texto usan la dimensional `sp`, mientras que todo lo demás usa `dp`.

### **Margins y Paddings**

Para todos los paddings y margins que usen, deben crear su respectivo resource. Algo a considerar es la reutilización de resources ya creados. Es decir, podemos crear una dimen que se llame `margin_small`, y que esta tenga un valor de `8dp`, y podemos reutilizarla en todos los lugares en donde el padding o margin deba ser de `8dp`.

**✅ Uso correcto:**

`res/layout/activity_main.xml`
```xml
<ImageView
    ...
    android:layout_marginStart="@dimen/margin_small"
    ...
/>

<TextView
    ...
    android:layout_paddingStart="@dimen/margin_small"
/>
```

`res/values/dimens.xml`
```xml
<dimen name="margin_small">8dp</dimen>
```

## ¿Qué no merece la pena tener ser creado como resource?

### **Elementos para vista previa**

No es necreario crear resource si estamos utilizándolo como vista previa en nuestro layout. Android permite, al importar la librería `xmlns:tools="http://schemas.android.com/tools"`, cambiar nuestra UI sólo para la vista previa de la tab de "Design". Es decir, estos cambios no se van a aplicar cuando nuestra app está ejecutando.

Por ejemplo, Aquí usamos `tools:text="Hello World!"` como vista previa, y el cambio lo verán reflejado en la vista previa de Android Studio.
![image](https://user-images.githubusercontent.com/13813905/185808306-8722d57e-ebc9-42f0-be63-c669e55bd044.png)

Pero ahora, si corremos el app, no veremos ese texto
![image](https://user-images.githubusercontent.com/13813905/185808365-7424b013-be73-418b-9dc0-3a5d7d7be5bc.png)

### **0dp**

En un `ConstraintLayout` utilizamos `0dp` como `width` cuando queremos que el widget ocupe el espacio entre los constraints horizontales de la vista, o bien, podemo usar `0dp` como `height` cuando queremos el mismo comportamiento pero con los constraints verticales.

# Resumen

## ✅ Es necesario crear resources para

```xml
    <Widget
        ...
        android:layout_width="@dimen/widget_size"
        android:layout_marginStart="@dimen/margin_medium"
        android:textSize="@dimen/font_size_primary"
        android:textColor="@color/font_primary"
        android:text="@string/title"
    />
```

## ❌ No es necesario crear resources para

```xml
    <Widget
        ...
        android:width="0dp"
        tools:text="Titulo de prueba"
    />
```
