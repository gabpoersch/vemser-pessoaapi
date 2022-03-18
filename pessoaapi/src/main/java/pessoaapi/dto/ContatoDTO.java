package pessoaapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ContatoDTO extends ContatoCreateDTO{
    private Integer idContato;
}
