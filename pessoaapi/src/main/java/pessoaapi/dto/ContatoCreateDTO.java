package pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class ContatoCreateDTO {
    private Integer idPessoa;
    @ApiModelProperty(example = "999999991")
    @NotEmpty
    @Size(min = 5, max = 13)
    private String numero;
}