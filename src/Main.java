import java.util.List;

import org.pineda.poointerfaces.modelo.Cliente;
import org.pineda.poointerfaces.repositorio.ClienteListRepositorio;
import org.pineda.poointerfaces.repositorio.Direccion;
import org.pineda.poointerfaces.repositorio.ICrudRepositorio;
import org.pineda.poointerfaces.repositorio.IOrdenablePaginableCrudRepositorio;
import org.pineda.poointerfaces.repositorio.IOrdenableRepositorio;
import org.pineda.poointerfaces.repositorio.IPaginableRepositorio;

public class Main {

	public static void main(String[] args) {
		
		IOrdenablePaginableCrudRepositorio repo = new ClienteListRepositorio();
		
		repo.guardarCliente(new Cliente("Jano", "Perez"));
		repo.guardarCliente(new Cliente("Bea", "Gonzales"));
		repo.guardarCliente(new Cliente("Luci", "Martinez"));
		repo.guardarCliente(new Cliente("Andres", "Guzman"));

		List<Cliente> clientes = repo.listar();
		clientes.forEach(cli -> System.out.println(cli));
		
		System.out.println("\n!!!!!!!!! Paginable !!!!!!!!!!");
		List<Cliente> paginable = repo.listar(1, 4);
		paginable.forEach(cli -> System.out.println(cli));
		
		System.out.println("\n!!!!!!!!! Ordenar Asendente !!!!!!!!!!");
		List<Cliente> clienteOrdenarAsc = repo.listar("nombre", Direccion.ASC);
		clienteOrdenarAsc.forEach(cli -> System.out.println(cli));
		
		System.out.println("\n!!!!!!!!! Ordenar Desendente !!!!!!!!!!");
		List<Cliente> clienteOrdenarDesc = repo.listar("nombre", Direccion.DESC);
		clienteOrdenarDesc.forEach(cli -> System.out.println(cli));
		
		System.out.println("\n!!!!!!! Editar !!!!!!!");
		Cliente beaActualizar = new Cliente("Bea", "Mena");
		beaActualizar.setId(2);
		repo.editarCliente(beaActualizar);
		Cliente bea = repo.poId(2);
		System.out.println(bea);
		
		System.out.println("\n!!!!!!! Eliminar !!!!!!!");
		repo.eliminarCliente(2);
		repo.listar().forEach(System.out::println);
		
		System.out.println("\n!!!!!!! Total !!!!!!!");
		System.out.println("Total Registros: " + repo.total());
	}
}