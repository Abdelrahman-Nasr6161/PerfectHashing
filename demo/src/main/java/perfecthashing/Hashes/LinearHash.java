package perfecthashing.Hashes;

import java.util.*;

public class LinearHash implements IHash {
    private QuadraticHash[] secondLevel;
    private String[] firstLevel;
    private int flSize;
    private int totalElements = 0;
    private int totalRehashes = 0;
    private int a, b;
    Random rand = new Random();

    public LinearHash() {
        this(16);
    }

    public LinearHash(int initialSize) {
        this.flSize = initialSize;
        this.firstLevel = new String[flSize];
        this.secondLevel = new QuadraticHash[flSize];
        this.totalRehashes = 0;
        this.a = rand.nextInt(Integer.MAX_VALUE);
        this.b = rand.nextInt(Integer.MAX_VALUE);
    }

    private int firstLevelHash(String key) {
        return Math.abs((a * key.hashCode() + b)) % flSize;
    }

    public boolean insert(String key) {
        if (key == null) return false;
        
        int index = firstLevelHash(key);
        
        // Exists in first level
        if (firstLevel[index] != null && firstLevel[index].equals(key)) {
            return false;
        }
        // Exists in second level
        if (secondLevel[index] != null && secondLevel[index].search(key)) {
            return false;
        }
        // Insert into first level
        if (firstLevel[index] == null) {
            firstLevel[index] = key;
            totalElements++;
            return true;
        }
        // Handle collision (second level)
        else {
            String existingKey = firstLevel[index];
            firstLevel[index] = null;
            
            if (secondLevel[index] == null) {
                secondLevel[index] = new QuadraticHash();
            }

            secondLevel[index].insert(existingKey);
            boolean inserted = secondLevel[index].insert(key);
            if (inserted) totalElements++;
            return inserted;
        }
    }

    public boolean delete(String key) {
        if (key == null) return false;
        int index = firstLevelHash(key);
        
        // Check first level
        if (firstLevel[index] != null && firstLevel[index].equals(key)) {
            firstLevel[index] = null;
            totalElements--;
            return true;
        }
        // Check second level
        if (secondLevel[index] != null) {
            boolean deleted = secondLevel[index].delete(key);
            if (deleted) totalElements--;
            return deleted;
        }
        return false;
    }

    public boolean search(String key) {
        if (key == null) return false;
        int index = firstLevelHash(key);
        
        // Check first level
        if (firstLevel[index] != null && firstLevel[index].equals(key)) {
            return true;
        }
        // Check second level
        if (secondLevel[index] != null) {
            return secondLevel[index].search(key);
        }
        return false;
    }

    public void batchInsert(String path) {
        try (Scanner scanner = new Scanner(new java.io.File(path))) {
            int newEntries = 0;
            int existingEntries = 0;
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim();
                if (!key.isEmpty()) {
                    boolean newEntry = insert(key);
                    if (newEntry)
                        newEntries++;
                    else
                        existingEntries++;
                }
            }
            System.out.println("Inserted " + newEntries + " entries in the table");
            System.out.println(existingEntries + " entries already exist in the table");
        } catch (java.io.IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void batchDelete(String path) {
        try (Scanner scanner = new Scanner(new java.io.File(path))) {
            int deletedEntries = 0;
            int nonExistingEntries = 0;
            while (scanner.hasNextLine()) {
                String key = scanner.nextLine().trim();
                if (!key.isEmpty()) {
                    boolean deleted = delete(key);
                    if (deleted)
                        deletedEntries++;
                    else
                        nonExistingEntries++;
                }
            }
            System.out.println("Deleted " + deletedEntries + " entries from the table");
            System.out.println(nonExistingEntries + " entries don't exist in the table");
        } catch (java.io.IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public int getSize() {
       return totalElements;
    }

    public void display() {
        int totalElements = getSize();
        // First level keys
        Arrays.stream(firstLevel)
              .filter(Objects::nonNull)
              .forEach(key -> System.out.print(key + " "));
        
        // Second level keys
        Arrays.stream(secondLevel)
              .filter(Objects::nonNull)
              .flatMap(qh -> Arrays.stream(qh.getTable()).filter(Objects::nonNull))
              .forEach(key -> System.out.print(key + " "));
        
        System.out.println();
        System.out.println("This is table length: " + totalElements);
    }
}