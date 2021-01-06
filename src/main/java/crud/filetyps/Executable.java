package crud.filetyps;

import com.fasterxml.jackson.core.JsonProcessingException;
import person.Person;

import java.util.List;

public interface Executable {
    boolean write(String fileName, List<Person> arrayList) throws JsonProcessingException;

    List<Person> read(String fileName) throws JsonProcessingException;

    List<Person> update(List<Person> arrayList, int id);

    List<Person> delete(int id, List<Person> arrayList);
}
