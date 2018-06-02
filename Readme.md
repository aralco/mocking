# PROGRAMACION - Test Driven Development (TDD)

## Estudiantes
  * Alexander Mamani Yucra
  * Ariel Alcocer Rodriguez

## Enunciado del problema
 Se tiene una determinada organización militar que con el objetivo de gestionar sus recursos de manera más eficiente, ha 
 decidido implementar una solución de software que le permita llevar a cabo las tareas que este proceso demanda. 
 En este sentido la solución de software debe tomar en cuenta la administración del personal (militares), sitios (armerías, 
 estaciones de servicio) y otros a ser definidos. Asimismo se debe facilitar las herramientas necesarias para gestionar 
 los activos de la organización: vehículos, armamentos (que pueden ser categorizados en armas y municiones) y otros a ser 
 definidos.

## Descripción (User Story)
 Se desea tener un módulo para la administración del personal de la organización. Inicialmente se requiere que el usuario 
 pueda llevar a cabo el registro de un nuevo personal, en ese proceso el sistema deberá generar un identificador único 
 que debe ser mostrado al usuario junto con el resto de los datos del personal una vez finalizado el registro. Los datos 
 que se desean registrar del Personal son: nombre, cédula de identidad, fecha y lugar de nacimiento, teléfono, dirección, 
 cargo/ocupación, fecha de registro. Se debe tener la posibilidad de mostrar un reporte del personal del personal existente, 
 asímismo se debe poder ordenar el reporte del personal de forma ascendente/descendente, en base al identificador, cédula 
 de identidad, fecha de nacimiento y fecha de registro. Adicionalmente se debe poder realizar búsquedas en base a criterios 
 como (identificador, nombre, cédula de identidad).
 
## Funcionalidades

1. Registrar un nuevo personal en la organización. 
    - Beneficios: Permitir realizar el registro del personal en la organización.
    - Criterios de Aceptación:
        - El Personal registrado debe tener un identificador único y una fecha de registro, que serán generados por la 
        aplicación.
        - Los datos ingresados por el usuario deben ser validados al momento del registro, mostrar mensaje de error si 
        corresponde.
        - Al finalizar el proceso de registro de personal se deberá mostrar el detalle de su información.

2. Mostrar un reporte(lista) del personal registrado en la organización.
    - Beneficios: Permitir visualizar al usuario la lista del personal registrado.
    - Criterios de Aceptación:
      - La lista debe tener un formato de tabla.
      - Los datos del personal registrado deberán ser visualizados (identificador, nombre, cédula de identidad, fecha de 
      nacimiento, cargo, fecha de registro).
      - Verificar que el personal registrado en el punto 1, exista en la lista del personal registrado.

3. Permitir ordenar el reporte(lista) del personal de forma ascendente/descendente en las siguientes columnas: identificador, 
nombre, cédula de identidad, fecha de nacimiento y fecha de registro.
    - Beneficios: Facilidad para el usuario en la visualización de datos.
    - Criterios de Aceptación:
      - La lista deberá poder ser ordenada dado un criterio y tipo de ordenamiento (ascendente/descendente). Ej: Identificador|ASC 
      ordenará la lista en base al identificador de forma ascendente.      

4. Permitir realizar búsquedas del personal en base a criterios como ser: identificador, cédula de identidad, nombre.
    - Beneficios: Facilidad para encontrar un determinado usuario en base a criterios.
    - Criterios de Aceptación:
      - Dado un criterio de búsqueda la aplicación deberá mostrar el resultado que hagan match con el criterio de 
      búsqueda. Ej: Si el criterio de búsqueda es por identificador **1111** se deberá retornar el registro que hace match 
      con el identificador.
