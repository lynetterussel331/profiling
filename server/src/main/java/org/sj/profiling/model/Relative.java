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
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "RELATIVE")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
public class Relative {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "UUID", updatable = false, nullable = false)
    private UUID UUID;

    @Column(name = "FIRST_NAME", length = 30)
    private String firstName;

    @Column(name = "MIDDLE_NAME", length = 30)
    private String middleName;

    @Column(name = "LAST_NAME", length = 30)
    private String lastName;

    @Column(name = "GENDER", length = 10)
    private String gender;

    @Column(name = "OCCUPATION", length = 30)
    private String occupation;

    @Column(name = "ADDRESS", length = 50)
    private String address;

    @CreationTimestamp
    @Column(name = "ADDED_DATE")
    private LocalDateTime addedDate;

    @UpdateTimestamp
    @Column(name = "MODIFIED_DATE")
    private LocalDateTime modifiedDate;
}