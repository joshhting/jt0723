# jt0723 Programming Demo
This is a simple tool rental application.
* The application is a point-of-sale tool for a store, like Home Depot, that rents big tools.
* Customers rent a tool for a specified number of days.
* When a customer checks out a tool, a Rental Agreement is produced.
* The store charges a daily rental fee, whose amount is different for each tool type.
* Some tools are free of charge on weekends or holidays.
* Clerks may give customers a discount that is applied to the total daily charges to reduce the final
charge.

## Usage
You can interface with this application by calling `ToolRental.checkout(Tool tool, int checkoutDays, int discount, LocalDate checkoutDate)`.

## Development

This project was developed with Gradle 7.2 and JDK 17.0.7. To build the project and run tests: `./gradlew build`

Test report can be found here: `lib/build/reports/tests/test/index.html`. Rental agreement outputs can be found under the "Standard Output" section of the test report.
