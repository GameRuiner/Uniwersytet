use crate::multichoice;
use crate::io_wrappers::record_not_found;
use crate::{db_api, io_wrappers::print_read};
use termion::clear;
use rusqlite::Result;

pub fn remove_acc() -> Result<usize> {
    loop{
        println!("{}", clear::All);
        let acc_num = print_read("Enter the account no. of the customer you want to delete:");
        if db_api::is_exist(&acc_num[..])? == 0 {
            let code = record_not_found();
            if code != 1 {
                return Ok(code);
            }
            continue;
        }
        db_api::delete_acc(&acc_num[..])?;
        println!("\nRecord deleted successfully!\n");
        let code = multichoice("Changes saved!",
        "\n\n\nEnter 1 to go to the main menu and 0 to exit:",(0,1));
        return Ok(code);
    }
}