# Kokonut Prueba T茅cnica

_Prueba t茅cnica_

## Comenzando 馃殌

_Estas instrucciones te permitir谩n obtener una copia del proyecto en funcionamiento en tu m谩quina local para prop贸sitos de desarrollo y pruebas._

Mira **Deployment** para conocer como desplegar el proyecto.


### Pre-requisitos 馃搵

_Que cosas necesitas para instalar el software y como instalarlas_

```
Java 8
Maven
Postman (opcional)
```

### Instalaci贸n 馃敡

_Generaci贸n un Usuario en SQL Server con las siguientes contrase帽as o modificar el applications.propierties_

```
user:prueba
password:prueba1
port:1433
```

_Generaci贸n del Jar y ejecuci贸n_

_Compilar el Jar_

```
cd BackEnd
maven clean
maven install
```

## Despliegue 馃摝

_Ejecutar el Jar_

```
cd kokonut/target
java -jar kokonut-0.0.1-SNAPSHOT.jar
```

_Esto levantara un servidor tomcat y creara la tablas de la base datos si se conecta correctamente_

_Ejecutar Scripts de la Carpeta Scripts_

```
cd kokonut/target
java -jar kokonut-0.0.1-SNAPSHOT.jar
```

_Solamente falta probar en la carpeta Postman estan los servicios_

_O se puede acceder a la vista de swagger_

```
http://localhost:8080/kokonut/v1/swagger-ui.html
```

_Futura implementaci贸n Uso de docker para agilizar el proceso_

## Construido con 馃洜锔?

_Menciona las herramientas que utilizaste para crear tu proyecto_

* [Java](https://www.java.com/es/) - Lenguaje principal
* [Maven](https://maven.apache.org/) - Manejador de dependencias
* [SpringBoot](https://spring.io/projects/spring-boot) - Framework
* [Postman](https://www.postman.com/) - Pruebas


## Wiki 馃摉

Puedes encontrar mucho m谩s de c贸mo utilizar este proyecto en nuestra [Wiki](https://github.com/tu/proyecto/wiki)

## Versionado 馃搶

Usamos [SemVer](http://semver.org/) para el versionado. Para todas las versiones disponibles, mira los [tags en este repositorio](https://github.com/tu/proyecto/tags).

## Autores 鉁掞笍

_Menciona a todos aquellos que ayudaron a levantar el proyecto desde sus inicios_

* **Carlos Diego Octavio Martinez Macias** - *Trabajo Inicial* - [cdoDiego](https://diegomtzcdo.github.io/portafolio/)

Tambi茅n puedes mirar la lista de todos los [contribuyentes](https://github.com/your/project/contributors) qu铆enes han participado en este proyecto. 

## Licencia 馃搫

Este proyecto est谩 bajo la Licencia (Tu Licencia) - mira el archivo [LICENSE.md](LICENSE.md) para detalles

## Expresiones de Gratitud 馃巵

* Comenta a otros sobre este proyecto 馃摙
* Invita una cerveza 馃嵑 o un caf茅 鈽? a alguien del equipo. 
* Da las gracias p煤blicamente 馃.
* etc.



---
鈱笍 con 鉂わ笍 por [cdoDiego](https://diegomtzcdo.github.io/portafolio/) 馃槉