# Базовый image
FROM eclipse-temurin:17-jdk-alpine

# Переменная, в которой указывается путь к jar- архиву
ARG JAR_FILE=target/*.jar

#Задаётся рабочая директория, в которой будут выполняться дальнейшие команды (перемещаемся в папку app)
WORKDIR /opt/app

#Jar-файл с локального хоста (путь до него задан в переменной JAR_FILE) копируется в папку app, копии задаётся имя app.jar
COPY ${JAR_FILE} app.jar

# Команда запуска приложения
ENTRYPOINT ["java","-jar","app.jar"]

#docker build -t graksergey/spring-boot-brt:latest .
#docker run -p 8081:8081 graksergey/spring-boot-brt:latest