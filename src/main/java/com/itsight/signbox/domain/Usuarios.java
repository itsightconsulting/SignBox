package com.itsight.signbox.domain;

import com.itsight.signbox.domain.base.AuditingEntity;
import com.itsight.signbox.domain.dto.SecurityUserDTO;
import com.itsight.signbox.domain.pojo.UsuariosPOJO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@SqlResultSetMappings({
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
        ),
        @SqlResultSetMapping(
                name="getByUsername",
                classes = {
                        @ConstructorResult(
                                targetClass = SecurityUserDTO.class,
                                columns = {
                                        @ColumnResult(name = "usuarioId"),
                                        @ColumnResult(name = "nombreUsuario"),
                                        @ColumnResult(name = "contrasena"),
                                        @ColumnResult(name = "rol"),
                                        @ColumnResult(name = "flagActivo")
                                }
                        )
                }
        ),
        @SqlResultSetMapping(
                name="getByUserId",
                classes = {
                        @ConstructorResult(
                                targetClass = SecurityUserDTO.class,
                                columns = {
                                        @ColumnResult(name = "usuarioId"),
                                        @ColumnResult(name = "nombreUsuario"),
                                        @ColumnResult(name = "nombres"),
                                        @ColumnResult(name = "apellidos")
                                }
                        )
                }
        )
})

@NamedNativeQueries({
        @NamedNativeQuery(query = "SELECT su.USUARIOID as usuarioId, su.NOMBREUSUARIO as nombreUsuario , su.NOMBRES as nombres ,  su.PATERNO CONCAT ' ' CONCAT   su.MATERNO  as apellidos " +
                " FROM usuarios su WHERE su.USUARIOID = :usuarioId",
                name = "Usuarios.getForCookieById",
                resultSetMapping = "getByUserId")
        ,
})
@NamedNativeQuery(query = "SELECT su.USUARIOID as usuarioId, su.NOMBREUSUARIO as nombreUsuario , su.CONTRASENA  as contrasena, p.ROL as rol, su.FLAGACTIVO as flagActivo" +
                          " FROM usuarios su INNER JOIN PERFIL p ON su.PERFILID = p.PERFILID  WHERE su.NOMBREUSUARIO = :nombreUsuario",
                  name = "Usuarios.findByNombreUsuario",
                  resultSetMapping = "getByUsername")

@EqualsAndHashCode(callSuper = false)
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
     this.contrasena =  new BCryptPasswordEncoder().encode(usuario.getContrasena());
     this.nombres = usuario.getNombres();
     this.paterno = usuario.getPaterno();
     this.materno = usuario.getMaterno();
     this.correoElectronico = usuario.getCorreoElectronico();
     this.dni = usuario.getDni();

  }

}
