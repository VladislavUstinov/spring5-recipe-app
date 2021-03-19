package guru.springframework.repositories;

import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Test
    public void findByDescription() throws Exception {

        Optional<UnitOfMeasure> resOpt = unitOfMeasureRepository.findByDescription("Pieces");

        assertEquals(resOpt.get().getDescription(), "Pieces");
    }

    @Test
    @DirtiesContext
    public void changeUnitOfMeasureRepository (){
        unitOfMeasureRepository.save(new UnitOfMeasure("SomeMeasure"));

        Optional<UnitOfMeasure> resOpt = unitOfMeasureRepository.findByDescription("SomeMeasure");

        assertEquals(resOpt.get().getDescription(), "SomeMeasure123");
    }

    @Test
    public void tryingToGetChangedContentFromRepository (){
        Optional<UnitOfMeasure> resOpt = unitOfMeasureRepository.findByDescription("SomeMeasure");

        //the test is expected to be isolated from DirtiesContext methods
        assertEquals(resOpt.isPresent(), false);
    }
}