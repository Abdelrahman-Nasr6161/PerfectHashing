package perfecthashing;

import perfecthashing.Hashes.QuadraticHash;

public class QuadraticHashDemo {
    public static void main(String[] args) {
        QuadraticHash map2 = new QuadraticHash();
        map2.batchInsert("data.txt");
        map2.display();
        System.out.println(map2.getSize());
    }
}
