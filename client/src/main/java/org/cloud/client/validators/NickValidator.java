package org.cloud.client.validators;

public record NickValidator(String nick) implements Validator {
    @Override
    public boolean isValid() {
        if (nick == null) {
            return true;
        }

        var length = nick.length();

        return !(length > 4 && 17 > length);
    }
}
