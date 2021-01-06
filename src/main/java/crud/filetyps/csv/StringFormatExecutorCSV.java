package crud.filetyps.csv;

import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import crud.filetyps.Executable;
import crud.fileutils.Constants;
import person.Person;

import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static crud.fileutils.Constants.ENTER_PERSON_DATA_UPDATE;
import static crud.fileutils.Constants.FILE_WAS_UPD;

public class StringFormatExecutorCSV implements Executable {
    private final Scanner scanner;

    public StringFormatExecutorCSV() {
        scanner = new Scanner(System.in);
    }

    public boolean write(String fileName, List<Person> arrayList) {

        try {
            Writer writer  = new FileWriter(fileName);
            StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer)
                    .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                    .build();
            sbc.write(arrayList);
            writer.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Person> read(String fileName) {
        List<Person> personList = null;
        try {
            personList = new CsvToBeanBuilder<Person>(new FileReader(fileName))
                    .withType(Person.class)
                    .build()
                    .parse();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public List<Person> update(List<Person> arrayList, int id) {
        Iterator<Person> iterator = arrayList.iterator();

        System.out.println(ENTER_PERSON_DATA_UPDATE);
        String personData = scanner.nextLine();

        String[] array = personData.split(" ");

        while (iterator.hasNext()) {

            Person iterPerson = iterator.next();

            if (iterPerson.getId() == id) {
                iterPerson.setFirstName(array[0]);
                iterPerson.setLastName(array[1]);
                iterPerson.setAge(array[2]);
                iterPerson.setCity(array[3]);
                break;
            }
        }
        System.out.println(FILE_WAS_UPD);
        return arrayList;
    }

    public List<Person> delete(int id, List<Person> arrayList) {

        Iterator<Person> iterator = arrayList.iterator();

        while (iterator.hasNext()) {

            Person iterPerson = iterator.next();

            if (iterPerson.getId() == id) {
                iterator.remove();
                break;
            }
        }
        System.out.println(Constants.FILE_ELEMENT_WAS_DEL);
        return arrayList;
    }
}
