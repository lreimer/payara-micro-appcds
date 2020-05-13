.PHONY: build

clean:
	./gradlew clean

build:
	./gradlew assemble

image/default:
	docker build -t payara-micro-appcds:1.0.0-default -f Dockerfile_default .

image/appcds:
	docker build -t payara-micro-appcds:1.0.0-appcds -f Dockerfile_appcds .

history/default:
	docker history payara-micro-appcds:1.0.0-default

history/appcds:
	docker history payara-micro-appcds:1.0.0-default
