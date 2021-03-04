use termion::clear;
use chrono::{Datelike, Local};
use rusqlite::Result;
use super::print_read;
use crate::acc_struct;
use super::multichoice;
use crate::db_api::add_acc;


pub fn new_acc() -> Result<usize> {
    println!("{}", clear::All);
    let b_c = "\u{2592}"; //block character
    println!("\t\t\t{b_c}{b_c}{b_c}{b_c}  ADD RECORD  {b_c}{b_c}{b_c}{b_c}", b_c = b_c);
    let now = Local::now();

    println!(
        "\n\n\nToday's date is {:02}/{:02}/{:04}",
        now.day(),
        now.month(),
        now.year()
    );

    let name = print_read("\nEnter the name:");
    let dob = print_read("\nEnter the date of birth(dd/mm/yyyy):");
    let address = print_read("\nEnter the address:");
    let citizenship = print_read("\nEnter the citizenship:");
    let phone = print_read("\nEnter the phone number:");
    let amt = print_read("\nEnter the amount to deposit: $");
    let accounts = ["Saving", "Current", "Fixed1", "Fixed2", "Fixed3"];
    let message = format!("\n\t1.{}\n\t2.{}\n\t3.{}(for 1 years)\n\t4.{}(for 2 years)\n\t5.{}(for 3 years)\n\n\tEnter your choice:",
                                accounts[0], accounts[1],accounts[2],accounts[3],accounts[4]);
    let acc_num_type = multichoice("\nType of account:",
                                         &message[..],
                                         (1,5))-1;
    let acc_type = accounts[acc_num_type].to_string();

    let acc = acc_struct::Account {
        id: 0,
        name,
        address,
        citizenship,
        acc_type,
        phone,
        amt: amt.parse::<f64>().unwrap_or(0.0),
        dob,
        doc: now.timestamp(),
        withdraw: 0
    };

    add_acc(acc)?;

    return Ok(multichoice("\nAccount created successfully!",
                         "\n\n\n\t\tEnter 1 to go to the main menu and 0 to exit:",
                         (0,1)));
}
