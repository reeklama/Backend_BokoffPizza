package com.pizzeriaweb.bokoffpizza.entity;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Favorites {
    @EmbeddedId
    private FavoritesId id;

    @ManyToOne
    @JoinColumn(name = "dish_id", insertable = false, updatable = false)
    private Dish dish;

    @ManyToOne
    @JoinColumn(name = "registered_user_id", insertable = false, updatable = false)
    private RegisteredUser registeredUser;
}
