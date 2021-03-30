Feature: Transfer money from/to account
    Validate the transference of money between two accounts

    Scenario: Transfer money success with balance
        Given account "123456" with balance 5000.0 and account "654321" with balance 5000.0
        When account "123456" transfer 100.0 to account "654321"
        Then the balance of account "123456" should be 4900.0 and account "654321" 5100.0