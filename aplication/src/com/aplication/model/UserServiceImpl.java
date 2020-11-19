package com.aplication.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.aplication.conexion.Conexion;
import java.sql.Connection;

public class UserServiceImpl implements UserService {
	// data model
	private List<CatUsuario> userList = new LinkedList<CatUsuario>();
	private Conexion conexion = new Conexion();

	public UserServiceImpl() {
		conexion.conectar();
		Connection con = conexion.conectar();
		ResultSet rs = null;
		// gestion de la conexion.
		try {
			PreparedStatement ps = con.prepareStatement("select * from usuarios");
			rs = ps.executeQuery();
			while (rs.next()) {
				CatUsuario usr = new CatUsuario(Integer.parseInt(rs.getString("CODE")), rs.getString("DOCUMENTO"),
						rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("CORREO"),
						rs.getString("DIRECCION"), rs.getString("TELEFONO"), rs.getString("PASSWORD"));
				userList.add(usr);
			}
			System.out.println("Datos cargados ");
		} catch (Exception ex) {
			System.out.println("Error al traer usuarios " + ex);
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				System.out.println("Error al cerrar coneccions" + ex);
			}
		}
	}

	@Override
	public List<CatUsuario> findAll() {
		// TODO Auto-generated method stub
		System.out.println("hay :" + userList.size());
		return userList;
	}

	@Override
	public List<CatUsuario> search(String keyword) {
		// TODO Auto-generated method stub
		List<CatUsuario> result = new LinkedList<CatUsuario>();
		if (keyword == null || "".equals(keyword)) {
			result = userList;
		} else {
			for (CatUsuario c : userList) {
				if (c.getDocumento().toLowerCase().contains(keyword.toLowerCase())
						|| c.getNombre().toLowerCase().contains(keyword.toLowerCase())
						|| c.getApellido().toLowerCase().contains(keyword.toLowerCase())
						|| c.getDireccion().toLowerCase().contains(keyword.toLowerCase())
						|| c.getCorreo().toLowerCase().contains(keyword.toLowerCase())
						|| c.getTelefono().toLowerCase().contains(keyword.toLowerCase())) {
					result.add(c);
				}
			}
		}
		return result;
	}

	@Override
	public void editUser(CatUsuario usuario) {
		// TODO Auto-generated method stub
		Connection con = conexion.conectar();
		int rs = 0;
		// gestion de la conexion.
		try {
			PreparedStatement ps = con.prepareStatement(
					"update usuarios set documento = ?, nombre = ?, apellido = ?, correo = ?, direccion = ?, telefono = ?, password = ? where code = ?;");
			ps.setString(1, usuario.getDocumento());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellido());
			ps.setString(4, usuario.getCorreo());
			ps.setString(5, usuario.getDireccion());
			ps.setString(6, usuario.getTelefono());
			ps.setString(7, usuario.getPassword());
			ps.setInt(8, usuario.getCodigo());

			rs = ps.executeUpdate();
			System.out.println("Datos actualizados. ");
		} catch (Exception ex) {
			System.out.println("Error al actualizar usuario " + ex);
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				System.out.println("Error al cerrar coneccions" + ex);
			}
		}

	}

	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		Connection con = conexion.conectar();
		// gestion de la conexion.
		try {
			PreparedStatement ps = con.prepareStatement("delete from usuarios where code = ?;");
			ps.setInt(1, id);
			ps.execute();
			System.out.println("Usuario Eliminados. ");
		} catch (Exception ex) {
			System.out.println("Error al eliminar usuario " + ex);
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				System.out.println("Error al cerrar coneccions" + ex);
			}
		}
	}

	@Override
	public void createUser(CatUsuario usuario) {
		// TODO Auto-generated method stub
		Connection con = conexion.conectar();
		int rs = 0;
		// gestion de la conexion.
		try {
			PreparedStatement ps = con.prepareStatement("insert into usuarios (documento, nombre, apellido, correo, direccion, telefono, password) values (?, ?, ?, ?, ?, ?, ?);");
			ps.setString(1, usuario.getDocumento());
			ps.setString(2, usuario.getNombre());
			ps.setString(3, usuario.getApellido());
			ps.setString(4, usuario.getCorreo());
			ps.setString(5, usuario.getDireccion());
			ps.setString(6, usuario.getTelefono());
			ps.setString(7, usuario.getPassword());
			rs = ps.executeUpdate();
			System.out.println("Usuario creadp. ");
		} catch (Exception ex) {
			System.out.println("Error al crear usuario " + ex);
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				System.out.println("Error al cerrar coneccions" + ex);
			}
		}
	}

	@Override
	public CatUsuario getUser(String email, String pass) {
		// TODO Auto-generated method stub
		Connection con = conexion.conectar();
		ResultSet rs = null;
		CatUsuario usr = null;
		// gestion de la conexion.
		try {
			PreparedStatement ps = con.prepareStatement("select * from usuarios where correo = ? and password = ?;");
			ps.setString(1, email);
			ps.setString(2, pass);
			rs = ps.executeQuery();
			usr = new CatUsuario(Integer.parseInt(rs.getString("CODE")), rs.getString("DOCUMENTO"),
					rs.getString("NOMBRE"), rs.getString("APELLIDO"), rs.getString("CORREO"),
					rs.getString("DIRECCION"), rs.getString("TELEFONO"), rs.getString("PASSWORD"));
			System.out.println("Usuario Encontrado. "+usr);
		} catch (Exception ex) {
			System.out.println("Error al buscar el usuario " + ex);
			return usr;
		} finally {
			try {
				con.close();
			} catch (Exception ex) {
				System.out.println("Error al cerrar coneccions" + ex);
				return usr;
			}
		}
		return usr;
	}

}
