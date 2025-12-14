import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class HippodromeTest {
    private List<Horse> horses = new ArrayList<>();
    private Hippodrome hippodrome;
    private final Random random = new Random();
    private final double maxDist = 0.9;
    private final double minDist = 0.2;
    private final int endExclusive = 31;

    @BeforeEach
    void setup(){
        IntStream.range(1, endExclusive).forEach(i -> {
            int rndNumber = random.nextInt(2) + 1;
            Horse horse= new Horse(
                    "Horse #" + i,
                    rndNumber,
                    rndNumber * (Math.random() * (maxDist - minDist))
            );
            horses.add(horse);
        });
        hippodrome = new Hippodrome(horses);
    }

    @Test
    @DisplayName("CheckConstructorHorseIllegalThrow")
    void checkConstructorHorseIllegalThrow(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("CheckConstructorHorseIllegalThrowGetMessage")
    void checkConstructorHorseIllegalThrowGetMessage(){
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exc.getMessage());
    }

    @Test
    @DisplayName("CheckConstructorHorseIllegalThrowEmptyList")
    void checkConstructorHorseIllegalThrowEmptyList(){
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<Horse>()));
    }

    @Test
    @DisplayName("CheckConstructorHorseIllegalThrowEmptyListGetMessage")
    void checkConstructorHorseIllegalThrowEmptyListGetMessage(){
        IllegalArgumentException exc = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<Horse>()));
        assertEquals("Horses cannot be empty.", exc.getMessage());
    }

    @Test
    @DisplayName("CheckConstructorHorseIllegalThrowEmptyListGetMessage")
    void checkGetHorses(){
        assertIterableEquals(hippodrome.getHorses(), horses);
    }

    @Test
    @DisplayName("CheckConstructorHorseIllegalThrowEmptyListGetMessage")
    void checkGetWinner() {
        Horse horseWinner = hippodrome.getWinner();
        Horse horseWithMaxDist = horses.stream().max(Comparator.comparing(Horse::getDistance)).get();
        assertEquals(horseWinner, horseWithMaxDist);
    }
}
