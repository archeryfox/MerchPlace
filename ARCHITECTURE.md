# Архитектура проекта MerchPlace

## Обзор

Проект использует **Clean Architecture** с разделением на слои:
- **Domain** - бизнес-логика (не зависит от других слоев)
- **Data** - реализация репозиториев (зависит только от Domain)
- **Presentation** - UI слой (зависит от Domain и Data)
- **Shared** - общие компоненты (DI, UI компоненты)

## Структура слоев

### Domain слой (`domain/`)

**Чистый Kotlin, без Android зависимостей**

- `entities/` - Domain модели (Product, Auction, User, CartItem, Lottery, FeedPost)
- `repository/` - Интерфейсы репозиториев (ProductRepository, AuctionRepository, etc.)

**Правила:**
- ✅ Не зависит от других слоев
- ✅ Использует только стандартные библиотеки Kotlin
- ✅ Определяет контракты (интерфейсы) для работы с данными

### Data слой (`data/`)

**Реализация репозиториев**

- `repository/` - Реализации интерфейсов из Domain
- `datasource/mock/` - Mock данные для разработки

**Правила:**
- ✅ Зависит только от Domain слоя
- ✅ Реализует интерфейсы из `domain.repository`
- ✅ Использует mock данные или API (в будущем)

**Пример:**
```kotlin
class ProductRepositoryImpl : ProductRepository {
    override fun getProducts(): Flow<List<Product>> {
        // Использует MockProducts из datasource
    }
}
```

### Presentation слой (`presentation/`)

**UI и логика представления**

- `screens/` - Экраны приложения
- `widgets/` - Переиспользуемые UI компоненты
- `features/` - Изолированные фичи (CartViewModel)
- `navigation/` - Навигация

**Правила:**
- ✅ Зависит от Domain и Data слоев
- ✅ ViewModels используют **интерфейсы репозиториев**, а не реализации
- ✅ **НЕ использует mock данные напрямую** - только через репозитории
- ✅ Использует `RepositoryProvider` для получения репозиториев

**Пример правильного использования:**
```kotlin
class ShopViewModel(
    private val repository: ProductRepository = RepositoryProvider.productRepository
) : ViewModel() {
    // Использует интерфейс, а не реализацию
}
```

### Shared слой (`shared/`)

**Общие компоненты**

- `di/` - Dependency Injection (RepositoryProvider)
- `ui/` - Переиспользуемые UI компоненты (Card, Badge, Avatar)

## Dependency Injection

В проекте используется `RepositoryProvider` в `shared/di/` для предоставления репозиториев.

**Принципы:**
- ViewModels получают репозитории через конструктор
- По умолчанию используется `RepositoryProvider`
- В будущем можно заменить на Hilt/Koin

**Пример:**
```kotlin
object RepositoryProvider {
    val productRepository: ProductRepository get() = ProductRepositoryImpl()
    val auctionRepository: AuctionRepository get() = AuctionRepositoryImpl()
    // ...
}
```

## Направление зависимостей

```
Presentation → Domain ← Data
     ↓                      ↓
  Shared                Mock Data
```

**Правила:**
1. Domain не зависит ни от кого ✅
2. Data зависит только от Domain ✅
3. Presentation зависит от Domain и может использовать Data через интерфейсы ✅
4. Shared может использоваться всеми слоями ✅

## Текущие нарушения и исправления

### ✅ Исправлено

1. **ViewModels используют интерфейсы репозиториев** вместо реализаций
2. **Создан RepositoryProvider** для DI
3. **ProfileScreen использует ProfileViewModel** вместо прямого доступа к mock данным

### ⚠️ Требует внимания

1. **ProfileTabs** - все еще использует ViewModels для фильтрации данных пользователя (работает, но можно улучшить)
2. **FeedScreen** - использует mock данные напрямую (нужен FeedRepository в Domain)
3. **Preview функции** - используют mock данные напрямую (допустимо для Preview)

## Следующие шаги

1. Создать `FeedRepository` в Domain слое
2. Реализовать `FeedRepositoryImpl` в Data слое
3. Обновить `FeedScreen` для использования ViewModel
4. Рассмотреть добавление Hilt/Koin для полноценного DI

