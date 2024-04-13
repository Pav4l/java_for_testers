package ru.stqa.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionTests {

    @Test
    void arrayTest(){
        var array = new String[]{"a", "b", "c"};
        //var array = new String[3];
        Assertions.assertEquals(3, array.length);
        array[0] = "a";
        Assertions.assertEquals("a", array[0]);

        array[0] = "d";
        Assertions.assertEquals("d", array[0]);
    }

    @Test
    void listTests(){
        //var list = List.of("a", "b", "c");
        var list = new ArrayList<>(List.of("a", "b", "c"));
        Assertions.assertEquals(3, list.size());

        //list.add("a");
        //list.add("b");
        //list.add("c");
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0,"d");
        Assertions.assertEquals("d", list.get(0));
    }

    @Test
    void setTests(){
        var set = new HashSet<>((List.of("a","b","c","a")));
        Assertions.assertEquals(3, set.size());
        //var element = set.stream().findAny().get();
        set.add("d");
        Assertions.assertEquals(4, set.size());
    }
}
