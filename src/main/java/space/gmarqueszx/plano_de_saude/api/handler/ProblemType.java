package space.gmarqueszx.plano_de_saude.api.handler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    ENTIDADE_EM_USO("/entidade-em-uso","Entidade em uso");

    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://gmarqueszx.space" + path;
        this.title = title;
    }
}
