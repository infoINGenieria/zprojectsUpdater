zprojectsUpdater
================

Utilidad para realizar las descargas de archivos actualizados mediante una api que indica la ultima versión junto a la lista de archivos a descargar. Esta herramienta funciona dentro de [url=https://github.com/infoINGenieria/zprojects](zProject).

Modo de uso
-----------

Para ejecutar la utilizada, compilar y ejecutar:

    java -jar ZProjectsUpdater.jar relativePath

donde relativePath se utilizará como ruta relativa para escribir los archivos descargados. 


TODO
----

- [HECHO] Descargar lista de archivos a descargar y la ubicación relativa de cada uno (en formato JSON).
- [HECHO]Hacer contraparte web para suministrar la lista de archivos a actualizar, y poder cargar las versiones y los archivos.
- Publicar la contraparte web en github.
