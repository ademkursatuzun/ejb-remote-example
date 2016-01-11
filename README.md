# ejb-remote-example
@author: akursat

@website: akursat.com
Technologies used
--------------
EJB  
JNDI  
Hibernate  
Jboss 7.1  
Mysql  

Introduction
--------------
I implemented a java enterprise application which cointains enterprise java beans and a remote client application. This application invokes these beans by using JNDI API.  
I used JBOSS as an application server.
The project created using maven with sub-modules (server-side and client)
In server-side there is two session bean one of them Stateful and the other Stateless. They invoked from a remote client in client-side.

Building
--------------
You must edit RemoteClient.java and jboss-ejb-client.properties You must change [username] and [password] to your username and password of the server. The default username is [jboss], password is [123456].
You must also configure jboss with mysql. To achive this, you should follow the link;
https://developer.jboss.org/wiki/DataSourceConfigurationInAS7?_sscc=t 

Here is my SQL query which I use in the project.
```
CREATE TABLE users ( 
username character varying(50) NOT NULL, 
password character varying(50) NOT NULL, 
email character varying(50) NOT NULL, 
birthday date, 
sex smallint, 
enabled boolean, 
CONSTRAINT users_pkey PRIMARY KEY (username) 
)

CREATE TABLE authorities ( 
username character varying(50) NOT NULL, 
authority character varying(50) NOT NULL, 
CONSTRAINT fk_authorities_users FOREIGN KEY (username) 
REFERENCES users (username) 
MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION 
)
```
Deploying
--------------
You just need to put your war file in webapps and then start your server.
Run RemoteBean.java
