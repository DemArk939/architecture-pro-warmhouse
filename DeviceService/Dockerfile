# Используем официальную версию OpenJDK с поддержкой Alpine Linux
FROM openjdk:17

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем упакованный .jar файл внутрь контейнера
COPY target/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8085

# Запускаем приложение
ENTRYPOINT ["java", "-jar","/app/app.jar"]
