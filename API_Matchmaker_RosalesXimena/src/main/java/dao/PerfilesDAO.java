/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dtos.ProfileDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ximena
 */
public class PerfilesDAO {
    
    public void agregarPerfil(ProfileDTO dto) throws SQLException {
        String sql = "INSERT INTO perfiles(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, urlFoto, correo, genero, pais, celular, direccion, edad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = Conexion.getConection();
                PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, dto.getNombre());
            ps.setString(2, dto.getApellidoPaterno());
            ps.setString(3, dto.getApellidoMaterno());
            ps.setString(4, dto.getFechaNacimiento());
            ps.setString(5, dto.getUrlFoto());
            ps.setString(6, dto.getCorreo());
            ps.setString(7, dto.getGenero());
            ps.setString(8, dto.getPais());
            ps.setString(9, dto.getCelular());
            ps.setString(10, dto.getDireccion());
            ps.setInt(11, dto.getEdad());
            
            ps.executeUpdate();
        }
    }
    
    public List<ProfileDTO> buscar(String genero, String pais, int edad) throws SQLException {
        List<ProfileDTO> perfiles = new ArrayList<>();
        
        StringBuilder sql = new StringBuilder("SELECT *, " +
            "(CASE WHEN genero=? THEN 1 ELSE 0 END + " +
            " CASE WHEN pais=? THEN 1 ELSE 0 END + " +
            " CASE WHEN edad=? THEN 1 ELSE 0 END) AS coincidencias " +
            "FROM perfiles");
        
        try (Connection con = Conexion.getConection();
                PreparedStatement ps = con.prepareStatement(sql.toString())) {
            
            ps.setString(1, genero);
            ps.setString(2, pais);
            ps.setInt(3, edad);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int coincide = rs.getInt("coincidencias");
                if (coincide >= 2) {
                    ProfileDTO perfil = new ProfileDTO(
                            rs.getString("nombre"),
                            rs.getString("apellidoPaterno"),
                            rs.getString("apellidoMaterno"),
                            rs.getString("fechaNacimiento"),
                            rs.getString("urlFoto"),
                            rs.getString("correo"), 
                            rs.getString("genero"),
                            rs.getString("pais"),
                            rs.getString("celular"),
                            rs.getString("direccion"),
                            rs.getInt("edad")
                    );

                    perfiles.add(perfil);
                    
                }
            }
        }
        
        return perfiles;
    }
    
}
