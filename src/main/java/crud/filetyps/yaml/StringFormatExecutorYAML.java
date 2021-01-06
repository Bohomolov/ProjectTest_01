package crud.filetyps.yaml;

import crud.filetyps.Executable;
import crud.fileutils.Constants;
import crud.fileutils.FileUtils;
import crud.string.IPersonStringConverter;
import person.Person;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import static crud.fileutils.Constants.ENTER_PERSON_DATA_UPDATE;
import static crud.fileutils.Constants.FILE_WAS_UPD;

public class StringFormatExecutorYAML implements Executable {
    private final FileUtils fileUtils;
    private final Scanner scanner;
    private final IPersonStringConverter personStringConverter;

    public StringFormatExecutorYAML(IPersonStringConverter personStringConverter) {
        this.personStringConverter = personStringConverter;
        fileUtils = new FileUtils();
        scanner = new Scanner(System.in);
    }


    public boolean write(String fileName, List<Person> arrayList) {
        String content;
        content = personStringConverter.personToString(arrayList);
        arrayList.clear();
        return fileUtils.saveToFile(fileName, content);
    }


    public List<Person> read(String fileName) {
        List<Person> personList;
        String output = fileUtils.readFromFile(fileName);
        personList = personStringConverter.stringToPerson(output);
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
