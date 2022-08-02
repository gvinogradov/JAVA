package core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
public class Station {
    private final String name;
    private final String lineNumber;
    private double depth;
    private Date date;


    public String toString() {
        return "(" + lineNumber + ")" + name + " depth: " + depth;
    }
}
