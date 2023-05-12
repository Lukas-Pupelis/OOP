package Calculation;

public class DataEntity
{

    public int month;
    public double monthly;
    public double interest;
    public double credit;
    public double reminder;

    public DataEntity(int month, double monthly, double interest, double credit, double reminder)
    {
        this.month = month;
        this.monthly = monthly;
        this.interest = interest;
        this.credit = credit;
        this.reminder = reminder;
    }

}
