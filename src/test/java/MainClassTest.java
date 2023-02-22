import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass {
    @Test
    public void testGetLocalNumber(){
        Assert.assertEquals("Результат метода getLocalNumber() != 14",14, getLocalNumber());
    }

    @Test
    public void testGetClassNumber(){
        Assert.assertTrue("Результат метода getClassNumber() < 45",getClassNumber()>45);
    }

    @Test
    public void testGetClassString(){
        Assert.assertTrue("Результат метода getClassString() не содержит подстроку 'Hello' или 'hello'",
                getClassString().contains("hello") | getClassString().contains("Hello"));{
        }
    }
}
