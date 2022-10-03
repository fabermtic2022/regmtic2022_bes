package tutorial.misionTIC.ModuloSeguridad.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tutorial.misionTIC.ModuloSeguridad.Modelos.Permiso;
import tutorial.misionTIC.ModuloSeguridad.Modelos.PermisosRol;
import tutorial.misionTIC.ModuloSeguridad.Modelos.Rol;
import tutorial.misionTIC.ModuloSeguridad.Repositorios.RepositorioPermiso;
import tutorial.misionTIC.ModuloSeguridad.Repositorios.RepositorioPermisosRoles;
import tutorial.misionTIC.ModuloSeguridad.Repositorios.RepositorioRol;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/permisos-roles")
public class ControladorPermisosRoles {
    @Autowired
    private RepositorioPermisosRoles miRepositorioPermisosRoles;

    @Autowired
    private RepositorioPermiso miRepositorioPermiso;

    @Autowired
    private RepositorioRol miRepositorioRol;

    @GetMapping("")
    public List<PermisosRol> index(){
        return miRepositorioPermisosRoles.findAll();
    }
    /**
     * Asignación de rol y permiso
     * @param id_rol
     * @param id_permiso
     * @return
     */
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRol create(@PathVariable String id_rol, @PathVariable String id_permiso){
        PermisosRol  nuevo = new PermisosRol();
        Rol elRol = miRepositorioRol.findById(id_rol).orElse(null);
        Permiso elPermiso = miRepositorioPermiso.findById(id_permiso).orElse(null);
        if(elRol!=null && elPermiso!= null){
            nuevo.setPermiso(elPermiso);
            nuevo.setRol(elRol);
            return miRepositorioPermisosRoles.save(nuevo);
        }else{
            return null;
        }
    }

    @GetMapping("{id}")
    public PermisosRol show(@PathVariable String id){
        PermisosRol permisosRolActual = miRepositorioPermisosRoles
                .findById(id)
                .orElse(null);
        return permisosRolActual;
    }

    /**
     * Modificaciones ROL y PERMISO
     * @param id
     * @param id_rol
     * @param id_permiso
     * @return
     */

    @PutMapping("{id}/rol/{id_rol}/permiso/{id_permiso}")
    public PermisosRol update(@PathVariable String id,
                              @PathVariable String id_rol,
                              @PathVariable String id_permiso){
        PermisosRol permisosRolesActuales = miRepositorioPermisosRoles
                .findById(id)
                .orElse(null);
        Rol elRol = miRepositorioRol.findById(id_rol).get();
        Permiso elPermiso = miRepositorioPermiso.findById(id_permiso).get();
        if(permisosRolesActuales!=null && elPermiso != null && elRol != null){
            permisosRolesActuales.setPermiso(elPermiso);
            permisosRolesActuales.setRol(elRol);
            return miRepositorioPermisosRoles.save(permisosRolesActuales);
        }else {
            return null;
        }
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable String id){
        PermisosRol permisosRolActual = miRepositorioPermisosRoles
                .findById(id)
                .orElse(null);
        if(permisosRolActual!=null) {
            miRepositorioPermisosRoles.delete(permisosRolActual);
        }
    }

    @GetMapping("validar-permiso/rol/{id_rol}")
    public PermisosRol getPermiso(@PathVariable String id_rol,
                                  @RequestBody Permiso infoPermiso) {
        Permiso elPermiso = miRepositorioPermiso
                .getPermiso(infoPermiso.getUrl(),
                        infoPermiso.getMetodo()
                );
        Rol elRol = miRepositorioRol.findById(id_rol).get();
        if (elPermiso!=null && elRol!= null){
            return miRepositorioPermisosRoles.getPermisoRol(elRol.get_id(),
                    elPermiso.get_id());
        }else{
            return null;
        }
    }
}
