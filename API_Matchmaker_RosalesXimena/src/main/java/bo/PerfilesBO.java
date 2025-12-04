/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bo;

import dao.PerfilesDAO;
import dtos.ProfileDTO;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ximena
 */
public class PerfilesBO {
    private final PerfilesDAO dao = new PerfilesDAO();
    
    public void agregar(ProfileDTO dto) throws Exception {
        dao.agregarPerfil(dto);
    }
    
    public ProfileDTO buscarAleatorio(String genero, String pais, int edad) throws Exception {
        List<ProfileDTO> periflesEncontrados = dao.buscar(genero, pais, edad);
        
        if (periflesEncontrados.isEmpty()) {
            return null;
        }
        
        Random random = new Random();
        return periflesEncontrados.get(random.nextInt(periflesEncontrados.size()));
    }
    
}
