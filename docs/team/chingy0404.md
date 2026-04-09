# Project Portfolio: Ledger67

## Overview
Ledger67 is a command-line double-entry accounting system designed to help individuals and small businesses manage their financial transactions efficiently. Built on the principles of double-entry bookkeeping, Ledger67 ensures that every financial transaction is recorded with both debit and credit entries, maintaining the fundamental accounting equation: **Assets = Equity - Liabilities + (Income - Expenses)**.

My role focused on implementing comprehensive quality assurance systems, enhancing error recovery mechanisms, improving user experience through better documentation, and establishing robust logging and testing frameworks to ensure system reliability and maintainability.

### Summary of Contributions

*   **Code contributed**: [Link to my code on the tP Code Dashboard](https://nus-cs2113-ay2526-s2.github.io/tp-dashboard/?search=chingy0404&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2026-02-20T00%3A00%3A00&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&filteredFileName=)
*   **Enhancements implemented**:
    *   **Comprehensive Quality Assurance System**:
        *   **What it does**: Implemented a complete quality assurance pipeline including Checkstyle, PMD, SpotBugs, and JaCoCo code coverage analysis. Created multiple configuration levels (lenient, minimal, simple, strict) to support different development stages.
        *   **Justification**: Ensures consistent code quality across the team, catches potential bugs early, and maintains professional coding standards throughout the project lifecycle.
        *   **Highlights**: Added `qualityAssurance` and `comprehensiveBuild` Gradle tasks that automatically run all quality checks, making quality enforcement an integral part of the development workflow.

    *   **Enhanced Logging System with JSON Formatting**:
        *   **What it does**: Implemented a structured logging system that outputs logs in JSON format to `logs/ledger67.log`. Tracks all user actions with timestamps, parameters, and execution results.
        *   **Justification**: Provides detailed debugging information for developers while maintaining a clean user interface. JSON formatting allows for easy parsing and analysis of log data.
        *   **Highlights**: Created `LoggingConfig` class with custom `JsonFormatter` that automatically initializes at application startup and provides different log levels (INFO, WARNING, ERROR) for appropriate verbosity.

    *   **Advanced Error Recovery and Validation Framework**:
        *   **What it does**: Implemented `TransactionValidator` with multi-stage validation (date, description, currency, postings, accounting equation) and intelligent auto-correction suggestions for common input errors.
        *   **Justification**: Significantly improves user experience by providing helpful, actionable error messages instead of generic failures. Reduces user frustration and learning curve.
        *   **Highlights**: Created `AutoCorrector` class that suggests corrections for date format errors, currency code typos, and account name issues. Implemented `ValidationResult` with structured feedback including warnings and suggestions.

    *   **Performance Testing Suite**:
        *   **What it does**: Created comprehensive `PerformanceTest` class that tests system performance with large datasets (10,000+ transactions), including filtering performance, balance sheet generation, memory usage, and stress testing.
        *   **Justification**: Ensures the application remains performant with real-world data volumes and identifies potential scalability bottlenecks before they affect users.
        *   **Highlights**: Tests transaction filtering (7ms for 10,000 transactions), balance sheet generation (9ms for 1,000 transactions), and memory usage (972 bytes per transaction on average).

    *   **Architectural Improvements for Maintainability**:
        *   **What it does**: Refactored codebase with `CommandHandler` base class, `AddCommandHandler` specialization, `Config` centralized configuration, and `ValidationUtils` shared utilities.
        *   **Justification**: Improves code organization, reduces duplication, and makes the system more extensible for future enhancements.
        *   **Highlights**: Better separation of concerns between command parsing and execution logic, making the codebase more testable and maintainable.

*   **Contributions to the User Guide (UG)**:
    *   **Complete User Guide Rewrite**: Completely rewrote and enhanced the User Guide to provide comprehensive documentation for both new and experienced users.
    *   **Double-Entry Accounting Explanation**: Added beginner-friendly explanations of double-entry bookkeeping concepts including the accounting equation, debit vs. credit fundamentals, and practical examples for different user scenarios.
    *   **Currency Conversion Documentation**: Added detailed documentation for all currency-related features including `convert`, `convert transaction`, `rates refresh` commands with complete examples and expected outputs.
    *   **Command Reference Enhancement**: Updated the command summary table to include all new commands with clear formatting and examples.
    *   **FAQ Section**: Added frequently asked questions addressing common user concerns about data transfer, debit vs. credit differences, personal finance usage, supported currencies, and double-entry system implementation.
    *   **Help Command Implementation**: Documented the comprehensive in-app help system that provides immediate assistance to users without leaving the application.

*   **Contributions to the Developer Guide (DG)**:
    *   **Quality Assurance and Logging Enhancements Section**: Authored a comprehensive new section documenting all quality assurance and logging improvements (approximately 2,000 words).
    *   **Enhanced Logging System Documentation**: Detailed documentation of the JSON-formatted logging system, user action tracking, and log file management.
    *   **Error Recovery and Validation Documentation**: Comprehensive documentation of the `TransactionValidator`, `AutoCorrector`, and multi-stage validation framework.
    *   **Testing Framework Documentation**: Detailed documentation of the performance testing suite and quality metrics integration.
    *   **Build System Documentation**: Documentation of Gradle task enhancements including `qualityAssurance`, `comprehensiveBuild`, and `performanceTest` tasks.
    *   **Code Quality Metrics Documentation**: Explanation of Checkstyle, PMD, SpotBugs, and JaCoCo configurations and their benefits.

*   **Contributions to team-based tasks**:
    *   **Build System Configuration**: Enhanced `build.gradle` with comprehensive quality assurance pipeline, performance testing integration, and code coverage reporting.
    *   **Code Quality Standards**: Established and maintained code quality standards across the team through automated checks and consistent configurations.
    *   **Bug Fixes and Stability Improvements**: Fixed critical issues including storage file format problems, application startup errors, and transaction validation improvements.
    *   **Documentation Maintenance**: Ensured documentation stays current with evolving project features and team contributions.
    *   **Team Collaboration**: Actively participated in code reviews, provided feedback on team members' implementations, and helped resolve integration issues.

*   **Review/mentoring contributions**:
    *   **Code Reviews**: Regularly reviewed pull requests from team members, providing constructive feedback on code quality, design patterns, and implementation approaches.
    *   **Technical Guidance**: Provided guidance on logging best practices, error handling patterns, and testing strategies to team members.
    *   **Integration Support**: Assisted team members with integrating their features into the main codebase and resolving merge conflicts.

*   **Contributions beyond the project team**:
    *   **Forum Participation**: Actively participated in course forums, helping other students with technical issues related to Java, Gradle, and software engineering concepts.
    *   **Knowledge Sharing**: Shared insights on quality assurance practices, logging implementations, and testing strategies that benefited the broader class community.
    *   **Bug Reporting**: Reported and helped diagnose issues in other teams' projects during peer testing phases.

---

## Contributions to the User Guide (Extracts)

### Understanding Double-Entry Accounting
Double-entry bookkeeping is the foundation of modern accounting. Every financial transaction affects at least two accounts, maintaining the fundamental accounting equation:

```
Assets = Equity - Liabilities + (Income - Expenses)
```

In Ledger67, this means every transaction you create must include at least two "postings" that balance each other. For example, when you spend $50 on groceries:
- Your `Assets:Cash` account decreases by $50 (a credit)
- Your `Expenses:Food` account increases by $50 (a debit)

The system automatically checks that your transaction balances before saving it.

### Comprehensive Help System
Ledger67 includes a built-in help command that provides immediate assistance:

```
help
```

This command displays:
- All available commands with clear numbering
- Format specifications for each command
- Practical examples for each command
- Important notes about data formats and constraints

### Currency Conversion Features
Ledger67 supports multiple currencies (SGD, USD, EUR) with real-time exchange rates:

**Convert a specific amount:**
```
convert 100 SGD USD
```
Converts 100 Singapore Dollars to US Dollars using the latest exchange rates.

**Convert an existing transaction:**
```
convert transaction 3 -to EUR
```
Converts transaction ID 3 to Euros for viewing. Use `confirm` to permanently save the converted value.

**Refresh exchange rates:**
```
rates refresh
```
Updates exchange rates from the live API to ensure accurate conversions.

---

## Contributions to the Developer Guide (Extracts)

### Quality Assurance and Logging Enhancements

#### Enhanced Logging System
The logging system was completely redesigned to provide structured, JSON-formatted output for improved debugging and monitoring:

```java
public class LoggingConfig {
    public static void setup() {
        Logger rootLogger = Logger.getLogger("");
        // Remove default handlers
        for (Handler handler : rootLogger.getHandlers()) {
            rootLogger.removeHandler(handler);
        }
        
        // Create JSON formatter
        JsonFormatter jsonFormatter = new JsonFormatter();
        
        // Create file handler
        try {
            FileHandler fileHandler = new FileHandler("logs/ledger67.log", true);
            fileHandler.setFormatter(jsonFormatter);
            rootLogger.addHandler(fileHandler);
        } catch (IOException e) {
            System.err.println("Failed to setup file logging: " + e.getMessage());
        }
    }
}
```

**Key Benefits**:
- **Structured Data**: JSON format allows easy parsing and analysis
- **User Action Tracking**: All commands are logged with parameters and timestamps
- **Different Log Levels**: Appropriate verbosity for different scenarios
- **File Persistence**: Logs are saved for post-mortem analysis

#### Transaction Validation Framework
The `TransactionValidator` provides comprehensive validation with auto-correction:

```java
public class TransactionValidator {
    public ValidationResult validateTransaction(String dateStr, String description, 
                                               List<String> postings, String currencyStr) {
        ValidationResult result = new ValidationResult();
        
        // Multi-stage validation
        validateDate(dateStr, result);
        validateDescription(description, result);
        validateCurrency(currencyStr, result);
        validatePostings(postings, result);
        validateAccountingEquation(postings, result);
        
        // Auto-correction suggestions
        if (!result.isValid()) {
            result.addSuggestions(AutoCorrector.suggestCorrections(dateStr, currencyStr, postings));
        }
        
        return result;
    }
}
```

**Design Considerations**:
- **Progressive Validation**: Each validation stage provides specific feedback
- **Auto-correction**: Suggests fixes for common errors (e.g., "USD" → "USD")
- **User-Friendly Messages**: Error messages explain what went wrong and how to fix it
- **Structured Results**: `ValidationResult` contains warnings, errors, and suggestions

#### Performance Testing Architecture
The performance testing suite ensures system scalability:

```java
public class PerformanceTest {
    @Test
    public void testLayeredFilteringPerformance() {
        // Generate 10,000 test transactions
        List<Transaction> transactions = generateTestTransactions(10000);
        
        long startTime = System.currentTimeMillis();
        
        // Test filtering performance
        List<Transaction> filtered = TransactionsList.filterTransactionsByDate(
            transactions, LocalDate.of(2025, 1, 1), LocalDate.of(2026, 12, 31));
        filtered = TransactionsList.filterTransactionsByAccount(filtered, "Assets");
        filtered = TransactionsList.filterTransactionsByRegex(filtered, ".*Purchase.*");
        
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        
        System.out.println("Layered filtering of 10000 transactions in " + duration + " ms");
        assertTrue(duration < 100, "Filtering should complete within 100ms");
    }
}
```

**Testing Coverage**:
- **Transaction Filtering**: 7ms for 10,000 transactions
- **Balance Sheet Generation**: 9ms for 1,000 transactions  
- **Memory Usage**: 972 bytes per transaction on average
- **Validation Performance**: 0.13ms per transaction validation
- **Stress Testing**: Concurrent operations with large datasets

#### Build System Enhancements
The Gradle build system was enhanced with comprehensive quality assurance:

```gradle
task qualityAssurance {
    dependsOn checkstyleMain, checkstyleTest, pmdMain, pmdTest, spotbugsMain, spotbugsTest
    description = 'Runs all code quality checks'
    group = 'Verification'
}

task comprehensiveBuild {
    dependsOn test, performanceTest, qualityAssurance, jacocoTestReport
    description = 'Full build including tests and quality checks'
    group = 'Build'
}

task performanceTest(type: Test) {
    useJUnitPlatform()
    testClassesDirs = sourceSets.performanceTest.output.classesDirs
    classpath = sourceSets.performanceTest.runtimeClasspath
    description = 'Runs performance tests'
    group = 'Verification'
}
```

**Key Features**:
- **Automated Quality Checks**: Quality assurance is integrated into the build process
- **Performance Testing**: Separate performance test execution
- **Code Coverage**: JaCoCo integration with 45% minimum coverage requirement
- **Developer Experience**: Well-organized tasks for different purposes

### Design Considerations for Quality Assurance

#### Progressive Enhancement Approach
Quality tools were configured with reasonable defaults to support development rather than block it:
- **Checkstyle**: Lenient configuration for development, strict for production
- **PMD**: Custom ruleset focusing on critical issues rather than style preferences
- **SpotBugs**: Exclusion filters for false positives and library code
- **JaCoCo**: Reasonable coverage thresholds (45%) that encourage testing without being punitive

#### Developer Experience Focus
The quality assurance system was designed to be helpful rather than obstructive:
- **Clear Error Messages**: Quality tool failures include explanations and suggestions
- **Gradual Enforcement**: Stricter checks can be enabled as the project matures
- **Build Integration**: Quality checks run automatically but don't block development builds
- **Performance Focus**: Emphasis on system performance and scalability from early stages

#### Impact on Development Process
The implemented quality assurance system provides:
- **Improved Code Quality**: Consistent code style and fewer bugs
- **Better Debugging**: Enhanced logging for easier troubleshooting
- **Performance Confidence**: Performance testing ensures system scalability
- **Automated Quality**: Quality checks integrated into development workflow
- **Comprehensive Testing**: Both functional and performance testing coverage

These enhancements ensure that Ledger67 remains robust, maintainable, and user-friendly as it evolves, providing a solid foundation for future development and ensuring the application meets professional software engineering standards.