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
@Table(name = "MEMBER_RELATIVE")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class MemberRelative {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "UUID", updatable = false, nullable = false)
    private UUID UUID;

    @Column(name = "MEMBER_UUID", updatable = false, nullable = false)
    private UUID memberUUID;

    @Column(name = "RELATIVE_UUID", nullable = false)
    private UUID relativeUUID;

    @Column(name = "RELATIONSHIP", length = 20)
    private String relationship;

    @CreationTimestamp
    @Column(name = "ADDED_DATE")
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;
}
