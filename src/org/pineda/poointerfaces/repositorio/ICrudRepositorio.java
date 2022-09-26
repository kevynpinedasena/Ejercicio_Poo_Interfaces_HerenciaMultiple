package org.pineda.poointerfaces.repositorio;

import java.util.List;

import org.pineda.poointerfaces.modelo.Cliente;

public interface ICrudRepositorio {
	
	List<Cliente> listar();
	
	Cliente poId(Integer id);
	
	void guardarCliente(Cliente cliente);
	
	void editarCliente(Cliente cliente);
	
	void eliminarCliente(Integer id);
}
