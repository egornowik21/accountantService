public class YRecord {
    int month;

    public int getMonth() {
        return month;
    }

    public int getAmount() {
        return amount;
    }

    public boolean getIsExpense() {
        return isExpense;
    }

    int amount;
    boolean isExpense;

    public YRecord(int month, int amount, boolean isExpense) {
        this.month = month;
        this.amount = amount;
        this.isExpense = isExpense;
    }

    @Override // переопределяем toString
    public String toString() {
        return month + ", " + amount+ ", "+ isExpense;
    }

}


