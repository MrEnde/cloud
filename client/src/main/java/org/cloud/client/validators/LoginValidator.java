package org.cloud.client.validators;

public record LoginValidator(String login) implements Validator {
    @Override
    public boolean isValid() {
        if (login == null) {
            return true;
        }

        var length = login.length();

        return !(length < 30 && length > 6);
    }
}
