Feature: Retrieve Employee Names

  Scenario: Verify employee names from the database
    Given I query all employee names from the "employees" table
    Then I should get the following names:
      | John Doe      |
      | Jane Smith    |
      | Alice Johnson |
      | Michael Brown |
      | Emily Davis   |
      | David Wilson  |
      | Sophia Lee    |
      | Chris Evans   |