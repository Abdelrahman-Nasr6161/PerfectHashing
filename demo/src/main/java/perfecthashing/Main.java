package perfecthashing;

import java.util.Scanner;

import perfecthashing.Hashes.IHash;
import perfecthashing.Hashes.QuadraticHash;
import perfecthashing.Hashes.LinearHash;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IHash dictionary = null;

        System.out.println("Choose hashing method: \n1. o(n^2)\n2. o(n)");
        int mainChoice = -1;
        try {
            mainChoice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input");
            System.exit(1);
        }

        switch (mainChoice) {
            case 1:
                dictionary = new QuadraticHash();
                break;

            case 2:
                // throw new UnsupportedOperationException("o(n) hashing is not implemented
                // yet.");
                dictionary = new LinearHash();
                break;
            default:
                System.out.println("Invalid choice!");
                System.exit(1);

        }

        while (true) {
            int subChoice = -1;

            System.out.println(
                    "1. Insert a string \n2. Delete a string \n3. Search for a string \n4. Batch insert a list of strings \n5. Batch delete a list of strings \n6. Display table \n7. Exit");

            String str;
            try {
                subChoice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Invalid input");
                scanner.nextLine();
                continue;
            }

            switch (subChoice) {
                case 1:
                    System.out.println("Enter the string to insert:");
                    str = scanner.nextLine();
                    boolean isInserted = dictionary.insert(str);
                    if (isInserted) {
                        System.out.println("String inserted succefully!\n");
                    } else {
                        System.out.println("String is already in the table!\n");
                    }

                    break;

                case 2:
                    System.out.println("Enter the string to delete:");
                    str = scanner.nextLine();
                    boolean isDeleted = dictionary.delete(str);
                    if (isDeleted) {
                        System.out.println("String deleted succefully!\n");
                    } else {
                        System.out.println("String isn't in the table!\n");
                    }

                    break;

                case 3:
                    System.out.println("Enter the string to search for:");
                    str = scanner.nextLine();
                    boolean exists = dictionary.search(str);
                    if (exists) {
                        System.out.println("String exists in the table!\n");
                    } else {
                        System.out.println("String isn't in the table!\n");
                    }

                    break;

                case 4:
                    System.out.println("Batch insert; Enter the path of the file (data.txt): ");
                    str = scanner.nextLine();
                    if (str.isEmpty()) {
                        str = "data.txt";
                    }
                    dictionary.batchInsert(str);

                    break;

                case 5:
                    System.out.println("Batch delete; Enter the path of the file (data.txt): ");
                    str = scanner.nextLine();
                    if (str.isEmpty()) {
                        str = "data.txt";
                    }
                    dictionary.batchDelete(str);

                    break;

                case 6:
                    dictionary.display();
                    break;

                case 7:
                    System.exit(0);
                    break;

                default:
                    subChoice = -1;
                    break;
            }

            if (subChoice == -1) {
                System.out.println("Invalid choice!\n");
                continue;
            }

        }

    }
}