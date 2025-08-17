package com.finance.finance_history.entities;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name="users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    public LocalDateTime createAt;
    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
    }
}
