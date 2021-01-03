package crud.string;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.opencsv.bean.CsvToBean;
import person.Person;

import java.util.List;

public interface IPersonStringConverter {
    String personToString(List<Person> person);
    List<Person> stringToPerson(String personsStr) ;
}
