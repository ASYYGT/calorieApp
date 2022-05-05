/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package vscode;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
class AppTest {
    public ArrayList<Double> array  = new ArrayList<>();
    
    public void testEqualsArray(){
        
       
        ArrayList<Double> array1 = new ArrayList<>(Arrays.asList(1.0,1.0,1.0)); 
        App.calorie(array, 1, 1, 1);
       
        assertEquals(array, array1);
       
    }
    @Test
    public void testNotEqualsArray(){
       
        ArrayList<Double> array1 = new ArrayList<>(Arrays.asList(1.0,1.0,1.0)); 
        App.calorie(array, 2, 2, 2);
       
        assertNotEquals(array, array1);
    }
    @Test
    public void testNotNull(){
       
        App.calorie(array, 1, 1, 1);
        assertNotNull(array);
    }
    @Test
    public void testNull(){
        
        assertNotNull(array);
    }

}