## SalesOrderApi

## comand create image
docker build --tag orderapi .

## command run container
docker run --name orderapi -d -p 8080:8080 --link mysqlserver --link salesusernode --link salesproductapi orderapi
docker run --name orderapi -d -p 8080:8080 --link mysqlserver orderapi

## command to run container mysql
docker run -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=123 --name mysqlserver -d -p=3306:3306 mysql/mysql-server:5.7.31


## command to acess and execute commands into mysql in container linux
docker exec -it mysqlserver mysql -u root -p

## link to commands line mysql linux
https://linuxize.com/post/how-to-create-a-mysql-database/

## command CURL via POST (updateAmount)
curl -X POST -H "Content-Type: application/json" -d '[{"id": 1, "amount": 1, "status": "Active", "price": "200"}]' http://localhost:8080/products/

## command CURL via POST (returnByEmailAndPassword)
curl -X POST -H "Content-Type: application/json" -d '{"id": "1", "email": "frmauro8@gmail.com", "password": "123", "token": ""}' http://localhost:8080/users/

## command CURL via POST (order)
curl -X POST -H "Content-Type: application/json" -d '{"id": 0, "description": "Order 011", "orderStatus": 1, "userId": "611aa80245c2ed2212c3ec3d", "items": [{"id": 1, "description": "Product 001", "quantity": 1, "price": 200, "productId": 1}]}' http://localhost:8080/orders



## sequence necessary command maven to put package in container docker
1  -  "/home/francisco/estudos/java/SalesOrderApi/mvnw" clean -f "/home/francisco/estudos/java/SalesOrderApi/pom.xml"
2  -  "/home/francisco/estudos/java/SalesOrderApi/mvnw" clean package  -f "/home/francisco/estudos/java/SalesOrderApi/pom.xml"
3  -  "/home/francisco/estudos/java/SalesOrderApi/mvnw" compile -f "/home/francisco/estudos/java/SalesOrderApi/pom.xml"

## this command put all resources and artfacts necessary to run in another enviroment (how docker)
4  -  "/home/francisco/estudos/java/SalesOrderApi/mvnw" package spring-boot:repackage -f "/home/francisco/estudos/java/SalesOrderApi/pom.xml"



## Quaries my sql
delete from order_orderitem where order_id in (5, 6, 7);
delete from tb_order_item where id in (5, 6, 7);
delete from tb_order where id in (5, 6, 7);




## ****************   configs databases   **************************
# DATASOURCE (DataSourceAutoConfiguration & DataSourceProperties)
#spring.datasource.url=jdbc:mysql://${MYSQL_HOST:dbmysql}:3306/orderapi


#DEV test
#spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/orderapi

spring.datasource.url=jdbc:mysql://${MYSQL_HOST:mysqlserver}:3306/orderapi

#spring.datasource.url=jdbc:mysql://${MYSQL_HOST:dbmysql}:3306/orderapi

#minikube test
# spring.datasource.url=jdbc:mysql://orderdb:3306/orderapi?useSSL=false
# spring.datasource.username=dbuser
# spring.datasource.password=admin

#Localhost test
  spring.datasource.username=root
  spring.datasource.password=123

#grpc server port
grpc.server.port=9093

# Hibernate

# The SQL dialect makes Hibernate generate better SQL for the chosen database
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect

# Hibernate ddl auto (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto = update

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type=TRACE

