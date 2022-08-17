# Solución lab 4 y 5 

Este proyecto contiene la solución del lab 5, que incluye a su vez el lab 4.

## Vista inicial

En esta vista el requerimiento era utilizar 1 Constraint Layout y 1 Linear Layout como mínimo. En mi caso, utilicé 2 Constraint Layouts (cuadros rojo y azul) y un Linear Layout (en verde):

![image](https://user-images.githubusercontent.com/13813905/185038794-61b0acc0-8269-42fd-8622-663c0bf2a9cf.png)

Las funcionalidades de esta activity son las siguientes:
* Si hacemos click en "Iniciar", un Toast Message aparecerá con mi nombre.
* Si hacemos click en "Descargar", nos abrirá el perfil de Whatsapp en la PlayStore o, en caso de no tenerla instalada, en Chrome
* Al hacer click en "Detalles", somos redireccionados a un nuevo activity

## Vista de detalles

Tenemos la siguiente vista, en la cual fue desarrollado utilizando un único Constraint Layout:

![image](https://user-images.githubusercontent.com/13813905/185037828-eea2d6c2-83a2-49ea-a798-71ece17a696d.png)

Al hacer click en el botón de "Iniciar visita" se nos solicitará permoisos de cámara (en caso no ternerlos todavía):

![image](https://user-images.githubusercontent.com/13813905/185037943-1ab30c7e-1150-49b9-8b5b-463f581ec568.png)

Si hacemos click en el botón de "Llamar", seremos redirigidos al app de teléfono de nuestro smartphone, con el número del restaurante ya marcado:

![image](https://user-images.githubusercontent.com/13813905/185037887-6a07d4fa-c2a1-4cb8-8d14-7ef8fe68ddee.png)
