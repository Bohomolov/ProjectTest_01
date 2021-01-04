package crud.string.impl;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import crud.string.IPersonStringConverter;
import person.Person;

import java.util.List;

public class XmlStringConverter implements IPersonStringConverter {

    @Override
    public String personToString(List<Person> person) {
        XmlMapper xmlMapper = new XmlMapper();
        return null;
    }

    @Override
    public List<Person> stringToPerson(String personsStr) {
        return null;
    }
}
