# Jokul Java Example

Official Demo Application for Java Library for [Jokul Java Library](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-library). Visit [https://jokul.doku.com](https://jokul.doku.com) for more information about the product and [https://jokul.doku.com/docs](https://jokul.doku.com/docs) for the technical documentation.

## Requirements

Java 11 or above

## Running Application

Insta run with docker :

at root project directory buil docker image 
```
docker build -t javaexample .
```
Run docker with this command
```
docker run -p 8080:8080 javaexample

open  : http://localhost:8080/demo/java-library/
```

Run project with spring-boot:run 

first step clone and build repository [Jokul Java Library](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-library). to build mvn dependency

```
        <dependency>
            <groupId>com.doku</groupId>
            <artifactId>java-library</artifactId>
            <version>2.0.0</version>
            <scope>compile</scope>
        </dependency>
```
Run this command if already install java-library dependency in m2 local repository:
```
mvn spring-boot:run 
```
This application will run in port 8080, you will see the Demo App:

## Demo Application Video
Click the Video : <br />

[<img src="https://img.youtube.com/vi/FX0bcR-RN6Q/hqdefault.jpg" width="50%">](https://youtu.be/n1K80uMt358)

## Help and Support

Got any issues? Found a bug? Have a feature requests? You can [open new issue](https://github.com/PTNUSASATUINTIARTHA-DOKU/jokul-java-example/issues/new).

For further information, you can contact us on [care@doku.com](mailto:care@doku.com).
