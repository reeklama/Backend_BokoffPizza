package com.pizzeriaweb.bokoffpizza.controller;

import com.pizzeriaweb.bokoffpizza.model.DishModel;
import com.pizzeriaweb.bokoffpizza.rest.FavoritesRequestDTO;
import com.pizzeriaweb.bokoffpizza.service.FavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin( origins = "*", maxAge = 3500)
@RestController
@RequestMapping("/favorites")
public class FavoritesController {

    @Autowired
    FavoritesService favoritesService;

    @GetMapping
    public ResponseEntity<?> getFavorites(Principal principal) {
        try {
            List<DishModel> dishes = favoritesService.getFavoritesDishesByMais(principal.getName());
            return ResponseEntity.ok(dishes);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> addFavorite(@RequestBody FavoritesRequestDTO request, Principal principal){
        try {
            favoritesService.addFavoriteDish(principal.getName(), request.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("success");
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFavorite(@RequestBody FavoritesRequestDTO request, Principal principal){
        try {
            favoritesService.deleteFavoriteDish(principal.getName(), request.getName());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("success");
    }
}
