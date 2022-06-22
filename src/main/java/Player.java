import java.util.Objects;

public class Player {
    private final String name;
    private int score;

    public Player(String name) {
        this.name = Objects.requireNonNull(name);
        this.score = 0;
    }

    public boolean isNamed(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    public String name() {
        return name;
    }

    public int score() {
        return score;
    }

    public void addPoint() {
        this.score++;
    }
}
