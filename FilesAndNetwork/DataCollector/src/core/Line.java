package core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Line {
    private String name;
    private String number;

    public String toString() {
        return number + " - " + name;
    }
}
