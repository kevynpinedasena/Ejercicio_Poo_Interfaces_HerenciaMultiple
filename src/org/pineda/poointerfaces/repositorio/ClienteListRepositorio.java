package org.pineda.poointerfaces.repositorio;

import java.util.ArrayList;
import java.util.List;

import org.pineda.poointerfaces.modelo.Cliente;

public class ClienteListRepositorio implements IOrdenablePaginableCrudRepositorio{

	private List<Cliente> dataSource;
	
	public ClienteListRepositorio() {
		this.dataSource = new ArrayList<>();
	}

	@Override
	public List<Cliente> listar() {
		return dataSource;
	}

	@Override
	public Cliente poId(Integer id) {
		
		Cliente cliente = null;
		
		for (Cliente cli : dataSource) {
			if (cli.getId() != null && cli.getId().equals(id)) {
				cliente = cli;
				break;
			}
		}
		
		return cliente;
	}

	@Override
	public void guardarCliente(Cliente cliente) {
		
		this.dataSource.add(cliente);
	}

	@Override
	public void editarCliente(Cliente cliente) {
		
		Cliente cli = this.poId(cliente.getId());
		
		cli.setNombre(cliente.getNombre());
		cli.setApellido(cliente.getApellido());
	}

	@Override
	public void eliminarCliente(Integer id) {
		
		Cliente cli = this.poId(id);
		
		this.dataSource.remove(cli);
	}

	@Override
	public List<Cliente> listar(String campo, Direccion dir) {
		
		List<Cliente> listaOrdenada = new ArrayList<>(this.dataSource);
		
		listaOrdenada.sort( (Cliente a, Cliente b) -> {
				int resultado = 0;
				
				if (dir == Direccion.ASC) {
					resultado = ordenar(campo, a, b);
				}
				else if (dir == Direccion.DESC){
					resultado = ordenar(campo, b, a);
				}
				
				return resultado;
			});
		
		return listaOrdenada;
	}

	@Override
	public List<Cliente> listar(int desde, int hasta) {
		return dataSource.subList(desde, hasta);
	}

	@Override
	public int total() {
		return this.dataSource.size();
	}

	private int ordenar(String campo, Cliente a, Cliente b) {
		int resultado = 0;
		
		switch (campo) {
			case "id":
				resultado = a.getId().compareTo(b.getId());
				break;
				
			case "nombre":
				resultado = a.getNombre().compareTo(b.getNombre());
				break;
				
			case "apellido":
				resultado = a.getApellido().compareTo(b.getApellido());
				break;

			default:
				break;
		}
		
		return resultado;
	}

}
