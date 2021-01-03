package crud.string.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import crud.string.IPersonStringConverter;
import person.Person;
import java.lang.reflect.Type;
import java.util.List;

public class JsonStringConverter implements IPersonStringConverter {

    private final Gson gson;
    private final Type TYPE = new TypeToken<List<Person>>() { }.getType();

    public JsonStringConverter() {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Override
    public String personToString(List<Person> person) {
        return gson.toJson(person);
    }

    @Override
    public List<Person> stringToPerson(String personsStr) {
        return gson.fromJson(personsStr, TYPE);
    }
}