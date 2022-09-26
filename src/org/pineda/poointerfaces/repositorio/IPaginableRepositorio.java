package org.pineda.poointerfaces.repositorio;

import java.util.List;

import org.pineda.poointerfaces.modelo.Cliente;

public interface IPaginableRepositorio {

	List<Cliente> listar(int desde, int hasta);
}
