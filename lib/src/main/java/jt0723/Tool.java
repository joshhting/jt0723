package jt0723;

public enum Tool {
    CHNS("CHNS", ToolType.CHAINSAW, "Stihl"),
    LADW("LADW", ToolType.LADDER, "Werner"),
    JAKD("JAKD", ToolType.JACKHAMMER, "DeWalt"),
    JAKR("JAKR", ToolType.JACKHAMMER, "Ridgid");

    public final String toolCode;
    public final ToolType toolType;
    public final String brand;

    Tool(String toolCode, ToolType toolType, String brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

}
