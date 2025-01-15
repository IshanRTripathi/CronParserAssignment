package org.cronparser.enums;

public enum TimeComponent {
    MINUTE(0, 59),
    HOUR(0, 23),
    DAY_OF_MONTH(1, 31),
    MONTH(1, 12),
    DAY_OF_WEEK(0, 6);

    private final int min;
    private final int max;

    TimeComponent(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int[] getBounds() {
        return new int[]{min, max};
    }
}

