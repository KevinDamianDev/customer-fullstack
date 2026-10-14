# Customers Frontend

Aplicación web desarrollada con Angular para la gestión de clientes
La aplicación consume una API REST desarrollada con Spring Boot

## Tecnologías utilizadas
- Angular
- TypeScript
- Node.js
- npm
- Angular HttpClient
- Reactive Forms
- Angular Signals

## Requisitos

Para ejecutar el proyecto es necesario tener instalado:

- Node.js
- npm
- Angular CLI

## Instalación

Ingresar al directorio del frontend:

## Instalación
Clonar el repositorio:

```bash

git clone https://github.com/KevinDamianDev/customer-fullstack.git

```
 
Ingresar al directorio del backend: cd customers-frontend

 
## Instalar las dependencias:

npm install

## Ejecución

Ejecutar:
ng serve


La aplicación estará disponible en: http://localhost:4200
Para que todas las funcionalidades estén disponibles, el backend debe estar ejecutándose en: http://localhost:8080


## Funcionalidades

La aplicación permite:
- Consultar todos los clientes registrados
- Buscar clientes por DNI
- Buscar clientes por email
- Crear nuevos clientes
- Validar los datos del formulario
- Consultar indicadores de clientes por mes y año de nacimiento

## Creación de clientes

El formulario permite registrar los siguientes datos:
- Nombre
- Apellido
- Email
- DNI
- Fecha de nacimiento

## Consulta de clientes
Los clientes registrados se muestran en una tabla

También es posible realizar búsquedas utilizando:
DNI
Email


## Indicadores

La aplicación muestra los indicadores calculados por el backend:

- Cantidad de clientes nacidos por mes/año
- Mes/año con mayor cantidad de clientes nacidos
- Mes/año con menor cantidad de clientes nacidos
- Tasa de natalidad por mes/año

Los cálculos son realizados en el backend

El frontend se encarga únicamente de consumir y mostrar los resultados en una tabla.

## Comunicación con el backend

La comunicación con la API REST se centraliza en `CustomerService`.

La URL base utilizada es: http://localhost:8080/api/customers

Los principales endpoints consumidos son:

GET  /api/customers
POST /api/customers
GET  /api/customers?dni={dni}
GET  /api/customers?email={email}
GET  /api/customers/indicators

Angular utiliza `HttpClient` para realizar las peticiones HTTP.


## Pruebas unitarias

Se encuentra una prueba de `CustomerService`
Para ejecutar las pruebas:
ng test


## Compilación
Para generar el build del proyecto:
ng build


