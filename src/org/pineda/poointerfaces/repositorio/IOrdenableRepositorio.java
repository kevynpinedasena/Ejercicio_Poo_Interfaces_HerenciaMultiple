package org.pineda.poointerfaces.repositorio;

import java.util.List;

import org.pineda.poointerfaces.modelo.Cliente;

public interface IOrdenableRepositorio {
	
	List<Cliente> listar(String campo, Direccion dir);
	
}