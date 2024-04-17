package com.archetype.api.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "seguridad", name="persona")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nationalid_type")
    private String documentType;
    @Column(name="national_identifier_number")
    private String documentNumber;

    @Column(name = "person_id", length = 50)
    public String personId;

    @Column(name = "person_number", length = 100)
    public String personNumber;
    @Column(name = "work_mail", length = 100)
    public String workMail;

    @Column(name = "father_lastname", length = 100)
    private String fatherLastName;
    @Column(name = "mother_lastname", length = 100)
    private String motherLastName;
    @Column(name = "first_name", length = 100)
    private String firstName;
    @Column(name = "business_unitid")
    private String businessUnitId;


    @Transient
    private String roleName;

    public String getFullName() {
        return firstName + " " + fatherLastName + " " + motherLastName;
    }
}
