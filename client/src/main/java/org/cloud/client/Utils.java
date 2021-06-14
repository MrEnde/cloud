package org.cloud.client;

import javafx.css.PseudoClass;
import javafx.scene.control.TextField;
import lombok.SneakyThrows;
import org.cloud.client.validators.Validator;

public class Utils {
    @SneakyThrows
    public static void setPseudoClassForField(TextField field, boolean condition) {
        field.pseudoClassStateChanged(PseudoClass.getPseudoClass("error"), condition);
    }
}
