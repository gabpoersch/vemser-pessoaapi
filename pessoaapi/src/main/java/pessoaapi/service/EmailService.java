package pessoaapi.service;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import pessoaapi.dto.EnderecoDTO;
import pessoaapi.dto.PessoaDTO;
import pessoaapi.repository.PessoaRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    @Autowired
    private PessoaRepository pessoaRepository;

    private final freemarker.template.Configuration fmConfiguration;

    private static final String MAIL_TO = "gabriel.poersch@dbccompany.com.br";

    @Value("${spring.mail.username}")
    private String from;
    private final JavaMailSender emailSender;


    public void sendEmail(String email, String assunto, String mensagem) {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(assunto);
            mimeMessageHelper.setText(mensagem, true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void sendEmailPessoa(PessoaDTO pessoa) {
        sendEmail(pessoa.getEmail(), "Olá, " + pessoa.getNome() + "!" , getCreatePersonTemplate(pessoa));
    }

    public void sendEmailUpdatePessoa(PessoaDTO pessoa) {
        sendEmail(pessoa.getEmail(), "Olá, " + pessoa.getNome() + "!" , getUpdatePersonTemplate(pessoa));
    }

    public void sendEmailDeletePessoa(PessoaDTO pessoa) {
        sendEmail(pessoa.getEmail(), "Olá, " + pessoa.getNome() + "!" , getDeletePersonTemplate(pessoa));
    }


    public void sendEmailEndereco(PessoaDTO pessoa, EnderecoDTO endereco) {
        sendEmail(pessoa.getEmail(), "Olá, " + pessoa.getNome() + "!" , getCreateAddressTemplate(pessoa, endereco));
    }

    public void sendEmailUpdateEndereco(PessoaDTO pessoa, EnderecoDTO endereco) {
        sendEmail(pessoa.getEmail(), "Olá, " + pessoa.getNome() + "!" , getUpdateAddressTemplate(pessoa, endereco));
    }


    public void sendEmailDeleteEndereco(PessoaDTO pessoa, EnderecoDTO endereco) {
        sendEmail(pessoa.getEmail(), "Olá, " + pessoa.getNome() + "!" , getDeleteAddressTemplate(pessoa, endereco));
    }

    public String getTemplate(String nome, Integer id, String templateHTML) throws IOException, TemplateException {
        Map<String, Object> dados = new HashMap<>();
        dados.put("nome", nome);
        dados.put("id", id);
        dados.put("from", from);
        fmConfiguration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        Template template = fmConfiguration.getTemplate(templateHTML);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
    }


    public String getCreatePersonTemplate(PessoaDTO pessoa) {
        try {
            return getTemplate(pessoa.getNome(), pessoa.getIdPessoa(), "email-template.ftl");

        } catch ( IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUpdatePersonTemplate(PessoaDTO pessoa) {
        try {
            return getTemplate(pessoa.getNome(), pessoa.getIdPessoa(), "email-template-update.ftl");

        } catch ( IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDeletePersonTemplate(PessoaDTO pessoa) {
        try {
            return getTemplate(pessoa.getNome(), pessoa.getIdPessoa(), "email-template-delete.ftl");

        } catch ( IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String getCreateAddressTemplate(PessoaDTO pessoa, EnderecoDTO endereco) {
        try {
            return getTemplate(pessoa.getNome(), endereco.getIdEndereco(), "email-template.ftl");

        } catch ( IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getUpdateAddressTemplate(PessoaDTO pessoa, EnderecoDTO endereco) {
        try {
            return getTemplate(pessoa.getNome(), endereco.getIdEndereco(), "email-template-update.ftl");

        } catch ( IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getDeleteAddressTemplate(PessoaDTO pessoa, EnderecoDTO endereco) {
        try {
            return getTemplate(pessoa.getNome(), endereco.getIdEndereco(), "email-template-delete.ftl");

        } catch ( IOException | TemplateException e) {
            e.printStackTrace();
        }
        return null;
    }
}