package crud.string.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import crud.string.IPersonStringConverter;
import person.Person;

import java.util.List;

public class XmlStringConverter implements IPersonStringConverter {
    XmlMapper xmlMapper;

    public XmlStringConverter() {
        xmlMapper = new XmlMapper();
    }

    @Override
    public String personToString(List<Person> person) {
        String xml = "";
        try {
            xml = xmlMapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return xml;
    }

    @Override
    public List<Person> stringToPerson(String personsStr) {
        List<Person> listPerson = null;
        try {
            listPerson = xmlMapper.readValue(personsStr, new TypeReference<List<Person>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return listPerson;
    }
}
