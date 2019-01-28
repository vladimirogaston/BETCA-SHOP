# Tecnología Spring: Proyecto TPV - Back-end
#### Back-end con Tecnologías de Código Abierto (SPRING)
#### [Máster en Ingeniería Web por la U.P.M.](http://miw.etsisi.upm.es)

[![Build Status](https://travis-ci.org/miw-upm/betca-tpv-spring.svg?branch=develop)](https://travis-ci.org/miw-upm/template-spring5)
![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=es.upm.miw%3Atemplate-spring5&metric=alert_status)

> Plantilla de proyecto Back-end con la tecnología Spring5.  

### Tecnologías necesarias
* Java
* Maven
* Spring
* Mongodb

### Clonar en repositorio en tu equipo mediante consola:
1. Situarse en una carpeta raíz donde se encuentran los proyectos, mediante la consola:  
 **>cd %ruta-de-la-carpeta%**
1. Clonar el repositorio, se visualizará el contenido de la rama por defecto:  
 **>git clone https://github.com/miw-upm/???**
 
 ### Importar el proyecto mediante IntelliJ IDEA
1. **Import Project**, y seleccionar la carpeta del proyecto
1. Marcar **Create Project from external model**, elegir **Maven**
1. **Next** … **Finish**

### Cliente Swagger 
> [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### Reproducir este proyecto
1. Crear el proyecto  
   - Bajarse la plantilla (con todas las dependencias): <https://github.com/miw-upm/template-spring5/blob/master/docs/template-spring5.zip>
   - Descomprimir en una carpeta llamada como el proyecto, y editar el *pom.xml* para cambiar el nombre del artefacto  
   - Importar la carpeta desde *IntelliJ*
1. Crear repositorio
   - Instalar *Git CLI* <https://git-scm.com/download>
   - Desde consola, crear el repositorio local, con las ramas *master* y *develop*
       - `>git init`
       - `>git add --all`
       - `>git commit -m "Initial template-spring5"`  
       - `>git checkout –b develop`
1. Conectar con GitHub 
   - Crear el repositorio remoto en *Github* <https://github.com>
   - Conectarlo con repositorio local
       - `>git remote add origin <url-repository>`
       - `>git push --all`
       - `>git config credential.helper store`
1. Conectar con *Travis-CI*
   - Crear una cuenta en Travis-CI <https://travis-ci.org>
   - Activa el seguimiento del repositorio de *GitHub*
      - `>My Account>Settings`
   - Configurar el fichero *.travis.yml*
   - Realizar *push* de la rama *develop* y comprobar que se ejecuta *Travis-CI*
      - `>git push origin develop`
1. Conectar con Sonarcloud
   - Crear una cuenta en *Sonarcloud* <https://sonarcloud.io>
   - Obtener el *ApiKey* de *Sonarcloud*
      - `>My Account>Security`
   - Definir una variable de entorno: *SONAR-ApiKey* asociada al proyecto en *Travis-CI*
      - `>More options>Settings>Environment Variables`
   - Configurar *.travis.yml* con la conexión de *Sonar*
      - `- mvn sonar:sonar -Dsonar.host.url=https://sonarcloud.io -Dsonar.organization=<organization> -Dsonar.login=$SONAR`
   - Realizar *push* en *develop* y comprobar que se dispara *Sonar* adecuadamente
      - `>git push origin develop`
1. Conectar con Heroku
   - Crear una cuenta en *Heroku* <https://www.heroku.com>
   - Definir en el proyecto un API con los correspondientes Tests
   - Configurar para *Swagger (Springfox)* <https://github.com/miw-upm/template-spring5/blob/master/src/main/java/es/upm/miw/config/SwaggerConfig.java>
   - Realizar *push* en la rama *develop* y comprobar que todo es correcto
      - `>git push origin develop`
   - Instalar *Heroku CLI* <https://devcenter.heroku.com/articles/heroku-cli#download-and-install>
   - Crear una cuenta en *Heroku* <https://www.heroku.com>
   - Obtener el *ApiKey* de *Heroku*
      - `>Account settings>API Key`
   - Crear una variable de entorno: *HEROKU-ApiKey* asociada al proyecto en *Travis-CI*
      - `>More options>Settings>Environment Variables`
   - Configurar .travis.yml con el despliegue en Heroku
      -`deploy:`
         -`provider: heroku`
         - `api_key:`
            - `secure: $HEROKU`
   - Realizar *push* en la rama master y comprobar que se despliega en *Heroku* adecuadamente
      - `>git push origin master`
   - Comprobar los logs de Heroku a través de consola
      - `>heroku login`
	  - `>heroku logs -app=myApp`
1. Conectar con MongoDB
   - Instalar *Mongodb* y arrancar el motor <https://www.mongodb.com/download-center/community>
   - Crear un API con acceso a BD con los test correspondientes
   - Ejecutar en local para comprobar los test
   - Configurar el *.travis.yml* con el servicio de mongoDB
      - `services:`
         - `- mongodb`
   - Realizar *push* en *develop* para comprobar que todo va bien
      - `>git push origin develop`
   - Crear una cuenta en *mLab* <https://mlab.com>
   - Obtener la URI de una base de datos
   - Añadir en *Heroku* un *add-ons* de *mLab*
      -`>project>Overview`
   - Crear una variable de entorno: *MONGODB_URI* asociada al proyecto en *Heroku*
      -`>project>Settings`
   - Configurar el fichero de propiedades con la uri de BD
      -`spring.data.mongodb.uri=${MONGODB_URI}`
   - Realizar *push* en la rama *master* y comprobar que se despliega en *Heroku* adecuadamente con acceso a BD
      - `>git push origin master`
   - Comprobar los logs de Heroku a través de consola
      - `>heroku login`
	  - `>heroku logs -app=myApp`
###### Autor: Jesús Bernal Bermúdez U.P.M.

