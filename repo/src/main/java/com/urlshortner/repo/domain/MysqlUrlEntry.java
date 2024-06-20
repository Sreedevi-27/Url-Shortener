package com.urlshortner.repo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "url_entry")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MysqlUrlEntry {
    @Id
    String id;
    String longUrl;
}
