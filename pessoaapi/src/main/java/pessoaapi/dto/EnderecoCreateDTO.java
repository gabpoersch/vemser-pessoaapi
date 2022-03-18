package pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class EnderecoCreateDTO {
    private Integer idPessoa;
    @ApiModelProperty(example = "11")
    @NotEmpty
    private String numero;
    @ApiModelProperty(example = "98741251")
    @NotEmpty
    @Size(max = 8)
    private String cep;
}