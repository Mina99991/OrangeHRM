Feature: Subscription Packages


  @Test
  Scenario Outline: Validate the Home Screen and Admin Functions
    Given the user navigates to orange hrm website
    When the user logs in with valid credentials "<username>" and "<password>"
    Then the dashboard should be displayed successfully
    And the user navigates to the admin page
    When the user creates a record with valid data and existing employee Name "<employeeName>"
    Then the record should be created successfully
    When the user attempts to delete the record
    Then the record should be deleted successfully

    Examples:
      | username | password | employeeName       |
      | Admin    | admin123 | Mina Maher Boutros |