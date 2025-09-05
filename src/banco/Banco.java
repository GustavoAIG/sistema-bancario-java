package banco;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Banco {
    public static void main(String[] args) {
        List<Cliente> clientes = new ArrayList<>();

        // Clientes de prueba
        Cliente c1 = new Cliente("C1", "Ana", 25);
        c1.agregarCuenta(new CuentaBancaria("001", 1000));
        c1.agregarCuenta(new CuentaBancaria("002", 2000));

        Cliente c2 = new Cliente("C2", "Luis", 40);
        c2.agregarCuenta(new CuentaBancaria("003", 5000));

        Cliente c3 = new Cliente("C3", "Pedro", 19);
        c3.agregarCuenta(new CuentaBancaria("004", 300));

        clientes.add(c1);
        clientes.add(c2);
        clientes.add(c3);

        // 1. Saldo total de todas las cuentas
        double saldoTotal = clientes.stream()
                .flatMap(c -> c.getCuentas().stream())
                .mapToDouble(CuentaBancaria::getSaldo)
                .sum();
        System.out.println("Saldo total en el banco: " + saldoTotal);

        // 2. Cliente con mayor saldo total
        Cliente clienteTop = clientes.stream()
                .max(Comparator.comparingDouble(c ->
                        c.getCuentas().stream().mapToDouble(CuentaBancaria::getSaldo).sum()
                ))
                .orElse(null);
        System.out.println("Cliente con más dinero: " + clienteTop);

        // 3. Lista de clientes mayores de 30
        List<Cliente> mayores30 = clientes.stream()
                .filter(c -> c.getEdad() > 30)
                .toList();
        System.out.println("Clientes mayores de 30: " + mayores30);

        // 4. Ver si existe un cliente con saldo mayor a 4000
        boolean existeRico = clientes.stream()
                .flatMap(c -> c.getCuentas().stream())
                .anyMatch(c -> c.getSaldo() > 4000);
        System.out.println("¿Existe cliente con saldo > 4000? " + existeRico);
    }
}
