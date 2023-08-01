import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MontlyReport {
    int month = 0;
    ArrayList<MRecord> rows = new ArrayList<>();

    public MontlyReport(int month, String path) {
        this.month=month;
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int j = 1; j < lines.length; j++) {
            String line = lines[j];
            String[] parts = line.split(",");
            String item_name = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sum_of_one = Integer.parseInt(parts[3]);
            MRecord mRecord = new MRecord(item_name, isExpense, quantity, sum_of_one);
            System.out.println(mRecord);
            rows.add(mRecord);
        }
    }


    private String readFileContentsOrNull(String path)
    {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}


