package com.company;
import java.util.Scanner;

public class Main {

    public static Scanner scanner = new Scanner(System.in);
    public static StudentDataBase myStudentDataBase = new StudentDataBase();

    public static void main(String[] args) {
        String character;
        boolean end = false;
        boolean read = false;

        while(!end) {

            System.out.println("\n0. Leave program.");
            System.out.println("1. Clear base (Make a new file).");
            System.out.println("2. Add person.");
            System.out.println("3. Save to file.");
            System.out.println("4. Read from file.");
            System.out.println("5. Browse elements with modify options.");
            System.out.println("6. Set password.");
            System.out.println("7. Search by surname.");
            System.out.println("8. Search by year range.");
            System.out.println("9. Sort by name");
            System.out.println("x. Sort by year of birth.\n");

            if (myStudentDataBase.getPasswordLength() == 0) {
                System.out.println("You have to set password first.");
                character = "6";
            } else {
                character = scanner.nextLine();
                character = character.toLowerCase();
            }

            switch (character) {
                case "0": {
                    System.out.println("Good bye!");
                    scanner.close();
                    end = true;
                    break;
                }

                case "1": {
                    System.out.println("Do you want to create a new file? y - yes n - no");
                    character = scanner.nextLine();
                    character = character.toLowerCase();

                    if (character.equals("y")) {
                        myStudentDataBase.deleteAll();
                        System.out.println("Program is cleared.");
                        break;
                    } else if (character.equals("n")) {
                        System.out.println("Canceled.");
                        break;
                    } else {
                        System.out.println("Wrong option.");
                        break;
                    }
                }

                case "2": {
                    System.out.print("Enter the password to modify: ");
                    String password = scanner.nextLine();

                    if (!myStudentDataBase.comparePassword(password)) {
                        System.out.println("Wrong password.");
                    } else {
                        System.out.print("Add name: ");
                        String name = scanner.nextLine();

                        if (isAlphabet(name)) {
                            System.out.println("Wrong name.");
                            break;
                        }

                        name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase(); // zmiana 1 literu na dużą

                        System.out.print("Add surname: ");
                        String surname = scanner.nextLine();

                        if (isAlphabet(surname)) {
                            System.out.println("Wrong surname.");
                            break;
                        }

                        surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();

                        System.out.print("Add group: ");
                        String group = scanner.nextLine();
                        group = group.toUpperCase();

                        if (group.length() < 3) {
                            System.out.println("The group should have at least 4 characters.");
                            break;
                        }

                        System.out.print("Add day of birth: ");
                        int day = scanner.nextInt();
                        scanner.nextLine();

                        if (day < 1 || day > 31) {
                            System.out.println("Day is out of range.");
                            break;
                        }

                        System.out.print("Add month of birth: ");
                        int month = scanner.nextInt();
                        scanner.nextLine();

                        if (month < 1 || month > 12) {
                            System.out.println("Month is out of range.");
                            break;
                        }

                        System.out.print("Add year of birth: ");
                        int year = scanner.nextInt();
                        scanner.nextLine();

                        if (year < 1920 || year > 2003) {
                            System.out.println("Year is out of range.");
                            break;
                        }

                        myStudentDataBase.addElement(new Student(name, surname, group, day, month, year));
                        break;
                    }
                    break;
                }

                case "3": {
                    if (myStudentDataBase.saveToFile()) {
                        System.out.println("Successfully written to file.");
                    } else {
                        System.out.println("Failed to write to file.");
                    }
                    break;
                }

                case "4": {
                    if (read) {
                        System.out.println("Data was read already");
                    } else if (myStudentDataBase.readFromFile()) {
                        System.out.println("Successfully read from file.");
                        read = true;
                    } else {
                        System.out.println("Failed to read from file.");
                    }
                    break;
                }

                case "5": {
                    boolean end2 = false;
                    boolean search = false;

                    while(!end2) {
                        if (myStudentDataBase.getArraySize() == 0) {
                            System.out.println("No elements to display.");
                            read = false;
                            break;
                        }

                        System.out.println("c - previous  v - next " + "u - update " + "d - delete " + "q - quit\n");

                        System.out.println(myStudentDataBase.getCurrentElement());

                        character = scanner.nextLine();
                        character = character.toLowerCase();

                        switch (character) {
                            case "c": {
                                myStudentDataBase.previous(search);
                                break;
                            }

                            case "v": {
                                myStudentDataBase.next(search);
                                break;
                            }

                            case "u": {
                                System.out.print("Enter the password to modify: ");
                                String password = scanner.nextLine();

                                if (!myStudentDataBase.comparePassword(password)) {
                                    System.out.println("Wrong password.");
                                    System.out.println();
                                    break;
                                } else {
                                    System.out.println("Which information do you want to update?: ");
                                    System.out.println("1. Name");
                                    System.out.println("2. Surname");
                                    System.out.println("3. Group");
                                    System.out.println("4. Day of birth");
                                    System.out.println("5. Month of birth");
                                    System.out.println("6. Year of birth");
                                    character = scanner.nextLine();
                                    character = character.toLowerCase();

                                    switch (character) {
                                        case "1": {
                                            System.out.print("Enter name: ");
                                            String name = scanner.nextLine();

                                            if (isAlphabet(name)) {
                                                System.out.println("Wrong name.");
                                                System.out.println();
                                            } else {
                                                name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

                                                System.out.println("Do you want to update name? y - yes n - no");
                                                character = scanner.nextLine();

                                                if (character.equals("y")) {
                                                    myStudentDataBase.updateName(name);
                                                    System.out.println("Updated!");
                                                    System.out.println();
                                                } else {
                                                    System.out.println("Canceled!");
                                                    System.out.println();
                                                }
                                            }
                                            continue;
                                        }

                                        case "2": {
                                            System.out.print("Enter surname: ");
                                            String surname = scanner.nextLine();

                                            if (isAlphabet(surname)) {
                                                System.out.println("Wrong surname.");
                                                System.out.println();
                                            } else {
                                                surname = surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();

                                                System.out.println("Do you want to update surname? y - yes n - no");
                                                character = scanner.nextLine();

                                                if (character.equals("y")) {
                                                    myStudentDataBase.updateSurname(surname);
                                                    System.out.println("Updated!");
                                                    System.out.println();
                                                } else {
                                                    System.out.println("Canceled!");
                                                    System.out.println();
                                                }
                                            }
                                            continue;
                                        }

                                        case "3": {
                                            System.out.print("Enter group: ");
                                            String group = scanner.nextLine();
                                            group = group.toUpperCase();

                                            if (group.length() < 3) {
                                                System.out.println("The group should have at least 3 characters.");
                                                System.out.println();
                                            } else {
                                                System.out.println("Do you want to update group? y - yes n - no");
                                                character = scanner.nextLine();

                                                if (character.equals("y")) {
                                                    myStudentDataBase.updateGroup(group);
                                                    System.out.println("Updated!");
                                                    System.out.println();
                                                } else {
                                                    System.out.println("Canceled!");
                                                    System.out.println();
                                                }
                                            }
                                            continue;
                                        }

                                        case "4": {
                                            System.out.print("Enter day of birth: ");
                                            int day = scanner.nextInt();
                                            scanner.nextLine();

                                            if (day < 1 || day > 31) {
                                                System.out.println("Day is out of range.");
                                                System.out.println();
                                            } else {
                                                System.out.println("Do you want to update day of birth? y - yes n - no");
                                                character = scanner.nextLine();

                                                if (character.equals("y")) {
                                                    myStudentDataBase.updateDay(day);
                                                    System.out.println("Updated!");
                                                    System.out.println();
                                                } else {
                                                    System.out.println("Canceled!");
                                                    System.out.println();
                                                }
                                            }
                                            continue;
                                        }

                                        case "5": {
                                            System.out.print("Enter month of birth: ");
                                            int month = scanner.nextInt();
                                            scanner.nextLine();

                                            if (month < 1 || month > 12) {
                                                System.out.println("Month is out of range.");
                                                System.out.println();
                                            } else {
                                                System.out.println("Do you want to update month of birth? y - yes n - no");
                                                character = scanner.nextLine();

                                                if (character.equals("y")) {
                                                    myStudentDataBase.updateMonth(month);
                                                    System.out.println("Updated!");
                                                    System.out.println();
                                                } else {
                                                    System.out.println("Canceled!");
                                                    System.out.println();
                                                }
                                            }
                                            continue;
                                        }

                                        case "6": {
                                            System.out.print("Add year of birth: ");
                                            int year = scanner.nextInt();
                                            scanner.nextLine();

                                            if (year < 1920 || year > 2003) {
                                                System.out.println("Year is out of range.");
                                                System.out.println();
                                            } else {
                                                System.out.println("Do you want to update year of birth? y - yes n - no");
                                                character = scanner.nextLine();

                                                if (character.equals("y")) {
                                                    myStudentDataBase.updateYear(year);
                                                    System.out.println("Updated!");
                                                    System.out.println();
                                                } else {
                                                    System.out.println("Canceled!");
                                                    System.out.println();
                                                }
                                            }
                                            continue;
                                        }
                                    }
                                }
                            }

                            case "d": {
                                System.out.print("Enter the password to modify: ");
                                String password = scanner.nextLine();

                                if(!myStudentDataBase.comparePassword(password)) {
                                    System.out.println("Wrong password.");
                                    System.out.println();
                                } else {
                                    System.out.println("Do you want to delete this element? y - yes n - no");
                                    character = scanner.nextLine();

                                    if (character.equals("y")) {
                                        if (myStudentDataBase.deleteCurrent()) {
                                            System.out.println("Successfully deleted.");
                                        } else {
                                            System.out.println("Failed to delete.");
                                        }
                                    } else {
                                        System.out.println("Canceled.");
                                    }
                                }
                                break;
                            }

                            case "q": {
                                end2 = true;
                                break;
                            }
                        }
                    }
                    break;
                }

                case "6": {
                    if(myStudentDataBase.getPasswordLength() == 0) {
                        System.out.print("Enter new password: ");
                        String newPassword = scanner.nextLine();

                        if (myStudentDataBase.savePassword(newPassword)) {
                            System.out.println("Password saved.\n");
                        } else {
                            System.out.println("Failed to save the password.");
                        }
                    } else {
                        System.out.print("Enter previous password: ");
                        String prevPassword = scanner.nextLine();

                        if(myStudentDataBase.comparePassword(prevPassword)) {
                            System.out.print("Enter new password: ");
                            String newPassword = scanner.nextLine();

                            if (myStudentDataBase.savePassword(newPassword)) {
                                System.out.println("Password saved.\n");
                            } else {
                                System.out.println("Failed to save the password.");
                            }
                        } else {
                            System.out.println("Wrong password.");
                        }
                    }
                    break;
                }

                case "7": {
                    System.out.println("Enter surname you are looking for");
                    String word = scanner.nextLine();

                    if (isAlphabet(word)) {
                        System.out.println("Wrong surname.");
                        System.out.println();
                        break;
                    }

                    word = word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();

                    if (myStudentDataBase.searchSurname(word)) {
                        boolean end2 = false;
                        boolean search = true;

                        while (!end2) {
                            System.out.println("\nc - previous  v - next q - quit\n");

                            System.out.println(myStudentDataBase.getSearchedElement());
                            character = scanner.nextLine();
                            character = character.toLowerCase();

                            switch (character) {
                                case "c": {
                                    myStudentDataBase.previous(search);
                                    break;
                                }

                                case "v": {
                                    myStudentDataBase.next(search);
                                    break;
                                }

                                case "q": {
                                    end2 = true;
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        System.out.println("Can't find searched surname");
                    }
                    break;
                }

                case "8": {
                    System.out.println("Enter searched range.");
                    System.out.print("From: ");
                    int min = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("To: ");
                    int max = scanner.nextInt();
                    scanner.nextLine();

                    if (min > 1920 && max < 2003 && min <= max) {
                        if (myStudentDataBase.searchYear(min,max)) {
                            boolean end2 = false;
                            boolean search = true;

                            while (!end2) {
                                System.out.println("\nc - previous  v - next q - quit\n");

                                System.out.println(myStudentDataBase.getSearchedElement());
                                character = scanner.nextLine();
                                character = character.toLowerCase();

                                switch (character) {
                                    case "c": {
                                        myStudentDataBase.previous(search);
                                        break;
                                    }

                                    case "v": {
                                        myStudentDataBase.next(search);
                                        break;
                                    }

                                    case "q": {
                                        end2 = true;
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        System.out.println("Can't find searched year.");
                    }
                    break;
                }

                case "9": {
                    System.out.print("Enter the password to modify: ");
                    String password = scanner.nextLine();

                    if (!myStudentDataBase.comparePassword(password)) {
                        System.out.println("Wrong password.");
                    } else {
                        System.out.println("How do you want to sort your base?");
                        System.out.println("1. Alphabetically.");
                        System.out.println("2. In reverse order.");
                        System.out.println("3. Leave.");

                        character = scanner.nextLine();
                        character = character.toLowerCase();

                        switch (character) {
                            case "1": {
                                if (myStudentDataBase.sortAlphabetically()) {
                                    System.out.println("Sorted!");
                                } else {
                                    System.out.println("Failed to sort.");
                                }
                                break;
                            }

                            case "2": {
                                if (myStudentDataBase.sortReverseOrder()) {
                                    System.out.println("Sorted!");
                                } else {
                                    System.out.println("Failed to sort.");
                                }
                                break;
                            }

                            case "3": {
                                break;
                            }
                        }
                    }
                    break;
                }

                case "x": {
                    System.out.print("Enter the password to modify: ");
                    String password = scanner.nextLine();

                    if (!myStudentDataBase.comparePassword(password)) {
                        System.out.println("Wrong password.");
                    } else {
                        System.out.println("How do you want to sort your base?");
                        System.out.println("1. From smallest to largest.");
                        System.out.println("2. In reverse order.");
                        System.out.println("3. Leave.");

                        character = scanner.nextLine();
                        character = character.toLowerCase();

                        switch (character) {
                            case "1": {
                                if (myStudentDataBase.sortSmallLarg()) {
                                    System.out.println("Sorted!");
                                } else {
                                    System.out.println("Failed to sort.");
                                }
                                break;
                            }

                            case "2": {
                                if (myStudentDataBase.sortLargSmall()) {
                                    System.out.println("Sorted!");
                                } else {
                                    System.out.println("Failed to sort.");
                                }
                                break;
                            }

                            case "3": {
                                break;
                            }
                        }
                    }
                    break;
                }

                case "y": {
                    myStudentDataBase.printAll();
                    break;
                }
            }
        }
    }

    public static boolean isAlphabet(String string) {
        return string.equals("") || !string.matches("^[a-zA-Z]*$"); // wyrażenie regularne (jeśli string nie jest pusty i ma same litery, zwróci true
    }
}
