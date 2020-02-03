# ecrc-spring-tiles-template

A basic Spring / Tiles Starter tempate project for eCRC and compatible with Wildfly 10.1.0. 

Contains Wildfly-Maven plugin for deployment tasks. 

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

1. download jdk 1.8.241 (jdk-8u241-windows.exe)
installed in C:\Program Files\Java\jdk1.8.0_241\
2. set system JavaHome to C:\Program Files\Java\jdk1.8.0_241\
	add C:\Program Files\Java\jdk1.8.0_241\ to system path 
3. download sts4 (spring tool suite 4)
4. download wildfly 10.1.0
5. start sts4, window->show view->other->server
6. then follow http://doraprojects.net/blog/?p=3307 to add and configure WildFly 10 server in Spring Tool Suite

### Prerequisites

Wildfly 10.1.0 installed and running during application development. 
Possibly STS4 (Used to create this initial code base). 

Note: Clone the repo then import as a 'Maven' project into STS4. 

### Installing

Install Wildfly 10.1.0 and set up admin user. (Run add-user.bat in the Wildfly bin folder). 

```
mvn clean install
```

## War file creation

```
mvn war:war
```

## Deploy to running Wildfly server

```
mvn wildfly:deploy
```

## Application Entry point

```
http://localhost:8080/ecrc/
```

## Built With

TBD

## Contributing

TBD

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

TBD

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

## Outstanding 


