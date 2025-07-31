
---

# Backend-DigitalMoney

## 📌 Descripción

**Backend-DigitalMoney** es un sistema basado en microservicios desarrollado en Java con Spring Boot. Proporciona funcionalidades para la gestión de cuentas, actividades, tarjetas y autenticación de usuarios. Utiliza **Keycloak** para la autenticación y **Docker Compose** para la orquestación de contenedores.

---

## 🧱 Arquitectura

El sistema está compuesto por los siguientes servicios:

* **Account Service**: Gestión de cuentas de usuario.
* **Activity Service**: Registro de actividades de cuenta.
* **Cards Service**: Operaciones relacionadas con tarjetas.
* **Login Service**: Autenticación de usuarios.
* **User Service**: Administración de información de usuarios.
* **Eureka Server**: Descubrimiento de servicios.
* **Config Server**: Gestión centralizada de configuración.
* **API Gateway**: Enrutamiento y balanceo de carga.

---

## ✨ Características

* Autenticación y gestión de tokens con **Keycloak**.
* Creación, actualización y transferencia de cuentas.
* Registro de actividades.
* Gestión de tarjetas.
* Configuración centralizada con Spring Cloud Config.
* Descubrimiento de servicios con Eureka.
* Enrutamiento inteligente mediante API Gateway.

---

## 🛠️ Tecnologías utilizadas

* **Lenguaje**: Java 17+
* **Framework**: Spring Boot
* **Autenticación**: Keycloak
* **Contenedores**: Docker, Docker Compose
* **Service Discovery**: Eureka Server
* **Configuración**: Spring Cloud Config Server
* **Gateway**: Spring Cloud Gateway
* **Pruebas**: JUnit 5, RestAssured
* **Build Tool**: Maven

---

## 🚀 Requisitos previos

* Java 17 o superior
* Maven 3.8 o superior
* Docker y Docker Compose instalados

---

## ⚙️ Setup y despliegue

1. Clonar el repositorio:

   ```bash
   git clone <REPOSITORY_URL>
   cd Backend-DigitalMoney
   ```

2. Configurar Keycloak:

    * Revisar la carpeta `data/` con las configuraciones necesarias.

3. Construir los servicios:

   ```bash
   mvn clean install
   ```

4. Levantar la aplicación con Docker Compose:

   ```bash
   docker-compose up --build
   ```

5. Acceder a los servicios:

    * **Eureka Server**: `http://localhost:<eureka-port>`
    * **API Gateway**: `http://localhost:<gateway-port>`
    * **Keycloak**: `http://localhost:<keycloak-port>`

6. Usar Postman u otra herramienta para probar los endpoints.

---

## 🔑 Endpoints principales

### Account Service

* `POST /accounts` – Crear nueva cuenta
* `PUT /accounts/{accountId}` – Actualizar cuenta
* `POST /accounts/transfer` – Transferir entre cuentas

### User Service

* `POST /users` – Crear usuario
* `GET /users/{userId}` – Obtener detalles de usuario

### Cards Service

* `POST /cards` – Crear tarjeta
* `GET /cards/{cardId}` – Obtener tarjeta

---

## 🧪 Pruebas

Ejecutar las pruebas con:

```bash
mvn test
```

---

## 👨‍💻 Autor

* **Nombre**: Colby
* **Email**: vertiluscolby@gmail.com

---

## 📄 Licencia

Este proyecto está licenciado bajo (MIT, Apache 2.0, etc.).

---


