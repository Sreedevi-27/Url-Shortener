package com.urlshortner.repo.domain;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "counter")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MysqlCounter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(unique=true)
    long startValue;
    @Column(unique=true)
    long endValue;
    boolean used;
}
