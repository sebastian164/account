# 📒 Account Microservice - Devsu

Microservicio de gestión de cuentas y movimientos bancarios, parte del sistema bancario Devsu. Implementado con Spring Boot y arquitectura hexagonal.

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot 3.3.4
- Maven
- MySQL
- MapStruct
- Spring Security (JWT)
- Swagger OpenAPI
- Docker

---

## 🧱 Arquitectura

Este microservicio sigue una arquitectura hexagonal (puertos y adaptadores):

- **Dominio**
  - `model`: Entidades del dominio (`Account`, `Movement`)
  - `port.in`: Casos de uso
  - `port.out`: Contratos hacia infraestructura
- **Aplicación**
  - `dto`: Objetos de transferencia
  - `mapper`: Mapeos entre DTO y modelos
  - `service`: Implementación de casos de uso
- **Infraestructura**
  - `controller`: Controladores REST
  - `persistence`: Repositorios JPA y mapeadores `Entity` ↔ `Model`
  - `handler`: Manejadores de errores globales

---

## 📌 Endpoints principales

> Todos los endpoints están bajo `/api/accounts` y `/api/movements`

### 🧾 Accounts

| Método | Endpoint             | Descripción                  |
|--------|----------------------|------------------------------|
| POST   | `/`                  | Crear una nueva cuenta       |
| GET    | `/{id}`              | Obtener cuenta por ID        |
| GET    | `/`                  | Obtener todas las cuentas    |
| PUT    | `/{id}`              | Actualizar cuenta            |
| DELETE | `/{id}`              | Eliminar cuenta              |

### 💸 Movements

| Método | Endpoint                                           | Descripción                                      |
|--------|----------------------------------------------------|--------------------------------------------------|
| POST   | `/api/movements`                                   | Crear un nuevo movimiento                       |
| GET    | `/api/movements/account/{accountId}`              | Obtener movimientos por ID de cuenta             |
| GET    | `/api/movements/account/{accountId}/range`        | Obtener movimientos por cuenta y rango de fechas |

**Ejemplo de request para `/api/movements`:**

```json
{
  "accountId": 1,
  "type": "RETIRO",
  "amount": 100.0
}
```

**Parámetros de fecha en rango:**

```http
GET /api/movements/account/1/range?from=2024-01-01&to=2024-04-01
```

Swagger disponible en: [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)

---

## 🧹 Configuración

### Base de datos

Se utiliza una base de datos MySQL en el entorno `dev`.


---

## 🚀 Docker: Construcción y Ejecución

### 🧱 Construir la imagen Docker

Genera el `.jar` previamente:

```bash
mvn clean package
```

Construye la imagen Docker:

```bash
docker build -t account-ms .
```

### ▶️ Ejecutar el contenedor

```bash
docker run -p 8081:8081 account-ms
```

El microservicio es expuesto en  `http://localhost:8081`.

### 📦 Requisitos

- Tener [Docker Desktop](https://www.docker.com/products/docker-desktop/) instalado y corriendo.