package com.ejemplo.mvc.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.ejemplo.mvc.db.ConnectionDB;

public class PersonaDBRepository {

	public ArrayList<Persona> findAll() {
		ArrayList<Persona> lista = new ArrayList<Persona>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = ConnectionDB.getConnection();
			ps = cn.prepareStatement("SELECT id, nombre, apellido, edad " 
			+ "FROM personas");
			rs = ps.executeQuery();		
			
			while (rs.next()) {
				lista.add(mapear(rs));
			}
			
		} catch (SQLException error) {
			System.out.println("Error: " + error.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException error) {
				System.out.println("Error: " + error.getMessage());
			}
		}
		return lista;
	}
	
	public Persona findById(Long id) {
		Persona persona = null;
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = ConnectionDB.getConnection();
			ps = cn.prepareStatement("SELECT id, nombre, apellido, edad " 
			+ "FROM personas WHERE id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();		
			
			if (rs.next()) {
				persona = mapear(rs);
			} 
			
		} catch (SQLException error) {
			System.out.println("Error: " + error.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException error) {
				System.out.println("Error: " + error.getMessage());
			}
		}
		return persona;
	}
	
	public Persona save(Persona persona) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = ConnectionDB.getConnection();
			ps = cn.prepareStatement("INSERT INTO personas (nombre, apellido, edad) "
					+ "VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, persona.getNombre());
			ps.setString(2, persona.getApellido());
			ps.setInt(3, persona.getEdad());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				persona.setId(rs.getLong(1));
			} 
			
		} catch (SQLException error) {
			System.out.println("Error: " + error.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException error) {
				System.out.println("Error: " + error.getMessage());
			}
		}
		return persona;
	}
	
	public Persona update(Persona persona) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = ConnectionDB.getConnection();
			ps = cn.prepareStatement("UPDATE personas SET nombre = ?, "
					+ "apellido = ?, edad = ? WHERE id = ?");
			ps.setString(1, persona.getNombre());
			ps.setString(2, persona.getApellido());
			ps.setInt(3, persona.getEdad());
			ps.setLong(4, persona.getId());
			
			if (ps.executeUpdate() == 0) {
				persona = null;
			}
			
		} catch (SQLException error) {
			System.out.println("Error: " + error.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException error) {
				System.out.println("Error: " + error.getMessage());
			}
		}
		return persona;
	}
	
	public boolean delete(Long id) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = ConnectionDB.getConnection();
			ps = cn.prepareStatement("DELETE FROM personas WHERE id = ?");
			ps.setLong(1, id);
			
			if (ps.executeUpdate() > 0) {
				return true;
			}
			
		} catch (SQLException error) {
			System.out.println("Error: " + error.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException error) {
				System.out.println("Error: " + error.getMessage());
			}
		}
		return false;
	}
	
	// Agregamos un método extra:
	
	public boolean existsById(Long id) {
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			cn = ConnectionDB.getConnection();
			ps = cn.prepareStatement("SELECT * FROM personas WHERE id = ?");
			ps.setLong(1, id);
			rs = ps.executeQuery();		
			
			if (rs.next()) {
				return true;
			} 
			
		} catch (SQLException error) {
			System.out.println("Error: " + error.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (cn != null) {
					cn.close();
				}
			} catch (SQLException error) {
				System.out.println("Error: " + error.getMessage());
			}
		}
		return false;
	}
	
	private Persona mapear(ResultSet rs) throws SQLException {
		Persona persona = new Persona();
		persona.setId(rs.getLong("id"));
		persona.setNombre(rs.getString("nombre"));
		persona.setApellido(rs.getString("apellido"));
		persona.setEdad(rs.getInt("edad"));
		return persona;
	}
	
}
