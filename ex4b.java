import java.util.*;

// Employee class
class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
    }
}

// Custom Exception for invalid salary
class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }
}

// Payroll System with functional undo
class PayrollSystem {

    private List<Employee> employees = new ArrayList<>();
    private Deque<Operation> history = new ArrayDeque<>();

    private enum OpType { ADD, REMOVE }

    private static class Operation {
        OpType type;
        Employee employee;

        Operation(OpType type, Employee employee) {
            this.type = type;
            this.employee = employee;
        }
    }

    // Add employee
    public void addEmployee(int id, String name, double salary) throws InvalidSalaryException {
        if (salary < 0) {
            throw new InvalidSalaryException("Salary cannot be negative!");
        }
        Employee emp = new Employee(id, name, salary);
        employees.add(emp);
        history.push(new Operation(OpType.ADD, emp));
        System.out.println("Employee added successfully!");
    }

    // Display all employees
    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }
        System.out.println("----- Employee Payroll -----");
        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }

    // Remove employee
    public void removeEmployee(int id) throws Exception {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            if (emp.id == id) {
                iterator.remove();
                history.push(new Operation(OpType.REMOVE, emp));
                System.out.println("Employee removed successfully!");
                return;
            }
        }
        throw new Exception("Employee with ID " + id + " not found!");
    }

    // Undo last operation (functional undo)
    public void undoOperation() {
        if (history.isEmpty()) {
            System.out.println("No operations to undo.");
            return;
        }
        Operation lastOp = history.pop();
        switch (lastOp.type) {
            case ADD:
                // Undo add = remove employee
                employees.removeIf(emp -> emp.id == lastOp.employee.id);
                System.out.println("Undo Add: Removed Employee " + lastOp.employee);
                break;

            case REMOVE:
                // Undo remove = add employee back
                employees.add(lastOp.employee);
                System.out.println("Undo Remove: Added Employee back " + lastOp.employee);
                break;
        }
    }
}

// Main Class
public class PayrollMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayrollSystem payroll = new PayrollSystem();

        while (true) {
            System.out.println("\n--- Payroll System Menu ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Remove Employee");
            System.out.println("4. Undo Last Operation");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Salary: ");
                        double salary = sc.nextDouble();
                        payroll.addEmployee(id, name, salary);
                        break;

                    case 2:
                        payroll.displayEmployees();
                        break;

                    case 3:
                        System.out.print("Enter Employee ID to remove: ");
                        int removeId = sc.nextInt();
                        payroll.removeEmployee(removeId);
                        break;

                    case 4:
                        payroll.undoOperation();
                        break;

                    case 5:
                        System.out.println("Exiting Payroll System...");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Please enter again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter correct data.");
                sc.nextLine(); // clear buffer
            } catch (InvalidSalaryException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }  // end of while loop
    }  // end of main method
}  // end of PayrollMain class

