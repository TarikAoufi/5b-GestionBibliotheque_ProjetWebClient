# 5b-GestionBibliotheque_ProjetWebClient
Web Project + EJB 3 + Hibernate 3 + Struts 2 + JSP + Ajax + JBoss AS 7.1 + Oracle 11g

Domaine: Gestion d'une bibliothèque 

    Cette application est composée de 2 projets: EJB Project: biblioServer + Dynamic Web Project: biblioClient

    Dans ce projet Web (biblioClient), il faut utiliser les JAR suivants:
       - commons-fileupload-1.3.1.jar
       - commons-io-2.2.jar
       - commons-lang3-3.2.jar
       - freemarker-2.3.19.jar
       - javassist-3.11.0.GA.jar
       - ognl-3.0.6.jar
       - struts2-core-2.3.20.jar
       - struts2-dojo-plugin-2.3.20.jar
       - xwork-core-2.3.20.jar

    Pour faciliter l'utilisation de ce projet, nous avons copier dans celui-ci les packages: fr.aoufi.clientServer et fr.aoufi.entity; Aulieu d'impoter le JAR du projet biblioServer contenant seulement le package: fr.aoufi.clientServer
       - La création de l'archive sous eclipse : Clic droit sur le projet "biblioServer" puis --> Export > Java > Jar File
       - Ne sélectionner que le package "fr.aoufi.clientServer" (dans lequel on trouve l'interface "IServiceFacade" contenant les
         déclarations des méthodes de Service)
       - Entrez l'adresse de destination de votre JAR puis "suivant" 2 fois
