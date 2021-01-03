package crud.string.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import crud.string.IPersonStringConverter;
import person.Person;

import java.util.ArrayList;
import java.util.List;

public class YamlStringConverter implements IPersonStringConverter {
    @Override
    public String personToString(List<Person> person) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        String result = "";
        try {
            result = mapper.writeValueAsString(person);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Person> stringToPerson(String personsStr) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        List<Person> personList = new ArrayList<>();
        TypeReference<List<Person>> typeReference = new TypeReference<List<Person>>() {
        };
        try {
            personList = mapper.readValue(personsStr, typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return personList;
    }
}
