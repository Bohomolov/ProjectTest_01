package controller;

import crud.fileutils.Constants;
import crud.fileutils.FileUtils;

import java.util.Scanner;

public class ControllerUtils {
   private final Scanner scanner;
   private final FileUtils fileUtils;
    public ControllerUtils(){
        scanner = new Scanner(System.in);
        fileUtils = new FileUtils();
    }

    public String fileCreator() {
        System.out.println(Constants.ENTER_FILE_NAME);
        String fileName = scanner.nextLine();
        fileUtils.createFile(fileName);
        return fileName;
    }

}
