package lab13.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author rauldarius
 * An abstract class representing the one of the bussiness entities of the lab13.domain.
 */
@MappedSuperclass
@Data
public abstract class BaseEntity<ID> implements Serializable {
    @Id
    @GeneratedValue
    private ID id;

}
