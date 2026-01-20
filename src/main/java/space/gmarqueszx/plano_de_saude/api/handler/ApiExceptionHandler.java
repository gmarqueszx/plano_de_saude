package space.gmarqueszx.plano_de_saude.api.handler;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import space.gmarqueszx.plano_de_saude.domain.exception.EntityInUseException;
import space.gmarqueszx.plano_de_saude.domain.exception.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected @Nullable ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                             HttpHeaders headers,
                                                                             HttpStatusCode status,
                                                                             WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = String.format("O recurso '%s' que você tentou acessar é inexistente.",
                ex.getRequestURL());

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                                   HttpHeaders headers,
                                                                                   HttpStatusCode status,
                                                                                   WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = "Método HTTP não aceito nesse endpoint.";

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    protected @Nullable ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = "URL com tipo não inesperado.";

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }


    @Override
    public @Nullable ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = "O corpo da requisição está inválido ou mal formatado.";


        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @Override
    public @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatusCode status,
                                                                         WebRequest request) {
        ProblemType problemType = ProblemType.REQUEST_FALHO;
        String detail = "A validação do argumento falhou.";

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        Problem problem = createProblemBuilder(status, problemType, detail, errors).build();
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityNotFoundException ex,
                                                        WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
        String detail = ex.getMessage();
        ;

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityInUseException.class)
    public ResponseEntity<?> handleEntityInUseException(EntityInUseException ex,
                                                        WebRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
        String detail = ex.getMessage();

        Problem problem = createProblemBuilder(status, problemType, detail, null).build();
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }


    @Override
    protected @Nullable ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                                       @Nullable Object body,
                                                                       HttpHeaders headers,
                                                                       HttpStatusCode status,
                                                                       WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .title(HttpStatus.valueOf(status.value()).getReasonPhrase())
                    .status(status.value())
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .title((String) body)
                    .status(status.value())
                    .build();
        }
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode status,
                                                        ProblemType problemType, String detail,
                                                        Map<String, String> errors) {
        return Problem.builder()
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail)
                .errors(errors);
    }
}
