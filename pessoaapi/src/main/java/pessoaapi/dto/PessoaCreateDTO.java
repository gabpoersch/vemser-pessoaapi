package pessoaapi.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class PessoaCreateDTO {

    @ApiModelProperty(example = "Gabriel")
    @NotBlank
    @Size(min = 2, max = 20, message = "Tamanho inválido")
    private String nome;
    @ApiModelProperty(example = "1996-09-23")
    @Past
    @NotNull
    private LocalDate dataNascimento;
    @ApiModelProperty(example = "8198458654")
    @NotNull
    private String cpf;
    @ApiModelProperty(example = "gab@mail.com")
    @Email
    private String email;
}