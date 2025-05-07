package perfecthashing;

import static org.junit.Assert.assertTrue;

import perfecthashing.Hashes.QuadraticHash;

public class QuadraticHashDemo {
    public static void main(String[] args) {
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
                hashMap.insert(word);
            }
            System.out.println(words.length);
            System.out.println(hashMap.getSize());
    }
}
