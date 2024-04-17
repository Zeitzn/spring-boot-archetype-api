package com.archetype.api.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(schema = "loans", name = "loan_quote")
public class LoanQuoteEntity extends Auditable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(name = "discount_date")
        private Date discountDate;
        @Column(name = "status", length = 20)
        private String status;
        @Column(name = "amount", columnDefinition = "DECIMAL(10, 2)")
        private BigDecimal amount;
        @ManyToOne
        @JoinColumn(name = "loan_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_loan_quote_loan_id"))
        private LoanEntity loanRequestType;
}
