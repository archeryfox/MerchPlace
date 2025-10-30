# Требования к окружению

## Java

Проект требует **Java 21** для компиляции.

### Настройка Java 21

#### Linux
```bash
# Установка через пакетный менеджер (Ubuntu/Debian)
sudo apt update
sudo apt install -y openjdk-21-jdk

# Проверка установки
java -version
```

#### macOS
```bash
# Установка через Homebrew
brew install openjdk@21

# Настройка JAVA_HOME
export JAVA_HOME=$(/usr/libexec/java_home -v 21)
```

#### Windows
1. Скачайте OpenJDK 21 с [adoptium.net](https://adoptium.net/)
2. Установите JDK
3. Настройте переменную окружения `JAVA_HOME`:
   ```
   JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-21.x.x-hotspot
   ```

### Использование через IDE

В Android Studio/IntelliJ IDEA:
1. File → Project Structure → SDKs
2. Добавьте JDK 21 если её нет
3. File → Settings → Build, Execution, Deployment → Build Tools → Gradle
4. Убедитесь, что Gradle JVM использует Java 21

### Gradle Toolchain

Проект использует Gradle toolchain для автоматического определения Java 21:
- Настроен в `app/build.gradle.kts`: `kotlin { jvmToolchain(21) }`
- Плагин `foojay-resolver-convention` в `settings.gradle.kts` автоматически загрузит Java 21, если она не найдена локально

**Примечание:** Если Gradle не может найти Java 21 автоматически, убедитесь, что:
1. Java 21 установлена в системе
2. Переменная окружения `JAVA_HOME` указывает на Java 21
3. Или настройте Java 21 в IDE (см. выше)

