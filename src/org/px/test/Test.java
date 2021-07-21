package org.px.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.px.model.Product;
import org.px.model.Talla;

/*
 * Preguntas adicionales :
 *  - Que estructuras de datos has utilizado (listas, set.. )
 *  - Que complejidad crees que tiene el algoritmo de visibilidad , crees que podria mejorarse?
 */
public class Test {

	public static final String SEPARATOR = ",";

	public static void main(String[] args) {

		Set<Product> listaProducts = new HashSet<Product>();
		Set<Talla> listaTallas = new HashSet<Talla>();
		try {
			cargaProductos(listaProducts, listaTallas);
			List<String> listaSalida = checkVisibilidadProductos(listaTallas);
			List<Product> listaFinal = listaProducts.stream().filter(e -> listaSalida.contains(e.getId()))
					.collect(Collectors.toList());
			listaFinal.sort((Product p1, Product p2) -> p1.getSequence().compareTo(p2.getSequence()));

			int i = 1;
			// Mostramos los productos a la venta
			for (Product product : listaFinal) {
				System.out.print(product.getId());
				if (listaSalida.size() != i)
					System.out.print(",");

				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Realiza el chequeo para ver que productos pueden ser mostrados al publico
	 * 
	 * @param listaProductos
	 * @return
	 */
	public static List<String> checkVisibilidadProductos(Set<Talla> listaTallas) {
		List<String> listaSalida = new ArrayList<String>();

		int nesp = 0;
		int esp = 0;
		List<Talla> listaTallaOrdenada = listaTallas.stream()
				.sorted((Talla t1, Talla t2) -> t1.getId_producto().compareTo(t2.getId_producto()))
				.collect(Collectors.toList());
		String id_ant = "";
		boolean productoConTallaEsp = false;

		// Sera visible cuando tenga stock y no hace tallas especiales ese producto
		// Si ese producto hace tallas especiales, solo sera visible si tiene stock en
		// ambas tallas, o ambas tallas tienen commingsoon
		for (Talla t : listaTallaOrdenada) {

			if (!t.getId_producto().equals(id_ant)) {

				if (productoConTallaEsp && nesp > 0 && esp > 0) {
					listaSalida.add(id_ant);
				} else if (!productoConTallaEsp && nesp > 0) {
					listaSalida.add(id_ant);
				}
				productoConTallaEsp = false;
				nesp = 0;
				esp = 0;
			}

			if (t.isTalla_especial()) {
				productoConTallaEsp = true;
				// Es talla especial y es backsoon, se muestra
				if (t.getUnidades() > 0 || t.isBacksoon()) {
					esp++;
				}
			} else {
				// Es talla especial y es backsoon, se muestra
				if (t.getUnidades() > 0 || t.isBacksoon()) {
					nesp++;
				}
			}

			id_ant = t.getId_producto();
		}

		if (productoConTallaEsp && nesp > 0 && esp > 0) {
			listaSalida.add(id_ant);
		} else if (!productoConTallaEsp && nesp > 0) {
			listaSalida.add(id_ant);
		}

		return listaSalida;
	}

	/**
	 * Carga los datos de los ficheros
	 * 
	 * @param listaProductos
	 * @throws IOException
	 */
	public static void cargaProductos(Set<Product> listaProductos, Set<Talla> listaTallas) throws IOException {
		BufferedReader br = null;

		try {

			// Fichero 1 (campos id producto y sequence)
			cargaFicheroProductos(listaProductos);

			// Fichero 2 (campos id talla, id producto, backsoon, talla esecial)
			cargaFicheroTallas(listaTallas);

			// Fichero 3 (id talla, unidaes)
			cargaFicheroStock(listaTallas);

		} catch (Exception e) {
			System.out.println("Ha habido un error " + e);
		} finally {
			if (null != br) {
				br.close();
			}
		}
	}

	/**
	 * Carga el fichero con el stock de las tallas de los productos
	 * 
	 * @param listaProductos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void cargaFicheroStock(Set<Talla> listaTallas) throws FileNotFoundException, IOException {
		BufferedReader br;
		br = new BufferedReader(new FileReader("resources/unidades.csv"));
		String line = br.readLine();
		while (null != line) {
			String[] campos = line.split(SEPARATOR);

			for (Talla t : listaTallas) {
				if (t.getId_talla().equals(campos[0])) {
					t.setUnidades(t.getUnidades() + Integer.valueOf(campos[1]));
					// la talla estará solo una vez, una vez se encuentre se modifica, y no
					// tendremos que iterar mas.
					break;
				}

			}
			line = br.readLine();

		}

		br.close();
	}

	/**
	 * Carga el fichero con los datos de las tallas de los productos
	 * 
	 * @param listaProductos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void cargaFicheroTallas(Set<Talla> listaTallas) throws FileNotFoundException, IOException {
		BufferedReader br;
		br = new BufferedReader(new FileReader("resources/Tallas.csv"));
		String line = br.readLine();
		while (null != line) {
			String[] campos = line.split(SEPARATOR);
			// id talla, backsoon, especial
			Talla t = new Talla(campos[0], campos[1], Boolean.valueOf(campos[2]), Boolean.valueOf(campos[3]));
			listaTallas.add(t);

			line = br.readLine();
		}
		br.close();
	}

	/**
	 * Carga el fichero con los datos de los productos
	 * 
	 * @param listaProductos
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void cargaFicheroProductos(Set<Product> listaProductos) throws FileNotFoundException, IOException {
		BufferedReader br;
		br = new BufferedReader(new FileReader("resources/Productos.csv"));
		String line = br.readLine();
		while (null != line) {
			String[] campos = line.split(SEPARATOR);
			// Creamos el productos con los datos obtenidos
			Product p = new Product(campos[0], campos[1]);
			listaProductos.add(p);

			line = br.readLine();
		}
		br.close();
	}
}
