# Payara Micro 5.201 with Java 11 AppCDS

A demo repository to showcase the usage of Java 11 AppCDS with Payara Micro 5.201 to improve startup time.

## Build and run with JDK11

```bash
$ ./gradlew kick ass
$ ./gradlew microStart

$ cd build/libs/
$ java -jar payara-micro-appcds-microbundle.jar --rootdir micro-root --outputlauncher

# with Java 11
$ java -XX:DumpLoadedClassList=classes.lst -jar micro-root/launch-micro.jar --deploy payara-micro-appcds.war:/ --warmup
$ java -Xshare:dump -XX:SharedClassListFile=classes.lst -XX:SharedArchiveFile=payara.jsa -jar micro-root/launch-micro.jar

# with Java 13
$ java -XX:ArchiveClassesAtExit=payara.jsa -jar micro-root/launch-micro.jar --deploy payara-micro-appcds.war:/ --warmup

$ java -Xshare:off -jar micro-root/launch-micro.jar --nocluster
$ java -Xshare:on -XX:SharedArchiveFile=payara.jsa -jar micro-root/launch-micro.jar --nocluster
```

## Build and run with Docker

```bash
# build and run without AppCDS enabled
$ docker build -t payara-micro-appcds:1.0.0-default -f Dockerfile_default .
$ docker run -it --rm --cpus 4 --memory 512m -p 8080:8080 -p 9090:9090 payara-micro-appcds:1.0.0-default

# build and run with AppCDS enabled
$ docker build -t payara-micro-appcds:1.0.0-appcds -f Dockerfile_appcds .
$ docker run -it --rm --cpus 4 --memory 512m -p 8080:8080 -p 9090:9090 payara-micro-appcds:1.0.0-appcds

$ docker images | grep payara-micro-appcds
$ docker history payara-micro-appcds:1.0.0-default
$ docker history payara-micro-appcds:1.0.0-appcds
```

## Maintainer

M.-Leander Reimer (@lreimer), <mario-leander.reimer@qaware.de>

## License

This software is provided under the MIT open source license, read the `LICENSE` file for details.


