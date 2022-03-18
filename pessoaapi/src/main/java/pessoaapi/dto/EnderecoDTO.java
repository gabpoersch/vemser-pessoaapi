package pessoaapi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EnderecoDTO extends EnderecoCreateDTO{
    private Integer idEndereco;
}