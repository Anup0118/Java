import java.util.Scanner;

// Base class
class Employee {
    private int id;
    private String name;
    protected double salary; // protected to allow subclasses to set

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public double calculateSalary() {
        return salary;
    }

    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }
}

// Full-time employee subclass
class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(int id, String name, double monthlySalary) {
        super(id, name);
        this.monthlySalary = monthlySalary;
        this.salary = calculateSalary(); // use overridden method
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

// Part-time employee subclass
class PartTimeEmployee extends Employee {
    private double hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int id, String name, double hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.salary = calculateSalary();
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

// Main class
public class PayrollSystem {
    public static void main(String[] args) {
        FullTimeEmployee fte = new FullTimeEmployee(1, "Alice", 5000);
        PartTimeEmployee pte = new PartTimeEmployee(2, "Bob", 80, 20);

        System.out.println("Full-Time Employee:");
        fte.display();

        System.out.println("Part-Time Employee:");
        pte.display();
    }
}
