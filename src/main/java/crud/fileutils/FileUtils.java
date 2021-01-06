package crud.fileutils;

import person.Person;

import java.io.*;
import java.util.Iterator;
import java.util.List;

import static crud.fileutils.Constants.ILLEGAL_PERSON_ID;

public class FileUtils {

    public boolean saveToFile(String fileName, String content) {
        File file = new File(fileName);
        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(content);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createFile(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public boolean isFileEmpty(String fileName) {
        File file = new File(fileName);
        return file.length() == 0;
    }

    public boolean isIdLegal(List<Person> arrayList, Person person) {
        Iterator<Person> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            Person iterPerson = iterator.next();
            if (iterPerson.getId() == person.getId()) {
                System.out.println(ILLEGAL_PERSON_ID);
                return false;
            }
        }
        return true;
    }


    public String readFromFile(String fileName) {
        int bit = 0;
        String output = "";
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF8")) {
            while ((bit = inputStreamReader.read()) != -1) {
                output += (char) bit;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}