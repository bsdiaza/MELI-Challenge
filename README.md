# Operación Fuego de Quásar - Solución Backend

¡Bienvenido a la solución del proyecto Operación Fuego de Quásar! Esta implementación aborda la tarea de determinar la ubicación y el mensaje de auxilio de una nave en problemas utilizando satélites.

## Contenido del Repositorio

Este repositorio contiene la solución para el desarrollo backend del proyecto. A continuación, se describen los aspectos más importantes:

### 1. Código Fuente

El código fuente de la aplicación se encuentra en el directorio `src`. Aquí encontrarás la implementación de los algoritmos para calcular la ubicación y el mensaje. Los cuales fueron implementadas con base en una version simplificada de la **arquitectura hexagonal** ademas de otras buenas practicas.

### 2. API REST

Se ha implementado una API REST con tres endpoints principales:

- `/api/v1.0/rescue/topsecret`: Recibe información de los satélites a través de un POST y devuelve la ubicación y el mensaje de la nave.

- `/api/v1.0/rescue/topsecret_split/{satellite_name}`: Permite actualizar la posición de un satélite específico mediante POST.

- `/api/v1.0/rescue/topsecret_split`: Un endpoint GET que indica la posición y el mensaje si es posible determinarlo, con una estructura similar al ejemplo del Nivel 2. Responde con un mensaje de error si no hay suficiente información.

## Documentación Swagger con OpenAPI 3

La documentación detallada de la API se encuentra en el archivo `Docs/rescue_api_swagger.yaml` y `Docs/rescue_api_swagger.html`. Este archivo describe los endpoints, los esquemas de solicitud y respuesta, así como cualquier información adicional relevante.
![image](https://github.com/bsdiaza/MELI-Challenge/assets/32690529/9f824363-c5e0-45c9-98da-08b45fca9b73)


## Ejecución de la Aplicación

Para ejecutar la aplicación y probar los endpoints, sigue estos pasos:

1. **Clonar el Repositorio:** Clona este repositorio en tu máquina local.

```bash
   git clone https://github.com/bsdiaza/MELI-Challenge
```

2. **Ejecutar la Compilado:** Navega al directorio `/out/artifacts/rescue_api_jar`. Allí encontrarás el archivo `rescue-api-1.0-ESTABLE.jar`, el cual puede ser ejecutado usando el siguiente comando:
```bash
   java -jar rescue-api-1.0-ESTABLE.jar
```

3. **Consumir api REST:** Para hacer uso de la API desarrollada se recomienda hacer uso de la coleccion de postman que se ubica en el archivo  `Docs/MELI Challenge.postman_collection` los cuales contienen datos de prueba funcionales para este reto.
   ![image](https://github.com/bsdiaza/MELI-Challenge/assets/32690529/292a5e97-7ed6-4d73-a8e8-54502ea85719)
