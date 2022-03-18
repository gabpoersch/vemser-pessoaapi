package pessoaapi.config;

import feign.Response;
import feign.codec.ErrorDecoder;

public class SimpleErrorDecode implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        return null;
    }
}
