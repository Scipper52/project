import com.fasterxml.jackson.databind.ObjectMapper;
import models.Item;
import models.Project;
import models.TopProject;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


/*
Задание:

Есть REST API сервиса GitHub, который предоставляет ответы на запросы в JSON формате.

Документация на API поиска:     https://developer.github.com/v3/search/

URL запроса:        https://api.github.com/search/repositories?q=tetris

Что требуется:
Найти top10 "популярных" проектов из первых 30 найденных по данному запросу.

В скобках приведены имя свойства и его значимость:
1. Количество форков (forks: +3 за каждый)                                      +
2. Количество людей следящих за проектом (watchers: +1 за каждого)              +
3. Количество открытых дефектов (open_issues: -1 за каждый)                     +
4. ID владельца проекта - нечётный (id: -30)                                    +
5. Размер репозитория (size: +0.1 за каждый мегабайт)                           +
6. Язык программирования Java (language: +5)                                    +
7. Не обновлялся больше двух лет (updated_at: -20)                              +

Авторизации для доступа к API требоваться не должно.
Для решения задачи можно использовать любые сторонние библиотеки.
Результат, по возможности, предоставить в виде maven проекта и в виде jar файла готового к запуску.
Необходимо вывести top10 проектов.
В выводе должно быть полное имя проекта и итоговый "вес" для данного проекта.
 */

public class Main {
    //Функция для вычисления периода времени, прошедшего с момента последнего обновления
    public static Period getPeriod(String tempDate) {
        //Парсинг даты последнего обновления (парсится строка с 0го элемента строки до 10го: "yyyy-mm-dd")
        String[] date = tempDate.substring(0, 10).split("-");
        LocalDate updatedAtDate = LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
        //Возвращение периода между датой последнего обновления и текущей датой
        return Period.between(updatedAtDate, LocalDate.now());
    }
    //Функция расчета рейтинга
    public static TreeSet<TopProject> processing(ArrayList<Item> items) {
        //Сортированное множество
        TreeSet<TopProject> top10 = new TreeSet<>(Comparator.comparingDouble(TopProject::getRank));
        for (int i = 0; i < items.size(); i++) {
            double rank = 0;
            Item item = items.get(i);
            //Условия 1-3, 5
            rank = item.getForks() * 3 + item.getWatchers() - item.getOpenIssues() + 0.1 * (item.getSize() % 1024);
            //Условие 4
            if (item.getId() % 2 != 0) {
                rank -= 30;
            }
            //Условие 6
            if (item.getLanguage().equals("Java")) {
                rank += 5;
            }
            //Условие 7
            if (getPeriod(item.getUpdatedAt()).getYears() > 2) {
                rank -= 20;
            }
            //Заполнение множества.
            //Сначала множество заполняется подряд 10 объектами.
            //Затем, на последующих итерациях, первый элемент множества
            // (множество отсортировано по возрастанию) сравнивается с текущим
            // объектом на итерации по ранку. Если ранк объекта во множестве меньше,
            // чем ранк текущего объекта на итерации, то этот элемент удаляется и
            // во множество добавляется текущий объект итерации.
            if (i < 10) {
                top10.add(new TopProject(rank, item.getName()));
            } else if (rank > top10.first().getRank()) {
                top10.remove(top10.first());
                top10.add(new TopProject(rank, item.getName()));
            }
        }
        return top10;
    }

    public static void main(String[] args) {
        //ObjectMapper используется для считывания JSON с GET запроса
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Project project = objectMapper.readValue(new URL("https://api.github.com/search/repositories?q=tetris"), Project.class);
            ArrayList<Item> items = project.getItems();
            for (TopProject item : processing(items)) {
                System.out.println("|name: " + String.format("%-20s", item.getName()) + "\t rank: " + String.format("%8.1f", item.getRank()) + "|");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
