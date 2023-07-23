package jt0723;

import java.time.LocalDate;

public class ToolRental {
    public RentalAgreement checkout(final Tool tool, final int days, final int discount, final LocalDate date) {
        if (days < 1) {
            throw new IllegalArgumentException("Rental day count must be a positive integer.");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Discount must be a percentage value in the range 0-100.");
        }
        return new RentalAgreement(tool, days, discount, date);
    }
}
