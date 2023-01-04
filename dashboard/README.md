docker stop $(docker ps -aq) 

docker rm $(docker ps -aq)

docker-compose up -d --build

docker-compose down

docker-compose start

docker network ls

docker network inspect docker_iot