package Calculation;

import java.util.ArrayList;
import static java.lang.Math.pow;
import static java.lang.ThreadLocal.withInitial;

public class AnnuityMortgage
{

    private double sum;
    private int term;
    private final double percent;
    private double monthly;
    private final int postponement;

    public final ThreadLocal<ArrayList<DataEntity>> numbers = withInitial(() -> new ArrayList<>(term));

    public AnnuityMortgage(double sum, int term, double percent, int postponement)
    {
        this.sum = sum;
        this.term = term;
        this.percent = percent;
        this.postponement = postponement;
        calculateMonth();
    }

    public void calculateMonth()
    {

        double interest = sum * (percent / 12);

        for (int i = 0; i < postponement; i++)
        {
            numbers.get().add(new DataEntity(i + 1, interest, interest, 0, sum));
        }

        monthly = interest / (1 - (1 / pow(1 + interest / sum, term - postponement)));

        for (int i = 0; i < term; i++)
        {
            double reminder = sum;
            interest = sum * (percent / 12);
            sum -= (monthly - interest);
            double credit = (monthly - interest);

            numbers.get().add(new DataEntity(i + 1, monthly, interest, credit, reminder));
        }
    }

    public Double getMonthly()
    {
        return monthly;
    }
}