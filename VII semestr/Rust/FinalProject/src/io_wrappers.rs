use std::io::stdin;
use std::io::{Write, stdout};
use std::{thread, time};
use termion::clear;

fn read_line_wrap() -> String {
    let mut buffer = String::new();
    stdin().read_line(&mut buffer).unwrap();
    buffer.trim().to_string()
}

pub fn print_wrap(message: &str) {
    print!("{}", message);
    stdout().flush().unwrap();
}

pub fn print_read(message: &str) -> String {
    print_wrap(message);
    read_line_wrap()
}

pub fn multichoice(message: &str, message2: &str, state: (usize, usize)) -> usize{
    println!("{}", message);
    loop {
        let buffer = print_read(message2);
        let num = buffer.parse::<i32>().unwrap_or(-1);
        if num == -1 || num > state.1 as i32 || num < state.0 as i32{
            println!("\nInvalid!");
            thread::sleep(time::Duration::from_millis(400));
            println!("{}", clear::All); 
        } else {
            println!("{}", clear::All);
            return num as usize;
        }
    }
}

pub fn record_not_found() -> usize {
    let message2 = "Enter 2 to return to main menu, 1 to try again and 0 to exit:";
    let code= multichoice("\nRecord not found!!", 
                                        message2,
                                        (0,2));
    return code;
}
