 import java.util.HashMap;
    public class Checker {
        public void check(HashMap<Integer, HashMap<String, MonthStatistic>> mon, HashMap<Integer, YearStatistic> year){

            if (mon.isEmpty() && year.isEmpty()) {
                System.out.println("Отчеты не считаны.");
            } else if (mon.isEmpty()) {
                System.out.println("Месячные отчеты не считаны. Выполните пункт 1 из меню.");
            } else if (year.isEmpty() ) {
                System.out.println("Годовой отчет не считан. Выполните пункт 2 из меню.");
            } else {
                int incomeOne = sumIncome(mon.get(1)); // сумма доходов по месяцам
                int incomeTwo = sumIncome(mon.get(2));
                int incomeThree = sumIncome(mon.get(3));
                int expenseOne = sumExpense(mon.get(1)); // сумма расходов по месяцам
                int expenseTwo = sumExpense(mon.get(2));
                int expenseThree = sumExpense(mon.get(3));

                YearStatistic monthOne = year.get(1); // годовой отчет за 1-3 мес
                YearStatistic monthTwo = year.get(2);
                YearStatistic monthThree = year.get(3);

                if ((incomeOne == monthOne.income) && (expenseOne == monthOne.expenses)) {
                    if ((incomeTwo == monthTwo.income) && (expenseTwo == monthTwo.expenses)) {
                        if ((incomeThree == monthThree.income) && (expenseThree == monthThree.expenses)) {
                            System.out.println("Отчеты сверены. Ошибок не обнаружено.");
                        } else {
                            System.out.println("Обнаружено несоответствие в месяце" + 3);
                        }
                    } else {
                        System.out.println("Обнаружено несоответствие в месяце" + 2);
                    }
                }
            }
        }


        public int sumIncome (HashMap<String, MonthStatistic> report){
            int sum = 0;
            for (MonthStatistic oneMonth : report.values()){
                if (!oneMonth.isExpense) {
                    sum += oneMonth.quantity * oneMonth.sum_of_one;
                }
            } return sum;
        }

        public int sumExpense(HashMap<String, MonthStatistic> report){
            int sum = 0;
            for (MonthStatistic oneMonth: report.values()){

                if (oneMonth.isExpense) {
                    sum += oneMonth.quantity * oneMonth.sum_of_one;
                }
            }
            return sum;
        }
    }