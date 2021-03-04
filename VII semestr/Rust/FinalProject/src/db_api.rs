use rusqlite::{Connection, NO_PARAMS, Result, named_params, params};

use crate::acc_struct::Account;

pub fn add_acc(acc: Account) -> Result<usize> {
    let conn = Connection::open("records.db")?;
    
    conn.execute(
        "CREATE TABLE IF NOT EXISTS account (
                  id              INTEGER PRIMARY KEY,
                  name            TEXT NOT NULL,
                  address         TEXT,
                  citizenship     TEXT,
                  acc_type        TEXT,
                  phone           TEXT,
                  amt             REAL,
                  dob             TEXT,
                  doc             INTEGER,
                  withdraw        INTEGER
                  )",
        params![],
    )?;

    conn.execute(
        "INSERT INTO account (name, address, citizenship, acc_type, phone, amt, dob, doc) 
             VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)",
        params![acc.name, acc.address, acc.citizenship, acc.acc_type, acc.phone, acc.amt, acc.dob, acc.doc],
    )?;

    Ok(0)
}

pub fn edit_acc(column: &str, value: String, acc_num: String) -> Result<usize> {
    let conn = Connection::open("records.db")?;

    if column == "address" {
        let mut stmt = conn.prepare(
                "UPDATE account 
                    SET address = :new_addr
                    WHERE id == :id")?;
        
        stmt.execute_named(named_params!{
                ":new_addr": value,
                ":id": acc_num
        })?; 
    } else if column == "phone" {
        let mut stmt = conn.prepare(
            "UPDATE account 
                SET phone = :new_phone
                WHERE id == :id")?;
        stmt.execute_named(named_params!{
                ":new_phone": value,
                ":id": acc_num
                })?;
    } else if column == "amt" {
        let mut stmt = conn.prepare(
            "UPDATE account 
                SET amt = :new_amt
                WHERE id == :id")?;
        stmt.execute_named(named_params!{
                ":new_amt": value,
                ":id": acc_num
                })?;
    }
    Ok(0)
}

pub fn is_exist(acc_num: &str) -> Result<usize> {
    let conn = Connection::open("records.db")?;
    let mut stmt = conn.prepare(
        "SELECT * FROM account WHERE id == :id")?;
    let rows = stmt.query_map_named(
        &[(":id",&acc_num)], |row| row.get(0))?;
    let mut entries = Vec::<i32>::new();
    for row in rows {
        entries.push(row?);
    }
    Ok(entries.len())
}

pub fn is_exist_name(acc_name: &str) -> Result<i32> {
    let conn = Connection::open("records.db")?;
    let mut stmt = conn.prepare(
        "SELECT * FROM account WHERE name == :name")?;
    let rows = stmt.query_map_named(
        &[(":name",&acc_name)], |row| row.get(0))?;
    let mut entries = Vec::<i32>::new();
    for row in rows {
        entries.push(row?);
    }
    if entries.len() == 0 {
        Ok(-1)
    } else {
        Ok(entries[0])
    }
}

pub fn get_acc(acc_num: &str) -> Result<Account> {
    let conn = Connection::open("records.db")?;
    let mut stmt = conn.prepare(
        "SELECT * FROM account WHERE id == :id")?;
    let acc = Account::default();

    let res = stmt.query_map_named(&[(":id",&acc_num)], |row| {
        Ok({
            let mut ac = Account::default();
            ac.id = row.get(0)?;
            ac.name = row.get(1)?;
            ac.address = row.get(2)?;
            ac.citizenship = row.get(3)?;
            ac.acc_type = row.get(4)?;
            ac.phone = row.get(5)?;
            ac.amt = row.get(6)?;
            ac.dob = row.get(7)?;
            ac.doc = row.get(8)?;
            ac})})?;
    for ac in res {
        return ac;
    }
    Ok(acc)
}

pub fn get_accs() -> Result<Vec<Account>> {
    let conn = Connection::open("records.db")?;
    let mut stmt = conn.prepare("SELECT * FROM account")?;
    let mut acc_vec = Vec::new();

    let res = stmt.query_map(NO_PARAMS, |row| {
        Ok({
            let mut ac = Account::default();
            ac.id = row.get(0)?;
            ac.name = row.get(1)?;
            ac.address = row.get(2)?;
            ac.phone = row.get(5)?;
            ac})})?;
    for ac in res {
        acc_vec.push(ac?);
    }
    Ok(acc_vec)
}

pub fn delete_acc(acc_num: &str) -> Result<usize> {
    let conn = Connection::open("records.db")?;
    let mut stmt = conn.prepare(
        "DELETE FROM account WHERE id == :id")?;
    stmt.execute_named(&[(":id",&acc_num)])?;   
    Ok(0)
}

#[test]
fn is_exist_test() {
    match is_exist("1") {
        Ok(n) => {
            assert_eq!(n,0);
        },
        Err(e) => println!("{}", e.to_string()),
    }
}

#[test]
fn is_exist_test2() {
    match is_exist("999") {
        Ok(n) => {
            assert_eq!(n,0);
        },
        Err(e) => println!("{}", e.to_string()),
    }
}

#[test]
fn get_acc_test() {
    let acc = get_acc("2").unwrap_or_default();
    assert_eq!(acc.acc_type, "Saving");
    assert_eq!(acc.amt, 100000000.0);
}
