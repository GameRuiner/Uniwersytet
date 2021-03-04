extern crate rpassword;
extern crate termion;

mod menu;
mod io_wrappers;
mod acc_struct;
mod db_api;

use rpassword::read_password;
use std::{thread, time};
use termion::clear;
use io_wrappers::{print_wrap, multichoice};

fn main() {
    //let passwd = "Rust2020";
    let passwd = "r";

    loop {
        print_wrap("\n\n\t\tEnter the password to login:");

        let password = read_password().unwrap();
        if password == passwd {
            print_wrap("\n\nPassword Match!\nLOADING");
            let millis = time::Duration::from_millis(250);
            for _i in 0..7 {
                thread::sleep(millis);
                print_wrap(".");
            }
            println!("{}", clear::All);
            menu::menu();
            break;
        } else {
            let code = multichoice("\n\nWrong password!!",
                                         "Enter 1 to try again and 0 to exit:", 
                                         (0,1));
            if code == 0 {
                break;
            }
        }
    }
}


