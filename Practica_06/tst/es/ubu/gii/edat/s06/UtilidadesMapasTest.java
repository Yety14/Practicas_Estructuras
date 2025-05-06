package es.ubu.gii.edat.s06;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class UtilidadesMapasTest {

    @Test
    public void pruebaCreaMapa() {
        System.out.println("pruebaCreaMapa");
        Map<String, String> mapStrStr = UtilidadesMapas.creaMapa(
                Arrays.asList("uno", "dos", "tres"),
                Arrays.asList("one", "two", "three"));
        System.out.println("mapStrStr:" + mapStrStr);
        assertEquals(3, mapStrStr.size());
        assertEquals("one", mapStrStr.get("uno"));
        assertEquals("two", mapStrStr.get("dos"));
        assertEquals("three", mapStrStr.get("tres"));
        assertNull(mapStrStr.get("one"));

        Map<Double, Integer> mapDblInt = UtilidadesMapas.creaMapa(
                Arrays.asList(1.1, 2.2, 3.3, 4.4).iterator(),
                Arrays.asList(1, 2, 3, 4).iterator());
        System.out.println("mapDblInt:" + mapDblInt);
        assertEquals(4, mapDblInt.size());
        assertEquals(1, mapDblInt.get(1.1));
        assertEquals(2, mapDblInt.get(2.2));
        assertEquals(3, mapDblInt.get(3.3));
        assertEquals(4, mapDblInt.get(4.4));
        assertNull(mapDblInt.get(5.5));
    }

    @Test
    public void pruebaMapaInverso() {
        System.out.println("\npruebaMapaInverso");
        Map<String, String> mapStrStr = UtilidadesMapas.creaMapa(
                Arrays.asList("uno", "dos", "tres"),
                Arrays.asList("one", "two", "three"));
        System.out.println("mapStrStr:" + mapStrStr);
        mapStrStr = UtilidadesMapas.mapaInverso(mapStrStr);
        System.out.println("mapStrStr:" + mapStrStr);
        assertEquals(UtilidadesMapas.creaMapa(
                Arrays.asList("one", "two", "three"),
                Arrays.asList("uno", "dos", "tres")), mapStrStr);

        Map<String, Integer> mapStrInt = UtilidadesMapas.creaMapa(
                Arrays.asList("uno", "dos", "tres"), Arrays.asList(1, 2, 3));
        System.out.println("mapStrInt:" + mapStrInt);
        Map<Integer, String> mapIntStr = UtilidadesMapas.mapaInverso(mapStrInt);
        System.out.println("mapIntStr:" + mapIntStr);
        assertEquals(UtilidadesMapas.creaMapa(Arrays.asList(1, 2, 3),
                Arrays.asList("uno", "dos", "tres")), mapIntStr);

        mapIntStr.put(4, "cuatro");
        mapIntStr.put(5, "cinco");
        System.out.println("mapIntStr:" + mapIntStr);
        mapStrInt = UtilidadesMapas.mapaInverso(mapIntStr);
        System.out.println("mapStrInt:" + mapStrInt);
        assertEquals(UtilidadesMapas.creaMapa(
                Arrays.asList("uno", "dos", "tres", "cuatro", "cinco"),
                Arrays.asList(1, 2, 3, 4, 5)), mapStrInt);
    }

    @Test
    public void pruebaMultiMapaInverso() {
        System.out.println("\npruebaMultiMapaInverso");
        Map<Double, Integer> mapDblInt = UtilidadesMapas.creaMapa(
                Arrays.asList(1.1, 2.2, 3.3, 4.4, 1.7, 3.8, 3.9),
                Arrays.asList(1, 2, 3, 4, 1, 3, 3));
        System.out.println("mapDblInt:" + mapDblInt);

        Map<Integer, Collection<Double>> mapIntColDbl = UtilidadesMapas.multiMapaInverso(mapDblInt);
        System.out.println("mapIntColDbl:" + mapIntColDbl);

        assertEquals(4, mapIntColDbl.size());
        assertEquals(2, mapIntColDbl.get(1).size());
        assertEquals(1, mapIntColDbl.get(2).size());
        assertEquals(3, mapIntColDbl.get(3).size());
        assertEquals(1, mapIntColDbl.get(4).size());
        assertTrue(mapIntColDbl.get(1).contains(1.1));
        assertTrue(mapIntColDbl.get(1).contains(1.7));
        assertTrue(mapIntColDbl.get(3).contains(3.3));
        assertTrue(mapIntColDbl.get(3).contains(3.8));
        assertTrue(mapIntColDbl.get(3).contains(3.9));
    }
}