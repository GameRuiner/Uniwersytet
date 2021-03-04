use termion::clear;
use rusqlite::Result;
use crate::io_wrappers::{multichoice, print_read, record_not_found};
use crate::db_api;

pub fn edit_acc() -> Result<usize> {
    loop {
        println!("{}", clear::All);
        let message = "\nEnter the account no. of the customer whose info you want to change:";
        let acc_num = print_read(message);
        if db_api::is_exist(&acc_num[..])? == 0 {
            let code = record_not_found();
            if code != 1 {
                return Ok(code);
            }
            continue;
        }
        
        let choice = &print_read("\nWhich information do you want to change?
                                              \n1.Address
                                              \n2.Phone
                                              \n\nEnter your choice (1 for address and 2 for phone):")[..];
        let mut changed = true;
        match choice {
                "1" => {
                        let new_addr = print_read("Enter the new address:");
                        db_api::edit_acc("address", new_addr, acc_num)?;
                    },
                "2" => {
                        let new_phone = print_read("Enter the new phone number:");
                        db_api::edit_acc("phone", new_phone, acc_num)?;
                    },
                    _ => {
                        let code = multichoice("\n\nWrong choice!!",
                              "Enter 1 to try again and 0 to exit:",
                              (0,1));
                        if code != 1 {
                            return Ok(code);
                        }
                        changed = false;
                    }
               }
               if changed {
                    return Ok(multichoice("Changes saved!", 
                            "\n\n\nEnter 1 to go to the main menu and 0 to exit:",
                            (0,1)));
                }
    }
}

