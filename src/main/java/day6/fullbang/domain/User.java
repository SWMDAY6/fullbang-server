package day6.fullbang.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;

@Entity
@Setter // TODO refactor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "user_id", unique = true)
    private String id;

    @Column(name = "user_name", unique = true)
    private String name;

    @Column
    private String password;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

//    TODO implement AUTH
}
