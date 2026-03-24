# prog-str-2e-integradora-Ian-Navarro-Javier-Diaz

Repositorio para la creacion del proyecto integrador de la clase programacion estructurada.

## Descripción

Aplicación de escritorio desarrollada en JavaFX para la gestión de un catálogo de libros. Permite realizar operaciones CRUD con persistencia en archivo local, garantizando la conservación de datos entre ejecuciones.

## Objetivo

Implementar un sistema que integre:

- CRUD completo
- Persistencia en archivo
- Validaciones
- Manejo de errores
- Programación orientada a objetos

## Funcionalidades

- Alta, consulta, actualización y eliminación de libros
- Carga automática de datos al iniciar
- Guardado automático en archivo (.csv o .txt)
- Validación de campos (no vacíos, formato y rangos)
- Prevención de duplicados por ISBN/ID
- Vista de detalle de libro
- Exportación de reporte a archivo

## Interfaz

- Pantalla principal con TableView y acciones CRUD
- Formulario para alta/edición
- Pantalla de detalle

## Estructura

- Model: Libro
- Repository/Service: manejo de datos y persistencia
- Controllers: uno por pantalla
- Utilidades para manejo de archivos

## Ejecución

1. Clonar repositorio
2. Abrir en IDE
3. Configurar JavaFX
4. Ejecutar la clase principal

## Persistencia

Los datos se almacenan en un archivo local estructurado.
Se cargan al iniciar y se actualizan tras cada operación.

## Reporte

Generación de archivo `reporte_catalogo.csv` con los datos actuales.

## Control de versiones

- main: versión final
- dev: integración
- ramas individuales por integrante

Flujo:

1. Trabajo en ramas personales
2. Merge a dev
3. Pruebas
4. Merge final a main

## Restricciones

- No usar base de datos
- No depender solo de memoria
- Debe conservar datos al reiniciar

## Entregables

- Repositorio
- README
- Proyecto completo
- Archivo con datos de prueba
- Capturas de pantalla
