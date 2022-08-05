# Frontend Basics

Este mini proyecto tiene como objetivo explicar algunos de los layouts y widgets disponibles en Android. La app consta de 5 vistas diferentes.

## Vista inicial

Aquí se puede acceder a las diferentes ventanas del app. A nivel de código, esta vista sí utiliza strings, dimensiones y tamaños de letra como string resources y no hardcodeadas, a diferencia de las demás vistas. Tomar en cuenta que es **totalmente recomendado** usar resources en vez de valores hardcodeados, pero por motivos de tiempo las demás vistas no se hicieron así.

<img width="328" alt="image" src="https://user-images.githubusercontent.com/13813905/182987742-5361f655-5415-44a3-9779-c79d1d22ab37.png">

## Widgets

Aquí utilizamos algunos de los widgets más comunes en Android: `MaterialButton`, `TextInputLayout`, `TextInputEditText` e `ImageView`. También hacemos de drawables (vectores para íconos y shape para fondo circular).

<img width="329" alt="image" src="https://user-images.githubusercontent.com/13813905/182639708-6d65503a-21be-4107-a508-6868136872bf.png">


## Linear Layout

Utilización de nested layouts. Esta vista cuenta con un `ScrollView` como parent (para poder scrollear en caso el contenido exceda el ViewPort). Adentro tenemos un `LinearLayout` vertical que cuenta con 3 `HorizontalScrollView` (que internamente tienen un `LinearLayout` horizontal con `CardView`).

<img width="327" alt="image" src="https://user-images.githubusercontent.com/13813905/182640303-3d2e13ad-f9a2-4fb7-a31c-d51a6c8dcdaa.png">

También cuenta con un `CardView` hasta al final, que internamente tiene un `LinearLayout` que demuestra como funciona la propiedad de `weightSum` en esos Layouts.

<img width="328" alt="image" src="https://user-images.githubusercontent.com/13813905/182641046-a0b775d8-329f-4b25-bfb8-7ee396e0bddd.png">

## Relative Layout

Ejemplo básico de un relative layout.

<img width="327" alt="image" src="https://user-images.githubusercontent.com/13813905/182641255-e5b84745-3d0c-440d-919f-79252427932e.png">

## Constraint Layout

Ejemplo de un `ConstraintLayout`. Aquí uso constraints básicos al parent y a childs, al igual que un horizontal `Guideline` al 50% de la pantalla.

<img width="326" alt="image" src="https://user-images.githubusercontent.com/13813905/182641834-e4fecee2-1c05-401f-be69-45d468f7bafc.png">
