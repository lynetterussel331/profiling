package org.sj.profiling.model;

import java.time.*;
import java.util.UUID;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "MEMBER")
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "UUID", updatable = false, nullable = false)
    private UUID UUID;

    @Column(name = "FIRSTNAME", length = 30)
    private String firstName;

    @Column(name = "MIDDLENAME", length = 30)
    private String middleName;

    @Column(name = "LASTNAME", length = 30)
    private String lastName;

    @Column(name = "BIRTHDATE")
    private LocalDate birthDate;

    @Column(name = "BIRTHPLACE", length = 30)
    private String birthplace;

    @Column(name = "GENDER", length = 10)
    private String gender;

    @Column(name = "BRGY_REGISTERED_VOTER")
    private boolean brgyRegVoter;

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
