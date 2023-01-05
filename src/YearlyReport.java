import java.io.IOException; // Импортируем необходимое
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class YearlyReport {
    public HashMap<Integer, YearStatistic> date = new HashMap<>(); // Создаем хеш-таблицу: мес и доходы, расходы
    int numberOfMonth=3;

     public YearlyReport(String path) {
         String content = readFileContentsOrNull(path);
         String[] lines = content.split("\r?\n");
         for (int i = 1; i < lines.length; i++) {
             String line = lines[i];
             String[] parts = line.split(",");
             int month = Integer.parseInt(parts[0]); // для первой строки-  01
             int amount = Integer.parseInt(parts[1]); // для первой строки- 1593150
             boolean isExpense = Boolean.parseBoolean(parts[2]); //для первой строки- false
             if (!date.containsKey(date)) {
                 date.put(month, new YearStatistic());
             }
             YearStatistic statistic = date.get(month);
             if (isExpense) {
                 statistic.expenses += amount; //расходы
             } else {
                 statistic.profit += amount; //доходы
             }
         }
     }

    public void printYearlyReport() { // Печать нужной информации
        System.out.println("Отчет за 2021 год.");
        for (Integer numMonth : date.keySet()) {
            System.out.println("За месяц № " + numMonth +":");
            YearStatistic statistic = date.get(numMonth);
            System.out.println("Прибыль- " + (statistic.profit - statistic.expenses)+" руб.");
        }

        System.out.println("Средний расход за все месяцы в году: " + sumExpenses()/numberOfMonth + " руб.");
        System.out.println("Средний доход за все месяцы в году: " + sumProfit()/numberOfMonth + " руб.");
    }

    public int sumProfit() { // Сумма доходов
        int sumProfit = 0;
        for (Integer numMonth : date.keySet()) {
            YearStatistic statistic = date.get(numMonth);
            sumProfit += statistic.profit;
        }
        return sumProfit;
    }


    public int sumExpenses() { // Сумма расходов
        int sumExpenses = 0;
        for (Integer numMonth : date.keySet()) {
            YearStatistic statistic = date.get(numMonth);
            sumExpenses += statistic.expenses;
        }
        return sumExpenses;
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}