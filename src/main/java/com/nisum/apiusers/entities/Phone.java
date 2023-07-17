package com.nisum.apiusers.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//import javax.persistence.*;

@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Phone {
    @Id
    @Column(name = "number")
    private String number;

    @Column(name = "city_code")
    private int cityCode;

    @Column(name = "country_code")
    private int countryCode;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
