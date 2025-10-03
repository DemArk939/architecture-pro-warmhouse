# Используем официальную версию OpenJDK с поддержкой Alpine Linux
FROM openjdk:17

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем все файлы проекта в контейнера
COPY . /app

# собираем jar файл
RUN ./mvnw package -DskipTests

# Expose the port the app runs on
EXPOSE 8085

# Запускаем приложение
ENTRYPOINT ["java", "-jar","target/DeviceService-0.0.1-SNAPSHOT.jar"]
