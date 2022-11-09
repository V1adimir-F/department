package com.department.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

/**
 * @author Vladimir F. 05.11.2022 21:08
 */

@Entity
@Table(name = "post")
@Getter
@Setter
@NoArgsConstructor
public class Post extends BaseEntity{

    @Column(name = "post_name")
    private String postName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(postName, post.postName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postName);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postName='" + postName + '\'' +
                '}';
    }
}
