# APP Escritorio - Taco Paco

Aplicación JavaFX para gestión de mesas del restaurante por parte del personal.

## 🖥️ Tecnologías

- Java 17
- JavaFX 21
- Retrofit 2 (API REST)
- Gson (JSON)

## 📋 Funcionalidades

- **Ver estado de mesas**: Verde (disponible) / Rojo (ocupada)
- **Limpiar mesas**: Liberar mesas pagadas
- **Actualizar**: Refrescar estado desde servidor
- **Restaurar**: Reiniciar todas las mesas (confirmación requerida). Eliminar en una práctica profesional, esto es solo para presentación

## 🔧 Componentes principales

### ControladorMesas.java
Controlador que gestiona la lógica de la interfaz:
- Carga mesas desde API
- Actualiza interfaz dinámicamente
- Gestiona eventos de botones

### vista-mesas.fxml
Interfaz gráfica con:
- Panel izquierdo: Logo y leyenda de colores
- Panel derecho: 5 mesas con estado actualizable

### RetrofitClient.java
Cliente HTTP para comunicación con API REST:
```java
BASE_URL = "http://localhost:3000/"
```

### Api.java
Métodos para la edición, guardado y listado de los datos que se comunica con la API

## 🎨 Indicadores visuales

- 🟢 **Verde**: Mesa disponible
- 🔴 **Rojo**: Mesa ocupada
- 🔴 + **Botón "Limpiar"**: Mesa pagada, lista para liberar

## 🚀 Ejecución

1. Asegurar que la API REST esté corriendo en `localhost:3000`
2. Ejecutar la aplicación JavaFX
3. La interfaz se actualiza automáticamente al iniciar

## 📡 Conexión API

Consume endpoints:
- `GET /mesas` - Obtener estado de todas las mesas
- `PUT /mesas/:nombre` - Actualizar estado de mesa específica

---

**Autor:** Santi Martínez
