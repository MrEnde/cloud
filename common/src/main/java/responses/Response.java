package responses;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Response implements Serializable {
    private Serializable data;
    private Status status;

}
