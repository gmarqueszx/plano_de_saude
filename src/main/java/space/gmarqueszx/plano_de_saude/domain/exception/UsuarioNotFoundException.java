package space.gmarqueszx.plano_de_saude.domain.exception;

public class UsuarioNotFoundException extends EntityNotFoundException {
    public UsuarioNotFoundException(String message) {
        super(message);
    }

    public UsuarioNotFoundException(Long usuarioId) {
        this("Não foi encontrado nenhum usuário com id: " + usuarioId + " em nosa base de dados.");
    }
}
