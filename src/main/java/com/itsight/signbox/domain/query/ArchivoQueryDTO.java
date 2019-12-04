package com.itsight.signbox.domain.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArchivoQueryDTO implements Serializable {

    @Positive
    @Max(100)
    private Integer limit;

    @PositiveOrZero
    private Integer offset;

    @Size(max = 50)
    private String documentoId;

}
