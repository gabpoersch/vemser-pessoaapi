package pessoaapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Endereco {
    private Integer idEndereco;
    private Integer idPessoa;
    private String numero;
    private String cep;
}
