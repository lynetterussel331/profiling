package org.sj.profiling.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "MEMBER_CONTACT")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class MemberContact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COLLECTION_ID", updatable = false, nullable = false)
    long collectionId;

    @Column(name = "MEMBER_UUID", updatable = false, nullable = false)
    private UUID memberUUID;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "VALUE")
    private String value;

    @CreationTimestamp
    @Column(name = "ADDED_DATE")
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;

}
