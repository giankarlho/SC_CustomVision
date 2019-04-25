package controller;

import dao.RestDao;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import lombok.Data;

@Data
@Named(value = "restControl")
@SessionScoped
public class RestController implements Serializable {

    private String UrlImagen;
    private String UrlImagenAnalizada;
    private String Resultado;

    public void consultar() throws Exception {
        RestDao dao;
        try {
            dao = new RestDao();
            setUrlImagenAnalizada(getUrlImagen());
            Resultado = dao.consultar(getUrlImagen());
            setUrlImagen(null);
        } catch (IOException e) {
            throw e;
        }
    }

}
