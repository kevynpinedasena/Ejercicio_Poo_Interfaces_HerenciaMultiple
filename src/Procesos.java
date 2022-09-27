import java.util.List;

import javax.swing.JOptionPane;

import org.pineda.poointerfaces.modelo.Cliente;
import org.pineda.poointerfaces.repositorio.ClienteListRepositorio;
import org.pineda.poointerfaces.repositorio.Direccion;
import org.pineda.poointerfaces.repositorio.IOrdenablePaginableCrudRepositorio;

public class Procesos {
	
	IOrdenablePaginableCrudRepositorio clienteRepo = new ClienteListRepositorio();
	
	public void inicio() {
		
		int opc = 0;
		do {
			
			String menu = "******* GESTION DE CLIENTES ******\n";
			menu += "1. Lista Total de Clientes\n";
			menu += "2. Buscar por Documento\n";
			menu += "3. Agregar Cliente\n";
			menu += "4. Actualizar Cliente\n";
			menu += "5. Eliminar Cliente\n";
			menu += "6. Total de clientes registrados\n";
			menu += "7. Ordenar Lista\n";
			menu += "8. Salir\n";
			
			opc = Integer.parseInt(JOptionPane.showInputDialog(menu));
			
			switch (opc) {
				case 1:
					listaTotal();
					break;
					
				case 2:
					System.out.println(buscarPorId());
					break;
					
				case 3:
					System.out.println(guardarCliente());
					break;
					
				case 4:
					System.out.println(actualizarCliente());
					break;
					
				case 5:
					System.out.println(eliminarCliente());
					break;
					
				case 6:
					System.out.println(totalClientes());
					break;
					
				case 7:
					ordenarDesendenteOAsendente();
					break;
					
				case 8:
					
					break;

				default:
					System.out.println("Ingrese un opcion valida");
					break;
			}
						
		} while (opc != 8);
		
		System.out.println("Termino");
	}
	
	public void listaTotal() {
		List<Cliente> listaClientes = clienteRepo.listar();
		if (listaClientes.size() <= 0) {
			System.out.println("NO hay personas registradas");
		}
		else {			
			listaClientes.forEach(cli -> System.out.println(cli));			
		}
	}
	
	public String buscarPorId() {
		
		int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del cliente que desea buscar"));
		Cliente buscarPorId = clienteRepo.poId(id);
		
		if (buscarPorId == null) {
			return "No se encontro el cliente con este id";
		}
		else {
			return ""+buscarPorId;			
		}
	}
	
	public String guardarCliente() {
		Cliente cliente = new Cliente();
		
		cliente.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del cliente"));
		cliente.setApellido(JOptionPane.showInputDialog("Ingrese el apellido"));
		
		clienteRepo.guardarCliente(cliente);
		
		return "Guardado con exito!";
	}
	
	public String actualizarCliente() {
		
		int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el documento del cliente que desea actualizar"));
		Cliente clienteId = clienteRepo.poId(id);
		
		if (clienteId != null) {
			clienteId.setNombre(JOptionPane.showInputDialog("Ingrese el nuevo nombre"));
			clienteId.setApellido(JOptionPane.showInputDialog("Ingrese el nuevo apellido"));
		
			clienteId.setId(clienteId.getId());
			clienteRepo.editarCliente(clienteId);
		}
		else {
			return "No se encontro el cliente con este id";
		}
		
		return "Actualizado con exito!";
	}
	
	public String eliminarCliente() {
		int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id"));
		Cliente buscarId = clienteRepo.poId(id);
		
		if (buscarId != null) {			
			clienteRepo.eliminarCliente(buscarId.getId());
		}
		else {
			return "No se encontro el cliente con este id";
		}
		
		return "Cliente eliminado con exito!";
	}
	
	public String totalClientes() {
		return "Total de Clienes: " + clienteRepo.total();
	}
	
	public void ordenarDesendenteOAsendente() {
		
		if (clienteRepo.listar().size() <= 0) {
			System.out.println("No hay clientes para ordenar");
		}
		else {
			ordenar();
		}
		
	}
	
	public void ordenar() {
		
		String menuOrden = "MENU ORGANIZAR LISTA\n";
		menuOrden += "1. Ordenar Asendente\n";
		menuOrden += "2. Ordenar Desendente\n";
		
		int opc = Integer.parseInt(JOptionPane.showInputDialog(menuOrden));
		
		switch (opc) {
			
			case 1:	
				String menu = "MENU ORDENAR\n";
				menu += "1. Por Documento\n";
				menu += "2. Por Nombre\n";
				menu += "3. Por Apellido\n";
				
				int opc1 = Integer.parseInt(JOptionPane.showInputDialog(menu));
				
				switch (opc1) {
					case 1:
						List<Cliente> clienteOrdenarId = clienteRepo.listar("id", Direccion.ASC);				
						clienteOrdenarId.forEach(cli -> System.out.println(cli));
						break;
					
					case 2:
						List<Cliente> clienteOrdenarNombre = clienteRepo.listar("nombre", Direccion.ASC);				
						clienteOrdenarNombre.forEach(cli -> System.out.println(cli));
						break;
						
					case 3:
						List<Cliente> clienteOrdenarApellido = clienteRepo.listar("apellido", Direccion.ASC);				
						clienteOrdenarApellido.forEach(cli -> System.out.println(cli));
						break;

					default:
						System.out.println("Ingrese una Opcion valida!");
						break;
				}
				
				break;
				
			case 2:
				
				String menu2 = "MENU ORDENAR\n";
				menu2 += "1. Por Documento\n";
				menu2 += "2. Por Nombre\n";
				menu2 += "3. Por Apellido\n";
				
				int opc2 = Integer.parseInt(JOptionPane.showInputDialog(menu2));
				
				switch (opc2) {
					
					case 1:
						List<Cliente> clienteOrdenarId = clienteRepo.listar("id", Direccion.DESC);				
						clienteOrdenarId.forEach(cli -> System.out.println(cli));
						break;
					
					case 2:
						List<Cliente> clienteOrdenarNombre = clienteRepo.listar("nombre", Direccion.DESC);				
						clienteOrdenarNombre.forEach(cli -> System.out.println(cli));
						break;
						
					case 3:
						List<Cliente> clienteOrdenarApellido = clienteRepo.listar("apellido", Direccion.DESC);				
						clienteOrdenarApellido.forEach(cli -> System.out.println(cli));
						break;

					default:
						System.out.println("Ingrese una Opcion valida!");
						break;
				}
				
				break;
				
			default:
				System.out.println("Ingrese una opcion valida");
				break;
		}
	}
}