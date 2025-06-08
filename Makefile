USERNAME := $(shell whoami)

DB_USER := postgres
DB_PASS := secret
DB_NAME := tree_walk
DB_PORT := 5432

build: 
	podman build --build-arg USERNAME=${USERNAME} --build-arg APP_DIR=TreeWalker -t treewalker-image -f Dockerfile .
	podman build --build-arg USERNAME=${USERNAME} --build-arg APP_DIR=History -t history-image -f Dockerfile .
run-db:
	podman run --rm --name postgres-container \
		-e POSTGRES_USER=${DB_USER} \
		-e POSTGRES_PASSWORD=${DB_PASS} \
		-e POSTGRES_DB=${DB_NAME} \
		-p ${DB_PORT}:5432 \
		-d docker.io/library/postgres:16

run-treewalker:
	podman run --rm --name treewalker-container \
		-e DB_URL=jdbc:postgresql://localhost:${DB_PORT}/${DB_NAME} \
		-e DB_USERNAME=${DB_USER} \
		-e DB_PASSWORD=${DB_PASS} \
		-p 8080:8080 \
		treewalker-image

run-history:
	podman run --rm --name history-container \
		-e DB_URL=jdbc:postgresql://localhost:${DB_PORT}/${DB_NAME} \
		-e DB_USERNAME=${DB_USER} \
		-e DB_PASSWORD=${DB_PASS} \
		-p 8081:8080 \
		history-image

run-all: run-db run-treewalker run-history

stop:
	podman stop postgres-container || true
	podman stop treewalker-container || true
	podman stop history-container || true
