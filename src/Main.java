import java.util.ArrayList;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Converter converter = new Converter();

        while (true) {
            printMenu();
            int userInput = scanner.nextInt();
            if (userInput == 1) {
                converter.readMonthReport();
            } else if(userInput==2) {
                converter.readYearReport();
            }
            else if(userInput==3) {
                converter.reconciliationReports();
            }
            else if(userInput==4) {
                converter.infoMonthReport();

            }
            else if(userInput==5) {
                converter.infoYearReport();
            }

            else if (userInput == 0) {
                System.out.println("Выход");
                break;
            }
        }
    }
        public static void printMenu() {
            System.out.println("Что вы хотите сделать? ");
            System.out.println("1 - Считать все месячные отчёты");
            System.out.println("2 - Считать годовой отчёт");
            System.out.println("3 - Сверить отчёты");
            System.out.println("4 - Вывести информацию о всех месячных отчётах");
            System.out.println("5 - Вывести информацию о годовом отчёте");
            System.out.println("0 - Выход");
        }

}

