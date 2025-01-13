package testtask.shift.shopapi.model.laptop;

import com.fasterxml.jackson.annotation.JsonValue;

public enum LaptopSize {
    Inch13("13 inches"),
    Inch14("14 inches"),
    Inch15("15 inches"),
    Inch17("17 inches");

    private final String value;

    LaptopSize(final String description) {
        this.value = description;
    }

    @JsonValue
    final String value() {
        return this.value;
    }
}
