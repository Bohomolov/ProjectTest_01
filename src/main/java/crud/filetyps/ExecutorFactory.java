package crud.filetyps;

import crud.filetyps.binary.BinaryExecutor;
import crud.filetyps.csv.StringFormatExecutorCSV;
import crud.filetyps.json.StringFormatExecutorJSON;
import crud.filetyps.xml.StringFormatExecutorXML;
import crud.filetyps.yaml.StringFormatExecutorYAML;
import crud.fileutils.Constants;
import crud.string.impl.JsonStringConverter;
import crud.string.impl.XmlStringConverter;
import crud.string.impl.YamlStringConverter;

import java.util.Scanner;

import static crud.fileutils.Constants.ENTER_COMMAND;

public class ExecutorFactory {
   private final Scanner scanner;
    public ExecutorFactory(){
        scanner = new Scanner(System.in);
    }

    public Executable getInstance() {

        while (true) {
            System.out.println(Constants.CHOOSE_FORMAT);
            String newFormat = scanner.nextLine();
            switch (newFormat) {
                case Constants.BINARY:
                    System.out.println(ENTER_COMMAND);
                    return new BinaryExecutor();
                case Constants.JSON:
                    System.out.println(ENTER_COMMAND);
                    return new StringFormatExecutorJSON(new JsonStringConverter());
                case Constants.CSV:
                    System.out.println(ENTER_COMMAND);
                    return new StringFormatExecutorCSV();
                case Constants.YAML:
                    System.out.println(ENTER_COMMAND);
                    return new StringFormatExecutorYAML(new YamlStringConverter());
                case Constants.XML:
                    System.out.println(ENTER_COMMAND);
                    return new StringFormatExecutorXML(new XmlStringConverter());
                default:
                    System.out.println(Constants.INCORRECT_FORMAT);
            }
        }
    }
}
