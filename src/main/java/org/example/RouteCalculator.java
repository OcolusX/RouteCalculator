package org.example;

import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@AllArgsConstructor
public class RouteCalculator {

    // Путь к xlsx-файлу с достопримечательностями
    private final String path;

    /**
     * Рассчитывает оптимальный маршрут, исходя из ценности одного часа на посещения
     *
     * @param routeTime - общее время на маршрут (кол-во часов)
     * @param sleepTime - время на сон (кол-во часов)
     * @param sleepNum  - сколько раз нужно поспать
     * @return - отсортированный по важности список оптимального маршрута
     * @throws IOException - если возникла ошибка при чтении xlsx-файла с достопримечательностями
     */
    public List<Route> calculateOptimalRouteList(double routeTime, double sleepTime, int sleepNum) throws IOException {
        List<Route> optimalRouteList = new LinkedList<>();

        // Получаем список достопримечательностей из xlsx файла и сортируем по ценности одного часа
        List<Route> routeList = readRouteExelList(path);
        routeList.sort(((o1, o2) -> Double.compare(o2.getHourValue(), o1.getHourValue())));

        // Рассчитываем время, оставшееся на посещение достопримечательностей
        double timeLeft = routeTime - sleepTime * sleepNum;

        // Оптимальный маршрут достигается засчёт ценности одного часа,
        // поэтому результирующий список есть срез отсортированного раннее списка достопримечательнотей.
        // Однако очередная достопримечательность может не поместится по времени, поэтому её нужно пропустить.
        // Таким образом, последним элементом может быть не самая выгодная (по ценности одного часа), а та,
        // которая поместится по времени
        int i;
        for (i = 0; i < routeList.size(); i++) {
            Route route = routeList.get(i);
            if (timeLeft - route.getSpentTime() < 0.0) {
                continue;
            }
            optimalRouteList.add(route);
            timeLeft -= route.getSpentTime();
        }

        // Сортируем результирующий список по важности
        optimalRouteList.sort(((o1, o2) -> Integer.compare(o2.getPriority(), o1.getPriority())));
        return optimalRouteList;

    }

    /**
     * Читает xlsx-файл с достопримечательностями
     *
     * @param file - путь к xlsx-файлу
     * @return - список достопримечательностей
     * @throws IOException - если возникла ошибка при чтении файла
     */
    private static List<Route> readRouteExelList(String file) throws IOException {
        List<Route> routeList = new ArrayList<>();

        XSSFWorkbook exelRoutes = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet exelRouteList = exelRoutes.getSheet("routeList");

        Iterator<Row> rowIterator = exelRouteList.iterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getLastCellNum() == -1) {
                continue;
            }

            Iterator<Cell> cellIterator = row.iterator();
            Route route = new Route(
                    cellIterator.next().getStringCellValue(),
                    cellIterator.next().getNumericCellValue(),
                    (int) cellIterator.next().getNumericCellValue()
            );
            routeList.add(route);
        }
        exelRoutes.close();
        return routeList;
    }
}