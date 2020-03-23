import com.tychovonr.Function.Function;
import com.tychovonr.Function.PolyFunction;
import org.junit.Test;
import com.tychovonr.Riemann.RightHandRule;
import com.tychovonr.Riemann.AbstractRiemann;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.opensourcephysics.frames.PlotFrame;
import org.dalton.polyfun.Polynomial;
import org.opensourcephysics.display.DrawableShape;
import org.opensourcephysics.frames.PlotFrame;
public class RightHandRuleTest {
    @Test
    public void slice(){
        Function fun = new PolyFunction();
        RightHandRule rightHandRule = new RightHandRule();
        //assertThat(rightHandRule.slice​(fun,1,2,2), is (4.0) );
    }
    @Test
    public void rs(){
        Function fun = new PolyFunction();
        RightHandRule rightHandRule = new RightHandRule();
        //assertThat(rightHandRule.rs​(fun,0,5,5,1),is(15.0));
    }
}
