USERNAME := $(shell whoami)

DB_USER := postgres
DB_PASS := secret
DB_NAME := tree_walk
DB_PORT := 5433

build: 
	podman build --build-arg --build-arg APP_DIR=TreeWalker -t treewalker-image -f Dockerfile .
run-db:
	podman run --rm --name postgres-container \
		-e POSTGRES_USER=${DB_USER} \
		-e POSTGRES_PASSWORD=${DB_PASS} \
		-e POSTGRES_DB=${DB_NAME} \
		-p ${DB_PORT}:5432 \
		-d docker.io/library/postgres:16

run-treewalker:
	until podman exec postgres-container pg_isready -U ${DB_USER} -d ${DB_NAME}; do echo "Waiting for Postgres..."; sleep 1; done
	podman run --rm -d --name treewalker-container \
		-e DB_URL=jdbc:postgresql://host.containers.internal:${DB_PORT}/${DB_NAME} \
		-e DB_USERNAME=${DB_USER} \
		-e DB_PASSWORD=${DB_PASS} \
		-e HOST_USERNAME=${USERNAME} \
		-v /:/host:ro \
		-p 8080:8080 \
		treewalker-image

run-all: run-db run-treewalker

stop:
	podman stop postgres-container || true
	podman stop treewalker-container || true

build-and-run: build run-all
