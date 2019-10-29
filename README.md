# HappyMall Store
This is the project of project managment course code# cs-490

==============================================================
Technologies used: (Spring Boot, Thymeleaf, JavaScript, MySQL)
==============================================================
Setup: 
  1- create an empty schema named "happymall" in your MySQL Workbench.
  2- change these two lines in happymall/application.properties file 
      "spring.datasource.initialization-mode=never
      spring.jpa.hibernate.ddl-auto=update"
    to (always, create) just for the first run to create the DB tables in happymall DB.
  3- change "spring.datasource.password=root" to whatever password you are using for your DB root.
        (application.propertis file in happymall & in webservices)
  4- Run /HappyMall/HappyMallApplication.java file and make sure that the tabels are created.
  5- change it back to this 
      "spring.datasource.initialization-mode=never
      spring.jpa.hibernate.ddl-auto=update"
  6- rerun the /HappyMall/HappyMallApplication.java and /webservice/WebserviceApplication.java.
  7- Enjoy using out Application.

===============================================================
