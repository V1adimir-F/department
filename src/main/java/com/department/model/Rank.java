package com.department.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Vladimir F. 05.11.2022 21:01
 */

@Entity
@Table(name = "rank")
@Getter
@Setter
@NoArgsConstructor
public class Rank extends BaseEntity{

    @Column(name = "rank_name")
    private String rankName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rank rank = (Rank) o;
        return Objects.equals(rankName, rank.rankName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rankName);
    }

    @Override
    public String toString() {
        return "Rank{" +
                "rankName='" + rankName + '\'' +
                '}';
    }
}
