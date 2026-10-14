# Customers Backend
 
API REST con Spring Boot para la gestión de clientes y la consulta de indicadores
 
## Tecnologías
- Java 21
- Spring Boot
- Maven
- Spring Web MVC
- Spring Data JPA
- Bean Validation
- H2 Database
- JUnit 5
- Mockito
 
## Requisitos
Para ejecutar el proyecto es necesario tener instalado:
 
- Java 21 o superior
- Git (opcional, para clonar el repositorio)
 
 
## Instalación
Clonar el repositorio:

```bash

git clone https://github.com/KevinDamianDev/customer-fullstack.git

```
 
Ingresar al directorio del backend: cd customers-backend

 
## Ejecución

.\mvnw.cmd spring-boot:run

Una vez iniciada la aplicación, el backend estará disponible en: http://localhost:8080

 
## Base de datos
 
El proyecto utiliza H2 como base de datos en memoria.
Configuración principal:

URL: jdbc:h2:mem:customersdb
Usuario: sa
Password: vacío

H2 esta disponible en: http://localhost:8080/h2-console

---
 
# API REST
 
La URL base de la API es: http://localhost:8080/api/customers
 
## Crear cliente

POST /api/customers
 
### Body
 
```json

{

    "nombre": "Kevin",
    "apellido": "Gonzales",
    "email": "kevin@gmail.com",
    "dni": "12345678",
    "fechaN": "1995-10-10"

}

```
 
La fecha de creación no debe enviarse en la petición ya que es generada automáticamente
 
### Response
 
```json

{

    "id": 1,
    "nombre": "Kevin",
    "apellido": "Gonzales",
    "email": "kevin@gmail.com",
    "dni": "12345678",
    "createdAt": "2026-10-14T00:10:00",
    "fechaN": "1995-10-10"

}

```
 
Código HTTP esperado: 201 Created


 
---
 
## Consultar clientes
GET /api/customers


### Ejemplo de respuesta
 
```json

[

    {

        "id": 1,
        "nombre": "Kevin",
        "apellido": "Gonzales",
        "email": "kevin@gmail.com",
        "dni": "12345678",
        "createdAt": "2026-10-14T00:10:00",
        "fechaN": "1995-10-10"

    }

]

```
 
---
 
## Consultar cliente por DNI
GET /api/customers?dni=12345678

 
---
 
## Consultar cliente por email
GET /api/customers?email=kevin@gmail.com

 
---
 
# Indicadores
 
La API permite consultar indicadores calculados a partir de las fechas de nacimiento de los clientes
 
Los indicadores disponibles son:
 
- Cantidad de clientes nacidos por mes/año.
- Mes/año con mayor cantidad de clientes nacidos.
- Mes/año con menor cantidad de clientes nacidos.
- Tasa de natalidad de cada mes/año.
 
## Consultar indicadores
 
### Request
GET /api/customers/indicators

 
### Ejemplo de respuesta
 
```json

{

    "nacimientosPorMesAnio": [

        {
            "mes": 10,
            "anio": 1995,
            "cantidad": 3,
            "tasaNatalidad": 75.0

        },

        {
            "mes": 8,
            "anio": 2000,
            "cantidad": 1,
            "tasaNatalidad": 25.0
        }

    ],

    "mayorCantidadNacimientos": {
        "mes": 10,
        "anio": 1995,
        "cantidad": 3

    },

    "menorCantidadNacimientos": {
        "mes": 8,
        "anio": 2000,
        "cantidad": 1

    }

}

```
 
## Cálculo de la tasa de natalidad
 
En este caso, la tasa de natalidad se calcula como el porcentaje de clientes nacidos en un determinado mes/año respecto al total de clientes registrados
 
Tasa de natalidad = (clientes nacidos en el mes/año / total de clientes) * 100


Ejemplo:
Clientes totales: 4
Clientes nacidos en 10/1995: 3
Tasa = (3 / 4) * 100 = 75%

 
# Validaciones
 
Durante la creación de clientes se realizan las siguientes validaciones:
 
- Nombre obligatorio.
- Apellido obligatorio.
- Email obligatorio y con formato válido.
- DNI obligatorio.
- Fecha de nacimiento obligatoria.
- La fecha de nacimiento debe ser anterior a la fecha actual.
- El DNI no puede estar registrado previamente.
- El email no puede estar registrado previamente.

Ejemplo:
 
```json

{

    "status": 409,
    "mensaje": "Ya existe un cliente registrado con el DNI ingresado",
    "timestamp": "2026-10-14T00:10:00"

}

```
 
# Pruebas unitarias
 
El proyecto incluye pruebas unitarias para la capa de servicio utilizando:
 
- JUnit 5
- Mockito
 
Escenarios:
 
- Creación correcta de un cliente.
- Intento de creación de un cliente con DNI duplicado.
 
## Ejecutar pruebas
.\mvnw.cmd test

---
 
# Colección Postman
 
El proyecto incluye una colección de Postman para probar la API
 
La colección contiene:
 
- Crear cliente.
- Consultar todos los clientes.
- Consultar cliente por DNI.
- Consultar cliente por email.
- Consultar indicadores.

 
---
 
# Compilar el proyecto
Para realizar una compilación limpia:
.\mvnw.cmd clean install

