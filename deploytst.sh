#!/bin/bash
########################################################

## Shell Script to Build Docker Image and run.   

########################################################


result=$( docker images -q orderapi )
if [[ -n "$result" ]]; then
echo "image exists"
 docker rmi -f orderapi
else
echo "No such image"
fi

echo "build the docker image"
echo "built docker images and proceeding to delete existing container"

result=$( docker ps -q -f name=orderapi )
if [[ $? -eq 0 ]]; then
echo "Container exists"
 docker container rm -f orderapi
echo "Deleted the existing docker container"
else
echo "No such container"
fi

cp -a /home/francisco/estudos/azuredevops/myagent/_work/9/s/.  /home/francisco/estudos/azuredevops/myagent/_work/r8/a/

docker build -t orderapi .

echo "built docker images and proceeding to delete existing container"
echo "Deploying the updated container"

docker run --name orderapi -d -p 8080:8080 -p 9093:9093 --link mysqlserver orderapi

echo "Deploying the container"