package jt0723;

public enum ToolType {
    LADDER("Ladder", 1.99, true, true, false),
    CHAINSAW("Chainsaw", 1.49, true, false, true),
    JACKHAMMER("Jackhammer", 2.99, true, false, false);

    public final String name;
    public final double dailyCharge;
    public final boolean weekdayCharge;
    public final boolean weekendCharge;
    public final boolean holidayCharge;

    ToolType(final String name, final double dailyCharge, final boolean weekdayCharge, final boolean weekendCharge, final boolean holidayCharge) {
        this.name = name;
        this.dailyCharge = dailyCharge;
        this.weekdayCharge = weekdayCharge;
        this.weekendCharge = weekendCharge;
        this.holidayCharge = holidayCharge;
    }
}
