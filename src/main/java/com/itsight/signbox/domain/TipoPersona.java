import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class TipoPersona {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "TIPOPERSONAID")
  private Integer tipoPersonaId;

  @NotBlank
  @Column(nullable = false , name = "DETALLE")
  private String detalle;


}
