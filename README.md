## SalesOrderApi

## comand create image
docker build --tag orderapi .

## command run container
docker run --name orderapi -d -p 8080:8080 --link mysqlserver --link salesusernode --link salesproductapi orderapi

## command to run container mysql
docker run -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=123 --name mysqlserver -d -p=3306:3306 mysql/mysql-server:5.7.31


## command to acess and execute commands into mysql in container linux
docker exec -it mysqlserver mysql -u root -p

## link to commands line mysql linux
https://linuxize.com/post/how-to-create-a-mysql-database/

## command CURL via POST (updateAmount)
curl -X POST -H "Content-Type: application/json" -d '[{"id": 1, "amount": 1, "status": "Active", "price": "200"}]' http://localhost:8080/products/


## sequence necessary command maven to put package in container docker
1  -  "/home/francisco/estudos/java/SalesOrderApi/mvnw" clean -f "/home/francisco/estudos/java/SalesOrderApi/pom.xml"
2  -  "/home/francisco/estudos/java/SalesOrderApi/mvnw" clean package  -f "/home/francisco/estudos/java/SalesOrderApi/pom.xml"
3  -  "/home/francisco/estudos/java/SalesOrderApi/mvnw" compile -f "/home/francisco/estudos/java/SalesOrderApi/pom.xml"

## this command put all resources and artfacts necessary to run in another enviroment (how docker)
4  -  "/home/francisco/estudos/java/SalesOrderApi/mvnw" package spring-boot:repackage -f "/home/francisco/estudos/java/SalesOrderApi/pom.xml"






