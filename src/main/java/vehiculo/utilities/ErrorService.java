package vehiculo.utilities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.ObjectError;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ErrorService<T> {
  
  private String mensaje;
  private int nivel;
  private String accionEjecutada;
  
  private String mensajeDebug;
  private T objectDebug;
  
  @JsonIgnore
  private ObjectMapper mapperJSON = new ObjectMapper();
  
  
  public static final int BDD = 0;
  public static final int SERVIDOR = 1;
  public static final int APP = 2;
  
  public ErrorService() {
  }
  
  public ErrorService(String mensaje, int nivel, String accionEjecutada, String mensajeDebug, T objectDebug) {
    this.mensaje = mensaje;
    this.nivel = nivel;
    this.accionEjecutada = accionEjecutada;
    this.mensajeDebug = mensajeDebug;
    this.objectDebug = objectDebug;
  }
  
  public ErrorService(String mensaje, int nivel, String accionEjecutada) {
    this.mensaje = mensaje;
    this.nivel = nivel;
    this.accionEjecutada = accionEjecutada;
  }
  
  
  public List<ErrorService<ObjectError>> generarErroresDeValidacion(List<ObjectError> errores, String accion)
  {
    List<ErrorService<ObjectError>> salida = new ArrayList<>();
    for (ObjectError error : errores) {
      ErrorService<ObjectError> errorService = new ErrorService<>(error.getDefaultMessage(), ErrorService.APP, accion, 
          this.codesToString(error.getCodes()), error);
      salida.add(errorService);
    }
    return salida;
  }
  
  private String codesToString(String[] codes)
  {
    String salida = "[ ";
    for (String code : codes) {
      salida += code + " ";
    }
    return salida + "]";
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public int getNivel() {
    return nivel;
  }

  public void setNivel(int nivel) {
    this.nivel = nivel;
  }

  public String getAccionEjecutada() {
    return accionEjecutada;
  }

  public void setAccionEjecutada(String accionEjecutada) {
    this.accionEjecutada = accionEjecutada;
  }

  public String getMensajeDebug() {
    return mensajeDebug;
  }

  public void setMensajeDebug(String mensajeDebug) {
    this.mensajeDebug = mensajeDebug;
  }

  public T getObjectDebug() {
    return objectDebug;
  }

  public void setObjectDebug(T objectDebug) {
    this.objectDebug = objectDebug;
  }

  public ObjectMapper getMapperJSON() {
    return mapperJSON;
  }

  public void setMapperJSON(ObjectMapper mapperJSON) {
    this.mapperJSON = mapperJSON;
  }
  
  

}
