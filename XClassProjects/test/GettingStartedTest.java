import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GettingStartedTest {

    @Test
    public void add() {
        assertThat(GettingStarted.add(2, 3), is(5));
    }
    @Test
    public void addNegative(){
        assertThat(GettingStarted.add(-2,-7), is(-9));
    }
    @Test
    public void addALot(){
        assertThat(GettingStarted.add(2,5),is(-1));
    }
    @Test
    public void addNegPos(){
        assertThat(GettingStarted.add(100,-64),is(36));
    }
    @Test
    public void Commutative(){
        assertThat(GettingStarted.add(5,GettingStarted.add(3,4)),is(GettingStarted.add(3,GettingStarted.add(4,5))));
    }
}