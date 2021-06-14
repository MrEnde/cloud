package org.cloud.client.validators;

public record LastnameValidator(String lastname) implements Validator {
    @Override
    public boolean isValid() {
        if (lastname == null) {
            return true;
        }

        var length = lastname.length();

        return !(50 > length && length > 1);
    }
}
