package guru.springframework.initialize;

import guru.springframework.domain.*;
import guru.springframework.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.*;

//import src/main/resources/data.sql;

@Slf4j
@Component
public class Bootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;

    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final NotesRepository notesRepository;

    private final CategoryRepository categoryRepository;

    private final IngredientRepository ingredientRepository;

    public Bootstrap(RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository, NotesRepository notesRepository, CategoryRepository categoryRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.notesRepository = notesRepository;
        this.categoryRepository = categoryRepository;
        this.ingredientRepository = ingredientRepository;
    }

/*    @Override
    public void run(String... args) throws Exception {

    }*/

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {


                    //load UnitOfMeasures from data.sql

        Optional<UnitOfMeasure>  uomPiecesOpt = unitOfMeasureRepository.findByDescription("Pieces");

        if (!uomPiecesOpt.isPresent())
            throw new RuntimeException ("Could find unit of measure Pieces in data.sql");

        UnitOfMeasure uomPieces = uomPiecesOpt.get();

                    //load Categories from data.sql

        Optional<Category> categoryAmericanOpt = categoryRepository.findByDescription ("American");

        if (!categoryAmericanOpt.isPresent())
            throw new RuntimeException ("Could find category American in data.sql ");

        Category categoryAmerican = categoryAmericanOpt.get();

        Optional<Category> categoryMexicanOpt = categoryRepository.findByDescription ("Mexican");

        if (!categoryMexicanOpt.isPresent())
            throw new RuntimeException ("Could find category Mexican in data.sql ");

        Category categoryMexican = categoryMexicanOpt.get();

                    //Guacomole Recipe:

        Ingredient ingredientAvocado = new Ingredient("avocado", new BigDecimal(2.0), uomPieces);
        Ingredient ingredientLemon = new Ingredient("lemon", new BigDecimal(1.0), uomPieces);

        Recipe recipeGuacomole = new Recipe();
        Set<Ingredient> ingredientsGuacomole = new HashSet<>();

        ingredientsGuacomole.add(ingredientAvocado);
        ingredientsGuacomole.add(ingredientLemon);

        recipeGuacomole.setIngredients(ingredientsGuacomole);
        recipeGuacomole.setDescription("Guacomole");

        ingredientAvocado.setRecipe(recipeGuacomole);
        ingredientLemon.setRecipe(recipeGuacomole);

        Notes notesGuacomole = new Notes();
        notesGuacomole.setRecipeNotes("Some notes");
        //notesGuacomole.setRecipe(recipeGuacomole);
        recipeGuacomole.setNotes(notesGuacomole);

     //   ingredientRepository.saveAll(ingredientsGuacomole);

        //Chicken Recipe:
        Ingredient ingredientChicken = new Ingredient("chicken", new BigDecimal(1.0), uomPieces);
        Ingredient ingredientLemon2 = new Ingredient("chicken2", new BigDecimal(1.0), uomPieces);
                //new Ingredient("lemon", new BigDecimal(2.0), uomPieces);

        Recipe recipeChicken = new Recipe();

        recipeChicken.addIngredient(ingredientChicken);
        recipeChicken.addIngredient(ingredientLemon2);
        recipeChicken.setDescription("Chicken");



        Notes notesChicken = new Notes();
        notesChicken.setRecipeNotes("Some notes 2");
        //notesChicken.setRecipe(recipeChicken);
        recipeChicken.setNotes(notesChicken);

        // adding categories


        //categoryAmerican.getRecipes().add (recipeGuacomole);
        //categoryAmerican.getRecipes().add (recipeChicken);

        //categoryMexican.getRecipes().add (recipeGuacomole);

        recipeGuacomole.getCategories().add(categoryAmerican);
        recipeGuacomole.getCategories().add(categoryMexican);

        recipeChicken.getCategories().add(categoryAmerican);



        recipeRepository.save(recipeGuacomole);
        recipeRepository.save(recipeChicken);


        System.out.println( "log.isDebugEnabled()" + log.isDebugEnabled());
        log.info("MY INFO EXAMPLE");
        log.debug("MY DEBUG EXAMPLE - bootstrapping data ended");


/*
//example of builder in category
        Category category = new Category().builder().description("Russian").id(111l).build();
        log.debug("category.getDescription()=" + category.getDescription() + " ; id = " + category.getId());

 */
    }
}
