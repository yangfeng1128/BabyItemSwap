# BabyItemSwap
Java EE /Spring MVC /Maven based dynamic web project

This is a small but functional website made by Java Spring framework.
It allows users to create account and exchange items through the website.

The website was depolyed on Tomcat 8.0 server and MYSQL community server when developed.

Key technical elements/functions of the website:

1. Users can use a Validated login system to create accounts. Account information is stored in database.

2. User can use a form to submit a baby-item offer and upload image of it. Offer information is stored in database.

3. User can use contact the offer lister by sending messages, which is implemented by spring-flow. 
   Numbers of messages can get updated dynamically by Javascript backed up by a Jason server.
   Message information is stored in database.
   
4. Database can be stored on a MYSQL server and hibernate is used to interact with database.

5. Junit tests was assigned a direct DBCP connection while the real service of the website was configured a JDNI datasource.

6. Views of web-pages are trough Apache tiles.

Here are the SQL files to generate databases by forward engineering.
