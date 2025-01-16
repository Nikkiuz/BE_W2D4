import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import Classi_Concrete.Product;
import Classi_Concrete.Customer;
import Classi_Concrete.Order;

public class Main {
	public static void main(String[] args) {
//Creo i prodotti
		Product product1 = new Product(1L, "Passeggino", "Baby", 218.01);
		Product product2 = new Product(2L, "Tolkien Collezione Completa", "Books", 195.86);
		Product product3 = new Product(3L, "Ciuccio Supersonico", "Baby", 111.94);
		Product product4 = new Product(4L, "Maglia NBA Lakers", "Boys", 190.19);
		Product product5 = new Product(5L, "Pallone da calcio ufficiale UEFA", "Boys", 52.28);
		Product product6 = new Product(6L, "Scalda Latte", "Baby", 142.49);
		Product product7 = new Product(7L, "Schiuma da barba", "Boys", 11.94);
		Product product8 = new Product(8L, "Diario di una brutta persona", "Books", 32.94);
		Product product9 = new Product(9L, "Orietta Berti-storia di un'Artista", "Books", 137.45);
		Product product10 = new Product(10L, "Mazza da baseball", "Boys", 114.66);

		List<Product> productList = new ArrayList<>();
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);
		productList.add(product4);
		productList.add(product5);
		productList.add(product6);
		productList.add(product7);
		productList.add(product8);
		productList.add(product9);
		productList.add(product10);

		Customer customer1 = new Customer(1, "Alice", 1);
		Customer customer2 = new Customer(2, "Bob", 2);
		Customer customer3 = new Customer(3, "Charlie", 1);

		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer1);
		customerList.add(customer2);
		customerList.add(customer3);

		Order order1 = new Order(1, "Shipped", LocalDate.of(2021, 5, 10),
			LocalDate.of(2025, 1, 15),
			Arrays.asList(product1, product3), customer1);

		Order order2 = new Order(2, "Delivered", LocalDate.of(2021, 3, 5),
			LocalDate.of(2025, 1, 12),
			Arrays.asList(product2, product4, product5), customer2);

		Order order3 = new Order(3, "Processing", LocalDate.of(2022, 1, 18),
			null,
			Arrays.asList(product1, product2), customer3);

		Order order4 = new Order(4, "Shipped", LocalDate.of(2023, 2, 13),
			null,
			Arrays.asList(product1, product2), customer3);


		List<Order> orderByClientList = new ArrayList<>(Arrays.asList(order1, order2, order3, order4));
		Esercizio1(orderByClientList);
		Esercizio2(orderByClientList);

		Esercizio3(productList);


	}

	public static void Esercizio1(List<Order> orderByClientList) {
		HashMap<Customer, List<Order>> ClientMap = (HashMap<Customer, List<Order>>) orderByClientList.stream()
			.collect(Collectors.groupingBy(order -> order.getCustomer()));

		for (Customer cliente : ClientMap.keySet()) {
			System.out.println("cliente: " + cliente + "\n ordini: " + ClientMap.get(cliente) + "\n");
		}

	}

	public static void Esercizio2(List<Order> orderByClientList) {
		Map<Customer, Double> acquistiMap = orderByClientList.stream()
			.collect(Collectors.groupingBy(
				Order::getCustomer,
				Collectors.summingDouble(order -> order.getProducts().stream()
					.mapToDouble(Product::getPrice).sum())
			));

		acquistiMap.forEach((customer, total) ->
			System.out.println("Cliente: " + customer + ", Totale acquisti: " + total));
	}

	public static void Esercizio3(List<Product> productList) {
		System.out.println("\nIl prezzo più alto " + productList.stream().max(Comparator.comparingDouble(Product::getPrice)));
	}
}