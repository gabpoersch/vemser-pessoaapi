package pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DadosPessoaisDTO {
    @ApiModelProperty(example = "Gabriel")
    private String nome;
    @ApiModelProperty(example = "84156146547")
    private String cpf;
    @ApiModelProperty(example = "57842022687")
    private String rg;
    @ApiModelProperty(example = "6241687")
    private String cnh;
    @ApiModelProperty(example = "Tatiana")
    private String nomeMae;
    @ApiModelProperty(example = "Herbert")
    private String nomePai;
    @ApiModelProperty(example = "afnafhwaw")
    private String tituloEleitor;
    @ApiModelProperty(example = "F")
    private Sexo sexo;
}
