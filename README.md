# Sorting and Selection Algorithms Benchmark

This project implements and analyzes MergeSort, QuickSort, and QuickSelect algorithms. It evaluates their performance across different array sizes and input types, tracking execution time, number of comparisons, and maximum recursion depth to empirically verify their theoretical asymptotic bounds.

## How to Build the Project
**Using IntelliJ IDEA:**
1. Open IntelliJ IDEA and select **File -> Open...**
2. Select the root folder of this project.
3. From the top menu, click **Build -> Build Project** (or press `Ctrl + F9`).

## How to Run the Benchmark
The benchmark generates testing datasets (random, sorted, and with duplicates) of sizes up to 1,000,000. It runs each algorithm 5 times to mitigate JVM warm-up anomalies and records the median result.
1. In the Project tool window, navigate to `src/main/java/org/example/Benchmark.java`.
2. Click the **Run** button (green play icon) located in the gutter next to the `main` method.
3. The script will execute and automatically generate a `results.csv` file in the root directory containing all the metrics.

## How to Run the Tests
The project includes automated JUnit 5 tests to verify the correctness of the sorting/selection logic, handle edge cases (empty arrays, single elements, duplicates), and check the maximum recursion depth constraints for QuickSort.
1. Navigate to `src/test/java/org/example/AlgorithmTest.java`.
2. Click the **Run** button (green play icon) next to the `AlgorithmTest` class declaration to run the entire suite.
3. The Run tool window will open at the bottom, displaying a green checkmark if all 4 test groups pass successfully.