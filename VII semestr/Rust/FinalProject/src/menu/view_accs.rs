use crate::multichoice;
use crate::db_api;
use termion::clear;
use rusqlite::Result;

pub fn view_accs() -> Result<usize> {
    println!("{}", clear::All);
    let accs_res = db_api::get_accs();
    if accs_res.is_err() {
        return Err(accs_res.err().unwrap());
    } 
    let accs = accs_res?;
    if accs.len() == 0 {
        println!("\nNO RECORDS!!");
    } else {
        println!("\nACC. NO.\tNAME\t\t\tADDRESS\t\t\tPHONE");
        for acc in accs {
           println!("{}\t\t{}\t\t\t{}\t\t\t{}", acc.id, acc.name, acc.address, acc.phone);
        }
    }
    let code = multichoice("",
    "\n\n\nEnter 1 to go to the main menu and 0 to exit:",(0,1));
    return Ok(code);
}