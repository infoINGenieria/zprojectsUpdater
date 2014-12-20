zprojectsUpdater
================
*Versión incompleta*

Utilidad para realizar actualización de archivos y versiones de sotfware para el sistema [url=https://github.com/infoINGenieria/zprojects](zProject).

Modo de uso
-----------

Para ejecutar la utilizada, compilar y ejecutar:

    java -jar ZProjectsUpdater.jar version url [url2...]

donde version es el número de versión del sistema a actualizar, y url es el archivo a descargar. Las url pueden ser varias, y descargará cada una en orden.


TODO
----

- Descargar lista de archivos a descargar y la ubicación relativa de cada uno (en formato JSON).
- Hacer contraparte web para suministrar la lista de archivos a actualizar, y poder cargar las versiones y los archivos.
