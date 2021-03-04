use rusqlite::Result;
use termion::clear;
use crate::db_api;
use crate::io_wrappers::{multichoice, print_read, record_not_found};
use std::{thread, time};

pub fn transact() -> Result<usize> {
    loop {
        println!("{}", clear::All);
        let acc_num = print_read("Enter the account no. of the customer:");
        if db_api::is_exist(&acc_num[..])? == 0 {
            let code = record_not_found();
            if code != 1 {
                return Ok(code);
            }
            continue;
        }
        let acc = db_api::get_acc(&acc_num[..])?;
        let fixed_accs = ["Fixed1", "Fixed2", "Fixed3"];
        if fixed_accs.contains(&&acc.acc_type[..]) {
            println!("\n\nYOU CANNOT DEPOSIT OR WITHDRAW CASH IN FIXED ACCOUNTS!!!!!");
            thread::sleep(time::Duration::from_millis(600));
            return Ok(1);
        }
        let message = "\n1.Deposit\n2.Withdraw\n\nEnter your choice(1 for deposit and 2 for withdraw):";
        let choice = multichoice("\n\nDo you want to", message, (1,2));
        if choice == 1 {
            let deposit = print_read("Enter the amount you want to deposit:$");
            let new_value = deposit.parse::<f64>().unwrap_or_default() + acc.amt;
            db_api::edit_acc("amt", new_value.to_string(), acc_num)?;
        } else {
            let withdraw = print_read("Enter the amount you want to withdraw:$");
            let new_value = acc.amt - withdraw.parse::<f64>().unwrap_or_default();
            db_api::edit_acc("amt", new_value.to_string(), acc_num)?;
        }

        let code = multichoice("Changes saved!",
        "\n\n\nEnter 1 to go to the main menu and 0 to exit:",(0,1));
        return Ok(code);
    }    
    
}
