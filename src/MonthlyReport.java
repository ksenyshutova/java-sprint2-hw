import java.util.HashMap;

public class MonthlyReport {
    public HashMap<Integer, HashMap<String, MonthStatistic >> monthReport;
    Read read= new Read();
    public MonthlyReport() {
        monthReport = new HashMap<>();
    }
    public void loadFile(int a) {

        for(int b = 1; b <= a; b++) {
            HashMap<String, MonthStatistic> monthContents = new HashMap<>();
            String monthPath = "resources/m.20210" + b + ".csv"; // Месячные отчеты
            String content = read.readFileContentsOrNull(monthPath); // Считывание файла
                System.out.println("Отчет за месяц считан.");
            String[] lines = content.split("\r?\n"); // массив строк
            for (int i = 1; i < lines.length; i++) {
                MonthStatistic monthStatisctic = new MonthStatistic();
                String line = lines[i];
                String[] parts = line.split(",");
                String itemName = parts[0];// для первой строки- коньки
                monthStatisctic.name = itemName;
                monthStatisctic.isExpense = Boolean.parseBoolean(parts[1]);// для первой строки- TRUE
                monthStatisctic.quantity  = Integer.parseInt(parts[2]); // для первой строки- 50
                monthStatisctic.sum_of_one = Integer.parseInt(parts[3]); // для первой строки- 2000

                monthContents.put(itemName, monthStatisctic);
            }
            monthReport.put(b, monthContents);
        }
    }

    public void printMonthlyReport() {
        if (!monthReport.isEmpty()) {
            System.out.println("Отчеты по месяцам:");
            System.out.println("Январь:");
            System.out.println("Самый прибыльный товар- " + maxProfit(1) + " руб.");
            System.out.println("Самая большая трата- " + maxExpense(1) + " руб.");
            System.out.println("Февраль:");
            System.out.println("Самый прибыльный товар- " + maxProfit(2) + " руб.");
            System.out.println("Самая большая трата- " + maxExpense(2) + " руб.");
            System.out.println("Март:");
            System.out.println("Самый прибыльный товар- " + maxProfit(3) + " руб.");
            System.out.println("Самая большая трата- " + maxExpense(3) + " руб.");
        } else {
            System.out.println("Отчеты не считаны. Выполните пункт 1 из меню.");
        }
    }

    public HashMap<String, Integer> maxProfit(int month) {  // Самый прибыльный товар
        HashMap<String, Integer> MaxIncome = new HashMap<>();
        int maxIncome = 0;
        String key = " ";
        int sum = 0;
        HashMap<String, MonthStatistic> oneMonthReport = monthReport.get(month);
        for (MonthStatistic oneMonth : oneMonthReport.values()) {
            if (!oneMonth.isExpense) {
                sum = oneMonth.quantity * oneMonth.sum_of_one;
            }
            if (maxIncome < sum) {
                maxIncome = sum;
                key = oneMonth.name;
            }
        }
        MaxIncome.put(key, maxIncome);
        return MaxIncome;
    }

    public HashMap<String, Integer> maxExpense(int month) { // Самая большая трата
        HashMap<String, MonthStatistic> oneMonthReport = monthReport.get(month);
        HashMap<String, Integer> MaxExpense = new HashMap<>();
        int maxExpense = 0;
        String key = " ";
        int sum = 0;
        for (MonthStatistic oneMonth : oneMonthReport.values()) {
            if (oneMonth.isExpense) {
                sum = oneMonth.quantity * oneMonth.sum_of_one;
            }
            if (maxExpense < sum) {
                maxExpense = sum;
                key = oneMonth.name;
            }
        }
        MaxExpense.put(key, maxExpense);
        return MaxExpense;
    }
}