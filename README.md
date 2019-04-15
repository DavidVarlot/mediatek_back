# mediatek_back

* 1 : importer le projet en tant que projet Maven
* 2 : à la racine du projet (ou depuis un IDE) procéder à la commande "mvn install"
* 3 : éditer le fichier : mediatek_back/src/main/resources/application.properties
adapter les valeurs suivantes avec la configuration locale de MongoDB :
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=mediatek
De même le port d'execution de l'API peut être modifié à cet emplacement si besoin.
* 4 : le projet devrait être prêt à être ouvert et exécuté (un serveur tomcat est embarqué), 
les controleurs se trouvent à cet emplacement : src/main/java/com/testDVA/mediatekMongo
