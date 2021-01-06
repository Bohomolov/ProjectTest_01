package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import crud.filetyps.Executable;
import crud.fileutils.Constants;
import crud.fileutils.FileUtils;
import person.Person;

import java.util.List;
import java.util.Scanner;

import static crud.fileutils.Constants.*;
import static crud.fileutils.Constants.ENTER_COMMAND;

public class Helper {
    private final Scanner scanner;
    private final FileUtils fileUtils;

    public Helper() {
        scanner = new Scanner(System.in);
        fileUtils = new FileUtils();
    }

    public void personCreator(String fileName, Executable executor, List<Person> personList) {
        System.out.println(Constants.ENTER_PERSON_DATA);
        String personData = scanner.nextLine();

        String[] array = personData.split(" ");

        Person person = new Person(
                Integer.parseInt(array[0]),
                array[1],
                array[2],
                array[3],
                array[4]);

        if (!fileUtils.isFileEmpty(fileName)) {

            try {
                personList = executor.read(fileName);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            if (fileUtils.isIdLegal(personList, person)) {
                personList.add(person);

                System.out.println(PERSON_WAS_CREATE);
                save(personList, fileName, executor);
            }
        } else {

            personList.add(person);
            save(personList, fileName, executor);
            System.out.println(PERSON_WAS_CREATE);
        }
    }

    public void save(List<Person> personList, String fileName, Executable executor) {
        if (!personList.isEmpty()) {
            try {
                executor.write(fileName, personList);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            System.out.println(FILE_WAS_SAVE);
        } else {
            System.out.println(FILE_UNCHANGED);
        }
        System.out.println(ENTER_COMMAND);
    }

}
