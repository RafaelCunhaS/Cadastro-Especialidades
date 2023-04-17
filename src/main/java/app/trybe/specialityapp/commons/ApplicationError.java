package app.trybe.specialityapp.commons;

import javax.ws.rs.core.Response.Status;

public class ApplicationError extends Throwable {
  private static final long serialVersionUID = 1L;
  private Status status;
  private String message;

  public ApplicationError(Status status, String message) {
    this.status = status;
    this.message = message;
  }

  public Status getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
