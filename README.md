# Тестовое задание на вакансию Программист-стажер для компании "ГЕТСИЭРЭМ"
Для выполнения задания я использовал Java 19 с использованием таких библиотек, как:
* **APACHE POI** - для чтения exel-файла (формата .xlsx) со списком достопримечательностей;
* **Lombok** - для упрощения написания геттеров и сеттеров;

Чтобы автоматизировать процесс заполнения данных, я скопировал их в exel таблицу и поместил получившийся xlsx-файл в /resources.

Для расчёта оптимального маршрута я вычислил ценность одного часа при посещении достопримечательности:  
*ценность = важность / затраты времени на посещение*;  
Сортируя по ценности одного часа, мы получаем список наиболее выгодных для посещения достопримечательностей и делаем срез с учётом вводных к заданию (время на сон и т.д.).

Проект состоит из трёх классов:
* **Main** - для запуска и демонстрации работы программы;
* **Route** - класс для хранения данных о достопримечательности (название, затраты на посещение, важность, ценность одного часа);
* **RouteCalculator** - для считывания данных о достопримечательностях из xlsx-файла и расчёта оптимального маршрута;

Для запуска проекта необходмо клонировать его в локальный репозиторий и запустить через IntelliJ IDEA.
## Пример работы программы:
* Exel таблица со списоком достопримечательностей:  
![image](https://github.com/OcolusX/RouteCalculator/assets/90719002/22ecc19f-0359-494f-b388-26a851e609de)

* Результат выполнения (48 ч. общего времени, 16 ч. на сон):  
![image](https://github.com/OcolusX/RouteCalculator/assets/90719002/bbc23d74-0261-4baa-83b1-06571bb2a370)

* Результат выполнения (47 ч. общего времени, 16 ч. на сон):
![image](https://github.com/OcolusX/RouteCalculator/assets/90719002/440c9935-0c87-4364-9b19-404e406f64ea)

* Результат выполнения (52 ч. общего времени, 12 ч. на сон):
![image](https://github.com/OcolusX/RouteCalculator/assets/90719002/e81ef40c-56c3-4f2a-b5ed-95d5ec7b8453)
