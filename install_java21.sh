#!/bin/bash
echo "=========================================="
echo "Установка Java 21 для проекта MerchPlace"
echo "=========================================="
echo ""
echo "Выполняется обновление списка пакетов..."
sudo apt update

echo ""
echo "Установка OpenJDK 21..."
sudo apt install -y openjdk-21-jdk

echo ""
echo "Проверка установки..."
/usr/lib/jvm/java-21-openjdk-amd64/bin/java -version

echo ""
echo "=========================================="
echo "Java 21 установлена!"
echo "=========================================="
echo ""
echo "Теперь можно собрать проект командой:"
echo "  ./gradlew :app:assembleDebug"
echo ""
echo "Если сборка все еще не работает, выполните:"
echo "  ./gradlew --stop"
echo "  ./gradlew :app:assembleDebug"
