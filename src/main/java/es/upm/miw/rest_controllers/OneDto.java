package es.upm.miw.rest_controllers;

public class OneDto {
    private String value;

    public OneDto() {
        // for the framework
    }

    public OneDto(One one) {
        this.value = one.getValue();
    }

    public OneDto(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "OneDto{" +
                "value='" + value + '\'' +
                '}';
    }
}
