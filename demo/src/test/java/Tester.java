import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;

import org.junit.Test;

import perfecthashing.Hashes.QuadraticHash;

public class Tester {
    @Test
    public void QuadraticInsert1()
    {
        QuadraticHash hashMap = new QuadraticHash();
        String word = "hello";
        assertTrue(hashMap.insert(word));
    }
    @Test
    public void QuadraticInsert2()
    {
        QuadraticHash hashMap = new QuadraticHash();
        String word = "hello";
        String word2 = "Hi";
        String word3 = "Bye";
        String word4 = "How are you doing ?";
        assertTrue(hashMap.insert(word));
        assertTrue(hashMap.insert(word2));
        assertTrue(hashMap.insert(word3));
        assertTrue(hashMap.insert(word4));
    }
    @Test
    public void QuadraticInsert3()
    {
        QuadraticHash hashMap = new QuadraticHash();
        String[] words = {
            "Apple", "Apricot", "Avocado", "Banana", "Blackberry", "Blueberry", "Boysenberry", "Cantaloupe",
            "Cherry", "Clementine", "Coconut", "Cranberry", "Currant", "Date", "Dragonfruit", "Durian",
            "Elderberry", "Feijoa", "Fig", "Goji Berry", "Gooseberry", "Grape", "Grapefruit", "Guava",
            "Honeydew", "Huckleberry", "Jackfruit", "Jambul", "Jujube", "Kiwi", "Kumquat", "Lemon",
            "Lime", "Longan", "Loquat", "Lychee", "Mandarin", "Mango", "Mangosteen", "Melon",
            "Mulberry", "Nance", "Nectarine", "Olive", "Orange", "Papaya", "Passionfruit", "Peach",
            "Pear", "Persimmon", "Pineapple", "Pitaya", "Plantain", "Plum", "Pomegranate", "Pomelo",
            "Prickly Pear", "Quince", "Raisin", "Rambutan", "Raspberry", "Redcurrant", "Salak", "Satsuma",
            "Star Apple", "Starfruit", "Strawberry", "Surinam Cherry", "Tamarillo", "Tamarind", "Tangerine", "Tomato",
            "Ugli Fruit", "Watermelon", "White Currant", "White Sapote", "Yuzu", "Ziziphus", "Atemoya", "Ackee",
            "Barbadine", "Bilberry", "Breadfruit", "Buddha's Hand", "Camu Camu", "Canistel", "Capuli Cherry", "Chico",
            "Cloudberry", "Crowberry", "Cupuacu", "Desert Lime", "Emu Apple", "Gac", "Genip", "Grumichama",
            "Hog Plum", "Illawarra Plum", "Jocote", "Kabosu"
        };
            for (String word : words)
            {
                assertTrue(hashMap.insert(word));
            }
    }
    @Test
    public void QuadraticDelete1()
    {
        QuadraticHash hashMap = new QuadraticHash();
        hashMap.insert("Hello");
        assertTrue(hashMap.delete("Hello"));
    }
    @Test
    public void QuadraticDelete2()
    {
        QuadraticHash hashMap = new QuadraticHash();
        String word = "hello";
        String word2 = "Hi";
        String word3 = "Bye";
        String word4 = "How are you doing ?";
        assertTrue(hashMap.insert(word));
        assertTrue(hashMap.insert(word2));
        assertTrue(hashMap.insert(word3));
        assertTrue(hashMap.insert(word4));
        assertTrue(hashMap.delete(word4));
        assertTrue(hashMap.delete(word));
        assertTrue(hashMap.delete(word2));
        assertTrue(hashMap.delete(word3));
        assertEquals(hashMap.getSize(), 0);
    }
    @Test
    public void QuadraticDelete3()
    {
        QuadraticHash hashMap = new QuadraticHash();
        String[] words = {
            "Apple", "Apricot", "Avocado", "Banana", "Blackberry", "Blueberry", "Boysenberry", "Cantaloupe",
            "Cherry", "Clementine", "Coconut", "Cranberry", "Currant", "Date", "Dragonfruit", "Durian",
            "Elderberry", "Feijoa", "Fig", "Goji Berry", "Gooseberry", "Grape", "Grapefruit", "Guava",
            "Honeydew", "Huckleberry", "Jackfruit", "Jambul", "Jujube", "Kiwi", "Kumquat", "Lemon",
            "Lime", "Longan", "Loquat", "Lychee", "Mandarin", "Mango", "Mangosteen", "Melon",
            "Mulberry", "Nance", "Nectarine", "Olive", "Orange", "Papaya", "Passionfruit", "Peach",
            "Pear", "Persimmon", "Pineapple", "Pitaya", "Plantain", "Plum", "Pomegranate", "Pomelo",
            "Prickly Pear", "Quince", "Raisin", "Rambutan", "Raspberry", "Redcurrant", "Salak", "Satsuma",
            "Star Apple", "Starfruit", "Strawberry", "Surinam Cherry", "Tamarillo", "Tamarind", "Tangerine", "Tomato",
            "Ugli Fruit", "Watermelon", "White Currant", "White Sapote", "Yuzu", "Ziziphus", "Atemoya", "Ackee",
            "Barbadine", "Bilberry", "Breadfruit", "Buddha's Hand", "Camu Camu", "Canistel", "Capuli Cherry", "Chico",
            "Cloudberry", "Crowberry", "Cupuacu", "Desert Lime", "Emu Apple", "Gac", "Genip", "Grumichama",
            "Hog Plum", "Illawarra Plum", "Jocote", "Kabosu"
        };
            for (String word : words)
            {
                assertTrue(hashMap.insert(word));
            }
            for (String word : words)
            {
                assertTrue(hashMap.delete(word));
            }
            assertEquals(hashMap.getSize(),0);
    }
    @Test
    public void QuadraticBatchInsert()
    {
        QuadraticHash hashMap = new QuadraticHash();
        hashMap.batchInsert("data.txt");
        assertEquals(hashMap.getSize(), 3000);

    }
    @Test
    public void QuadraticBatchDelete()
    {
        QuadraticHash hashMap = new QuadraticHash();
        hashMap.batchInsert("data.txt");
        assertEquals(hashMap.getSize(), 3000);
        hashMap.batchDelete("data.txt");
        assertEquals(hashMap.getSize(), 0);
    }
    @Test
    public void HeavyDutyBatchInsert()
    {
        QuadraticHash hashMap = new QuadraticHash();
        hashMap.batchInsert("heavy.txt");
        assertEquals(hashMap.getSize(), 10000);
    }
    @Test
    public void HeavyDutyBatchDelete()
    {
        QuadraticHash hashMap = new QuadraticHash();
        hashMap.batchInsert("heavy.txt");
        assertEquals(hashMap.getSize(), 10000);
        hashMap.batchDelete("heavy.txt");
        assertEquals(hashMap.getSize(), 0);
    }
}
