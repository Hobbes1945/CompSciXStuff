import com.tychovonr.Quadratic.Quadratic;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
public class QuadraticsTest {
    @Test
    public void getA(){
        Quadratic q = new Quadratic(1,2,1);

        assertThat(q.getA(), is(1.0));
    }
    @Test
    public void getRootArray(){
        Quadratic q = new Quadratic(1,2,1);
        assertThat(q.getRootArray()[0], is(-1.0));
        assertThat(q.getRootArray()[1], is(-1.0));
    }
    @Test
    public void axisOfSymmetry(){
        Quadratic q = new Quadratic(1,2,1);
        assertThat(q.axisOfSymmetry(), is(-1.0));
    }
    @Test
    public void getExtremeValue(){
        Quadratic q = new Quadratic(1,2,1);
        assertThat(q.getExtremeValue(), is(0));
    }
    @Test
    public void isMax(){
        Quadratic q = new Quadratic(-1,2,1);
        assertThat(q.isMax(), is(false));
    }
    @Test
    public void evaluateWith(){
        double x = 10;
        Quadratic q = new Quadratic(1,2,1);
        assertThat(q.evaluateWith(x), is(121.0));
    }
}
