package guru.springframework.services;

import guru.springframework.domain.Recipe;

import java.util.Set;

public interface RecipeMappedService {
    public Set<Recipe> getRecipes ();
}
