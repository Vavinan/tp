# Arun Gandhi Shyam Krishna - Project Portfolio Page

## Overview

BudgetBuddy is a desktop financial tracker application that helps users to manage their personal
finances. It allows users to track their income and expenses across multiple accounts and provides
insights into their financial activities. It is optimized for use via a Command Line Interface (CLI)
and is written in Java, and has about 3 kLoC.

## Summary of Contributions

### Code Contributed: 
#### [RepoSense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=Shyam&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=ShyamKrishna33&tabRepo=AY2324S2-CS2113-T15-2%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Documentation:

* User Guide:
    * Added documentation of feature `add`.
    * Added documentation of feature `insights`.
* Developer Guide:
    * Added implementation and details of `Category` feature.
    * Added implementation and details of processTransaction method with sequence diagram and class diagram.
* Community:
    * Reported bugs and suggestions for other teams in class and PE-D.
* Others:
    * Added headers for methods in TransactionList, Transaction and DataStorage classes.

### Feature 1 - Adding Transaction

1. Initiated the transaction class with appropriate parameters.
2. Created the `Add` command with the basic arguments to add a transaction to a
   list of available transactions.
3. Added JUnit test cases for all the important methods contributing to this feature.
4. Performed exception handling for many types of edge cases with respect to the input arguments.

**What it does:** Allows users to add a transaction. <br>
**Justification:** This feature is the backbone to the BudgetBuddy as users need to be able to enter a transaction to
use the application.

### Feature 2 - Data Storage

1. Initiated file paths and file format to store the data of the list of transactions.
2. Created `saveTransactions` method to take in an array of transactions, convert the objects into string format
   ensuring no
   information is lost in the process and writing the data in the specified file paths.
3. Enabled reading of saved data back into an array of transaction objects using the method `readTransactionFile`.
4. Performed exception handling to ensure that in all scenarios the data storage works as intended without any runtime
   errors. For example, ensuring that the program does not crash when the files get corrupted due to external
   intervention.

**What it does:** Stores all the transactions entered by the user to the database. <br>
**Justification:** This feature is very important as it allows the user to track all the transactions in previous
sessions as it would be meaningless to not have a data storage.

### Feature 3 - Insights

1. Used XChart library to display pie-charts on the available data.
2. Segregated all the available transactions into income and expense type and calculated total amount for each
   category in each type. Displayed the proportions of transactions in each category for each type.

**What it does:** Displays 2 pie-charts, one for each type of transaction, which shows the proportion
of each category in all the transactions. <br>
**Justification:** This feature is useful since it allows the user to visualize the distribution of
his/her income/expense among the available categories.

### Exception Handling

* Performed overall exception handling for many peculiar cases like user deleting the storage when program
  is running, user entering special characters in command syntax, user force quitting the program etc.
* Also added JUnit test cases for many crucial methods to ensure robustness.

### General Contribution

* Took part regularly in code reviews and team meetings.
* Reviewed around 20 Pull Requests.
