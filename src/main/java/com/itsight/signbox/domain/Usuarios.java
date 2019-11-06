package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.pojo.CertificadosPOJO;
import com.itsight.signbox.domain.pojo.UsuariosPOJO;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@SqlResultSetMapping(
        name = "UsuariosGetAll",
        classes = {
                @ConstructorResult(
                        targetClass = UsuariosPOJO.class,
                        columns = {
                                @ColumnResult(name = "id"),
                                @ColumnResult(name = "nombres"),
                                @ColumnResult(name = "dni"),
                                @ColumnResult(name = "perfilNombre"),
                                @ColumnResult(name = "nombreUsuario"),
                                @ColumnResult(name = "flagActivo"),
                                @ColumnResult(name = "rows")

                        }

                )
        }
)
@Entity
@Data
public class Usuarios  extends AuditingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USUARIOID")
  private Integer usuarioId;

  @Column(name = "CONTRASENA")
  private String contrasena;

  @NotBlank
  @Column(nullable = false, name = "NOMBRES")
  private String nombres;

  @NotBlank
  @Column(nullable = false, name = "NOMBREUSUARIO")
  private String nombreUsuario;

  @NotBlank
  @Column(nullable = false, name = "PATERNO")
  private String paterno;

  @NotBlank
  @Column(nullable = false, name = "MATERNO")
  private String materno;

  @Column(nullable = false, name = "PERFILID")
  private Integer perfilId;

  @Column(nullable = false , name = "MODOACCESOID")
  private Integer modoAccesoId;

  @NotBlank
  @Column(nullable = false , name = "CORREOELECTRONICO")
  private String correoElectronico;

  @NotBlank
  @Column(nullable = false , name = "DNI")
  private String dni;

  @Column(name = "FLAGACTIVO", nullable = false)
  private boolean flagActivo;

  @Column(name = "FLAGELIMINADO", nullable = false)
  private boolean flagEliminado;

  public void setUsuario ( Usuarios usuario){
     this.contrasena = usuario.getContrasena();
     this.nombres = usuario.getNombres();
     this.paterno = usuario.getPaterno();
     this.materno = usuario.getMaterno();
     this.correoElectronico = usuario.getCorreoElectronico();
     this.dni = usuario.getDni();

  }

}
