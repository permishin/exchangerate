# ExchangerRate

Данный репозиторий содержит сервис, который обращается к сервису курсов валют, и отдает gif в ответ.
Сервис построен на базе решений: Spring Boot 2, Gradle, Java, API (Feign), Thymeleaf.

Репозиторий доступен так же в Docker по ссылке https://hub.docker.com/r/permishin/exchangerate

Запуск в среде разработки:

1 Перейти по ссылке https://github.com/permishin/exchangerate и скопировать адрес проекта Code -> Clone (HTTPS) -> https://github.com/permishin/exchangerate.git

2 В среде разработки:

  2.1 Открыть File -> New -> Project from Version Control
  
  2.2 В поле URL вставить адрес проекта, полученный в шаге 1 (при необходимости в поле Directory можно изменить название выгружаемого проекта)
  
  2.3 Нажать кнопку Clone (произойдет загрузка проекта на локальную машину и автоматическое добавление его в среду разработки)
  
3 В терминале среды разработки выполнить Gradle -> clean -> build и запустить исполняемый файл DemoAplication.java
