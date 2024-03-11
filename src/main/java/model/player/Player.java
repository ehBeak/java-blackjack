package model.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import model.Outcome;
import model.card.Card;
import model.card.Cards;

public abstract class Player implements NoticeStatus {

    protected final String name;
    protected Cards cards;

    public Player(String name, List<Card> cards) {
        validateName(name);
        this.name = name;
        this.cards = new Cards(cards);
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름은 공백이거나 null일 수 없습니다.");
        }
    }

    public void addCards(List<Card> card) {
        cards.addCards(card);
    }

    public void addCard(Card card) {
        cards.addCard(card);
    }

    public int calculateScore() {
        return cards.calculateScore();
    }

    public boolean isOverMaximumSum() {
        return cards.isOverMaximumSum();
    }

    public int findPlayerDifference() {
        return cards.findPlayerDifference();
    }

    protected Outcome findPlayerOutcome(int otherDifference) {
        int difference = findPlayerDifference();
        if (otherDifference > difference) {
            return Outcome.WIN;
        }
        if (otherDifference < difference) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
