package Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class Endereco {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String endereco;
    private String cidade;
    private String estado;
    private String cep;


}
