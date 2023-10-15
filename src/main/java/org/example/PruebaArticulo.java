/*
 * 
Ejercicio 13
Modifica el programa Gestisimal realizado anteriormente añadiendo las siguientes mejoras:
• Utilizaunalistaenlugardeunarrayparaelalmacenamientodelosdatos.
• Comprueba la existencia del código en el alta, la baja y la modificación
de artículos para evitar errores.
• Cambia la opción “Salida de stock” por “Venta”. Esta nueva opción
permitirá hacer una venta de varios artículos y emitir la factura corres- pondiente. 
Se debe preguntar por los códigos y las cantidades de cada artículo que se quiere comprar. Aplica un 21% de IVA.
 */
package org.example;

import java.util.Scanner;

public class PruebaArticulo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Almacen almacen = new Almacen();
		almacen.addArticulos(
				new Articulo("1","Boligrafo",1,2,20),
				new Articulo("2","Lapiz",1,2,20)
		);

		Almacen carrito = new Almacen();
		
		Scanner entrada = new Scanner(System.in);
		int opcion = 0;
		int i;
		int code = 0;
		double precioDeCompra = 0;
		double precioDeVenta = 0;
		int uni = 0;
		int unidadesEnFactura = 0;
		String codigo = "";
		String descripcion = "";
		String precioCompra = "";
		String precioVenta = "";
		String unidades = "";
		double sumaPrecio =0;
		double sumaTotal=0;
		double iva = 0.21;
		double ivaArticulo=0;
		double ivaSuma =0;
		
		do {
			System.out.println("1. Listado");
			System.out.println("2. Alta");
			System.out.println("3. Baja");
			System.out.println("4. Modificar");
			System.out.println("5. Venta");
			System.out.println("6. Salir");
			System.out.println("Elige una opcion");
			opcion = entrada.nextInt();
			entrada.nextLine();
			
			switch(opcion) {
				case 1:
					System.out.println(almacen.listado());
				break;
				case 2:
					System.out.println("Introduce los datos del articulo");
					System.out.print("Introduce el codigo: ");
					codigo = entrada.nextLine();
					if (almacen.existeArticuloConCodigo(codigo)) {
			            System.out.print("Ese código ya existe.\nIntroduzca otro código: ");
			        }else {
			        	System.out.print("Descripcion: ");
			        	descripcion = entrada.nextLine();
			        	
			        	System.out.print("Precio de compra: ");
			        	precioDeCompra = Double.parseDouble(entrada.nextLine());
			        	
			        	System.out.print("Precio de venta: ");
			        	precioDeVenta = Double.parseDouble(entrada.nextLine());
			        	
			        	System.out.print("Stock: ");
			        	uni = Integer.parseInt(entrada.nextLine());

						almacen.addArticulo(new Articulo(codigo, descripcion, precioDeCompra, precioDeVenta, uni));
			        }
				break;
				case 3:
					System.out.println("Introduce el codigo que quieres borrar");
					codigo = entrada.nextLine();
					boolean seHaBorrado = almacen.bajaArticulo(codigo);

					if(!seHaBorrado){
						System.out.println("El no codigo existe, introduce otro");
					}else {
						System.out.println("almacen.Articulo borrado");
					}
				break;
				case 4:
					System.out.println("Introduce el codigo que deseas modificar");

					codigo = entrada.nextLine();

					Articulo articulo = almacen.findArticuloByCodigo(codigo);
					
					if(articulo == null) {
						System.out.println("No existe este codigo");
					}
					else {
						System.out.println("Introduzca los nuevos datos del artículo o INTRO para dejarlos igual.");
						System.out.println("Código: " + codigo);
			            System.out.println("Descripción: " + articulo.getDescripcion());
			            System.out.print("Nueva descripción: ");
			            descripcion = entrada.nextLine();
			            if (!descripcion.equals("")) {
			              articulo.setDescripcion(descripcion);
			            }
			            
			            System.out.println("Precio de compra: " + articulo.getPrecioCompra());
			            System.out.print("Nuevo precio de compra: ");
			            precioCompra = entrada.nextLine();
			            if (!precioCompra.equals("")) {
			              articulo.setPrecioCompra(Double.parseDouble(precioCompra));
			            }
			            
			            System.out.println("Precio de venta: " + articulo.getPrecioVenta());
			            System.out.print("Nuevo precio de venta: ");
			            precioVenta = entrada.nextLine();
			            if (!precioVenta.equals("")) {
			              articulo.setPrecioVenta(Double.parseDouble(precioVenta));
			            }
			            
			            System.out.println("Stock: " + articulo.getUnidades());
			            System.out.print("Nuevo stock: ");
			            unidades = entrada.nextLine();
			            if (!entrada.equals("")) {
			              articulo.setUnidades(Integer.parseInt(unidades));
			            }
					}
				break;
				case 5:
					do {
						System.out.println("1. Añadir al carrito");
						System.out.println("2. Sacar factura");
						System.out.println("3. Salir");
						System.out.println("Elige una opcion");
						opcion = entrada.nextInt();
						entrada.nextLine();
						switch(opcion) {
							case 1:
								System.out.println("Elige el codigo del articulo");
								codigo = entrada.nextLine();
								Articulo articulo1 = carrito.findArticuloByCodigo(codigo);
								if(articulo1 == null){
									System.out.println("No hay articulo con ese codigo");
								}else {
									System.out.println("Elige las unidades");
									uni = entrada.nextInt();
									if((articulo1.getUnidades()) < uni ) {
										System.out.println("No tenemos tantas unidades, el stock son " +
												articulo1.getUnidades() + " unidades");
									}else {
										articulo1.setUnidades(articulo1.getUnidades() - uni);
										System.out.println("Se ha añadido el carrito:");
										System.err.println("almacen.Articulo: " + articulo1.getDescripcion());
										System.out.println("Unidades: "+ uni);
										System.out.println("Precio por unidad : " + articulo1.getPrecioVenta());
									}
								}
							break;
							case 2:
								System.out.println("Factura completa");
								for(Articulo art : carrito.getArticulos()) {
									sumaPrecio = art.getPrecioVenta() * uni;
									ivaArticulo = sumaPrecio * iva;
									sumaTotal += sumaPrecio;
									System.out.println("Codigo: " + art.getCodigo());
									System.out.println("Descripcion: " + art.getDescripcion());
									System.out.println("Precio : " + art.getPrecioVenta());
									System.out.println("Unidades: " + uni);
									System.out.println("Precio total : " + sumaTotal);
									System.out.println("Iva :" + ivaArticulo);
								}
						}
						double totalFinal = sumaTotal + ivaArticulo;
						System.out.println("Total a pargar:" + totalFinal);
						System.out.println();
					} while(opcion !=3);
			}
		}while(opcion != 6);
	}

}
