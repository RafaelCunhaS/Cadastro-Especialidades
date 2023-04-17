package app.trybe.specialityapp.service;

import app.trybe.specialityapp.commons.ApplicationError;
import app.trybe.specialityapp.model.Professional;
import app.trybe.specialityapp.repository.ProfessionalRepository;
import java.util.List;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe Service com as lógicas das regras de negócio.
 */
@Service
public class ProfessionalService {
  @Autowired
  private ProfessionalRepository repository;

  /**
   * Lista todos registros salvos.
   */
  public List<Professional> findAll() throws ApplicationError {
    List<Professional> professionals = repository.findAll();
    if (professionals.isEmpty()) {
      throw new ApplicationError(Status.NOT_FOUND, "Nenhum registro foi encontrado!");
    }
    return professionals;
  }

  /**
   * Adiciona um registro novo.
   */
  public void insert(Professional professional) throws ApplicationError {
    if (professional.getId() != null) {
      throw new ApplicationError(Status.BAD_REQUEST,
          "Não é permitido inserir novos registros com ID explícito");
    }
    repository.save(professional);
  }

  /**
   * Edita registro.
   */
  public void edit(Integer id, Professional professionalDto) throws ApplicationError {
    Professional professionalToEdit = repository.findById(id).orElse(null);
    if (professionalToEdit == null) {
      throw new ApplicationError(Status.NOT_FOUND,
          "Não é possível editar, o ID informado não existe");
    }
    professionalToEdit.setName(professionalDto.getName());
    professionalToEdit.setSpeciality(professionalDto.getSpeciality());
    repository.save(professionalToEdit);
  }

  /**
   * Deleta registro.
   */
  public void delete(Integer id) throws ApplicationError {
    Professional professional = repository.findById(id).orElse(null);
    if (professional == null) {
      throw new ApplicationError(Status.NOT_FOUND,
          "Não é possível deletar, o ID informado não existe");
    }
    repository.delete(professional);
  }
}
