# Prueba Técnica Nisum Java
Api Restful para crear usuarios
### Pasos para utilizar la api
#### Paso 1
Clonar repositorio.
> [urlDelRepository](https://github.com/SteveenRodriguez/prueba-tecnica-nisum-java.git)

#### Paso 2
Levantar el proyecto seleccionando la clase Main
> ApiUsersApplication

Esto permite crear la BD en memoria, a continuación se muestran los datos para acceder a ella:

URL:
> http://localhost:8080/api/h2-console/

Datos de ingreso:
> Driver Class: org.h2.Driver
>
> JDBC URL: jdbc:h2:mem:nisum
>
> UserName: admin
>
> Password: admin
#### Paso 3
Ir a la url de la documentación de la API
> http://localhost:8080/api/doc/swagger-ui/index.html

En esta se especifica mediante swagger el schema necesario para enviarlo mediante el Body 

## Prueba en Postman
Para probar la api en postman debe copiar la siguiente URL mediante el metodo POST:
> http://localhost:8080/api/users/create

El Body a enviar es el siguiente:
```json
{
    "name": "Stick Torres",
    "email": "stick@mail.com",
    "password": "Hunter2",
    "phones": [
        {
            "number": "3217659315",
            "cityCode": 10,
            "countryCode": 57
        }
    ]
}
```
### Colección para probar en Postman

> [Descargar - clic](https://drive.google.com/file/d/1AFnnkb5jB239cDhxWNPFwvRpicfsZITg/view?usp=sharing)
