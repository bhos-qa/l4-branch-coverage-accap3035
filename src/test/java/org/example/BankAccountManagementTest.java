package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountManagementTest {
    BankAccountManagement mng = new BankAccountManagement();
    @Test
    void createAccount() {
        assertEquals("Account created successfully.",mng.createAccount("rundom"));
    }

    @Test
    void depositFunds() {
        mng.createAccount("rundom");
        assertEquals("Funds deposited successfully.",mng.depositFunds("rundom",12.0));
    }

    @Test
    void withdrawFunds() {
        mng.createAccount("rundom");
        assertEquals("Insufficient funds.",mng.withdrawFunds("rundom",12.0));
        mng.depositFunds("rundom",12.0);
        assertEquals("Funds withdrawn successfully.",mng.withdrawFunds("rundom",4.0));


    }

    @Test
    void checkBalance() {
        mng.createAccount("rundom");
        mng.depositFunds("rundom",12.0);
        assertEquals(12.0,mng.checkBalance("rundom"));
    }

    @Test
    void transferFunds() {
        mng.createAccount("rundom");
        mng.depositFunds("rundom",12.0);
        mng.createAccount("rundom2");
        mng.depositFunds("rundom2",12.0);
        assertEquals("Insufficient funds in the source account.",mng.transferFunds("rundom","rundom2",13.0));
        mng.transferFunds("rundom","rundom2",10.0);
        assertEquals(22.0,mng.checkBalance("rundom2"));
    }
}