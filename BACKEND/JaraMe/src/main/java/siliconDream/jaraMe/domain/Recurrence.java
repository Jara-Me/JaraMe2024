package siliconDream.jaraMe.domain;

import lombok.ToString;

@ToString
//@Getter
//@AllArgsConstructor

public enum Recurrence {
    SUNDAY("Sunday"),
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday");

    private final String value;

    Recurrence(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Recurrence fromValue(String value) {
        for (Recurrence recurrence : values()) {
            if (recurrence.value.equalsIgnoreCase(value)) {
                return recurrence;
            }
        }
        throw new IllegalArgumentException("설정값외" + value);
    }
}
