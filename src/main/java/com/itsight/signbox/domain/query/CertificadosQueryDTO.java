package com.itsight.signbox.domain.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CertificadosQueryDTO implements Serializable {

    @Positive
    @Max(100)
    private Integer limit;

    @PositiveOrZero
    private Integer offset;

    @Size(max = 50)
    private String alias;

    private Boolean flagActivo;
}
