# User Guide

## Introduction

BudgetBuddy is a desktop app for managing personal finances, optimized for use via a Command Line Interface (CLI). It offers the tracking of income and expenses of multiple accounts and even provides insights of your financial activities.

## Quick Start
1. Ensure that you have Java 11 or above installed.
2. Down the latest version of `tp.jar` from [here](https://github.com/AY2324S2-CS2113-T15-2/tp/releases/latest).
3. Copy the file to the folder you want to use as the home folder for your BudgetBuddy app.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar tp.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will prompt the help feature.

## Features

### Viewing help: `help`
Shows the instructions for using BudgetBuddy.
Format: `help`

### Adding a transaction: `add`
Adds a transaction into the transaction list of the specified account.
Parameters: Account Number, Transaction Type, Name, Amount, Date, Category
Format: `add /a/ACCOUNT_NUMBER /t/TRANSACTION_TYPE /n/NAME /$/AMOUNT /d/DATE /c/CATEGORY`

* The `ACCOUNT_NUMBER` can be viewed using the command `list-acc`.
* The `TRANSACTION_TYPE` includes **Expense** or **Income** ONLY.
* The `AMOUNT` is in dollars ($).
* The `DATE` should be in the format **DD-MM-YYYY**.
* The `CATEGORY` is an integer. The categories are mapped to the following integers: 1 (Dining), 2 (Groceries), 3 (Utilities), 4 (Transportation), 5 (Healthcare), 6 (Entertainment), 7 (Rent), 8 (Salary), 9 (Others).

Example of usage:

`add /a/5431 /t/Income /n/March Salary /$/10000 /d/01-03-2024 /c/8`

`add /n/New iPhone /$/2000 /c/9 /t/Expense /a/5431 /d/20-03-2024`

### View transaction history: `list`
List the transactions. List options will include 1. All Transactions, 2. Past Week Transactions, 3. Past Month Transactions, 4. Custom Date Transactions.
Format: `list`

Example of usage:
`list` followed by `1` to view All Transactions.
`list` followed by `2` to view Past Week Transactions.
`list` followed by `3` to view Past Week Transactions.
`list` followed by `4` followed by the start date `01-01-2024` followed by the end date `31-03-2024` to view Custom Date Transactions from 01-01-2024 to 31-03-2024.

### Deleting a transaction: `delete`
Removes a transaction from transaction history.
Parameters: Transaction ID
Format: `delete TRANSACTION_ID`
* The `TRANSACTION_ID` can be viewed using the command `list` followed by `1`.

Example of usage:
`delete 1`

### Edit a transaction: `edit`
Edits the details of an existing transaction.
Parameters: Transaction ID
Format: `edit TRANSACTION_ID`
* The `TRANSACTION_ID` can be viewed using the command `list` followed by `1`.

Example of usage:
`edit 2`

### Add an account: `add-acc`
Adds a new account with a specified initial balance.
Parameters: Account Name, Initial Balance
Format: `add-acc /n/ACCOUNT_NAME /$/INITIAL_BALANCE`
* The `INITIAL_AMOUNT` is in dollars ($).

Example of Usage:
`add-acc /n/DBS Savings /$/10000`

### List all accounts: `list-acc`
List all the existing accounts.
Format: `list-acc`

### Delete an account: `delete-acc`
Removes an account and removes all its transactions.
Parameters: Account Number
Format: `delete ACCOUNT_NUMBER`
* The `ACCOUNT_NUMBER` can be viewed using the command `list-acc`.

Example of usage:
`delete-acc 5431`

### Edit an account: `edit-acc`
Edits the details of an existing account.
Parameters: Account Number
Format: `edit-acc ACCOUNT_NUMBER`

Example of usage:
`edit-acc 5431`

### View transaction insights: `insights`
View transaction insights with the generated piecharts.
Format: `insights`

### Exiting the program: `bye`
Exits BudgetBuddy.
Format: `bye`

### Saving the data
BudgetBuddy data are saved in the hard disk automatically when the user exits the program. There is no need to save the data manually.


## FAQ

**Q**: How do I transfer my data to another Computer?

**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous BudgetBuddy home folder.

## Command Summary

{Give a 'cheat sheet' of commands here}

* View help `help`
* Add transaction `add /a/ACCOUNT_NUMBER /t/TRANSACTION_TYPE /n/NAME /$/AMOUNT /d/DATE /c/CATEGORY`
* List transactions `list`
* Delete transaction `delete TRANSACTION_ID`
* Edit transaction `edit TRANSACTION_ID`
* Add account `add-acc /n/ACCOUNT_NAME /$/INITIAL_BALANCE`
* List accounts `list-acc`
* Delete account `delete-acc ACCOUNT_NUMBER`
* Edit account `edit-acc ACCOUNT_NUMBER`
* View insights `insights`
* Exit program `bye`
