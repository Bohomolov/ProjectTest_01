import com.fasterxml.jackson.core.JsonProcessingException;
import crud.file_typs.Executable;
import crud.file_typs.binary.*;
import crud.file_typs.csv.StringFormatExecutorCSV;
import crud.file_typs.xml.StringFormatExecutorXML;
import crud.file_utils.Constants;
import crud.file_utils.FileUtils;
import crud.string.impl.JsonStringConverter;
import crud.string.impl.XmlStringConverter;
import crud.string.impl.YamlStringConverter;
import crud.file_typs.yaml.*;
import crud.file_typs.json.*;
import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static crud.file_utils.Constants.*;

public class AppController {
    private final Scanner scanner;
    private List<Person> arrayList;
    private List<Person> tempList;
    private Executable executor;
    private final FileUtils fileUtils;
    private String fileName;

    public AppController() {
        scanner = new Scanner(System.in);
        arrayList = new ArrayList<>();
        fileUtils = new FileUtils();
    }

    public void run()  {
        changeFormat();
        while (true) {

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase(CREATE)) {
                creator();

            } else if (command.equalsIgnoreCase(EXIT)) {
                System.exit(0);

            } else if (command.equalsIgnoreCase(READ)) {

                if (fileName.equalsIgnoreCase(SWITCH)) {
                    changeFormat();
                } else if (!fileUtils.isFileEmpty(fileName)) {

                    try {
                        tempList = executor.read(fileName);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                    for (Person p : tempList) {
                        System.out.println(p);
                    }
                } else {
                    System.out.println(FILE_IS_EMPTY);
                }
                System.out.println(ENTER_COMMAND);

            } else if (command.equalsIgnoreCase(SWITCH)) {
                changeFormat();

            } else if (command.equalsIgnoreCase(SAVE)) {
                save(arrayList);

            } else if (command.equalsIgnoreCase(UPDATE)) {

                if (!fileUtils.isFileEmpty(fileName)) {
                    try {
                        arrayList = executor.read(fileName);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println(FILE_IS_EMPTY);

                }

                System.out.println(Constants.ENTER_ID);
                int id = scanner.nextInt();

                executor.update(arrayList, id);

                System.out.println(ENTER_COMMAND);

            } else if (command.equalsIgnoreCase(DELETE)) {

                if (!fileUtils.isFileEmpty(fileName)) {
                    try {
                        arrayList = executor.read(fileName);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Constants.ENTER_ID);
                    int id = scanner.nextInt();
                    executor.delete(id, arrayList);

                } else {
                    System.out.println(FILE_IS_EMPTY);

                }

                System.out.println(ENTER_COMMAND);
            }
        }
    }

    public void changeFormat() {

        System.out.println(Constants.ENTER_FILE_NAME);
        fileName = scanner.nextLine();

        fileUtils.createFile(fileName);

        System.out.println(Constants.CHOOSE_FORMAT);
        String newFormat = scanner.nextLine();

        switch (newFormat) {
            case Constants.BINARY:
                executor = new BinaryExecutor();
                break;
            case Constants.JSON:
                executor = new StringFormatExecutorJSON(new JsonStringConverter());
                break;
            case Constants.CSV:
            executor = new StringFormatExecutorCSV();
            break;
            case Constants.YAML:
            executor = new StringFormatExecutorYAML(new YamlStringConverter());
            break;
            case Constants.XML:
            executor = new StringFormatExecutorXML(new XmlStringConverter());
            break;
            default:
                System.out.println(Constants.INCORRECT_FORMAT);
                break;
        }

        System.out.println(ENTER_COMMAND);
    }

    private void creator() {
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
                arrayList = executor.read(fileName);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

            if (fileUtils.isIdLegal(arrayList, person)) {
                arrayList.add(person);

                System.out.println(PERSON_WAS_CREATE);
                save(arrayList);
            }
        } else {

            arrayList.add(person);
            save(arrayList);
            System.out.println(PERSON_WAS_CREATE);
        }
    }

    private void save(List<Person> arrayList)  {
        if (!arrayList.isEmpty()) {
            try {
                executor.write(fileName, arrayList);
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
