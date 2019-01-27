package es.upm.miw.rest_controllers;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class One {

    @Id
    private String id;

    private String value;

    public One() {
        //Empty by framework
    }

    public One(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (value.equals(((One) obj).value));
    }

    @Override
    public String toString() {
        return "One{" +
                "id='" + id + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
