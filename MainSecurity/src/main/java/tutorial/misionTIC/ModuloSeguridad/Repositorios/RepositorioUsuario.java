package tutorial.misionTIC.ModuloSeguridad.Repositorios;

import org.springframework.data.mongodb.repository.Query;
import tutorial.misionTIC.ModuloSeguridad.Modelos.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioUsuario extends MongoRepository<Usuario,String> {
    @Query("{'correo':?0}")
    public Usuario getUserByMail(String correo);
}

