import java.util.Scanner;

public class Main {
    public static final int month = 3;
    public static void main(String[] args) {
        System.out.println("Добро пожаловать в наше приложение по автоматизации бухгалтерии!");
        Scanner scanner = new Scanner(System.in);
        MonthlyReport monthlyReport = new MonthlyReport();
        YearlyReport yearlyReport = new YearlyReport();
        Checker checker = new Checker();

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            switch (userInput) { // обработка разных случаев
                case 1:// Первый пункт- считывание месячного отчета
                    monthlyReport.loadFile(month);
                    break;

                case 2: // Второй пункт-считывание годового отчета
                        yearlyReport.loadFile("resources/y.2021.csv");
                    break;

                case 3: //Третий пункт- сверка отчетов
                    checker.check(monthlyReport.monthReport, yearlyReport.monthsData);
                    break;

                case 4: // Четвертый пункт- информация о месячных расходах
                    monthlyReport.printMonthlyReport();
                    break;

                case 5: // Пятый пункт- информация о годовых расходах
                    yearlyReport.printYearlyReport();
                    break;

                case 0:  // Выход из программы
                    System.out.println("Выход из программы.");
                    return;

                default:
                    System.out.println("Извините, такой команды пока нет.");
                    break;
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