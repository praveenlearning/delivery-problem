import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeliveryCostEstimator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input1 = scanner.nextLine().split(" ");
        int baseCost = Integer.parseInt(input1[0]);
        int packagesCount = Integer.parseInt(input1[1]);

        List<Package> packages = new ArrayList<>();

        for (int i = 0; i < packagesCount; i++) {
            String input = scanner.nextLine();
            Package pkg = Package.parsePackage(input);
            packages.add(pkg);
        }

        PackageDispatcher dispatcher = new PackageDispatcher(baseCost);
        packages.forEach(pkg -> System.out.println(dispatcher.detail(pkg)));
    }
}
