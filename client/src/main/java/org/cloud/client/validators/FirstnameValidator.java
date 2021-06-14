package org.cloud.client.validators;

public record FirstnameValidator(String firstname) implements Validator {
    @Override
    public boolean isValid() {
        if (firstname == null) {
            return true;
        }

        var length = firstname.length();

        return !(length > 1 && 50 > length);
    }
}
