
---

# Backend-DigitalMoney

## 📌 Descripción

**Backend-DigitalMoney** es una plataforma de microservicios desarrollada en Java con Spring Boot, orientada a la gestión de cuentas, actividades, tarjetas y autenticación de usuarios. Utiliza **Keycloak** para la autenticación y **Docker Compose** para la orquestación de contenedores.

---

## 🧱 Arquitectura

El sistema está conformado por los siguientes servicios:

* **Account Service**: Administración de cuentas de usuario.
* **Activity Service**: Registro de actividades de las cuentas.
* **Cards Service**: Gestión de tarjetas.
* **Login Service**: Servicio de autenticación.
* **User Service**: Manejo de información de usuarios.
* **Eureka Server**: Descubrimiento de servicios.
* **Config Server**: Configuración centralizada.
* **API Gateway**: Enrutamiento y balanceo de carga.

---

## ✨ Funcionalidades

* Autenticación y gestión de tokens mediante **Keycloak**.
* Creación, actualización y transferencia de cuentas.
* Registro de actividades de usuario.
* Gestión de tarjetas.
* Configuración centralizada con Spring Cloud Config.
* Descubrimiento de servicios con Eureka.
* Enrutamiento inteligente a través del API Gateway.

---

## 🛠️ Tecnologías

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
* Docker y Docker Compose

---

## ⚙️ Instalación y despliegue

1. Clona el repositorio:

   ```bash
   git clone <REPOSITORY_URL>
   cd Backend-DigitalMoney
   ```

2. Configura Keycloak y la base de datos:

   * Verifica que la carpeta `data/` contenga las configuraciones necesarias.
   * Asegúrate de que `data_db/` tenga los datos requeridos para la base de datos.
   * Revisa el archivo `docker-compose.yml` para los puertos y configuraciones de Keycloak.
   * Comprueba que el realm y los clientes estén correctamente configurados en Keycloak.

3. Compila los servicios:

   ```bash
   mvn clean install
   ```

4. Inicia la aplicación con Docker Compose:

   ```bash
   docker-compose build
   docker-compose up
   ```

5. Accede a los servicios:

   * **Eureka Server**: `http://localhost:<eureka-port>`
   * **API Gateway**: `http://localhost:<gateway-port>`
   * **Keycloak**: `http://localhost:<keycloak-port>`

6. Utiliza Postman u otra herramienta para probar los endpoints.

---

## 🔑 Endpoints principales

* Todas las solicitudes deben incluir el token de acceso de Keycloak.
* Los endpoints deben ser accedidos a través del API Gateway.

### Account Service

* `POST /accounts` – Crear cuenta
* `PUT /accounts/{accountId}` – Actualizar cuenta
* `POST /accounts/transfer` – Transferir fondos

### User Service

* `GET /users/{userId}` – Obtener información de usuario

### Cards Service

* `POST /cards` – Crear tarjeta
* `GET /cards/{cardId}` – Consultar tarjeta

---

## 🧪 Pruebas

Para ejecutar las pruebas:

> Nota: Se utiliza una base de datos externa para las pruebas. Es necesario configurar la conexión en el archivo `application.properties` de cada servicio.

```bash
mvn test
```

---

## 👨‍💻 Autor

* **Nombre**: Colby
* **Email**: vertiluscolby@gmail.com

---

## 📄 Licencia

Este proyecto está licenciado bajo (VC, etc.).

---