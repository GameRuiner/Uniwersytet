mod new_acc;
mod edit_acc;
mod transact;
mod see_acc;
mod remove_acc;
mod view_accs;

use color::Rgb;
use termion::{clear, color};
use super::io_wrappers::print_read;
use super::multichoice;
use rusqlite::Result;


pub fn menu() {
    let text_color = Rgb(41,225,140);
    let background_color = Rgb(14,46,43);
    let b_c = "\u{2592}"; //block character
    println!("{}{}{}", clear::All, color::Fg(text_color), color::Bg(background_color));
    println!("\n\n\t\t\tCUSTOMER ACCOUNT BANKING MANAGEMENT SYSTEM");
    println!("\n\n\n\t\t\t{b_c}{b_c}{b_c}{b_c}{b_c}{b_c}{b_c} WELCOME TO THE MAIN MENU {b_c}{b_c}{b_c}{b_c}{b_c}{b_c}{b_c}", b_c = b_c);
    loop {    
        let input = print_read("\n\n\t\t1.Create new account
                \n\t\t2.Update information of existing account
                \n\t\t3.For transactions
                \n\t\t4.Check the details of existing account
                \n\t\t5.Removing existing account
                \n\t\t6.View customer's list
                \n\t\t7.Exit
                \n\n\n\n\n\t\t Enter your choice:");
        println!("{}", clear::All);
        let num = input.parse::<i32>().unwrap_or(-1); 

        let res = match num {
            1 => new_acc::new_acc(),
            2 => edit_acc::edit_acc(),
            3 => transact::transact(),
            4 => see_acc::see_acc(),
            5 => remove_acc::remove_acc(),
            6 => view_accs::view_accs(),
            7 => close(),
            _ => Ok(multichoice("\n\nWrong choice!!",
                               "Enter 1 to try again and 0 to exit:", 
                               (0,1)))
        };
        match res {
            Ok(n) => {
                if n == 0 {
                    break;
                }
            },
            Err(e) => println!("{}", e.to_string()),
        }
    }
}

fn close() -> Result<usize> {
    println!("Rust 2020 Final Project");
    Ok(0)
}