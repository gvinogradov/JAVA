import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Station {
    private String name;
    private String lineNumber;

    public String toString() {
        return "(" + lineNumber + ")" + name;
    }
}
