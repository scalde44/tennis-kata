import org.javatuples.Pair;

public class TennisGame1 implements TennisGame {
    private static final Score[] SCORES = new Score[]{Score.LOVE, Score.FIFTEEN, Score.THIRTY, Score.FORTY};
    private static final String CONCATENATOR = "-";
    private static final String ADVANTAGE = "Advantage %s";
    private static final String WIN_FOR = "Win for %s";
    private static final int MINIMUM_POINTS_FOR_WINNER = 4;
    private static final int MINIMUM_DIFFERENCE_FOR_WINNER = 2;
    private final Player firstPlayer;
    private final Player secondPlayer;

    public TennisGame1(String firstPlayerName, String secondPlayerName) {
        this.firstPlayer = new Player(firstPlayerName);
        this.secondPlayer = new Player(secondPlayerName);
    }

    public void wonPoint(String playerName) {
        getPlayerByName(playerName).addPoint();
    }

    private Player getPlayerByName(String name) {
        return (firstPlayer.isNamed(name)) ? firstPlayer : secondPlayer;
    }

    private boolean isTie() {
        return firstPlayer.score() == secondPlayer.score();
    }

    private boolean isMinimumPointsForWinner() {
        return firstPlayer.score() >= MINIMUM_POINTS_FOR_WINNER || secondPlayer.score() >= MINIMUM_POINTS_FOR_WINNER;
    }

    private String getResultByScore(int score) {
        return SCORES[score].getName();
    }

    private String getResultByScoreTie(int score) {
        return getResultByScore(score).concat(CONCATENATOR).concat(Score.ALL.getName());
    }

    private Pair<Player, Integer> getAdvantagePlayer() {
        Integer difference = firstPlayer.score() - secondPlayer.score();
        return (difference > 0) ?
                new Pair<>(firstPlayer, difference) :
                new Pair<>(secondPlayer, Math.abs(difference));
    }

    public String getScore() {
        if (isTie()) {
            return (firstPlayer.score() < 3) ? getResultByScoreTie(firstPlayer.score()) : Score.DEUCE.getName();
        }
        if (isMinimumPointsForWinner()) {
            Pair<Player, Integer> advantagePlayer = getAdvantagePlayer();
            String playerName = advantagePlayer.getValue0().name();
            return (advantagePlayer.getValue1() < MINIMUM_DIFFERENCE_FOR_WINNER) ? String.format(ADVANTAGE, playerName) : String.format(WIN_FOR, playerName);
        }
        return getResultByScore(firstPlayer.score()).concat(CONCATENATOR).concat(getResultByScore(secondPlayer.score()));
    }
}