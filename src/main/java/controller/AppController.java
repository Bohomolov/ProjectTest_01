package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import crud.filetyps.Executable;
import crud.filetyps.ExecutorFactory;
import crud.fileutils.Constants;
import crud.fileutils.FileUtils;
import helper.Helper;
import person.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static crud.fileutils.Constants.*;

public class AppController {
    private final ControllerUtils controllerUtils;
    private final ExecutorFactory executorFactory;
    private final Scanner scanner;
    private final Helper helper;
    private List<Person> personList;
    private final FileUtils fileUtils;

    public AppController() {
        executorFactory = new ExecutorFactory();
        controllerUtils = new ControllerUtils();
        helper = new Helper();
        scanner = new Scanner(System.in);
        personList = new ArrayList<>();
        fileUtils = new FileUtils();
    }

    public void run() {
        String fileName = controllerUtils.fileCreator();
        Executable executor = executorFactory.getInstance();
        while (true) {

            String command = scanner.nextLine();

            if (command.equalsIgnoreCase(CREATE)) {
                helper.personCreator(fileName, executor, personList);

            } else if (command.equalsIgnoreCase(EXIT)) {
                System.exit(0);

            } else if (command.equalsIgnoreCase(READ)) {

                if (fileName.equalsIgnoreCase(SWITCH)) {
                    fileName = controllerUtils.fileCreator();
                    executor = executorFactory.getInstance();
                } else if (!fileUtils.isFileEmpty(fileName)) {
                    List<Person> tempList = new ArrayList<>();
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
                fileName = controllerUtils.fileCreator();
                executor = executorFactory.getInstance();
            } else if (command.equalsIgnoreCase(SAVE)) {
                helper.save(personList, fileName, executor);

            } else if (command.equalsIgnoreCase(UPDATE)) {

                if (!fileUtils.isFileEmpty(fileName)) {
                    try {
                        personList = executor.read(fileName);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }

                } else {
                    System.out.println(FILE_IS_EMPTY);

                }

                System.out.println(Constants.ENTER_ID);
                int id = scanner.nextInt();

                executor.update(personList, id);

                System.out.println(ENTER_COMMAND);

            } else if (command.equalsIgnoreCase(DELETE)) {

                if (!fileUtils.isFileEmpty(fileName)) {
                    try {
                        personList = executor.read(fileName);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Constants.ENTER_ID);
                    int id = scanner.nextInt();
                    executor.delete(id, personList);

                } else {
                    System.out.println(FILE_IS_EMPTY);

                }

                System.out.println(ENTER_COMMAND);
            }
        }
    }

}
