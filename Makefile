docker-start:
	docker-compose -f ./dockerfiles/docker-compose.yml up -d
docker-stop:
	docker-compose -f ./dockerfiles/docker-compose.yml down -v
docker-remove-all:
	docker system prune -a --volumes