package crud.file_typs.yaml;

import com.fasterxml.jackson.core.JsonProcessingException;
import crud.file_typs.Executable;
import crud.file_utils.Constants;
import crud.file_utils.FileUtils;
import crud.string.IPersonStringConverter;
import person.Person;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static crud.file_utils.Constants.ENTER_PERSON_DATA_UPDATE;
import static crud.file_utils.Constants.FILE_WAS_UPD;

public class StringFormatExecutorYAML implements Executable {
    private FileUtils fileUtils;
    private Scanner scanner;
    private IPersonStringConverter personStringConverter;

    public StringFormatExecutorYAML(IPersonStringConverter personStringConverter) {
        this.personStringConverter = personStringConverter;
        fileUtils = new FileUtils();
        scanner = new Scanner(System.in);
    }


    public boolean write(String fileName, List<Person> arrayList) {
        String content = null;
        try {
            content = personStringConverter.personToString(arrayList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        arrayList.clear();
        return fileUtils.saveToFile(fileName, content);
    }


    public List<Person> read(String fileName) {
        List<Person> personList = new ArrayList<>();
        String output = fileUtils.readFromFile(fileName);
        try {
            personList = personStringConverter.stringToPerson(output);
        } catch (JsonProcessingException e) {
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
                iterPerson.setAge(Integer.parseInt(array[2]));
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
