package com.pottery.subscribe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "subscribes")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subscribe {

    @Id
    private String email;

    @Column
    private UUID token;
}
