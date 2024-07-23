package com.example.demo.filter.persistence;

import com.example.demo.common.persistence.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Entity
@SuperBuilder(toBuilder = true)
@Table(name = "filter")
@EntityListeners(AuditingEntityListener.class)
public class FilterEntity extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long filterId;

    @Column(nullable = false)
    private String filterName;

    @Column(nullable = false)
    private String filterColor;
}
