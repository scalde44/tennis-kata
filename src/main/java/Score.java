public enum Score {
    LOVE("Love"), FIFTEEN("Fifteen"), THIRTY("Thirty"), FORTY("Forty"), DEUCE("Deuce"), ALL("All");
    private final String name;

    Score(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
