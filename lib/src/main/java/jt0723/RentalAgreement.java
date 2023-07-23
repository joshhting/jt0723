package jt0723;

import java.text.DecimalFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;

public class RentalAgreement {
    private final static DecimalFormat df = new DecimalFormat("###0.00");

    private final String toolCode;
    private final ToolType toolType;
    private final String brand;
    private final int rentalDays;
    private final LocalDate checkoutDate;
    private final LocalDate dueDate;
    private final double dailyRentalCharge;
    private final int chargeDays;
    private final double preDiscountCharge;
    private final int discountPercent;
    private final double discountAmount;
    private final double finalCharge;

    public RentalAgreement(final Tool tool, final int rentalDays, final int discountPercent, final LocalDate checkoutDate) {
        this.toolCode = tool.toolCode;
        this.toolType = tool.toolType;
        this.brand = tool.brand;
        this.rentalDays = rentalDays;
        this.checkoutDate = checkoutDate;
        this.dueDate = checkoutDate.plusDays(rentalDays);
        this.dailyRentalCharge = toolType.dailyCharge;
        this.chargeDays = computeChargeableDayCount();
        this.preDiscountCharge = roundPreDiscountCharge(chargeDays * dailyRentalCharge);
        this.discountPercent = discountPercent;
        this.discountAmount = roundPreDiscountCharge(discountPercent * preDiscountCharge / 100);
        this.finalCharge = preDiscountCharge - discountAmount;
    }

    private int computeChargeableDayCount() {
        int checkoutDayOfWeek = checkoutDate.get(ChronoField.DAY_OF_WEEK);
        final int rentalWeeks = rentalDays / 7;
        final int extraDays = rentalDays % 7;
        int weekendCount = rentalWeeks * 2;
        for (int i = 1; i <= extraDays; i++) {
            int dayOfWeekInt = (checkoutDayOfWeek + i) % 7;
            if (dayOfWeekInt == 0) {
                dayOfWeekInt = 7;
            }
            final DayOfWeek dayOfWeek = DayOfWeek.of(dayOfWeekInt);
            if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                weekendCount++;
            }
        }
        int weekdayCount = rentalDays - weekendCount;
        int holidayCount = 0;
        // labor days
        for (int y = checkoutDate.getYear(); y <= dueDate.getYear(); y++) {
            final LocalDate sept1 = LocalDate.of(y, 9, 1);
            final int sept1DayOfWeek = sept1.get(ChronoField.DAY_OF_WEEK);
            LocalDate firstMonday = (DayOfWeek.of(sept1DayOfWeek).equals(DayOfWeek.MONDAY)) ? sept1 : sept1.plusDays(8 - sept1DayOfWeek);
            if ((firstMonday.isAfter(checkoutDate) && firstMonday.isBefore(dueDate)) || firstMonday.equals(dueDate)) {
                holidayCount++;
                weekdayCount--;
            }
        }
        // independence day
        for (int y = checkoutDate.getYear(); y <= dueDate.getYear(); y++) {
            final LocalDate july4 = LocalDate.of(y, 7, 4);
            final DayOfWeek july4DayOfWeek = DayOfWeek.of(july4.get(ChronoField.DAY_OF_WEEK));
            LocalDate independence = july4;
            if (july4DayOfWeek.equals(DayOfWeek.SATURDAY)) {
                independence = july4.minusDays(1);
            } else if (july4DayOfWeek.equals(DayOfWeek.SUNDAY)) {
                independence = july4.plusDays(1);
            }
            if ((independence.isAfter(checkoutDate) && independence.isBefore(dueDate)) || independence.equals(dueDate)) {
                holidayCount++;
                weekdayCount--;
            }
        }

        int chargeableDayCount = 0;
        if (toolType.weekdayCharge) {
            chargeableDayCount += weekdayCount;
        }
        if (toolType.weekendCharge) {
            chargeableDayCount += weekendCount;
        }
        if (toolType.holidayCharge) {
            chargeableDayCount += holidayCount;
        }
        return chargeableDayCount;
    }

    /**
     * @return double charge, rounded to nearest cent
     */
    private double roundPreDiscountCharge(double charge) {
        return Double.parseDouble(df.format(charge));
    }

    public String toString() {
        final StringBuilder sb = new StringBuilder("Tool code: ");
        sb.append(toolCode);
        sb.append("\nTool type: ");
        sb.append(toolType.name);
        sb.append("\nTool brand: ");
        sb.append(brand);
        sb.append("\nRental days: ");
        sb.append(rentalDays);
        sb.append("\nCheck out date: ");
        sb.append(checkoutDate);
        sb.append("\nDue date: ");
        sb.append(dueDate);
        sb.append("\nDaily rental charge: $");
        sb.append(df.format(dailyRentalCharge));
        sb.append("\nCharge days: ");
        sb.append(chargeDays);
        sb.append("\nPre-discount charge: $");
        sb.append(df.format(preDiscountCharge));
        sb.append("\nDiscount percent: ");
        sb.append(discountPercent);
        sb.append("%\nDiscount amount: $");
        sb.append(df.format(discountAmount));
        sb.append("\nFinal charge: $");
        sb.append(df.format(finalCharge));

        return sb.toString();
    }
}
