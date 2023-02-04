package com.company;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StudentDataBase extends com.company.Student {

    private final ArrayList <com.company.Student> myArrayList = new ArrayList<>();
    private final ArrayList <com.company.Student> mySearchArray = new ArrayList<>();
    private int i;
    private int j;
    private int current;
    private int currentFound;

    public StudentDataBase() {
        super();
        this.current = 0;
        this.currentFound = 0;
    }

    public int getArraySize() {
        return myArrayList.size();
    }

    public void addElement(com.company.Student myStudent) {
        this.myArrayList.add(myStudent);
    }

    public void deleteAll() {
        File myFile = new File("dane.txt");

        if(myFile.exists()) {
            myFile.delete();
        }
        this.myArrayList.clear();
    }

    public boolean saveToFile() {
        try {
            FileOutputStream writeData = new FileOutputStream("dane.txt"); // tworzy plik do zapisania danych
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData); // zajmie się zapisaem obiektu do pliku

            writeStream.writeObject(this.myArrayList);   // zapisuje naszą array liste do pliku
            writeStream.flush(); // czyści dane w strumieniu I/O
            writeStream.close();

            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean readFromFile() {
        try {
            FileInputStream readData = new FileInputStream("dane.txt");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            ArrayList<com.company.Student>myArrayList2 = (ArrayList<com.company.Student>) readStream.readObject();

            for(i = 0; i < myArrayList2.size(); i++) { // przepisanie wartości do początkowej tablicy nie nadpisując danych
                myArrayList.add(myArrayList2.get(i));
            }

            readStream.close();

            return true;
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void previous(boolean search) {
        if (search) {
            if (currentFound > 0) {
                currentFound--;
            }
        } else {
            if (current > 0) {
                current--;
            }
        }
    }

    public void next(boolean search) {
        if (search) {
            if (currentFound < mySearchArray.size() - 1) {
                currentFound++;
            }
        }
        if(current < getArraySize()-1) {
            current++;
        }
    }

    public boolean savePassword(String newPassword) {
        Path path = Paths.get("C:\\Users\\kniet\\Desktop\\Sem4_Zal_Jakub_Nietupski\\password.txt");

        try {

            char[] chars = newPassword.toCharArray();

            for(i = 0; i<chars.length; i++) { // szyfrowanie
                chars[i] += 5;
            }

            newPassword = String.valueOf(chars);

            Files.writeString(path,newPassword);  // zapisanie do ścieżki, string, standardowe ustawienie charów
            return true;
        }
        catch (IOException e) {
            return false;
        }

    }

    private String readPassword() {
        Path fileName = Path.of("C:\\Users\\kniet\\Desktop\\Sem4_Zal_Jakub_Nietupski\\password.txt");

        try {
            return Files.readString(fileName);
        }
        catch (IOException e) {
            return null;
        }
    }

    public int getPasswordLength() {
        if (readPassword() == null || readPassword().length() == 0) {
            return 0;
        } else {
            return readPassword().length();
        }
    }

    public boolean comparePassword(String password) {
        String filePassword = readPassword();

        char[] chars = filePassword.toCharArray();

        for(i = 0; i<chars.length; i++) { // deszyfrowanie
            chars[i] -= 5;
        }

        filePassword = String.valueOf(chars);

        return password.equals(filePassword);
    }

    public String getCurrentElement() {
        return myArrayList.get(current).toString();
    }

    public String getSearchedElement() {
        return mySearchArray.get(currentFound).toString();
    }

    public boolean deleteCurrent() {
        if(getArraySize() == 0) {
            return false;
        } else {
            myArrayList.remove(current);
            if (current > 0) {
                current--;
            }
            return true;
        }
    }

    public void updateName(String name) {
        myArrayList.get(current).setName(name);
    }

    public void updateSurname(String surname) {
        myArrayList.get(current).setSurname(surname);
    }

    public void updateGroup(String group) {
        myArrayList.get(current).setGroup(group);
    }

    public void updateDay(int day) {
        myArrayList.get(current).setDay(day);
    }

    public void updateMonth(int month) {
        myArrayList.get(current).setMonth(month);
    }

    public void updateYear(int year) {
        myArrayList.get(current).setYear(year);
    }

    public boolean searchSurname(String word) {
        mySearchArray.clear();
        if (getArraySize() > 0) {
            for (i = 0 ; i < getArraySize(); i++) {
                if (myArrayList.get(i).getSurname().contains(word)) {
                    mySearchArray.add(myArrayList.get(i));
                }
            }
        }
        if (mySearchArray.size() == 0) {
            return false;
        } else {
            currentFound = 0;
            return true;
        }
    }

    public boolean searchYear(int min, int max) {
        mySearchArray.clear();
        if (getArraySize() > 0) {
            for (i = 0; i < getArraySize(); i++) {
                if (myArrayList.get(i).getYear() >= min && myArrayList.get(i).getYear() <= max) {
                    mySearchArray.add(myArrayList.get(i));
                }
            }
        }
        if (mySearchArray.size() == 0) {
            return false;
        } else {
            currentFound = 0;
            return true;
        }
    }

    public boolean sortAlphabetically() {
        if (getArraySize() > 1) {
            com.company.Student temp;

            for (i = 0; i < getArraySize(); i++) {

                for (j = i + 1; j < getArraySize(); j++) {

                    if (myArrayList.get(i).getName().compareTo(myArrayList.get(j).getName()) > 0) {
                        temp = myArrayList.get(i);
                        myArrayList.set(i,myArrayList.get(j));
                        myArrayList.set(j,temp);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean sortReverseOrder() {
        if (getArraySize() > 1) {
            com.company.Student temp;

            for (i = 0; i < getArraySize(); i++) {

                for (j = i + 1; j < getArraySize(); j++) {

                    if (myArrayList.get(i).getName().compareTo(myArrayList.get(j).getName()) < 0) {
                        temp = myArrayList.get(i);
                        myArrayList.set(i,myArrayList.get(j));
                        myArrayList.set(j,temp);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean sortSmallLarg() {
        if (getArraySize() > 1) {
            com.company.Student temp;

            for (i = 0; i < getArraySize(); i++) {

                for (j = i + 1; j < getArraySize(); j++) {

                    if (myArrayList.get(i).getYear() > myArrayList.get(j).getYear()) {
                        temp = myArrayList.get(i);
                        myArrayList.set(i,myArrayList.get(j));
                        myArrayList.set(j,temp);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean sortLargSmall() {
        if (getArraySize() > 1) {
            com.company.Student temp;

            for (i = 0; i < getArraySize(); i++) {

                for (j = i + 1; j < getArraySize(); j++) {

                    if (myArrayList.get(i).getYear() < myArrayList.get(j).getYear()) {
                        temp = myArrayList.get(i);
                        myArrayList.set(i,myArrayList.get(j));
                        myArrayList.set(j,temp);
                    }
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void printAll() {
        for (i=0; i<myArrayList.size();i++) {
            myArrayList.get(i).readAll();
        }
    }
}
