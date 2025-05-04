package perfecthashing;

import perfecthashing.Hashes.QuadraticHash;

public class QuadraticHashDemo {
    public static void main(String[] args) {
        String[] corpus = {
            "apple", "banana", "cherry", "date", "elderberry", "fig", "grape", "honeydew"
        };
        QuadraticHash hashTable = new QuadraticHash(corpus);
        hashTable.insert("honeydew");
        hashTable.insert("apple");
    }
}
