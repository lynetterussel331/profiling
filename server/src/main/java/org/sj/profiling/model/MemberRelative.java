package org.sj.profiling.model;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "MEMBER_RELATIVE")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "relative_uuid", referencedColumnName = "uuid", insertable = false, updatable = false)
    private Relative mainDetails;

    @Column(name = "RELATIONSHIP")
    private String relationship;

    @CreationTimestamp
    @Column(name = "ADDED_DATE")
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;
}
