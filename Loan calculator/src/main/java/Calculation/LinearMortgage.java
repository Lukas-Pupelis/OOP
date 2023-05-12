package Calculation;

import java.util.ArrayList;

import static java.lang.ThreadLocal.*;

public class LinearMortgage
{
    private double sum;
    private int term;
    private final double percent;
    private final int postponement;
    public double max = 0;
    public final ThreadLocal<ArrayList<DataEntity>> numbers = withInitial(() -> new ArrayList<>(term));

    public LinearMortgage(double sum, int term, double percent, int postponement)
    {
        this.sum = sum;
        this.term = term;
        this.percent = percent;
        this.postponement = postponement;
        calculateMonth();
    }

    public void calculateMonth()
    {

        double interest = (sum * percent) / 12;

        for (int i = 0; i < postponement; i++)
        {
            numbers.get().add(new DataEntity(i + 1, interest, interest, 0, sum));
        }

        double credit = sum / (term - postponement);

        for (int i = 0; i < term; i++)
        {
            interest = (sum * percent) / 12;
            double monthly = interest + credit;
            double reminder = sum;
            sum -= credit;

            if (monthly > max) {
                max = monthly;
            }

            numbers.get().add(new DataEntity(i + 1, monthly, interest, credit, reminder));
        }

    }
}
