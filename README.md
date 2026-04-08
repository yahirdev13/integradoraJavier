# Catalogo de Libros - Proyecto Integrador

Repositorio para el proyecto integrador de la clase Programacion Estructurada 2E.

**Autores:** Ian Navarro, Javier Diaz

---

## Descripcion

Aplicacion de escritorio desarrollada con **JavaFX** que permite gestionar un catalogo de libros. Implementa operaciones CRUD completas (Crear, Leer, Actualizar, Eliminar) con persistencia en archivos CSV, garantizando que los datos se conserven entre ejecuciones.

---

## Estructura del Proyecto

```
src/main/
├── java/
│   ├── module-info.java                          # Declaracion del modulo Java
│   └── com/example/integradora_ian_adael/
│       ├── Launcher.java                         # Clase main (punto de entrada)
│       ├── HelloApplication.java                 # Inicializador de JavaFX
│       ├── Controllers/
│       │   ├── MainController.java               # Controlador de la ventana principal
│       │   ├── FormController.java               # Controlador del formulario (alta/edicion)
│       │   └── DetailController.java             # Controlador de la vista de detalle
│       ├── Modelo/
│       │   └── libro.java                        # Modelo de datos del libro
│       ├── Repositorio/
│       │   └── libroRepositorio.java             # Capa de acceso a datos (lectura/escritura CSV)
│       └── Services/
│           └── libroServicio.java                # Capa de logica de negocio y validaciones
└── resources/
    └── com/example/integradora_ian_adael/views/
        ├── main-view.fxml                        # Vista principal con tabla
        ├── form-view.fxml                        # Vista del formulario
        └── detail-view.fxml                      # Vista de detalle de libro

data/
├── catalogo.csv                                  # Archivo de datos persistentes
└── reporte_catalogo.csv                          # Archivo de reporte exportado
```

---

## Arquitectura (MVC + Servicio/Repositorio)

El proyecto sigue el patron **Modelo-Vista-Controlador (MVC)** combinado con capas de **Servicio** y **Repositorio** para separar responsabilidades:

```
Vista (FXML)  <-->  Controlador (Controller)  <-->  Servicio  <-->  Repositorio  <-->  Archivo CSV
```

### Modelo (`libro.java`)

Representa un libro con los siguientes atributos:

| Atributo      | Tipo      | Descripcion                        |
|---------------|-----------|------------------------------------|
| `id_libro`    | `String`  | Identificador unico (ISBN)         |
| `titulo`      | `String`  | Titulo del libro                   |
| `autor`       | `String`  | Autor del libro                    |
| `fecha_pub`   | `int`     | Anio de publicacion                |
| `genero`      | `String`  | Genero literario                   |
| `disponible`  | `boolean` | Si el libro esta disponible o no   |

### Vistas (archivos FXML)

| Vista               | Descripcion                                                        |
|---------------------|--------------------------------------------------------------------|
| `main-view.fxml`    | Ventana principal con una tabla (`TableView`) y botones de accion  |
| `form-view.fxml`    | Ventana modal con formulario para agregar o editar un libro        |
| `detail-view.fxml`  | Ventana modal que muestra los datos de un libro en modo lectura    |

### Controladores

| Controlador          | Responsabilidad                                                  |
|----------------------|------------------------------------------------------------------|
| `MainController`     | Maneja la tabla principal, abre modales, elimina libros, exporta |
| `FormController`     | Valida y guarda datos del formulario (modo crear y editar)       |
| `DetailController`   | Muestra la informacion de un libro seleccionado                  |

### Servicio (`libroServicio.java`)

Contiene la **logica de negocio** y las **validaciones**:

- Ningun campo puede estar vacio
- Titulo y autor deben tener minimo 3 caracteres
- Anio de publicacion debe estar entre 1500 y el anio actual
- No se permiten IDs/ISBN duplicados
- Delega las operaciones de lectura/escritura al repositorio

### Repositorio (`libroRepositorio.java`)

Se encarga de la **persistencia de datos**:

- Lee los libros desde `data/catalogo.csv` al iniciar la aplicacion
- Escribe los cambios al archivo despues de cada operacion
- Crea la carpeta `data/` automaticamente si no existe
- Formato del archivo: campos separados por `|` (pipe)

---

## Como Funciona (Flujo de la Aplicacion)

### 1. Inicio

1. `Launcher.main()` ejecuta `Application.launch()` de JavaFX
2. `HelloApplication.start()` carga `main-view.fxml`
3. `MainController.initialize()` crea las instancias de Repositorio y Servicio
4. El repositorio lee `data/catalogo.csv` y carga los libros en memoria
5. La tabla se llena con los datos usando `ObservableList`

### 2. Agregar un libro

1. El usuario hace clic en **"Nuevo"**
2. Se abre la ventana modal `form-view.fxml` con los campos vacios
3. El usuario llena los datos y hace clic en **"Guardar"**
4. `FormController` envia los datos a `libroServicio.agregar()`
5. El servicio valida los datos (campos no vacios, longitudes, rango de anio, ISBN unico)
6. Si es valido, el repositorio agrega el libro a la lista y guarda el archivo CSV
7. Si hay error de validacion, se muestra el mensaje en rojo en el formulario
8. La tabla principal se actualiza automaticamente

### 3. Editar un libro

1. El usuario selecciona un libro de la tabla y hace clic en **"Editar"**
2. Se abre el formulario pre-llenado con los datos del libro
3. El campo ID se deshabilita (no se puede cambiar el ISBN)
4. El usuario modifica los campos y hace clic en **"Guardar"**
5. El servicio valida y el repositorio actualiza el registro en el archivo

### 4. Eliminar un libro

1. El usuario selecciona un libro y hace clic en **"Eliminar"**
2. Aparece un dialogo de confirmacion (`Alert`)
3. Si confirma, el servicio elimina el libro y el repositorio actualiza el archivo

### 5. Ver detalle

1. El usuario selecciona un libro y hace clic en **"Ver Detalle"**
2. Se abre `detail-view.fxml` mostrando todos los datos del libro en modo lectura

### 6. Exportar reporte

1. El usuario hace clic en **"Exportar Reporte"**
2. El servicio genera `data/reporte_catalogo.csv` con encabezados y datos formateados
3. La disponibilidad se muestra como "Si"/"No" en lugar de true/false
4. Se muestra un mensaje de confirmacion en la ventana principal

---

## Formato del Archivo CSV

El archivo `data/catalogo.csv` usa `|` como separador:

```
ISBN001|Cien anios de soledad|Gabriel Garcia Marquez|1967|Novela|true
ISBN002|El Quijote|Miguel de Cervantes|1605|Clasico|true
ISBN003|1984|George Orwell|1949|Distopia|false
ISBN004|El Principito|Antoine de Saint-Exupery|1943|Fantasia|true
ISBN005|Harry Potter y la piedra filosofal|J.K. Rowling|1997|Fantasia|false
```

---

## Validaciones

| Regla                                    | Donde se aplica       |
|------------------------------------------|-----------------------|
| Campos no vacios                         | Todos los campos      |
| Minimo 3 caracteres                      | Titulo y Autor        |
| Anio entre 1500 y anio actual            | Fecha de publicacion  |
| ISBN/ID unico (sin duplicados)           | ID del libro          |

---

## Requisitos para Ejecutar

- **Java 21** o superior
- **JavaFX 21.0.6** (incluido como dependencia en `pom.xml`)
- **Maven** (el proyecto incluye Maven Wrapper `mvnw`)

### Pasos

1. Clonar el repositorio:
   ```bash
   git clone <url-del-repositorio>
   ```

2. Abrir el proyecto en un IDE compatible con Maven (IntelliJ IDEA, Eclipse, VS Code)

3. Ejecutar con Maven Wrapper:
   ```bash
   ./mvnw javafx:run
   ```

   O ejecutar directamente la clase `Launcher.java` desde el IDE.

---

## Tecnologias Utilizadas

| Tecnologia    | Version | Uso                                |
|---------------|---------|------------------------------------|
| Java          | 21      | Lenguaje principal                 |
| JavaFX        | 21.0.6  | Framework de interfaz grafica      |
| BootstrapFX   | 0.4.0   | Estilos CSS para JavaFX            |
| Maven         | -       | Gestion de dependencias y build    |
| JUnit 5       | -       | Framework de pruebas               |

---

## Control de Versiones

| Rama       | Proposito                          |
|------------|------------------------------------|
| `main`     | Version final y estable            |
| `dev`      | Rama de integracion                |
| Personales | Ramas individuales por integrante  |

**Flujo de trabajo:**
1. Cada integrante trabaja en su rama personal
2. Se hace merge a `dev` para integrar
3. Se realizan pruebas en `dev`
4. Merge final a `main`

---

## Restricciones del Proyecto

- No se usa base de datos (solo archivos)
- Los datos deben persistir al cerrar y reabrir la aplicacion
- No se depende unicamente de memoria RAM
