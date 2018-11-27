package gol;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.HashSet;
import java.util.Set;

public class LocationTest{
    @Test
    public void twoLocationsAreTheSame() {
        Location loc1 = new Location(0,0);
        Location loc2 = new Location(0,0);
        assertTrue(loc1.equals(loc2));
    }
    
    @Test
    public void twoDifferentLocationsAreNotTheSame() {
        Location loc1 = new Location(0,0);
        Location loc2 = new Location(0,1);
        assertFalse(loc1.equals(loc2));
    }
    
    @Test
    public void twoOtherDifferentLocationsAreNotTheSame() {
        Location loc1 = new Location(0,0);
        Location loc2 = new Location(1,0);
        assertFalse(loc1.equals(loc2));
    }
    
    @Test
    public void twoDifferentThingsAreNotTheSame() {
        Location loc1 = new Location(0,0);
        Object loc2 = new String("0,1");
        assertFalse(loc1.equals(loc2));
    }
    
    @Test
    public void getSetOfNeighboursForKnownLocation() {
        Set<Location> expected = new HashSet<>();
        Location target = new Location(1,1);
        expected.add(new Location(0,0));
        expected.add(new Location(1,0));
        expected.add(new Location(2,0));
        expected.add(new Location(0,1));
        expected.add(new Location(2,1));
        expected.add(new Location(0,2));
        expected.add(new Location(1,2));
        expected.add(new Location(2,2));
        
        Set<Location> neighbours = target.getNeighbourhood();
        assertEquals(expected, neighbours);
    }
    
    @Test
    public void getSetOfNeighboursForCornerLocation() {
        Set<Location> expected = new HashSet<>();
        Location target = new Location(0,0);
        expected.add(new Location(1,0));
        expected.add(new Location(0,1));
        expected.add(new Location(1,1));
        
        Set<Location> neighbours = target.getNeighbourhood();
        assertEquals(expected, neighbours);
    }
    
    @Test
    public void getSetOfNeighboursForEdgeLocation() {
        Set<Location> expected = new HashSet<>();
        Location target = new Location(1,0);
        expected.add(new Location(0,0));
        expected.add(new Location(2,0));
        expected.add(new Location(0,1));
        expected.add(new Location(1,1));
        expected.add(new Location(2,1));
        
        Set<Location> neighbours = target.getNeighbourhood();
        assertEquals(expected, neighbours);
    }
}