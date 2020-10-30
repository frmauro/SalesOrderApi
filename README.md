## SalesOrderApi

## comand create image
docker build --tag orderapi .

## command run container 
docker run --name orderapi -d -p 8080:8080 --link mysqlserver orderapi

