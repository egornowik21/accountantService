public class MRecord {
        String item_name;
        boolean isExpense;
        int quantity;
        int sum_of_one;

    public String getItem_name() {
        return item_name;
    }

    public boolean getIsExpense() {
        return isExpense;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getSum_of_one() {
        return sum_of_one;
    }

    public MRecord(String item_name, boolean isExpense, int quantity, int sum_of_one) {
        this.item_name = item_name;
        this.isExpense = isExpense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
    }

    @Override // переопределяем toString
        public String toString() {
            return item_name + ", " + isExpense + ", " + quantity + ", " + sum_of_one;
        }

    }

