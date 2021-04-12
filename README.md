## SalesOrderApi

## comand create image
docker build --tag orderapi .

## command run container
docker run --name orderapi -d -p 8080:8080 --link mysqlserver --link salesusernode --link salesproductapi orderapi

## command to run container mysql
docker run -e MYSQL_ROOT_HOST=% -e MYSQL_ROOT_PASSWORD=123 --name mysqlserver -d -p=3306:3306 mysql/mysql-server:5.7.31


## command to acess and execute commands into mysql in container linux
docker exec -it mysqlserver mysql -u root -p

