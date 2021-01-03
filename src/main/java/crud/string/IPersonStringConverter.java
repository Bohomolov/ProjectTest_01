package crud.string;
import com.fasterxml.jackson.core.JsonProcessingException;
import person.Person;

import java.util.List;

public interface IPersonStringConverter {
    String personToString(List<Person> person) throws JsonProcessingException;
    List<Person> stringToPerson(String personsStr) throws JsonProcessingException;
}
