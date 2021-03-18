package guru.springframework.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class IngredientTest {

    Ingredient ingredient;
    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        ingredient = new Ingredient();
    }

    @Test
    void getId() throws Exception {
        Long id = 5l;
        ingredient.setId (id);
        assertEquals(ingredient.getId(), id);
    }
}