package org.cloud.client.validators;

public record PasswordValidator(String password) implements Validator {
    @Override
    public boolean isValid() {
        if (password == null) {
            return true;
        }

        var length = password.length();

        return !(length > 8 && 30 > length);
    }
}
