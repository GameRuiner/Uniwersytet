#[derive(Debug)]
#[derive(Default)]
pub struct Account {
    pub id: i32,
    pub name: String,
    pub address: String,
    pub citizenship: String,
    pub acc_type: String,
    pub phone: String,
    pub amt: f64, //amount of deposit
    pub dob: String, //date of birth
    pub doc: i64, //date of create
    pub withdraw: i64, //date of withdraw
}
