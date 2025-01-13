# Модуль по работе с контейнерами Docker

Для выполнения лабораторной работы требуется следующее:

1. **Установить Docker на виртуальную машину из первого задания**. Для проверки работоспособности можете запустить контейнер `hello-world`: [https://hub.docker.com/_/hello-world](https://hub.docker.com/_/hello-world)

2. **Выбрать приложение**:
    - Возьмите любое приложение с просторов интернета (подойдет любой рабочий сервис с вашего личного GitHub; если сервиса у вас нет, то возьмите один из репозиториев [https://github.com/somnoynadno/hits-docker-practice](https://github.com/somnoynadno/hits-docker-practice)) и разместите в любой директории.
    - Важным критерием для проекта должна быть зависимость от внешней базы данных (не SQLite), потому что во второй части лабораторной работы часть баллов будет засчитана за правильную настройку контейнера с БД.

3. **Создать Dockerfile**:
    - Создайте `Dockerfile` для сборки своего приложения (пошаговую "инструкцию" для создания контейнера).
    - Файл необходимо разместить в корне проекта, чтобы сборка инициировалась командой `$ docker build .`.

    **Для дополнительных баллов**:
    - Использовать любую alpine-сборку в качестве базового сборочного образа, найденную на [https://hub.docker.com/](https://hub.docker.com/).
    - Через директиву `LABEL` указать maintainer'а (свои контакты) (достаточно фамилию + имя).
    - Перебить дефолтного пользователя контейнера с использованием директивы `USER`.
    - Определить в процессе сборки переменную окружения `ENVIRONMENT` со значением `stage`.
    - Использовать в процессе сборки multi-stage build, состоящий из двух шагов.
    - Разместить в корневой директории `.dockerignore`, чтобы запретить попадание в контейнер файла `docker-compose.yml`, содержимого директории `.git`, а также всех файлов с расширением `.env` и `.md`.
    - Установить и запустить `hadolint` для проверки наличия грубых ошибок в вашем `Dockerfile`; если таковые имеются, то их требуется исправить (важно: `hadolint` должен находиться в PATH и быть исполняемым для всех).

4. **Создать `docker-compose.yml`**:
    - Указать версию спецификации не ниже третьей.
    - Настроить контейнер с БД:
        - Дать сервису название `db` (иначе дальше проверка не пойдет).
        - Найти на [https://hub.docker.com/](https://hub.docker.com/) образ БД с тегом `latest` и включить его в `docker-compose.yml` файл.
        - Настроить название хоста равное `database_host`.
        - Передавать все креды для запуска через директиву `environment`.
        - Через директиву `expose` указать порт БД (при этом сама БД наружу торчать не должна, то есть директиву `ports` использовать не нужно).
        - Настроить `volumes` для персистентного хранения данных на диске.
    - Настроить сборку своего приложения:
        - Дать ему название `app` (иначе дальше проверка не пойдет).
        - Использовать в сборке приложения `Dockerfile` из первой части лабораторной работы.
        - Прокинуть наружу порт 80.
        - Сделать порт 80 доступным только для хостовой машины.
    - Настроить виртуальную сеть между контейнером приложения и БД, чтобы они могли друг с другом общаться.
    - Явно установить подходящую политику для рестарта контейнеров.

5. **Запуск чекера**:
    - После того как всё готово, скачайте и запустите чекер для своей платформы с параметрами `$ docker-checker -name 'Ваше Имя' -project /path/to/project`, указав вторым параметром путь к проверяемому проекту.
    - Чекер доступен для скачивания по ссылке: [https://disk.yandex.ru/d/j9zQGKBHPvah1w](https://disk.yandex.ru/d/j9zQGKBHPvah1w).


Шаг 1. Установка Docker

Установите Docker на виртуальную машину:
```shell
sudo apt update
sudo apt install -y docker.io
```

Убедитесь, что Docker установлен:
```shell
docker --version
```

Запустите тестовый контейнер hello-world:
```shell
sudo docker run hello-world
```


Шаг 2. Подготовка проекта
Скачайте проект из репозитория https://github.com/somnoynadno/hits-docker-practice или используйте свой:
```
git clone https://github.com/somnoynadno/hits-docker-practice.gi
cd hits-docker-practice/java-app
```
Разместите проект в любой директории, например, /home/user/my_project.


Шаг 3. Создание Dockerfile
```docker
# Base build image
FROM maven:3.9.4-eclipse-temurin-17-alpine AS builder
LABEL maintainer="Иван Иванов"
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Runtime image
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENV ENVIRONMENT stage

# Create a non-root user and change to that user
RUN adduser -D myuser
USER myuser

ENTRYPOINT ["java", "-jar", "app.jar"]
```

Шаг 4. Создание .dockerignore
```
.git
*.md
*.env
docker-compose.yml
```

Шаг 5. Установка и запуск Hadolint

Установите Hadolint:
```
wget -O /usr/local/bin/hadolint https://github.com/hadolint/hado
chmod +x /usr/local/bin/hadolint
```

Проверьте ваш Dockerfile:
```hadolint Dockerfile```


Шаг 6. Создание docker-compose.yml
```yml
version: '3.9'

services:
  db:
    image: postgres:latest
    container_name: db
    hostname: database_host
    environment:
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
      POSTGRES_DB: shopdb
    expose:
      - "5432"
    volumes:
      - /var/docker-db-lab:/var/lib/postgresql/data
    restart: always

  app:
    build:
      context: .
      dockerfile: Dockerfile
    container_name: app
    ports:
      - "127.0.0.1:8080:8080"  # Expose to localhost only
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://database_host:5432/shopdb
      SPRING_DATASOURCE_USERNAME: user
      SPRING_DATASOURCE_PASSWORD: password
    depends_on:
      - db
    networks:
      - app_network
    restart: always

volumes:
  db_data:

networks:
  app_network:
  ```

- перезапустить volumes
After making the changes, run:
```
docker-compose down --volumes
docker-compose up --build
```

- Make sure everything is running correctly with:

```shell
docker ps
```

Verify that the app is only accessible locally by visiting http://localhost:8080/swagger-ui/index.html.

Шаг 7. Запуск
```shell
sudo docker-compose up --build
```

Шаг 8. Проверка(checker) - download and use

```
wget https://disk.yandex.ru/d/j9zQGKBHPvah1w -O docker-checker
chmod +x docker-checker
./docker-checker -name 'Иван Иванов' -project /path/to/java-app
```