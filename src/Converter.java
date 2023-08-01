import java.util.HashMap;
public class Converter {
    YearlyReport yearlyReport;
    HashMap<Integer, MontlyReport> monthHashMap = new HashMap<>();

    void readYearReport() {
        String path = "resources/y.2021.csv";
        yearlyReport = new YearlyReport(2021, path);
        System.out.println("Годовые отчеты считаны успешно");
    }

    void readMonthReport() {
        for (int i = 1; i < 4; i++) {
            String path = "resources/m.20210" + i + ".csv";
            System.out.println("Месяц " + i);
            monthHashMap.put(i, new MontlyReport(i, path));
        }
        System.out.println("Месячные отчеты считаны успешно");
    }

    void infoMonthReport() {
        double maxCategorySum = 0;
        String maxCategoryName = "";
        double maxExpense = 0;
        String maxExpenseName = "";
        for (int month : monthHashMap.keySet()) {
            System.out.println("Месяц " + month);
            for (int i = 0; i < (monthHashMap.get(month)).rows.size(); i++) {
                boolean isExpense = ((monthHashMap.get(month)).rows.get(i)).getIsExpense();
                int Quantity = ((monthHashMap.get(month)).rows.get(i)).getQuantity();
                int Sum_of_one = ((monthHashMap.get(month)).rows.get(i)).getSum_of_one();
                String item_name = ((monthHashMap.get(month)).rows.get(i)).getItem_name();
                if (isExpense == false) {
                    if ((Quantity * Sum_of_one) > maxCategorySum) {
                        maxCategorySum = (Quantity * Sum_of_one);
                        maxCategoryName = item_name;
                    }
                }
                if (isExpense == true) {
                    if ((Quantity * Sum_of_one) > maxExpense) {
                        maxExpense = (Quantity * Sum_of_one);
                        maxExpenseName = item_name;
                    }
                }
            }
            System.out.println("Самая большая трата на товар: " + maxExpenseName + ", составила " + maxExpense);
            System.out.println("Самый прибыльный товар: " + maxCategoryName + ", куплен на сумму " + maxCategorySum);
        }

    }

    void infoYearReport() {
        int sumExpense = 0;
        int countExpense = 0;
        int sumIncome = 0;
        int countIncome = 0;
        int spentExpense = 0;
        int spentIncone = 0;
        int difference = 0;
        int month = 0;
        for (int i = 0; i < yearlyReport.rows.size(); i++) {
            boolean isExpense = (yearlyReport.rows.get(i)).getIsExpense();
            int Amount = (yearlyReport.rows.get(i)).getAmount();
            if (isExpense == false) {
                sumIncome += (yearlyReport.rows.get(i)).getAmount();
                ;
                countIncome++;
                spentIncone += Amount;
                month = (yearlyReport.rows.get(i)).getMonth();
            }
            if (isExpense == true) {
                sumExpense += Amount;
                countExpense++;
                spentExpense += Amount;
                month = (yearlyReport.rows.get(i)).getMonth();
            }
            if ((spentIncone != 0) && (spentExpense != 0)) {
                difference = spentIncone - spentExpense;
                System.out.println("За месяц " + month + " прибыль составила " + difference);
                spentExpense = 0;
                spentIncone = 0;
            }

        }
        double avgIncone = 0;
        double avgExpense = 0;
        avgExpense = sumExpense / countExpense;
        avgIncone = sumIncome / countIncome;
        System.out.println("Средний расход за все месяцы: " + avgExpense);
        System.out.println("Средний доход за все месяцы: " + avgIncone);
    }

    void reconciliationReports() {
        try {
            if (monthHashMap.isEmpty()) {
                System.out.println("Cчитайте месячный и годовой отчеты");
                return;
            }
            int allExpensexYear = 0;
            int allIncomeYear = 0;
            double allExpensesMonth = 0;
            double allIncomeMonth = 0;
            int expenseErrorMonth = 0;
            int IncomeErrorMonth = 0;
            for (int i = 0; i < yearlyReport.rows.size(); i++) {
                int month = 0;
                boolean isExpense = (yearlyReport.rows.get(i)).getIsExpense();
                int Amount = (yearlyReport.rows.get(i)).getAmount();
                //считаем доходы за каждый месяц в годовом отчете
                if (isExpense == false) {
                    allIncomeYear = Amount;
                    month = (yearlyReport.rows.get(i)).getMonth();
                }
                //считаем расходы за каждый месяц в годовом отчете
                if (isExpense == true) {
                    allExpensexYear = Amount;
                    month = (yearlyReport.rows.get(i)).getMonth();
                }
                if (allIncomeYear != 0 && allExpensexYear != 0) {
                    allIncomeYear = 0;
                    allExpensexYear = 0;

                }
            }

            for (int month1 : monthHashMap.keySet()) {
                System.out.println("Месяц " + month1);
                for (int i = 0; i < (monthHashMap.get(month1)).rows.size(); i++) {
                    boolean isExpense = ((monthHashMap.get(month1)).rows.get(i)).getIsExpense();
                    int Quantity = ((monthHashMap.get(month1)).rows.get(i)).getQuantity();
                    int Sum_of_one = ((monthHashMap.get(month1)).rows.get(i)).getSum_of_one();
                    String item_name = ((monthHashMap.get(month1)).rows.get(i)).getItem_name();
                    //считаем доходы за каждый месяц в месячном отчет
                    if (isExpense == false) {
                        allIncomeMonth = Quantity * Sum_of_one;
                    }
                    //считаем расходы за каждый месяц в месячном отчете
                    if (isExpense == true) {
                        allExpensesMonth = Quantity * Sum_of_one;
                    }

                }

                if (allIncomeMonth != allIncomeYear) {
                    IncomeErrorMonth = month1;
                }
                if (allExpensesMonth != allExpensexYear) {
                    expenseErrorMonth = month1;
                }
                if (IncomeErrorMonth != 0) {
                    System.out.println("В месяце " + IncomeErrorMonth + " несоотвествие доходов");
                }
                if (expenseErrorMonth != 0) {
                    System.out.println("В месяце " + expenseErrorMonth + " несоотвествие расходов");
                }
                if (expenseErrorMonth == 0 && IncomeErrorMonth == 0) {
                    System.out.println("Несоответсвия необнаружено");
                }
            }

        } catch (Exception e) {
            System.out.println("Считайте месячный и годовой отчеты");
        }
    }
}



