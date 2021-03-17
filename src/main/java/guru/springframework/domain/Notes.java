package guru.springframework.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by jt on 6/13/17.
 */
@Getter
@Setter
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //не надо ли mappedBy?
    @OneToOne
    private Recipe recipe;

    @Lob
    private String recipeNotes;


}
