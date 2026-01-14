package space.gmarqueszx.plano_de_saude.domain.exception;

public class UsuarioInUseException extends EntityInUseException {
    public UsuarioInUseException(String message) {
        super(message);
    }

    public UsuarioInUseException(Long usuarioId) {
        this("O usuário de id: " + usuarioId + " não pode ser deletado, pois está em uso.");
    }
}
