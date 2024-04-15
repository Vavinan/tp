# Vaibhav Dileep Pillai's Project Portfolio Page

## Overview

BudgetBuddy is a desktop financial tracker application that helps users to manage their personal finances. It allows
users to track their income and expenses across multiple accounts and provides insights into their financial activities.
It is optimized for use via a Command Line Interface (CLI) and is written in Java, and has more than 4 kLoC.

## Summary of Contributions

Given below is a summary of my contributions to the project.

### Code Contributed:

#### [RepoSense Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=vibes-863&breakdown=true)

### Features implemented:

#### 1. Feature: Account Addition

- **What it does:** Enables users to create new financial accounts within the application.
- **Justification:** Fundamental for financial tracking, this feature allows users to start managing their finances by
  setting up accounts with a name and initial balance.
- **Highlights:** The implementation ensures that each account has a unique account number and integrates seamlessly
  with the rest of the system to reflect updates across transactions and balances.

#### 2. Feature: Account Deletion

- **What it does:** Provides the functionality to remove existing accounts from the application.
- **Justification:** Essential for maintaining current and relevant financial data, this feature allows users to delete
  accounts that are obsolete or were created in error.
- **Highlights:** Includes checks to prevent deletion of the last remaining account, thereby ensuring there is always at
  least one account active for transaction logging.

#### 3. Feature: Account Listing

- **What it does:** Displays a comprehensive list of all user accounts with details such as account number, name, and
  current balance.
- **Justification:** Users need a quick overview of all their accounts to make informed financial decisions. This
  feature supports financial awareness and planning.
- **Highlights:** The list is dynamically updated, reflecting real-time changes in account details and balances.

#### 4. Feature: Account Editing

- **What it does:** Allows users to modify the details of existing accounts, such as changing the account name.
- **Justification:** Flexibility in updating account information is crucial as it evolves with the user's financial
  landscape.
- **Highlights:** This feature ensures data integrity while allowing modifications, enhancing user experience by
  allowing personalization of account information.

#### 5. Feature: Account Data Storage

- **What it does:**  Ensures that account data is saved to a file system and can be reloaded each time the application
  starts, preserving user data between sessions.
- **Justification:** Vital for long-term financial tracking, users can shut down and restart the application without
  losing their data, enabling continuous financial management.
- **Highlights:** Implements robust error handling to manage file integrity issues and provides a seamless user
  experience by automatically handling data storage and retrieval.

### Contributions to User Guide:

- Updated documentation for the features `delete-acc`, `list-acc`, and `edit-acc`.
- Formatted the user guide to improve readability and consistency.

### Contributions to Developer Guide:

- Added implementation details for the features `Add Account` and `Remove Account`.
- Formatted the developer guide to improve readability and consistency.

### General Contributions:

- Participated in team meetings and discussions to plan and implement features.
- Managed `v1.0` release on GitHub.
- Reviewed around 20 plus PRs and provided feedback to teammates.
- 

