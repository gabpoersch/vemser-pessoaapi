package pessoaapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PessoaDTO extends PessoaCreateDTO{
    private Integer idPessoa;
}