package org.sj.profiling.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "RELATIVE_CONTACT")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class RelativeContact {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "UUID", updatable = false, nullable = false)
    private UUID UUID;

    @Column(name = "RELATIVE_UUID", updatable = false, nullable = false)
    private UUID relativeUUID;

    @Column(name = "TYPE", length = 20)
    private String type;

    @Column(name = "VALUE", length = 30)
    private String value;

    @CreationTimestamp
    @Column(name = "ADDED_DATE")
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

}
