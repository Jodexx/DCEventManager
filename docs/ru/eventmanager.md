---
sidebar_position: 1
id: dc-event-manager
title: DCEventManager
toc_min_heading_level: 2
toc_max_heading_level: 5
---

# DCEventManager Wiki
Этот аддон позволяет вам управлять ивентами DonateCase</br>
С его помощью вы можете выполнять определенные действия, такие как отправка сообщений и выполнение команд от имени консоли. </br>
**How does it work?</br>**
Аддон прослушивает все события, зарегистрированные в DonateCase.
Получение событий происходит с помощью рефлексии, а это значит, что при обновлении DonateCase новые события будут автоматически загружаться в DCEventManager, без необходимости обновлять аддон! </br>

## Пример config.yml
```yml
Debug: false
Package: "com.jodexindustries.donatecase.api.events" # dont change this

Events:
  MyEvent:
    Event: AnimationStartEvent
    Actions:
      - "[command] say Animation started!"

  MyAnotherEvent:
    Event: AnimationEndEvent
    Actions:
      - "[broadcast] &dAnimation ended!"
```

## Ивенты
> **Список всех доступных событий [здесь](https://repo.jodexindustries.xyz/javadoc/releases/com/jodexindustries/donatecase/DonateCaseAPI/latest/.cache/unpack/com/jodexindustries/donatecase/api/events/package-summary.html)** </br>

## Действия
### Команда
Пример
`- [command] kick _Jodex__`
### Объявление
Пример
`- [broadcast] Hello!`
### Вызов метода
Пример
`- [invoke] setCancelled(true)`

## Поддержка конкретного кейса
Работает, только если у события есть методы `getCaseType` или `getCaseData`
```yaml
Events:
  MyEvent:
    Event: AnimationStartEvent # ваш ивент
    Case: case # здесь конкретный кейс
    Actions:
      - "[command] say Case opened!"
```

## Поддержка конкретного слота кейса
Работает только в том случае, если у события есть метод `getSlot`
```yaml
Events:
  MyEvent:
    Event: CaseGuiClickEvent # ваш ивент
    Case: case # здесь конкретный кейс
    Slot: 1 # здесь конкретный слот
    Actions:
      - "[command] say Gui clicked!"
```

## Заполнители
У DCEventManager есть своя собственная система заполнителей, которая позволяет вам использовать **ВСЕ** методы, предоставляемых событиями.</br>
**Эта система работает с помощью Рефлексии!**</br>

Пример заполнителей:
```yaml
Events:
  AddonDisableEvent: # название класса ивента
    addon: # айди заполнителя
      placeholder: "%addon%" # заменённый плейсхолдер
      method: "getAddon#getName" # метод класса
    reason: # айди заполнителя
      placeholder: "%reason%" # заменённый плейсхолдер
      method: "getReason" # метод класса
    caused: # айди заполнителя
      placeholder: "%caused%" # заменённый плейсхолдер
      method: "getCaused#getName" # метод класса
```

Если вы не разбираетесь в программировании, то это может выглядеть немного запутанно из-за параметра `method`.
Но не разочаровывайтесь, это просто! </br>
Этот параметр используется для определения места, откуда будет взято значение placeholder. </br>

Давайте посмотрим на JavaDocs [AddonDisableEvent](https://repo.jodexindustries.xyz/javadoc/releases/com/jodexindustries/donatecase/DonateCaseAPI/latest/.cache/unpack/com/jodexindustries/donatecase/api/events/AddonDisableEvent.html#method-summary) класса</br>
![img.png](../assets/jd.png)

Здесь мы видим, что класс [AddonDisableEvent](https://repo.jodexindustries.xyz/javadoc/releases/com/jodexindustries/donatecase/DonateCaseAPI/latest/.cache/unpack/com/jodexindustries/donatecase/api/addon/internal/InternalAddon.html#method-summary) имеет несколько интересных методов, таких как: `getAddon`, `getCaused` и `getReason`. </br>
Думаю, вы уже догадались, что эти методы используются для получения значений placeholder, но что такое `#`? </br>
Это символ для разделения методов. Например, мы вызываем метод getAddon, который представляет собой объект [InternalAddon](https://repo.jodexindustries.xyz/javadoc/releases/com/jodexindustries/donatecase/DonateCaseAPI/latest/.cache/unpack/com/jodexindustries/donatecase/api/addon/internal/InternalAddon.html#method-summary), имеющий следующие методы:
![img.png](../assets/addon.png)
Конечно, будет немного странно просто отображать информацию о некоторых [InternalJavaAddon](https://repo.jodexindustries.xyz/javadoc/releases/com/jodexindustries/donatecase/DonateCaseAPI/latest/.cache/unpack/com/jodexindustries/donatecase/api/addon/internal/InternalAddon.html#method-summary) без дополнительных инструкций, но стоит посмотреть, что это за класс, похоже, он представляет собой интерфейс внутреннего аддона, и мы можем извлечь из него некоторую полезную информацию! Давайте узнаем имя этого аддона, похоже, что метод [getName](https://repo.jodexindustries.xyz/javadoc/releases/com/jodexindustries/donatecase/DonateCaseAPI/latest/.cache/unpack/com/jodexindustries/donatecase/api/addon/Addon.html#getName()) как раз подходит нам! </br>
Теперь наш метод выглядит следующим образом: `getAddon#getName`</br>

Если у вас есть дополнительные вопросы, пожалуйста, свяжитесь с нами в [Discord сервере](https://discord.gg/2syNtcKcgR)!