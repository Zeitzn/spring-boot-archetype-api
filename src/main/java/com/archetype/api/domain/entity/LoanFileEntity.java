package com.archetype.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "loans", name = "loan_file")
public class LoanFileEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "folder_path", length = 150)
        private String folderPath;
        @Column(name = "active")
        private boolean active;
        @ManyToOne
        @JoinColumn(name = "loan_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_loan_file_loan_id"))
        private LoanEntity loanRequestType;
}
