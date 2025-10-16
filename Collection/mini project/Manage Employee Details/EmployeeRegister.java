import java.util.*;

public class EmployeeRegister {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employeeList = new ArrayList<>();

        System.out.println("Enter the Number of Employees");
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        for (int i = 1; i <= n; i++) {
            System.out.println("Enter Employee " + i + " Details:");

            System.out.print("Enter the Firstname: ");
            String firstName = sc.nextLine();

            System.out.print("Enter the Lastname: ");
            String lastName = sc.nextLine();

            System.out.print("Enter the Mobile: ");
            String mobile = sc.nextLine();

            System.out.print("Enter the Email: ");
            String email = sc.nextLine();

            System.out.print("Enter the Address: ");
            String address = sc.nextLine();

            employeeList.add(new Employee(firstName, lastName, mobile, email, address));
        }

        Collections.sort(employeeList);

        System.out.println("\nEmployee List:");
        System.out.format("%-15s %-15s %-15s %-30s %-15s\n",
                "FirstName", "SecondName", "MobileNumber", "Email", "Address");
        for (Employee e : employeeList) {
            System.out.println(e);
        }

        sc.close();
    }
}
