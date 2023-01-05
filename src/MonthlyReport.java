import java.io.IOException; // Импортируем необходимое
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class MonthlyReport { // Месячные отчеты
    public HashMap<String, MonthStatistic> date = new HashMap<>(); // Создаем хеш-таблицу: мес и доходы, расходы
    public void loadFile(String path) { // Проводим считывание информации из csv файлов
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n"); // Проходимся по строкам
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(","); // Проходимся внутри строки
            String item = parts[0]; // для первой строки- коньки
            boolean isExpense = Boolean.parseBoolean(parts[1]); // для первой строки- TRUE
            int quantity = Integer.parseInt(parts[2]); // для первой строки- 50
            int sum_of_one = Integer.parseInt(parts[3]); // для первой строки- 2000
            if (!date.containsKey(item)) {
                date.put(item, new MonthStatistic());
            }
            MonthStatistic statistic = date.get(item);
            if (isExpense) {
                statistic.expenses = quantity * sum_of_one; //расход
            } else {
                statistic.profit = quantity * sum_of_one; // доход
            }
        }
    }

    public void printMonthlyReport() { // Печааем информацию по месяцам

        System.out.println("Самый прибыльный товар:");
        maxProfit();
        System.out.println("Самая большая трата:");
        maxExpense();
    }

    public void maxProfit() { // Максимальный доход
        int maxProfit = 0;
        String nameMaxProfit = "";
        for (String item: date.keySet()) {
            MonthStatistic statistic = date.get(item);
            if (maxProfit < statistic.profit) {
                maxProfit = statistic.profit;
                nameMaxProfit = item;
            }
        }
        System.out.println(nameMaxProfit + "- " + maxProfit + " руб."); // Печатаем максимальных доход по товару
    }

    public void maxExpense() { // Максимальная трата
        int maxExpenses = 0;
        String nameMaxExpenses = "";
        for (String item : date.keySet()) {
            MonthStatistic statistic = date.get(item);
            if (maxExpenses < statistic.expenses) {
                maxExpenses = statistic.expenses;
                nameMaxExpenses = item;
            }
        }
        System.out.println(nameMaxExpenses + "- " + maxExpenses + " руб."); // Печатаем максимальный расход по товару
    }

    public int sumExpenses() { // Сумма расходов
        int sumExpenses = 0;
        for (String item : date.keySet()) {
            MonthStatistic statistic = date.get(item);
            sumExpenses += statistic.expenses;
        }
        return sumExpenses;
    }


    public int sumProfit() { // Сумма доходов
        int sumProfit = 0;
        for (String item : date.keySet()) {
            MonthStatistic statistic = date.get(item);
            sumProfit += statistic.profit;
        }
        return sumProfit;
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