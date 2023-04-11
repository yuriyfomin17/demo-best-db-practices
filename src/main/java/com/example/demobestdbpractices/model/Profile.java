package com.example.demobestdbpractices.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "persons")
public class Profile {

    @Id
    private Long id;

    private String pictureUrl;
    private boolean active;
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false, name = "last_updated_at")
    private LocalDateTime lastUpdatedAt = LocalDateTime.now();

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profile profile)) return false;
        return id != null && id.equals(profile.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
