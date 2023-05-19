package ar.edu.utn.frc.tup.lciii.entities;

import ar.edu.utn.frc.tup.lciii.models.DummyType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.List;


/*
Esta anotación se utiliza para marcar una clase como una entidad de la base de datos.
Cada entidad representa una tabla en la base de datos y cada instancia
de la entidad representa una fila en esa tabla.
 */
@Entity
/*
Suppose we want to provide an additional condition to the query whenever we request some entity.
For instance, we need to implement “soft delete”. This means that the entity is never deleted
from the database, but only marked as deleted with a boolean field.
The @Where annotation on a method contains an SQL clause that will be added to any query or subquery to this entity
 */
@Where(clause = "is_deleted = false")
/*
Esta anotación se utiliza para especificar el nombre de la tabla asociada a una entidad.
Puede utilizarse para personalizar el nombre de la tabla y sus columnas,
así como para establecer restricciones adicionales.
 */
@Table(name = "dummies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DummyEntity {

    /*
    Esta anotación se utiliza para marcar una propiedad de una entidad como
    la clave primaria de la tabla. Generalmente, se utiliza en combinación
    con la anotación @GeneratedValue para indicar que el valor de la clave
    primaria será generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
    Esta anotación se utiliza para marcar una propiedad que representa la
    versión de una entidad. Se utiliza en combinación con una estrategia de
    control de concurrencia para garantizar la consistencia de los datos.
     */
    @Version
    private Integer version;

    /*
    Esta anotación se utiliza para mapear una propiedad de una entidad a una
    columna en la tabla de la base de datos. Puede utilizarse para personalizar
    el nombre de la columna, su tipo de datos y otras características, como la longitud o si es nullable.
     */
    @Column
    private String dummyInfo;

    /*
    Esta anotación se utiliza para establecer una relación uno a muchos entre dos
    entidades. Indica que una entidad tiene una colección de otras entidades relacionadas.
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dummy", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<DummyItemEntity> dummyItems;

    /*
    Esta anotación se utiliza para establecer una relación uno a uno entre dos entidades.
    Indica que una entidad está relacionada con otra entidad de forma exclusiva.
     */
    @OneToOne(mappedBy = "dummy", cascade = CascadeType.ALL)
    private DummyDetailEntity dummyDetail;

    /*
    Esta anotación se utiliza para mapear una propiedad enumerada a una columna de
    base de datos. Permite especificar cómo se debe almacenar y recuperar el
    valor de la propiedad enumerada.
     */
    @Enumerated(EnumType.STRING)
    private DummyType type;

    /*
    Esta anotación se utiliza para mapear una propiedad de fecha o tiempo a una
    columna de base de datos. Permite especificar el tipo de temporalidad
    de la propiedad, como fecha, hora o fecha y hora.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedDate;

    @Column
    private Boolean isDeleted = false;
}
