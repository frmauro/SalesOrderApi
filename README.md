## SalesOrderApi

## comand create image
docker build --tag orderapi .

## command run container 
docker run --name orderapi -d -p 8080:8080 --link mysqlserver orderapi

## command to acess and execute commands into mysql in container linux
docker exec -it mysqlserver mysql -u root -p

