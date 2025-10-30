# Установка Java 21

Для сборки проекта требуется Java 21. Выполните следующие команды:

```bash
sudo apt update
sudo apt install -y openjdk-21-jdk
```

После установки проверьте версию:
```bash
java -version
```

Если Java 21 установлена, но не используется по умолчанию, установите её через update-alternatives:
```bash
sudo update-alternatives --config java
```

Или укажите путь к Java 21 в gradle.properties:
```properties
org.gradle.java.home=/usr/lib/jvm/java-21-openjdk-amd64
```

После установки Java 21 проект должен собираться успешно.
