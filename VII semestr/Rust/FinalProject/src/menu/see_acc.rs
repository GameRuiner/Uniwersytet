use rusqlite::Result;
use crate::db_api;
use termion::clear;
use chrono::{self, Local, TimeZone, Datelike};
use crate::io_wrappers::{multichoice, print_read, record_not_found};

pub fn see_acc() -> Result<usize> {
    loop{ 
        println!("{}", clear::All);
        let choice = multichoice("Do you want to check by",
                "\n1.Account no\n2.Name\nEnter your choice:", (1,2));
        if choice == 1 {
            let acc_num = print_read("Enter the account number:");
            if db_api::is_exist(&acc_num[..])? == 0 {
                let code = record_not_found();
                if code != 1 {
                    return Ok(code);
                }
                continue;
            }
            account_detail(&acc_num[..])?;
        } else {
            let acc_name = print_read("Enter the name:");
            let acc_num = db_api::is_exist_name(&acc_name[..])?;
            if acc_num == -1 {
                let code = record_not_found();
                if code != 1 {
                    return Ok(code);
                }
                continue;
            }
            account_detail(&acc_num.to_string()[..])?;
        }

        let code = multichoice("Changes saved!",
        "\n\n\nEnter 1 to go to the main menu and 0 to exit:",(0,1));
        return Ok(code);
    }
}

fn account_detail(acc_num: &str) -> Result<usize>{
    println!("{}", clear::All);
    let acc = db_api::get_acc(acc_num)?;
    let date_time = Local.timestamp(acc.doc, 0);
    let doc = format!("{:02}/{:02}/{:04}", date_time.day(), date_time.month(), date_time.year());
    println!("Account NO.:{}\nName:{}\nDOB:{}\nAddress:{}\nCitizenship:{}\nPhone number:{}\nType Of Account:{}\nAmount deposited:${}\nDate Of Deposit:{}\n\n",
              acc.id,acc.name,acc.dob,
              acc.address,acc.citizenship,acc.phone,
              acc.acc_type,acc.amt,doc);
    if acc.acc_type == "Fixed1" {
        let time = 1;
        let int_date = format!("{:02}/{:02}/{:04}", date_time.day(), date_time.month(), date_time.year()+time);
        let int_amt = interest(time as f64, acc.amt, 9.0);
        println!("\n\nYou will get ${:.2} as interest on {}",
                  int_amt,int_date);
    } else if acc.acc_type == "Fixed2" {
        let time = 2;
        let int_date = format!("{:02}/{:02}/{:04}", date_time.day(), date_time.month(), date_time.year()+time);
        let int_amt = interest(time as f64, acc.amt, 11.0);
        println!("\n\nYou will get ${:.2} as interest on {}",
                  int_amt,int_date);
    } else if acc.acc_type == "Fixed3" {
        let time = 3;
        let int_date = format!("{:02}/{:02}/{:04}", date_time.day(), date_time.month(), date_time.year()+time);
        let int_amt = interest(time as f64, acc.amt, 13.0);
        println!("\n\nYou will get ${:.2} as interest on {}",
                  int_amt,int_date);              
    } else if acc.acc_type == "Saving" {
        let time = 1.0/12.0;
        let int_amt = interest(time as f64, acc.amt, 8.0);
        println!("\n\nYou will get ${:.2} as interest on {} of every month",
                  int_amt,date_time.day());             
    } else if acc.acc_type == "Current" {
        println!("\n\nYou will get no interest");
    }
    Ok(0)
}

fn interest(t: f64, amount:f64, rate: f64) -> f64 {
    (rate*t*amount)/100.0
}