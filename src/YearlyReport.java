import java.util.HashMap;
public class YearlyReport {
    public HashMap<Integer, YearStatistic> monthsData;
    Read read = new Read();
    public YearlyReport() {
        monthsData = new HashMap<>();
    }

    public void loadFile(String path) {
        String content = read.readFileContentsOrNull(path); // Считывание файла
            System.out.println("Годовой отчет считан.");
        String[] lines = content.split("\r?\n"); // массив строк
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i]; // строка-01,350000,true
            String[] parts = line.split(","); // 01,350000,true -> ["01", "350000", "true"]
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            if (!monthsData.containsKey(month)) {
                monthsData.put(month, new YearStatistic(month));
            }
            YearStatistic oneMonthData = monthsData.get(month);
            if (isExpense) {
                oneMonthData.expenses += amount;
            } else {
                oneMonthData.income += amount;
            }
        }
    }

    public void printYearlyReport() { // Вывод по году
        if (!monthsData.isEmpty()) {
            System.out.println("Отчет за 2021 год:");
            sumProfit();
            System.out.println("Средний расход- " + averageExpense() + " руб.");
            System.out.println("Средний доход- " + averageProfit() + " руб.");
        } else {
            System.out.println("Отчет не считан. Выполните пункт 2 из меню.");
        }
    }

    public void sumProfit() { // Прибыль

        int profit = 0;
        for (YearStatistic oneMonthData : monthsData.values()) {
            profit += oneMonthData.income - oneMonthData.expenses;
            System.out.println("Прибыль за " + oneMonthData.month + " месяц- " + profit + " руб.");
        }
    }

    public int averageProfit() { // средний доход
        int income = 0;
        int sum = 0;
        for (YearStatistic oneMonthData : monthsData.values()) {
            sum += oneMonthData.income;
            income = sum / 3;
        }
        return income;
    }

    public int averageExpense() { // средний расход
        int expense = 0;
        int sum = 0;
        for (YearStatistic oneMonthData : monthsData.values()) {
            sum += oneMonthData.expenses;
            expense = sum / 3;
        }
        return expense;
    }
}