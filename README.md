Тестовое задание для вакансии "Junior Java разработчик".
_Backend-часть социальной сети (для размещения постов пользователями и общения)._

**Исполнитель**:
Екатерина Шульга

**Реализованный функционал:**
- Авторизация и аутентификация пользователей(большая часть функционала доступна только пользовалям прошедшим
авторизацию и аутентификацию).
- Возможна дальнейшее проработка распределения ролей между пользователями: пользователь и администратор.
  (у пользователя есть поле ROLE).
- Пользоатель может изменить свой пароль.
- CRUD для размещения постов: пользователь может добавлять, редактировать, удалять свои посты.
- Пользователь может запросить список своих постов.
- Пользователь может запросить пост по заголовку или почте(она же userName) любого пользователя.
- Пользователь может добавлять себе в друзья других пользователей (не может добавлять в друзья 
одного и того же пользователя несколько раз или пользователя, которого не существует, а также 
самого себя).


**Технологии, использованные в проекте:**<br>
Язык: Java<br>
Фреймворк: Spring Boot<br>
Аутентификация и авторизация: Spring Security
Документация: Swagger
СУБД: PostgreSQL
Тестирование: Mockito(юнит тесты)