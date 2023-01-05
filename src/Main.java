import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в наше приложение по автоматизации бухгалтерии!");
        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport("resources/y.2021.csv"); //путь к csv файлу
        MonthlyReport monthlyReport = new MonthlyReport();
        monthlyReport.loadFile("resources/m.202101.csv");//путь к csv файлам по месяцам
        monthlyReport.loadFile("resources/m.202102.csv");
        monthlyReport.loadFile("resources/m.202103.csv");

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) { // Первый пункт- считывание месячного отчета
                    if (!monthlyReport.date.isEmpty()) {
                        System.out.println("Месячные отчеты считаны.");
                    }
            } else if (userInput == 2) { // Второй пункт-считывание годового отчета
                if (!yearlyReport.date.isEmpty()) {
                    System.out.println("Годовой отчет считан.");
                }
            } else if (userInput == 3) { //Третий пункт- сверка отчетов
                    if ((monthlyReport.sumExpenses() == yearlyReport.sumExpenses()) && (monthlyReport.sumProfit() == yearlyReport.sumProfit())) {
                        System.out.println("Отчеты сверены. Ошибок не обнаружено.");
                    }
                System.out.println("Проверьте данные по отчетам, в сверке найдены ошибки.");
            } else if (userInput == 4) { // Четвертый пункт- информация о месячных расходах
                    monthlyReport.printMonthlyReport();
            } else if (userInput == 5) { // Пятый пункт- информация о годовых расходах
                yearlyReport.printYearlyReport();
            } else if (userInput == 0) { // Выход из программы
        System.out.println("Выход из программы.");
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() { // Меню
        System.out.println("Выберите необходимое действие:");
        System.out.println("1- Считать все месячные отчёты");
        System.out.println("2- Считать годовой отчёт");
        System.out.println("3- Сверить отчёты");
        System.out.println("4- Вывести информацию о месячных отчётах");
        System.out.println("5- Вывести информацию о годовом отчёте");
        System.out.println("0- Выход");
    }
}