package app.trybe.specialityapp.controller;

import app.trybe.specialityapp.commons.ApplicationError;
import app.trybe.specialityapp.model.Professional;
import app.trybe.specialityapp.service.ProfessionalService;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Classe Controller que recebe e envia respostas ao cliente.
 */
@Controller
@Path("/professional")
@Produces("application/json")
@Consumes("application/json")
public class ProfessionalController {
  @Autowired
  private ProfessionalService service;

  /**
   * Lista todos registros salvos.
   */
  @GET
  @Path("/all")
  public Response findAll() {
    try {
      return Response.ok(service.findAll()).build();
    } catch (ApplicationError e) {
      return Response.status(e.getStatus()).entity(e).build();
    }
  }

  /**
   * Insere um novo registro.
   */
  @POST
  @Path("/add")
  public Response insert(@RequestBody Professional professional) {
    try {
      service.insert(professional);
      return Response.status(Response.Status.CREATED).entity("Inserido").build();
    } catch (ApplicationError e) {
      return Response.status(e.getStatus()).entity(e).build();
    }
  }

  /**
   * Atualiza dados do registro.
   */
  @PUT
  @Path("/edit/{id}")
  public Response edit(@PathParam("id") Integer id, @RequestBody Professional professional) {
    try {
      service.edit(id, professional);
      return Response.ok("ID [" + id + "] atualizado").build();
    } catch (ApplicationError e) {
      return Response.status(e.getStatus()).entity(e).build();
    }
  }

  /**
   * Deleta registro específico.
   */
  @DELETE
  @Path("/delete/{id}")
  public Response delete(@PathParam("id") Integer id) {
    try {
      service.delete(id);
      return Response.ok("ID [" + id + "] removido").build();
    } catch (ApplicationError e) {
      return Response.status(e.getStatus()).entity(e).build();
    }
  }
}
