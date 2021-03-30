Feature: Transfer money from/to account
    Validate the transference of money between two accounts

    Scenario: Transfer money success with balance
        Given account "<from>" with balance <balanceFrom> and account "<to>" with balance <balanceTo>
        When account "<from>" transfer <value> to account "<to>"
        Then the balance of account "<from>" should be <resultFrom> and account "<to>" <resultTo>

    Examples:
        | from   | to     | value   | balanceFrom | balanceTo   | resultFrom | resultTo |
        | 123456 | 654321 | 100.0   | 5000.0      | 5000.0      | 4900.0     | 5100.0   |
        | 987654 | 321654 | 1000.0  | 2000.0      | 100.0       | 1000.0     | 1100.0   |
        | 987654 | 321654 | 2500.0  | 20000.0     | 0.0         | 17500.0    | 2500.0   |