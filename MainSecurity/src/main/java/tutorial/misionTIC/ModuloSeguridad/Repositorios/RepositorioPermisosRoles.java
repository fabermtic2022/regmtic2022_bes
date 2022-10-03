package tutorial.misionTIC.ModuloSeguridad.Repositorios;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tutorial.misionTIC.ModuloSeguridad.Modelos.PermisosRol;

public interface RepositorioPermisosRoles extends MongoRepository<PermisosRol, String> {
    @Query("{'rol.$id': ObjectId(?0), 'permiso.$id':ObjectId(?1)}")
    PermisosRol getPermisoRol(String id_rol, String id_permiso);
}
