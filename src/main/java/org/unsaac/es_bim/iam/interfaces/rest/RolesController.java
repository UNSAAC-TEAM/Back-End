package org.unsaac.es_bim.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.unsaac.es_bim.iam.domain.model.queries.role.GetAllRolesQuery;
import org.unsaac.es_bim.iam.domain.services.role.IRoleQueryService;
import org.unsaac.es_bim.iam.interfaces.Resources.RoleResource;
import org.unsaac.es_bim.iam.interfaces.transform.RoleResourceFromEntityAssembler;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Roles", description = "Role Management Endpoints")
public class RolesController {
    private final IRoleQueryService roleQueryServices;

    public RolesController(IRoleQueryService roleQueryServices) {
        this.roleQueryServices = roleQueryServices;
    }

    /**
     * Get all roles
     * @return List of roles
     */
    @GetMapping
    public ResponseEntity<List<RoleResource>> getAllRoles() {
        var getAllRolesQuery = new GetAllRolesQuery();
        var roles = roleQueryServices.handle(getAllRolesQuery);
        var roleResources = roles.stream().map(RoleResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(roleResources);
    }
}
