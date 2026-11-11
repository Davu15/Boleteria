# Sistema de Boletería

Aplicación de escritorio desarrollada en **Java Swing**, con **MySQL** como base de datos, para la gestión de eventos, venta de boletos y control de acceso de asistentes.

El sistema permite registrar eventos, administrar su capacidad, emitir boletos en PDF con código de barras y validar entradas para reducir el riesgo de accesos duplicados.

## Funcionalidades

- **Gestión de eventos:** registro, consulta, actualización y eliminación de eventos; configuración de nombre, categoría, descripción, fecha, precio, aforo y estado activo.
- **Venta de boletos:** registro de ventas vinculadas a eventos y control de disponibilidad según su aforo.
- **Generación de boletos:** emisión de entradas en formato PDF con códigos de barras.
- **Control de acceso:** lectura y validación de códigos de barras para verificar las entradas.
- **Consulta de ventas:** visualización y seguimiento de boletos registrados.

## Tecnologías

| Tecnología | Uso |
| --- | --- |
| Java | Lógica y funcionamiento de la aplicación |
| Java Swing | Interfaz gráfica de escritorio |
| Apache NetBeans | Entorno de desarrollo |
| MySQL | Persistencia de eventos, ventas y boletos |
| JDBC | Conexión y consultas a la base de datos |
| Git y GitHub | Control de versiones y colaboración |

## Arquitectura MVC y DAO

El proyecto organiza sus responsabilidades mediante el patrón **Modelo–Vista–Controlador (MVC)** y clases **DAO (Data Access Object)**.

| Capa | Responsabilidad | Ejemplo |
| --- | --- | --- |
| Modelo | Representa los datos utilizados por el sistema | `Evento.java` |
| Vista | Presenta formularios, tablas y botones al usuario | `PanelEventos.java` |
| Controlador | Recibe solicitudes de la Vista y coordina operaciones | `ControladorEventos.java` |
| DAO | Realiza consultas SQL y operaciones sobre MySQL | `EventoDAO.java` |

**Flujo de ejemplo:** `PanelEventos` → `ControladorEventos` → `EventoDAO` → `MySQL`.

## Estructura general

El proyecto de NetBeans se encuentra en la carpeta `BoleteriaG4/`. Entre los componentes del módulo de eventos se encuentran:

```text
modelo/Evento.java
vista/PanelEventos.java
controlador/ControladorEventos.java
dao/EventoDAO.java
conexion/Conexion.java
```

La ubicación exacta de estos paquetes depende de la estructura de fuentes del proyecto.

## Requisitos para ejecutar

- JDK compatible con la versión de Java utilizada en el proyecto.
- Apache NetBeans con soporte para proyectos Java.
- Servidor MySQL.
- Controlador JDBC de MySQL y las bibliotecas que utilice el proyecto, agregados a las dependencias de NetBeans.
- Base de datos con el esquema requerido por la aplicación.

## Ejecución local

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/Davu15/Boleteria.git
   ```

2. En NetBeans, seleccionar **Archivo → Abrir proyecto** y abrir la carpeta `BoleteriaG4`.
3. Crear o importar la base de datos del proyecto en MySQL.
4. Revisar la configuración de conexión en la clase `Conexion` y ajustar servidor, puerto, nombre de la base de datos y credenciales al entorno local.
5. Verificar que las bibliotecas requeridas estén configuradas en el proyecto.
6. Ejecutar el proyecto desde NetBeans.

> **Nota:** la configuración de MySQL y las dependencias debe ajustarse al entorno de cada equipo. No se deben publicar contraseñas reales en el repositorio.

## Integridad de los datos

El módulo de eventos usa sentencias SQL parametrizadas mediante `PreparedStatement`. También incorpora la consulta de boletos no anulados para apoyar el control del aforo. La aplicación contempla la validación de códigos de barras para el control de ingreso.

## Trabajo colaborativo

Proyecto académico desarrollado en equipo. El historial de Git registra la participación y los cambios realizados por los integrantes.

## Repositorio

[GitHub — Davu15/Boleteria](https://github.com/Davu15/Boleteria)
