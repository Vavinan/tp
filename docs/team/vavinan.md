# Jeevanandham Vavinan - Project Portfolio Page

### Overview
BudgetBuddy is a desktop financial tracker application that helps users to manage their personal finances. It allows
users to track their income and expenses across multiple accounts and provides insights into their financial activities.
It is optimized for use via a Command Line Interface (CLI) and is written in Java, and has about 3 kLoC.

### Summary of Contributions
##### Code Contributed: [RepoSense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=vavinan&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)
##### Documentation:
   * User Guide:
      * Added documentation for the feature `delete`.
      * Added documentation for the feature `edit`.
   * Developer Guide:
      * Added implementation details for the feature `delete`.
      * Added implementation details for the feature `edit`.
### Feature 1 - Deleting Transaction

1. Created the `removeTransaction` method to handle deletion of a transaction based on its index in the list.
2. Facilitated with the command `delete` followed by the index ID of the transaction to be deleted.
3. Implemented error handling for various edge cases, including empty input, invalid index, and out-of-bound
   index.
4. Removed the transaction from the transaction list and updated the account balance. Then the user will
   get the confirmation message about the process.

**What it does:** Allows users to delete the transaction. <br>
**Justification:** this feature is key to the BudgetBuddy as users need to be able to delete the transactions
which has errors or added by mistake.

### Feature 2 - Editing Transaction

1. Implemented the `processEditTransaction` method to facilitate editing of a transaction based on its index.
2. Facilitated by the command `edit` followed by the index ID of the transaction to be edited.
3. Validated user input for the index and transaction data, handling exceptions such as empty input and
   non-integer index.
4. Prompted the user to provide updated information for the transaction and validated each piece of data,
   and throw appropriate exceptions associated with the input being given.
5. Updated the transaction list with the edited transaction and printed a confirmation message.
   
**What it does:** Allows users to edit the transaction.<br>
**Justification:** this feature is key to the BudgetBuddy as users need to be able to edit the transactions 
which has been added with some mistakes and need to be updated. <br>
**Highlights:** Instead of expecting the user to type long command this feature prompts the user to input 
value for each data

